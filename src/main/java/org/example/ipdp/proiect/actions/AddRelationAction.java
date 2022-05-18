package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;
import org.example.ipdp.proiect.misc.Relation;

public class AddRelationAction implements IAction {
    Relation relation;

    public AddRelationAction(Relation relation) {
        this.relation = relation;
    }

    @Override
    public void applyDataModel(DataModel data) {
        data.addRelation(relation);
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.addRelation(relation);
    }
}
