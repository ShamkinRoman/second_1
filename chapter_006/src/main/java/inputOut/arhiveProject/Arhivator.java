package inputOut.arhiveProject;

import java.util.*;

public class Arhivator {
    public static Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
        String[] arg = new String[]{"-d", "directory", "-e", "java.xml", "readme.txt", "-o", "arhive.zip"};

        String key = new String();
        int count = 0;
        String lastkey = null;
        List<Integer> nomer = new ArrayList<>();
        List<String> arguments = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            if (arg[i].contains("-")) {
                count++;
                if (count >= 2) {
                    map.put(key, arguments);
                    arguments = new ArrayList<>();
                    count = 0;
                }
                key = arg[i];

            } else {
                arguments.add(arg[i]);
            }

        }
        nomer.forEach(System.out::println);
        for (String string: map.keySet()){
            System.out.println("-----------");
            System.out.println(string);
            map.get(string).forEach(System.out::println);
        }
    }
}
