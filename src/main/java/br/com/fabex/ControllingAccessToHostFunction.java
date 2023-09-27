package br.com.fabex;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.io.IOAccess;

import java.net.URL;
import java.nio.file.Paths;

import br.com.fabex.dto.ObjectMetaDado;

public class ControllingAccessToHostFunction {

    public static void main(String[] args) {
        try (Context ctx = Context.newBuilder()
                .allowPolyglotAccess(PolyglotAccess.newBuilder()
                        .allowBindingsAccess("python")
                        .build())
                //.allowHostAccess(HostAccess.ALL) /* Permite acesso a todos os membros 'public' */
                //.allowHostAccess(HostAccess.EXPLICIT) /* Todos que usam @HostAccess.Export */
                /* Sem interacao de entrada da linguagem conidada (dados atraves de parametro de metodo) salve excecao dos metodos com @HostAccess.DisableMethodScoping */
                .allowHostAccess(HostAccess.SCOPED)
                .allowValueSharing(false)
                .allowIO(IOAccess.ALL)
                .allowExperimentalOptions(true)
                .option("python.ForceImportSite", "true")
                .build()) {
            ObjectMetaDado metaDado = new ObjectMetaDado("1", "Root");

            URL urlScriptPython = ControllingAccessToHostFunction.class
                    .getClassLoader()
                    .getResource(Paths.get("polyglot/python/src/script-utils.py").toString());

            System.out.println("Before: " + metaDado.getValue().toString());

            /* getBindings - não precisa usar poly.import_value('metaDado'), implicitamente é incorporada no código python. */
            ctx.getBindings("python").putMember("metaDado", metaDado);

            ctx.eval(Source.newBuilder("python", urlScriptPython).build());
            Value methodRun = ctx.getBindings("python")
                    .getMember("methodControllingValueToHostAndPython");
            methodRun.execute();
            System.out.println("After: " + metaDado.getValue().asString());

        } catch (Exception e) {
            System.out.printf("Class: " + e.getClass());
            e.printStackTrace();
        }
    }
}
