package com.acme.edu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.lineSeparator;

public class iteration5 implements SysoutCaptureAndAssertionAbility {
    @Before
    public void setUpSystemOut() {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldLogStrings() {
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
        assertSysoutEquals("string: aaa (x5)" + lineSeparator() +
                "string: bbb (x2)" + lineSeparator() +
                "primitive: 71" + lineSeparator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBeException() {
        LoggerFacade.log("aaa");
        LoggerFacade.log(1);
        LoggerFacade.flush();

    }

    @Test
    public void shouldStackOverflow() {
        LoggerFacade.log(-1);
        LoggerFacade.log(Integer.MAX_VALUE);
        LoggerFacade.log(5);
        LoggerFacade.flush();

        assertSysoutEquals("primitive: " + (Integer.MAX_VALUE - 1) + lineSeparator() +
                "primitive: 5" + lineSeparator());

    }
}
