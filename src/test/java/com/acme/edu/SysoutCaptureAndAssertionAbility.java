package com.acme.edu;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;


public interface SysoutCaptureAndAssertionAbility {
    ByteArrayOutputStream OUT = new ByteArrayOutputStream();

    default void captureSysout() {
        System.setOut(new PrintStream(OUT));
    }

    default void assertSysoutEquals(String expected) {
        assertThat(OUT.toString()).isEqualTo(expected);
    }

    default void assertSysoutContains(String expected) {
        assertThat(OUT.toString()).contains(expected);
    }

    default void assertFileContains(String fileName, String expected) throws IOException {
        String data =  new BufferedReader(new FileReader(new File(fileName))).readLine();
        assertThat(data).isEqualTo(expected);
    }

    default void resetOut() {
        OUT.reset();
    }
}
