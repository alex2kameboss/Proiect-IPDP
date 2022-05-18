package org.example.ipdp.proiect.misc;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Attr;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Entity {
    protected String entityName;
    protected HashMap<String, Attribute> attributes;

    public Entity(String entityName) {
        this.entityName = entityName;
        this.attributes = new HashMap<>();
    }

    public Entity(@NotNull final Entity entity) {
        this.entityName = entity.getEntityName();
        this.attributes = new HashMap<>();

        Collection<Attribute> attrs = entity.attributes.values();
        for (Attribute attr : attrs) {
            this.addAttribute(new Attribute(attr));
        }
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void addAttribute(Attribute attribute) {
        attributes.put(attribute.getAttributeName(), attribute);
    }

    public Attribute getAttribute(String attributeName){
        return attributes.get(attributeName);
    }

    public List<Attribute> getAttributes() {
        return new LinkedList<>(attributes.values());
    }

    public Attribute removeAttribute(String attributeName){
        if (attributes.containsKey(attributeName)) {
            return attributes.remove(attributeName);
        } else {
            return null;
        }
    }
}
