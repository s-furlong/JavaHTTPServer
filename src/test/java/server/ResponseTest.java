package server;

import org.junit.jupiter.api.Test;
import response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTest {
    @Test
    public void testBuiltIntoString() {
        var response = new Response(200);
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.toString());
    }

    @Test
    public void testCustomMessage() {
        var response = new Response(200);
        assertEquals("HTTP/1.1 200 OKAY\r\n\r\n", response.toString());
    }

    @Test
    public void testOneHeader() {
        var response = new Response(200);
        response.addHeader("Content-Type", "html/text");
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: html/text\r\n\r\n", response.toString());
    }
}
