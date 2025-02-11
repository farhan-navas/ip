package eva.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Ui class.
 */
public class UiTest {
    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        String expected = "Hello! I'm Eva. \nWhat can I do for you? \n";
        assertEquals(expected, ui.showWelcome());
    }
}
