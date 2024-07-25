package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.net.URL;
import java.nio.file.Paths;

public class HelloWorld {

    public static void main(String[] args) throws Exception {
        try (Context ctx = Context
                .newBuilder()
                .allowAllAccess(true) /* Take Care! */
                .build()) {

            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            ctx.eval("python", /* language=python */ "print('#Python.print Hello world')");
            ctx.eval("python", /* language=python */ "name, nickName = 'Fabio', 'Fabex'");

            //language=python
            final String PYTHON_SNIPPET_METHOD = """
                    def method_hello_snippet():
                        print('#Python.print#method_hello_snippet Hello world')
                    """;
            ctx.eval("python", PYTHON_SNIPPET_METHOD);
            ctx.getBindings("python").getMember("method_hello_snippet").execute();

            // call method file: polyglot/python/src/script-utils.py
            Value method = ctx.getBindings("python").getMember("method_hello");
            method.execute();

            Value name = ctx.getBindings("python").getMember("nickName");
            Value nickname = ctx.getBindings("python").getMember("nickName");
            System.out.println("#System.out.println " + name.asString() + " " + nickname.asString());
        }
    }
}
