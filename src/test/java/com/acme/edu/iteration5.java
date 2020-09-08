package com.acme.edu;

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

    //FIXME
    @Test
    public void thisTestFailsNeedFix() throws IOException {
        LoggerFacade.log("aaa");
        LoggerFacade.log("bbb");
        LoggerFacade.log("aaa");
        LoggerFacade.flush();
        assertSysoutEquals("string: aaa, bbb, aaa" + lineSeparator());
    }

    //FIXME
    @Test
    public void thisTestFailsTooNeedFix() throws IOException {
        LoggerFacade.log("aaa");
        LoggerFacade.log("bbb");
        LoggerFacade.flush();
        assertSysoutEquals("string: aaa, bbb" + lineSeparator());
    }

    @Test
    public void hardTest() throws IOException {
        LoggerFacade.log("aaa");
        LoggerFacade.log("aa");
        LoggerFacade.log(1);
        LoggerFacade.log(-1);
        LoggerFacade.flush();
        LoggerFacade.log(8);
        LoggerFacade.log(-1);
        LoggerFacade.log("bbb");
        LoggerFacade.log("bbb");
        LoggerFacade.log(-1);
        LoggerFacade.log("bbb");
        LoggerFacade.flush();
        assertSysoutEquals("string: aaa, aa" + lineSeparator() +
                "primitive: 0" + lineSeparator() +
                "primitive: 7" + lineSeparator() +
                "string: bbb (x2)" + lineSeparator() +
                "primitive: -1" + lineSeparator() +
                "string: bbb" + lineSeparator());
    }
}
