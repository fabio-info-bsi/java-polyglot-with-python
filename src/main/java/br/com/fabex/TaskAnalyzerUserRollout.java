package br.com.fabex;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.io.IOAccess;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.logging.*;
import java.util.stream.IntStream;

public class TaskAnalyzerUserRollout {
    private final static java.util.logging.Logger LOGGER;

    static {
        try {
            LogManager.getLogManager()
                    .readConfiguration(TaskAnalyzerUserRollout.class.getResourceAsStream("/logging.properties"));
        } catch (IOException | SecurityException | ExceptionInInitializerError ex) {
            java.util.logging.Logger
                    .getLogger(TaskAnalyzerUserRollout.class.getName())
                    .log(Level.SEVERE, "Failed to read logging.properties file", ex);
        }
        LOGGER = java.util.logging.Logger.getLogger(TaskAnalyzerUserRollout.class.getName());
    }

    public static void main(String[] args) throws Exception {
        String venvExePath = Objects.requireNonNull(TaskAnalyzerUserRollout.class
                        .getClassLoader()
                        .getResource(Paths.get("polyglot", "python", ".env", "bin", "graalpy").toString()))
                .getPath();
        try (Context ctx = Context.newBuilder()
                .allowPolyglotAccess(PolyglotAccess.newBuilder()
                        .allowBindingsAccess("python")
                        .build())
                .allowHostAccess(HostAccess.ALL)
                .allowIO(IOAccess.ALL)
                .allowNativeAccess(true)  /* Native C extensions Numpy */
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .option("python.Executable", venvExePath)
                .option("log.level", "INFO")
                .build()) {

            URL urlScriptPython = HelloWorld.class.getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-analyzer-user-rollout.py").toString());
            URL pathDataSource = HelloWorld.class.getClassLoader()
                    .getResource(Paths.get("dataset/user-rollout.csv").toString());

            if (args.length > 0) {
                LOGGER.info("Args: " + args[0]);
                pathDataSource = Paths.get(args[0]).toUri().toURL();
                LOGGER.info("Overload Path(datasource): " + pathDataSource.getPath());
            }
            String path = pathDataSource.getPath();

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());

            //Value globalDataRollout = ctx.getBindings("python").getMember("globalDataRollout");
            //LOGGER.info("Global Datasource: " + globalDataRollout.toString());

            Value methodUserRollOutAnalysis = ctx.getBindings("python")
                    .getMember("methodUserRollOutAnalysis");
            Instant start = Instant.now();
            Value resultAnalyze = methodUserRollOutAnalysis.execute(path, "SEED");
            Instant finish = Instant.now();
            LOGGER.info("%sx Execution(methodUserRollOutAnalysis) | Time(milli, nano):  %d | %d".formatted(1,
                    Duration.between(start, finish).toMillis(),
                    Duration.between(start, finish).toNanos()));
            LOGGER.info("Result: %d".formatted(resultAnalyze.getHashValue("total").asInt()));
            LOGGER.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

            Value methodUserRollOutAnalysisOptimizedLogging = ctx.getBindings("python")
                    .getMember("methodUserRollOutAnalysisOptimizedLogging");
            start = Instant.now();
            methodUserRollOutAnalysisOptimizedLogging.execute(path, "SMALL");
            finish = Instant.now();
            LOGGER.info("%sx Execution(methodUserRollOutAnalysisOptimizedLogging) | Time(milli, nano):  %d | %d".formatted(1,
                    Duration.between(start, finish).toMillis(),
                    Duration.between(start, finish).toNanos()));
            LOGGER.info("Result: %d".formatted(resultAnalyze.getHashValue("total").asInt()));
            LOGGER.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

            Value methodUserRollOutOptimizeAnalysis = ctx.getBindings("python")
                    .getMember("methodUserRollOutAnalysisOptimized");
            start = Instant.now();
            resultAnalyze = methodUserRollOutOptimizeAnalysis.execute(path, "MEDIUM");
            finish = Instant.now();
            LOGGER.info("%sx Execution(methodUserRollOutOptimizeAnalysis) | Time(milli, nano):  %d | %d".formatted(1,
                    Duration.between(start, finish).toMillis(),
                    Duration.between(start, finish).toNanos()));
            LOGGER.info("Result: %d".formatted(resultAnalyze.getHashValue("total").asInt()));
            LOGGER.info("= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =");

            //globalDataRollout = ctx.getBindings("python").getMember("globalDataRollout");
            //LOGGER.info("Global Datasource: %s".formatted(globalDataRollout.toString()));

            if (null != System.getProperty("loopPerformance")) {
                int loopPerformance = Integer.parseInt(System.getProperty("loopPerformance"));
                LOGGER.info("= = = = = = = = = = Run test [loop %d] = = = = = = = = = = ".formatted(
                        loopPerformance));
                Instant startLoop = Instant.now();
                IntStream.range(0, loopPerformance)
                        .forEach(i -> {
                            Instant startInteraction = Instant.now();
                            methodUserRollOutOptimizeAnalysis.execute(path, "MEDIUM");
                            Instant finishInteraction = Instant.now();
                            LOGGER.info("%sx Execution | Time(sec, milli, nano):  %d | %d | %d".formatted(i,
                                    Duration.between(startInteraction, finishInteraction).toSeconds(),
                                    Duration.between(startInteraction, finishInteraction).toMillis(),
                                    Duration.between(startInteraction, finishInteraction).toNanos()));
                        });
                Instant finishLoop = Instant.now();
                LOGGER.info("Total | Time(milli): %d".formatted(
                        Duration.between(startLoop, finishLoop).toMillis()));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
