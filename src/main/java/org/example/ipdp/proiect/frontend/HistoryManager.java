package org.example.ipdp.proiect.frontend;

import org.example.ipdp.proiect.misc.DataModel;
import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;

import java.util.LinkedList;

public class HistoryManager {
    protected DataModel baseData, currentData;
    protected IStorage storage;
    protected LinkedList<IAction> actions;

    public HistoryManager(IStorage storage) {
        this.baseData = storage.getDataModel();
        this.currentData = new DataModel(baseData);
        this.storage = storage;
        this.actions = new LinkedList<>();
    }

    public DataModel getData() {
        return currentData;
    }

    public void addAction(IAction action) {
        actions.add(action);

        action.applyDataModel(currentData);
    }

    public void undoAction() {
        actions.removeLast();

        currentData = new DataModel(baseData);
        for (IAction action : actions) {
            action.applyDataModel(currentData);
        }
    }

    public void saveActions() {
        baseData = new DataModel(currentData);

        storage.applyActions(actions);
    }
}
