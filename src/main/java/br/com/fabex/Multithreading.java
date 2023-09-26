package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotAccess;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.io.IOAccess;

import java.net.URL;
import java.nio.file.Paths;

public class Multithreading {

    public static void main(String[] args) throws Exception {
        try (Context ctx = Context.newBuilder()
                .allowPolyglotAccess(PolyglotAccess.newBuilder().
                        allowBindingsAccess("python")
                        .build())
                .allowIO(IOAccess.ALL)
                .allowCreateThread(true)
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .build()) {

            URL urlScriptPython = HelloWorld.class.getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-multithreading.py").toString());

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());

            Value methodRun = ctx.getBindings("python")
                    .getMember("run");
            methodRun.execute();
        } catch (Exception e) {
            System.out.printf("Class: " + e.getClass());
            System.out.println(e.getMessage());
        }
    }
}
