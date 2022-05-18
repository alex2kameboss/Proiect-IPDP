package org.example.ipdp.proiect.frontend;

import org.example.ipdp.proiect.misc.IAction;
import org.example.ipdp.proiect.misc.IStorage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ApplyActionOnBackendThread extends Thread{
    protected List<IAction> actions = null;
    protected IStorage storage = null;

    public ApplyActionOnBackendThread(@NotNull List<IAction> actions, @NotNull IStorage storage){
        this.actions = new LinkedList<>(actions);
        this.storage = storage;
    }

    @Override
    public void run() {
        storage.applyActions(actions);
    }
}
