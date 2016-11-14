package ivan.textsearch.search;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.WordsSearch;
import ivan.textsearch.WordsStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Search engine taking into account quantity of words occurrence only.
 * Relevance of search results is calculated based on number of words from request found in a source.
 */

public class QuantitySearch implements WordsSearch {
    private static final int TOTAL_QUANTITY = 0;
    private static final int WORDS_QUANTITY = 1;
    private static final int SHIFT = 2;

    @Override
    public List<SearchResult> findAnyWords(WordsStorage storage, List<WordAndContext> wordsToSearch) {
        Map<String, int[]> quantityOfWordsInSource = findWords(storage, wordsToSearch);

        List<SearchResult> results = new ArrayList<>();
        for (String source : quantityOfWordsInSource.keySet()) {
            int[] array = quantityOfWordsInSource.get(source);
            List<WordAndContext> found = new ArrayList<WordAndContext>();
            SimpleSearchResult result = new SimpleSearchResult();
            result.setSource(source);
            result.setFound(found);
            result.setRelevance(100. * array[WORDS_QUANTITY] / wordsToSearch.size());
            results.add(result);
            int index = SHIFT;
            for (WordAndContext wordAndContext : wordsToSearch) {
                if (array[index] > 0) {
                    found.add(wordAndContext);
                }
                index++;
            }
        }
        return results;
    }

    @Override
    public List<SearchResult> findAllWords(WordsStorage storage, List<WordAndContext> wordsToSearch) {
        return null;    // Implementation is not necessary for the current task
    }

    private Map<String, int[]> findWords(WordsStorage storage, List<WordAndContext> wordsToSearch) {
        // quantity of each searched word in a source:
        // - array[0] = total quantity,
        // - array[1] = how many words found,
        // - array[n+SHIFT] = quantity of nth word
        Map<String, int[]> quantityOfWordsInSource = new HashMap<>();
        int wordIndex = SHIFT;
        for (WordAndContext wordAndContext : wordsToSearch) {
            Map<String, Integer> wordData = storage.getSources(wordAndContext);
            if (wordData != null) {
                for (String source : wordData.keySet()) {
                    Integer quantity = wordData.get(source);
                    quantityOfWordsInSource.computeIfAbsent(source, key -> new int[wordsToSearch.size()+SHIFT]);
                    int[] array = quantityOfWordsInSource.get(source);
                    array[TOTAL_QUANTITY] += quantity;
                    array[WORDS_QUANTITY] += 1;
                    array[wordIndex] += quantity;
                }
            }
            wordIndex++;
        }
        return quantityOfWordsInSource;
    }

}
