package com.acme.edu;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
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
        assertSysoutEquals("string: aaa (x5)" + lineSeparator() +
                "string: bbb (x2)" + lineSeparator());
    }
}
