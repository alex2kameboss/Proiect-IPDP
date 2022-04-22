package org.example.ipdp.proiect.backend;

import org.apache.cayenne.query.ObjectSelect;
import org.example.ipdp.proiect.backend.orm.Attribute;
import org.example.ipdp.proiect.backend.orm.Entity;

import java.util.List;

public class BackendHelper {
    protected Backend backend;

    public BackendHelper(Backend backend){
        this.backend = backend;
    }

    public Entity getEntityByName(String entityName) {
        return ObjectSelect.query(Entity.class)
                .where(Entity.NAME.eq(entityName))
                .selectFirst(backend.context);
    }

    public List<Attribute> getEntityAttributes(String entityName) {
        return this.getEntityByName(entityName).getAttributes();
    }
}
