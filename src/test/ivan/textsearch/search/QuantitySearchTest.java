package ivan.textsearch.search;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.WordsParser;
import ivan.textsearch.WordsStorage;
import ivan.textsearch.parser.EnglishTextParser;
import ivan.textsearch.parser.SimpleWordAndContext;
import ivan.textsearch.storage.QuantityStorage;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuantitySearchTest {

    WordsParser parser = new EnglishTextParser();
    QuantitySearch search = new QuantitySearch();

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void findAnyWords() throws Exception {
        String[] inputs = {"Quick brown dog jumps over lazy fox"};
        for (String input : inputs) {
            List<String> arr = Arrays.asList(input.toLowerCase().split(" "));
            List<WordAndContext> wordsToSearch = new ArrayList<>();
            for (String a : arr) {
                wordsToSearch.add(new SimpleWordAndContext(a, null));
            }
            StringBuilder builder = new StringBuilder();
            int wordCount = 0;
            for (String word : arr) {
                builder.append(word).append(" ");
                wordCount++;
                WordsStorage storage = new QuantityStorage();
                parser.parseAll(new ByteArrayInputStream(builder.toString().getBytes()), wordAndContext -> storage.addWord("", wordAndContext));
                List<SearchResult> results = search.findAnyWords(storage, wordsToSearch);
                assertEquals(results.get(0).getRelevance(), (Double) (100. * wordCount / wordsToSearch.size()));
            }
        }
    }

}