package it.sevenbits.formatter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(Formatter.format("aaa { bbbb; ccc;}"));

        try(FileReader reader = new FileReader("src/main/java/it/sevenbits/" +
                "formatter/test/HelloWorld.java")) {
            char[] buf = new char[256];
            int c;
            StringBuilder sb = new StringBuilder();
            while((c = reader.read(buf))>0){
                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                sb.append(buf);
            }
            System.out.println(Formatter.format(sb.toString()));
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
