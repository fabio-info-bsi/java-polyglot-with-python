package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.net.URL;
import java.nio.file.Paths;

public class HelloWorld {

    public static void main(String[] args) throws Exception{
        try (Context ctx = Context
                .newBuilder()
                .allowAllAccess(true)
                .build()) {
            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());
            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            Value method = ctx.getBindings("python").getMember("methodHello");
            method.execute();
        }
    }
}
