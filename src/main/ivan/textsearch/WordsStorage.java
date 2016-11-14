package ivan.textsearch;

import java.util.Map;

/**
 * Interface of storage which keeps data about words and quantity of their appearance in sources
 */
public interface WordsStorage {
    void addWord(String source, WordAndContext wordAndContext);
    Integer getDifferentWords();
    Map<String, Integer> getSources(WordAndContext wordAndContext);
}
