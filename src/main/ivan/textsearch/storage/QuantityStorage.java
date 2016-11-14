package ivan.textsearch.storage;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.WordsStorage;

import java.util.HashMap;
import java.util.Map;

public class QuantityStorage implements WordsStorage {

    // counter of all
    private Integer differentWords = 0;
    // map of word to map of source to quantity of the word
    private Map<String, HashMap<String, Integer>> words = new HashMap<>();
    private final Object editLock = new Object();

    @Override
    public Integer getDifferentWords() {
        return differentWords;
    }

    @Override
    public void addWord(String source, WordAndContext wordAndContext) {
        String word = wordAndContext.getWord();
        synchronized (editLock) {
            if (words.get(word) == null) {
                words.put(word, new HashMap<>());
                differentWords++;
            }
            words.get(word).compute(source, (key, value) -> value == null ? 1 : value+1);
        }
    }

    @Override
    public Map<String, Integer> getSources(WordAndContext wordAndContext) {
        return words.get(wordAndContext.getWord());
    }

}
