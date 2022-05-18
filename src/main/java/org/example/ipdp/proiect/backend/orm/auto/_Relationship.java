package org.example.ipdp.proiect.backend.orm.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.Property;
import org.example.ipdp.proiect.backend.orm.Entity;

/**
 * Class _Relationship was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Relationship extends BaseDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<String> TYPE = Property.create("type", String.class);
    public static final Property<Entity> FIRST_MEMBER = Property.create("firstMember", Entity.class);
    public static final Property<Entity> SECOND_MEMEBR = Property.create("secondMemebr", Entity.class);

    protected String type;

    protected Object firstMember;
    protected Object secondMemebr;

    public void setType(String type) {
        beforePropertyWrite("type", this.type, type);
        this.type = type;
    }

    public String getType() {
        beforePropertyRead("type");
        return this.type;
    }

    public void setFirstMember(Entity firstMember) {
        setToOneTarget("firstMember", firstMember, true);
    }

    public Entity getFirstMember() {
        return (Entity)readProperty("firstMember");
    }

    public void setSecondMemebr(Entity secondMemebr) {
        setToOneTarget("secondMemebr", secondMemebr, true);
    }

    public Entity getSecondMemebr() {
        return (Entity)readProperty("secondMemebr");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "type":
                return this.type;
            case "firstMember":
                return this.firstMember;
            case "secondMemebr":
                return this.secondMemebr;
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
            case "type":
                this.type = (String)val;
                break;
            case "firstMember":
                this.firstMember = val;
                break;
            case "secondMemebr":
                this.secondMemebr = val;
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
        out.writeObject(this.type);
        out.writeObject(this.firstMember);
        out.writeObject(this.secondMemebr);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.type = (String)in.readObject();
        this.firstMember = in.readObject();
        this.secondMemebr = in.readObject();
    }

}
