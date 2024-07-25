package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class ModifyExecutablePython {

    public static void main(String[] args) throws Exception {
        String venvExePath = Objects.requireNonNull(ModifyExecutablePython.class
                        .getClassLoader()
                        .getResource(Paths.get("polyglot/python/", ".env-3.10.8", "bin", "graalpy")
                                .toString()))
                .getPath();
        try (Context ctx = Context
                .newBuilder("python")
                .allowAllAccess(true)
                .option("python.ForceImportSite", "true") /* 'import site' - dependencies in site-packages */
                .option("python.Executable", venvExePath) /* Different executable Python */
                .build()) {
            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            Value method = ctx.getBindings("python").getMember("method_version_python");
            method.execute();
        }
    }
}
