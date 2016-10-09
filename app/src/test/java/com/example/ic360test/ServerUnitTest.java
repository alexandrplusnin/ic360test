package com.example.ic360test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ServerUnitTest {
    @Test
    public void divisionIsCorrect() throws Exception {
        MockServer server = MockServer.getInstance();
        server.setSleepTime(0);
        assertEquals(1, server.divideBy2(2), 0.01);
        assertEquals(-1, server.divideBy2(-2), 0.01);
        assertEquals(1.25, server.divideBy2(2.5), 0.01);
        assertEquals(0, server.divideBy2(0), 0.01);
    }
}