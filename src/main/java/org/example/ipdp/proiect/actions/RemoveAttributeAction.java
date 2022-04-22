package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.*;

public class RemoveAttributeAction implements IAction {
    String parentName;
    Attribute attribute;

    public RemoveAttributeAction(String parentName, Attribute attribute) {
        this.parentName = parentName;
        this.attribute = attribute;
    }

    @Override
    public void applyDataModel(DataModel data) {
        Entity parent = data.getEntity(parentName);
        parent.removeAttribute(attribute.getAttributeName());
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.removeAttribute(parentName, attribute);
    }
}
