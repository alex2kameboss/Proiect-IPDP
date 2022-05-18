package org.example.ipdp.proiect.misc;

import org.jetbrains.annotations.NotNull;

public class Attribute {
    protected String attributeName;
    protected String attributeType;

    public Attribute(String attributeName, String attributeType) {
        this.attributeName = attributeName;
        this.attributeType = attributeType;
    }

    public Attribute(@NotNull final Attribute attribute) {
        this.attributeName = attribute.getAttributeName();
        this.attributeType = attribute.getAttributeType();
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }
}
