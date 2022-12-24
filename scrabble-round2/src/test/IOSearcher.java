package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOSearcher {
    public static boolean search(String word, String... filesNames) throws IOException
    {
        for (String string : filesNames) {
            BufferedReader reader = new BufferedReader(new FileReader(string));
            String line;
            int lineNumber=0;
            while((line = reader.readLine())!= null){
                lineNumber++;
                if(line.contains(word)) {
                    reader.close();
                    return true;
                }
            }
        }
        return false;

    }
}
