package com.acme.edu;

import java.io.*;

import static java.lang.System.lineSeparator;
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
        BufferedReader data = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(fileName))));
        String lines;
        StringBuilder sb = new StringBuilder();
        while((lines = data.readLine()) != null){
            sb.append(lines).append(lineSeparator());
        }
        assertThat(sb.toString()).isEqualTo(expected);
    }

    default void resetOut() {
        OUT.reset();
    }
}
