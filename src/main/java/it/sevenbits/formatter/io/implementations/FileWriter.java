package it.sevenbits.formatter.io.implementations;

import it.sevenbits.formatter.io.interfaces.IWriter;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Writer implementation to write to files.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class FileWriter implements IWriter, AutoCloseable {

    private BufferedWriter writer;

    /**
     * Constructs a writer.
     * @param path a string representing file-to-write-to path
     * @param charset charset to use while writing to a file
     * @throws FileNotFoundException if the path is incorrect (never)
     */
    public FileWriter(final String path, final Charset charset) throws FileNotFoundException {
        this(new File(path), charset);
    }
    /**
     * Constructs a writer.
     * @param file file-to-write-to path
     * @param charset charset to use while writing to a file
     * @throws FileNotFoundException if the path is incorrect (never)
     */
    public FileWriter(final File file, final Charset charset) throws FileNotFoundException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
    }

    @Override
    public void write(final int c) throws IOException {
        writer.write((char) c);
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
