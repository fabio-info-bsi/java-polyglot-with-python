package br.com.fabex;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.io.IOAccess;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class CodeCachingByEngineInContexts {

    /* Até a data do teste (10/09/2023) há um limitação com relação a muti-contextos quando usando com extenções C
     * ou código nativo. [Se utilizar scritp: script-sort-array-with-numpy.py chamando metodo: methodSortArray
     * terá erro: Exception in thread "main" SystemError: cannot execute multi-context code with native extensions] */
    public static void main(String[] args) throws Exception {

        try (Engine engine = Engine.newBuilder("python")
                .allowExperimentalOptions(true)
                .build()) {
            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-math.py").toString());
            Source source = Source.newBuilder("python", urlScriptPython)
                    .cached(true) /* default is true */
                    .build();
            System.out.println("Caching Engine");
            IntStream.rangeClosed(1, 1_000).forEach(i -> runningContextByEngine(engine, source));
            System.out.println("Renew Engine");
            Source sourceNoCached = Source.newBuilder("python", urlScriptPython)
                    .cached(false)
                    .build();
            IntStream.rangeClosed(1, 1_000).forEach(i -> runningContextByEngine(Engine.create(), sourceNoCached));

        }
    }

    private static void runningContextByEngine(Engine engine, Source source) {
        Instant start = Instant.now();
        try (Context context = Context.newBuilder()
                .engine(engine)
                .allowNativeAccess(true)
                .allowHostAccess(HostAccess.ALL)
                .allowIO(IOAccess.ALL)
                .allowPolyglotAccess(PolyglotAccess.newBuilder()
                        .allowBindingsAccess("python")
                        .build())
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .build()) {
            context.eval(source);

            int anInt = context.getBindings("python").getMember("add")
                    .execute(1, 2).asInt();
            System.out.println("Soma(1+2): " + anInt);
        }
        Instant finish = Instant.now();
        System.out.printf("Execution | Time(sec, milli, nano): %d | %d | %d%n",
                Duration.between(start, finish).toSeconds(),
                Duration.between(start, finish).toMillis(),
                Duration.between(start, finish).toNanos());
    }
}
