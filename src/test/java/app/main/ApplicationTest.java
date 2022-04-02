package app.main;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApplicationTest {

    @Test
    @DisplayName("Given valid input, Then application should execute successfully.")
    public void testMainSuccess() {
        Application.main(null);
    }
}