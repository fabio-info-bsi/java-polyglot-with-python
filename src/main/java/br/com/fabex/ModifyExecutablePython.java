package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class ModifyExecutablePython {

    public static void main(String[] args) throws Exception {
        String venvExePathGraalVm = Paths.get("/Users/fabio.henrique/Workspace-fabex/java-polyglot-with-python/src/main/resources/polyglot/python/.env/bin/graalpy")
                                .toString();

        /*String venvExePathonV3dot8 = Objects.requireNonNull(TaskAnalyzerUserRollout.class
                .getClassLoader()
                .getResource(Paths.get("polyglot/python/", ".env-3.8.5", "bin", "python")
                        .toString()))
                .getPath();*/
        //System.out.println(venvExePathonV3dot8);
        String venvExePath = venvExePathGraalVm;//venvExePathGraalVm
        try (Context ctx = Context
                .newBuilder("python")
                //.option("llvm.managed", "true")
                .allowAllAccess(true)
                //.allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true") /* 'import site' - dependencies in site-packages */
                .option("python.Executable", venvExePath) /* Different executable Python */
                //.option("python.Executable", "python/.env-3.8.5/bin/python")
                .build()) {
            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());
            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            Value method = ctx.getBindings("python").getMember("methodVersionPython");
            method.execute();
        }
    }
}
