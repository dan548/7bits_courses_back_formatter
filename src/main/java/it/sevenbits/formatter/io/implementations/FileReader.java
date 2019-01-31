package it.sevenbits.formatter.io.implementations;

import it.sevenbits.formatter.io.exceptions.ReadException;
import it.sevenbits.formatter.io.exceptions.ReadingErrorCode;
import it.sevenbits.formatter.io.interfaces.IReader;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Reader implementation to read from files.
 * @since 1.0
 * @version 1.0
 * @author Daniil Polyakov
 */
public class FileReader implements IReader, AutoCloseable {

    private boolean isBuffer;
    private BufferedReader reader;
    private int buf;

    /**
     * Constructs a reader.
     * @param path a string representing file-to-read-from path
     * @param charset charset to use while reading from a file
     * @throws ReadException if the path is incorrect
     */
    public FileReader(final String path, final Charset charset) throws ReadException {
        this(new File(path), charset);
    }

    /**
     * Constructs a reader.
     * @param file file-to-read-from path
     * @param charset charset to use while reading from a file
     * @throws ReadException if the path is incorrect
     */
    public FileReader(final File file, final Charset charset) throws ReadException {
        if (!file.isFile()) {
            if (file.isDirectory()) {
                throw new ReadException(ReadingErrorCode.DIRECTORY_FOUND);
            } else {
                throw new ReadException(ReadingErrorCode.FILE_NOT_FOUND);
            }
        }
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
        } catch (FileNotFoundException e) {
            throw new ReadException(ReadingErrorCode.FILE_NOT_FOUND);
        }
        isBuffer = false;
        buf = 0;
    }

    @Override
    public boolean hasNext() throws IOException {
        if (isBuffer) {
            return true;
        } else {
            buf = reader.read();
            isBuffer = true;
            return buf != -1;
        }
    }

    @Override
    public int read() throws ReadException, IOException {
        if (isBuffer) {
            isBuffer = false;
            return buf;
        }
        buf = reader.read();
        if (buf != -1) {
            return buf;
        } else {
            throw new ReadException(ReadingErrorCode.NO_CHARS);
        }
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
