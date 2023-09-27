package br.com.fabex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class MathOperators {

    public static void main(String[] args) throws Exception {
        try (Context ctx = Context
                .newBuilder()
                .allowAllAccess(true)
                .build()) {
            URL urlScriptPython = HelloWorld.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-math.py").toString());
            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            Value result;

            Value methodAdd = ctx.getBindings("python").getMember("add");
            result = methodAdd.execute(1, 10);
            int anInt = result.asInt();
            System.out.println(anInt);

            Value methodSubtract = ctx.getBindings("python").getMember("subtract");
            result = methodSubtract.execute(5, 10);
            System.out.println(result.asDouble());

            Value methodMultiply = ctx.getBindings("python").getMember("multiply");
            System.out.println(methodMultiply.execute(1, 7).asLong());

            try {
                Value methodDivide = ctx.getBindings("python").getMember("divide");
                System.out.println(methodDivide.execute(1, 7).asDouble());
                /* Force Exception */
                System.out.println(methodDivide.execute(1, 0).asDouble());
            } catch (Exception e) {
                System.out.println(e.getClass());
                System.out.println(e.getMessage());
            }
        }
    }

}
