import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class MainExecutionTest {

    @Test
    void demoProgramRunsAndPrintsItsCompletionMessage() {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));
            main.main(new String[0]);
        } finally {
            System.setOut(original);
        }

        new main(); // Cover the implicit default constructor.
        main.displaySearchResult(new books(99, "9780134685991", "Found", "Author", 1));
        main.displaySearchResult(null);

        String output = captured.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Library Management System"));
        assertTrue(output.contains("Book 101 added: true"));
        assertTrue(output.contains("Duplicate book added: false"));
        assertTrue(output.contains("Program Finished"));
        assertTrue(output.contains("Book found: Found"));
        assertTrue(output.contains("Book not found."));
    }
}
