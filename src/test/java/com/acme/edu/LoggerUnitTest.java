package com.acme.edu;

import com.acme.edu.messagetype.IntMessage;
import com.acme.edu.messagetype.StringMessage;
import com.acme.edu.saver.FileLoggerSaver;
import com.acme.edu.saver.LoggerSaver;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;

import static java.lang.System.lineSeparator;
import static org.mockito.Mockito.*;

public class LoggerUnitTest implements SysoutCaptureAndAssertionAbility{

    @Test
    public void shouldWriteNothingWhenNothingLogged() throws IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.flush();
        verify(loggerSaver, times(0)).save(any());
    }

    @Test
    public void shouldWriteStringsWhenLoggedJustStrings() throws IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new StringMessage("aa"));
        loggerController.log(new StringMessage("aa"));
        loggerController.log(new StringMessage("bb"));
        loggerController.flush();
        verify(loggerSaver).save("string: aa (x2), bb" + lineSeparator());
    }

    @Test
    public void shouldWriteIntsWhenLoggedJustInts() throws IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new IntMessage(1));
        loggerController.log(new IntMessage(-1));
        loggerController.log(new IntMessage(2));
        loggerController.flush();
        verify(loggerSaver).save("primitive: 2" + lineSeparator());
    }

    @Test
    public void shouldWriteIntsAndStringsWhenLoggedBoth() throws IOException {
        LoggerSaver loggerSaver = mock(LoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        loggerController.log(new IntMessage(1));
        loggerController.log(new StringMessage("bb"));
        loggerController.flush();
        verify(loggerSaver).save("primitive: 1" + lineSeparator() +
                "string: bb" + lineSeparator());
    }

    /*@Test
    public void shouldSavedIntoConsoleWhenLoggerFacadeCalled() throws IOException {
        LoggerSaver loggerSaver = mock(ConsoleLoggerSaver.class);
        LoggerController loggerController = new LoggerController(loggerSaver);
        LoggerFacade.controller = loggerController;
        LoggerFacade.log(1);
        LoggerFacade.log(2);
        LoggerFacade.flush();

    }*/
    @Mock
    LoggerSaver loggerSaver;

    @Test
    public void shouldSavedIntoFileWhenLoggerFacadeCalled() throws IOException {
        String fileName = "output.txt";
        loggerSaver = new FileLoggerSaver(fileName);

        LoggerController loggerController = new LoggerController(loggerSaver);
        LoggerFacade.controller = loggerController;
        LoggerFacade.log(1);
        LoggerFacade.log(2);
        LoggerFacade.flush();

        assertFileContains(fileName, "primitive: 3");

    }
}
