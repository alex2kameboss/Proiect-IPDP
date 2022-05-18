package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.*;

public class AddAttributeAction implements IAction {
    protected String parentName;
    protected Attribute newAttribute;

    public AddAttributeAction(String parentName, Attribute newAttribute) {
        this.parentName = parentName;
        this.newAttribute = newAttribute;
    }

    @Override
    public void applyDataModel(DataModel data) {
        Entity parent = data.getEntity(parentName);
        parent.addAttribute(newAttribute);
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.addAttribute(parentName, newAttribute);
    }
}
