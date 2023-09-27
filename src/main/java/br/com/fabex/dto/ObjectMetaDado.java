package br.com.fabex.dto;

import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

public class ObjectMetaDado {
    private String id, module;
    @HostAccess.Export
    private Value value;

    public ObjectMetaDado(String id, String module) {
        this.id = id;
        this.module = module;
        this.value = Value.asValue("Init");
    }

    @HostAccess.Export
    @HostAccess.DisableMethodScoping /* Em HostAccess.SCOPED, essa antocacao permite que o metodo possa compartilhar entrada de dados (parametro do metodo) */
    public void setValue(Value value) {
        this.value = value;
    }

    @HostAccess.Export
    public Value getValue() {
        return value;
    }

    @HostAccess.Export
    public String getDetail() {
        return """
                {
                    "id": %s,
                    "module": %s
                }""".formatted(this.id, this.module);
    }
}
