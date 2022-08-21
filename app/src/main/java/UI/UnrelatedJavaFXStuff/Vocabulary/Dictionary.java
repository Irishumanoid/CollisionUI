package UI.Vocabulary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

//includes hashmap of words/translations and a list of all user-inputted words
public class Dictionary {

    ArrayList<String> words;
    Map<String, String> dictionary;

    public Dictionary() {
        this.words = new ArrayList<>();
        this.dictionary = new HashMap<>();

        add("stergator", "eraser");
    }

    public String get(String word) {
        return this.dictionary.get(word);
    }

    public Set<String> getKeys() {
        return this.dictionary.keySet();
    }

    public void add(String word, String translation) {
        if(!this.dictionary.containsKey(word)) {
            this.words.add(word);
        }
        this.dictionary.put(word, translation);
    }

    public String getRandomWord() {
        Random random = new Random();
        return this.words.get(random.nextInt(this.words.size()));
    }  
}
