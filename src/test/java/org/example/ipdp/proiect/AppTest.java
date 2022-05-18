package org.example.ipdp.proiect;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testLoggingOnConsole() {
        Log.getContext().error("Error test");
        Log.getContext().info("Info test");
        assertTrue( true );
    }

    @Test
    public void testLoggingOnFile() {
        Log.getContext().setLogFile("F:\\test.log");
        Log.getContext().error("Error test");
        Log.getContext().info("Info test");
        assertTrue( true );
    }
}
