package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;
import com.acme.edu.saver.ConsoleLoggerSaver;
import com.acme.edu.saver.FileLoggerSaver;
import com.acme.edu.saver.LoggerSaver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import com.acme.edu.saver.SaverExceptions;

import static java.lang.System.lineSeparator;
import static org.mockito.Mockito.*;

public class LoggerUnitTest implements SysoutCaptureAndAssertionAbility {
    String fileName = "OutputFileOnlyForLoggerTest.txt";

    @Before
    public void init() {
        resetOut();
        captureSysout();
    }

    @After
    public void end() {
        File file = new File(fileName);
        if(file.exists()) file.delete();
        resetOut();
    }

    @Test
    public void shouldWriteNothingWhenNothingLogged() throws SaverExceptions, IOException{
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.flush();
        verify(loggerSaver, times(0)).save(any());
    }

    @Test
    public void shouldWriteStringsWhenLoggedJustStrings() throws SaverExceptions, IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new StringMessage("aa"));
        loggerController.log(new StringMessage("aa"));
        loggerController.log(new StringMessage("bb"));
        loggerController.flush();
        verify(loggerSaver).save("string: aa (x2), bb" + lineSeparator());
    }

    @Test
    public void shouldWriteIntsWhenLoggedJustInts() throws SaverExceptions, IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new IntMessage(1));
        loggerController.log(new IntMessage(-1));
        loggerController.log(new IntMessage(2));
        loggerController.flush();
        verify(loggerSaver).save("primitive: 2" + lineSeparator());
    }

    @Test
    public void shouldWriteIntsAndStringsWhenLoggedBoth() throws SaverExceptions, IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new IntMessage(1));
        loggerController.log(new StringMessage("bb"));
        loggerController.flush();
        verify(loggerSaver).save("primitive: 1" + lineSeparator() +
                "string: bb" + lineSeparator());
    }

    @Test
    public void shouldSavedIntoConsoleWhenLoggerFacadeCalled() throws SaverExceptions {
        LoggerSaver loggerSaver = new ConsoleLoggerSaver();
        LoggerController loggerController = new LoggerController(loggerSaver);
        LoggerFacade.controller = loggerController;
        LoggerFacade.log("aaa");
        LoggerFacade.log("bbb");
        LoggerFacade.log("aaa");
        LoggerFacade.flush();
        assertSysoutEquals("string: aaa, bbb, aaa" + lineSeparator());
    }


    @Test
    public void shouldSavedIntoFileWhenLoggerFacadeCalled() throws SaverExceptions, Exception {

        LoggerSaver loggerSaver = new FileLoggerSaver(fileName);
        LoggerController loggerController = new LoggerController(loggerSaver);
        LoggerFacade.controller = loggerController;
        LoggerFacade.log(1);
        LoggerFacade.log(2);
        LoggerFacade.flush();
        LoggerFacade.close();

        assertFileContains(fileName, "primitive: 3" + lineSeparator());

    }

    @Test(expected = SaverExceptions.class)
    public void shouldThrowsException() throws SaverExceptions, IOException {
        LoggerSaver loggerSaver = mock(FileLoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        doThrow(IOException.class).when(loggerSaver).save(any());

        loggerController.log(new IntMessage(1));
        loggerController.flush();
    }

    @Test
    public void shouldLogIntoTwoChannels() throws SaverExceptions, Exception {
        LoggerSaver consoleSaver = new ConsoleLoggerSaver();
        LoggerSaver fileSaver = new FileLoggerSaver(fileName);

        LoggerController controller = new LoggerController(consoleSaver, fileSaver);

        controller.log(new IntMessage(1));
        controller.log(new StringMessage("abc"));
        controller.flush();
        controller.close();

        assertFileContains(fileName, "primitive: 1" + lineSeparator() + "string: abc" + lineSeparator());
        assertSysoutContains("primitive: 1" + lineSeparator() + "string: abc");
    }
}
