package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.formatter.Formatter;
import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.io.implementations.FileReader;
import it.sevenbits.formatter.io.implementations.StringReader;
import it.sevenbits.formatter.io.implementations.StringWriter;
import it.sevenbits.formatter.io.interfaces.IReader;
import it.sevenbits.formatter.io.interfaces.IWriter;
import it.sevenbits.formatter.lexer.factory.LexerFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FormatterTest {

    private Formatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void before() {
        formatter = new Formatter(new LexerFactory());
        writer = new StringWriter();
    }

    @Test
    public void simpleTest() throws FormatterException {
        reader = new StringReader("{{{{}}}}");
        formatter.format(reader, writer);
        assertEquals("{\n    {\n        {\n            {\n                \n            }\n        }\n    }\n}", writer.toString());
    }

    @Test
    public void simpleTest2() throws FormatterException {
        reader = new StringReader("a{b{c{d{}}}}");
        formatter.format(reader, writer);
        assertEquals("a {\n    b {\n        c {\n            d {\n                \n            }\n        }\n    }\n}", writer.toString());
    }

    @Test
    public void testValidExample() throws FormatterException {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        try (Scanner in = new Scanner(new File("src/main/java/it/sevenbits/" +
                "formatter/test/HelloWorld.txt"))) {
            while (in.hasNext()) {
                s1.append(in.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new StringReader(s1.toString());
        try (Scanner in = new Scanner(new File("src/main/java/it/sevenbits/" +
                "formatter/test/HelloWorldFormatted.txt"))) {
            while (in.hasNext()) {
                s2.append(in.nextLine()).append("\n");
            }
            s2.deleteCharAt(s2.length() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        formatter.format(reader, writer);
        assertEquals(s2.toString(), writer.toString());
    }

    @Test
    public void testValidExampleFileReader() {
        StringBuilder s2 = new StringBuilder();
        try (Scanner in = new Scanner(new File("src/main/java/it/sevenbits/" +
                "formatter/test/HelloWorldFormatted.txt"))) {
            while (in.hasNext()) {
                s2.append(in.nextLine()).append("\n");
            }
            s2.deleteCharAt(s2.length() - 1);
        } catch (FileNotFoundException e) {
            fail();
        }

        try (FileReader reader2 = new FileReader("src/main/java/it/sevenbits/" +
                "formatter/test/HelloWorld.txt", Charset.forName("UTF-8"))) {
            formatter.format(reader2, writer);
            assertEquals(s2.toString(), writer.toString());
        } catch (Exception e) {
            fail();
        }
    }
}
