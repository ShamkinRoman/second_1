package inputOut.checkArray;

import java.io.*;

public class CheckOnNumber {
    public boolean isNumberEven(InputStream input) {
        boolean result = false;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            int number = Integer.valueOf(br.readLine());
            if (number % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
