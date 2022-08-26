package server;

import java.io.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import wrappers.InputOutputWrappers;

public class InputOutputWrapperTest {
    @Test
    public void canBeCreated() throws IOException {
        var actual = new InputOutputWrappers();
        assertNotNull(actual);
    }
}
