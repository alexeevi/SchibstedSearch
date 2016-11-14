package ivan.textsearch.search;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SearchResultTest {

    List<SearchResult> results = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        SearchResult result1 = new SearchResult() {};
        result1.setSource("result1");
        result1.setRelevance(1.);
        results.add(result1);

        SearchResult result2 = new SearchResult() {};
        result2.setSource("result2");
        result2.setRelevance(-20.);
        results.add(result2);

        SearchResult result3 = new SearchResult() {};
        result3.setSource("result3");
        result3.setRelevance(100.);
        results.add(result3);
    }

    @Test
    public void getSource() throws Exception {
        assertEquals(results.get(0).getRelevance(), (Double) 1.);
    }

    @Test
    public void getRelevance() throws Exception {
        assertEquals(results.get(0).getSource(), "result1");
    }

    @Test
    public void compareTo() throws Exception {
        Collections.sort(results);
        assertEquals(results.get(0).getRelevance(), (Double) 100.);
    }

}