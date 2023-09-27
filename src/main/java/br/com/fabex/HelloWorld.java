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
            ctx.eval("python", "print('Hello world')");
            ctx.eval("python", "name, nickName = 'Fabio', 'Fabex'");

            final String PYTHON_SNIPPET_METHOD = """
                    def methodHelloSnippet():
                        print('Hello world')
                    """;
            ctx.eval("python", PYTHON_SNIPPET_METHOD);
            ctx.getBindings("python").getMember("methodHelloSnippet").execute();

            Value method = ctx.getBindings("python").getMember("methodHello");
            method.execute();

            Value member = ctx.getBindings("python").getMember("nickName");
            System.out.println(member.asString());
        }
    }
}
