package inputOut.deleteWord;

import java.io.*;

public class DeleteAbuses {
    public void dropAbuses(InputStream in, OutputStream out, String[] words) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            String check;
            while ((check = reader.readLine()) != null) {
                for (String word : words) {
                    check = check.replace(word, "***");
                }
                writer.write(check);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
