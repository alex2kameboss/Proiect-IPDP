package org.example.ipdp.proiect.backend.orm.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.example.ipdp.proiect.backend.orm.Attribute;
import org.example.ipdp.proiect.backend.orm.Relationship;

/**
 * Class _Entity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Entity extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> NAME = Property.create("name", String.class);
    public static final Property<List<Attribute>> ATTRIBUTES = Property.create("attributes", List.class);
    public static final Property<List<Relationship>> FIRST_ENTITY = Property.create("firstEntity", List.class);
    public static final Property<List<Relationship>> SECOND_ENTITY = Property.create("secondEntity", List.class);

    protected String name;

    protected Object attributes;
    protected Object firstEntity;
    protected Object secondEntity;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void addToAttributes(Attribute obj) {
        addToManyTarget("attributes", obj, true);
    }

    public void removeFromAttributes(Attribute obj) {
        removeToManyTarget("attributes", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Attribute> getAttributes() {
        return (List<Attribute>)readProperty("attributes");
    }

    public void addToFirstEntity(Relationship obj) {
        addToManyTarget("firstEntity", obj, true);
    }

    public void removeFromFirstEntity(Relationship obj) {
        removeToManyTarget("firstEntity", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Relationship> getFirstEntity() {
        return (List<Relationship>)readProperty("firstEntity");
    }

    public void addToSecondEntity(Relationship obj) {
        addToManyTarget("secondEntity", obj, true);
    }

    public void removeFromSecondEntity(Relationship obj) {
        removeToManyTarget("secondEntity", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Relationship> getSecondEntity() {
        return (List<Relationship>)readProperty("secondEntity");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "attributes":
                return this.attributes;
            case "firstEntity":
                return this.firstEntity;
            case "secondEntity":
                return this.secondEntity;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "name":
                this.name = (String)val;
                break;
            case "attributes":
                this.attributes = val;
                break;
            case "firstEntity":
                this.firstEntity = val;
                break;
            case "secondEntity":
                this.secondEntity = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.name);
        out.writeObject(this.attributes);
        out.writeObject(this.firstEntity);
        out.writeObject(this.secondEntity);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.attributes = in.readObject();
        this.firstEntity = in.readObject();
        this.secondEntity = in.readObject();
    }

}
