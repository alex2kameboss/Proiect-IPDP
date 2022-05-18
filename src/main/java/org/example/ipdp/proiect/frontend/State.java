package org.example.ipdp.proiect.frontend;

import javax.swing.*;

public abstract class State {
    protected JFrame frame = null;

    public final void init(JFrame frame) {
        this.frame = frame;
        this.userInit();
    }

    protected abstract void userInit();

    protected abstract void exit();

    protected final void next(State nextState){
        this.exit();
        frame.getContentPane().removeAll();
        frame.repaint();
        nextState.init(frame);
        this.frame = null;
    }
}
