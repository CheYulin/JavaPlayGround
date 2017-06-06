import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by yche on 6/6/17.
 */
public class ReverseReadLine {
    public static void main(String[] args) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("/tmp/test/lines.txt")));
        for (int i = 0; i < 1024 * 1024 * 2.5; i++) {
            fileWriter.write("Current Line Number:" + i);
            fileWriter.newLine();
        }
        fileWriter.close();

        ReversedLinesFileReader reversedLinesFileReader = new ReversedLinesFileReader(new File("/tmp/test/lines.txt"), 1024 * 1024, Charset.defaultCharset());
        String line;

        while ((line = reversedLinesFileReader.readLine()) != null)
            System.out.println(line);
    }
}
