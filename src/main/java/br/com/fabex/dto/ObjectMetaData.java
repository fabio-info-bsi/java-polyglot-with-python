package br.com.fabex.dto;

import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

public class ObjectMetaData {
    private int id;
    private String module;
//    @HostAccess.Export
    private Value value;

    public ObjectMetaData(int id, String module) {
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

    public int getId() {
        return id;
    }

    @HostAccess.Export
    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    @HostAccess.Export
    public void setModule(String module) {
        this.module = module;
    }

    @HostAccess.Export
    public String getDetail() {
        return """
                {
                    "id": %s,
                    "module": %s
                    "value": %s
                }""".formatted(this.id, this.module, this.value);
    }
}
