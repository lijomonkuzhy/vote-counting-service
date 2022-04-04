package app.service;

import app.exception.ServiceException;
import app.util.console.ConsoleWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConsoleWriterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintInfoSuccess() {
        ConsoleWriter.printInfo("message");
        assertThat(outContent.toString()).contains("message");
    }

    @Test
    public void testPrintErrorSuccess() {
        ServiceException e = new ServiceException("Invalid candidate");
        ConsoleWriter.printError(e);
        assertThat(outContent.toString()).contains("Error", "Invalid candidate");
    }
}