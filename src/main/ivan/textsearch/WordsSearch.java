package ivan.textsearch;

import ivan.textsearch.search.SearchResult;

import java.util.List;

/**
 * Interface of search engine which works with given storage
 */

public interface WordsSearch {

    /**
     * Find sources which contain any words from the given list.
     * @param storage Storage to look into.
     * @param wordsToSearch List of words to look for.
     * @return List of SearchResult with source name and relevance: with 100 when all words are found; zero relevance is never given (the record just will not exist).
     */
    List<SearchResult> findAnyWords(WordsStorage storage, List<WordAndContext> wordsToSearch);

    /**
     * Find sources which contain all words from the given list.
     * @param storage Storage to look into.
     * @param wordsToSearch List of words to look for.
     * @return List of SearchResult with source name and relevance.
     */
    List<SearchResult> findAllWords(WordsStorage storage, List<WordAndContext> wordsToSearch);
}
