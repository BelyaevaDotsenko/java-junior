package com.acme.edu;

import deprecated.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.lang.System.lineSeparator;

public class iteration5 implements SysoutCaptureAndAssertionAbility {
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() throws IOException {
        resetOut();
    }

    @Test
    public void shouldLogIntegers() throws IOException {
        LoggerFacade.log(1);
        LoggerFacade.log(2);
        LoggerFacade.log(3);
        LoggerFacade.flush();
        LoggerFacade.log(1);
        LoggerFacade.flush();
        LoggerFacade.log(2);
        LoggerFacade.flush();

        assertSysoutEquals("primitive: 6" + lineSeparator() +
                "primitive: 1" + lineSeparator() +
                "primitive: 2" + lineSeparator());
    }

    @Test
    public void shouldLogStrings() throws IOException {
        LoggerFacade.log("str 1");
        LoggerFacade.flush();
        LoggerFacade.log("aaa");
        LoggerFacade.log("aaa");
        LoggerFacade.log("aaa");
        LoggerFacade.log("aaa");
        LoggerFacade.log("aaa");
        LoggerFacade.flush();
        LoggerFacade.log("bbb");
        LoggerFacade.log("bbb");
        LoggerFacade.flush();
        LoggerFacade.log(1);
        LoggerFacade.log(100);
        LoggerFacade.log(-30);
        LoggerFacade.flush();
        assertSysoutEquals("string: str 1" + lineSeparator() +
                "string: aaa (x5)" + lineSeparator() +
                "string: bbb (x2)" + lineSeparator() +
                "primitive: 71" + lineSeparator());
    }

    @Test
    public void shouldBeIllegalArgumentException() throws IOException {
        LoggerFacade.log("aaa");
        LoggerFacade.log(1);
        LoggerFacade.flush();

        assertSysoutEquals("string: aaa" + lineSeparator() +
                "primitive: 1" + lineSeparator());
    }

    //@Test
    //blocked
    public void shouldStackOverflow() throws IOException {
        LoggerFacade.log(10);
        LoggerFacade.log(Integer.MAX_VALUE);
        LoggerFacade.log(5);
        LoggerFacade.flush();

        assertSysoutEquals("primitive: 10" + lineSeparator() +
                "primitive: " + (Integer.MAX_VALUE - 1) + lineSeparator() +
                "primitive: 5" + lineSeparator());

    }
}
