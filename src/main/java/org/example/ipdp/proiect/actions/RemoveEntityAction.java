package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.Entity;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;

public class RemoveEntityAction implements IAction {
    Entity entity;

    public RemoveEntityAction(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void applyDataModel(DataModel data) {
        data.removeEntity(entity.getEntityName());
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.removeEntity(entity);
    }
}
