package br.com.fabex;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.io.IOAccess;

import java.net.URL;
import java.nio.file.Paths;

public class SharedValueInContext {

    public static void main(String[] args) {
        try (Context ctx = Context.newBuilder()
                .allowPolyglotAccess(PolyglotAccess.newBuilder()
                        .allowBindingsAccess("python")
                        .allowBindingsAccess("js")
                        .build())
                /* Se false, objetos não podem compartilhar valores entre [Java <-> Guest language] */
                .allowValueSharing(true)
                .allowIO(IOAccess.ALL)
                .allowCreateThread(true)
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .build()) {
            /* Primitivo(da linguagem Java - Wrappers, String, primitvos: boolean, int, long ... - não compartilha valores entre os contextos [Java <-> Pythton] */
            Boolean sharedBooleanObject = false;
            /* Value - compartilha valores entre os contextos [Java <-> Pythton] */
            Value sharedValueObject = Value.asValue(Boolean.FALSE);
            /* ObjectValueIncorporated - Classe com metodos que mpermitem interoperabilidade entre linguages convidadas. Vale destacar a anotacao: @HostAccess.Export */

            ObjectValueIncorporated valueIncorporated = new ObjectValueIncorporated();

            URL urlScriptPython = SharedValueInContext.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());
            ctx.eval(Source.newBuilder("python", urlScriptPython).build());

            System.out.println("= = = = = = = = = = Shared Object = = = = = = = = = = ");
            System.out.println("Before value sharedBooleanObject: " + sharedBooleanObject);
            /* getPolyglotBindings - Precisa usar poly.import_value('method_shared_object'), implicitamente é incorporada no código python */
            ctx.getPolyglotBindings().putMember("sharedBooleanObject", sharedBooleanObject);
            Value methodRun = ctx.getBindings("python").getMember("method_shared_object");
            methodRun.execute();
            System.out.println("After value sharedBooleanObject: " + sharedBooleanObject);

            System.out.println("= = = = = = = = = = Shared Value Object = = = = = = = = = = ");
            System.out.println("Before value sharedValueObject: " + valueIncorporated.getValue().toString());
            /* getPolyglotBindings - Precisa usar poly.import_value('sharedBooleanObject'), implicitamente é incorporada no código python. Perceba que é possível compartilhar com TODAS linguages do Context (Python & Js) */
            ctx.getPolyglotBindings().putMember("valueIncorporated", valueIncorporated);
            methodRun = ctx.getBindings("python").getMember("method_shared_value_object");
            methodRun.execute();
            System.out.println("After value sharedValueObject: " + valueIncorporated.getValue().asString());

            System.out.println("= = = = = = = = = = Shared Value Object JS = = = = = = = = = = ");
            System.out.println("Before value sharedValueObject: " + valueIncorporated.getValue().toString());
            URL urlScriptJs = SharedValueInContext.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/js/script-shared-value.js").toString());
            ctx.eval(Source.newBuilder("js", urlScriptJs).build());
            methodRun = ctx.getBindings("js").getMember("methodSharedValueObject");
            methodRun.execute();
            System.out.println("After value sharedValueObject: " + valueIncorporated.getValue().asString());

        } catch (Exception e) {
            System.out.printf("Class: " + e.getClass());
            e.printStackTrace();
        }
    }

    /* PutaQueParoquia !!! Se classe não for "public", método não será reconhecido. */
    public static class ObjectValueIncorporated {
        private Value value;

        public ObjectValueIncorporated() {
            this.value = Value.asValue("Init");
        }

        @HostAccess.Export
        public void setValue(Value value) {
            this.value = value;
        }

        @HostAccess.Export
        public Value getValue() {
            return value;
        }
    }
}
