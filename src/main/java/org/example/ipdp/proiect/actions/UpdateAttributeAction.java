package org.example.ipdp.proiect.actions;

import org.example.ipdp.proiect.misc.*;

public class UpdateAttributeAction implements IAction {
    String parentName;
    Attribute oldAttribute, newAttribute;

    public UpdateAttributeAction(String parentName, Attribute oldAttribute, Attribute newAttribute) {
        this.parentName = parentName;
        this.oldAttribute = oldAttribute;
        this.newAttribute = newAttribute;
    }

    @Override
    public void applyDataModel(DataModel data) {
        Entity parent = data.getEntity(parentName);
        Attribute old = parent.getAttribute(oldAttribute.getAttributeName());
        if (old != null) {
            parent.addAttribute(newAttribute);
        }
    }

    @Override
    public void applyStorage(IStorage storage) {
        storage.updateAttribute(parentName, newAttribute, oldAttribute);
    }
}
