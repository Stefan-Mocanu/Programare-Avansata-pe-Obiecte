package org1.Containers;

import java.util.Objects;

/**
 * Clasa care retine obiecte sub forma elementelor din tabelul Audit
 */
public class AuditCont {
    int id;
    String command, succes;
    public AuditCont(String command, String succes, String id) {
        this.command = command;
        this.succes = succes;
        this.id = Integer.parseInt(id);
    }

    @Override
    public String toString() {
        if(Objects.equals(succes, "1")){
            return "[ID: "+id+"] "+"Reusita " + command;
        }else{
            return "[ID: "+id+"] "+"Esuata " + command;
        }
    }
}
