package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.Entity;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;

public class UpdateEntityNameAction implements IAction {
    protected String oldName;
    protected String newName;

    public UpdateEntityNameAction(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    @Override
    public void applyDataModel(DataModel data) {
        Entity old = data.removeEntity(oldName);
        if (old != null) {
            old.setEntityName(newName);
            data.addEntity(old);
        }
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.updateEntity(new Entity(oldName), new Entity(newName));
    }
}
