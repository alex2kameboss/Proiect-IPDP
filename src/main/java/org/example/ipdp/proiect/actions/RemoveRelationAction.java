package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;
import org.example.ipdp.proiect.misc.Relation;

public class RemoveRelationAction implements IAction {
    Relation relation;

    public RemoveRelationAction(Relation relation) {
        this.relation = relation;
    }

    @Override
    public void applyDataModel(DataModel data) {
        data.removeRelation(relation.getFirstEntity(),
                relation.getSecondEntity());
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.removeRelation(relation);
    }
}
