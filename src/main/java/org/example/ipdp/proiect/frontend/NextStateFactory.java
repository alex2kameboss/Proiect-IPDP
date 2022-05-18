package org.example.ipdp.proiect.frontend;

import org.example.ipdp.proiect.Log;

public class NextStateFactory {
    static State getNextState (State currentState) {
        if (currentState.getClass().getSimpleName().equals("HomeScreen")) {
            return new WorkScreen();
        } else if (currentState.getClass().getSimpleName().equals("WorkScreen")) {
            return new HomeScreen();
        } else {
            Log.getContext().error("Invalid state");
            return null;
        }
    }
}
