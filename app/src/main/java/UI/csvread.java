package UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.gradle.internal.impldep.org.apache.commons.lang.ArrayUtils;



public class csvread {
    public final String englishWords() {
        String line = "";
        String splitBy = " ";
        String good = new String(" ");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/irislitiu/Documents/words.rtf"));
            while ((line=reader.readLine()) != null) {
                String[] words = line.split(splitBy);
                for(String str : words) {
                    if (ArrayUtils.indexOf(words, str) > 0) {
                        System.out.println(str);
                        good.concat(str);
                    }
                }
            }
            reader.close(); 
        
        } catch (IOException io) {
            io.printStackTrace();
        }
        return good;
    }
   
}

