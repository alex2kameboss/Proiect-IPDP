package org.example.ipdp.proiect.misc;

public class Relation {
    protected Entity firstEntity, secondEntity;
    protected RelationTypes relationTypes;

    public Relation(Entity firstEntity, Entity secondEntity, RelationTypes relationTypes) {
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
        this.relationTypes = relationTypes;
    }

    public Entity getFirstEntity() {
        return firstEntity;
    }

    public Entity getSecondEntity() {
        return secondEntity;
    }

    public RelationTypes getRelationTypes() {
        return relationTypes;
    }

    public boolean equals(Relation relation) {
        if (this == relation) return true;

        return firstEntity.getEntityName().equals(relation.getFirstEntity().getEntityName()) &&
                secondEntity.getEntityName().equals(relation.getSecondEntity().getEntityName());
    }
}
