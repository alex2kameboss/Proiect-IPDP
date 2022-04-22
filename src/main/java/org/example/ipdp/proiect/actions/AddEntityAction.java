package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.Entity;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;

public class AddEntityAction implements IAction {
    protected Entity newEntity;

    public AddEntityAction(Entity newEntity) {
        this.newEntity = newEntity;
    }

    @Override
    public void applyDataModel(DataModel data) {
        data.addEntity(newEntity);
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.addEntity(newEntity);
    }
}
