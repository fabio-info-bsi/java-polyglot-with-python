package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SortArrayWithNumpy {

    public static void main(String[] args) throws Exception {
        String venvExePath = Objects.requireNonNull(TaskAnalyzerUserRollout.class
                        .getClassLoader()
                        .getResource(Paths.get("polyglot", "python", ".env-3.10.8", "bin", "python").toString()))
                .getPath();
        try (Context ctx = Context.newBuilder()
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .option("python.Executable", venvExePath)
                .allowAllAccess(true)
                .build()) {

            URL urlScriptPython = HelloWorld.class.getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-sort-array-with-numpy.py").toString());

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());

            Value method = ctx.getBindings("python").getMember("method_sort_array");

            final int[] unsorted =
                    {499, 200, 549, 2, 3, 567, 44, 8, 222, 9, 5, 44, 7, 78, 22, 45, 2212, 567, 34, 4556, 87, 44};

            Value result = method.execute(unsorted);
            List<Long> list = LongStream.range(0, result.getArraySize())
                    .mapToObj(index -> result.getArrayElement(index).asLong())
                    .toList();
            System.out.println("Unsorted    => " + IntStream.of(unsorted).boxed().toList());
            System.out.println("Sorted      => " + list);

            System.out.println("Run [loop x10.000]");
            IntStream.range(0, 10_000).forEach(i -> {
                unsorted[0] = i;
                Instant start = Instant.now();
                method.execute(unsorted);
                Instant finish = Instant.now();
                System.out.printf("%d x Execution | Time(sec, milli, nano): %d | %d | %d%n", i,
                        Duration.between(start, finish).toSeconds(),
                        Duration.between(start, finish).toMillis(),
                        Duration.between(start, finish).toNanos());
            });
        }
    }
}
