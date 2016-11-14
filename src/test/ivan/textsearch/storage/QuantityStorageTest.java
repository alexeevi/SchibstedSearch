package ivan.textsearch.storage;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.parser.SimpleWordAndContext;
import oracle.jrockit.jfr.StringConstantPool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityStorageTest {

    QuantityStorage storage = new QuantityStorage();
    String source1 = "Source1";
    String source2 = "Source2";
    WordAndContext word1 = new SimpleWordAndContext("word1", null);
    WordAndContext word2 = new SimpleWordAndContext("word2", null);

    @Before
    public void setUp() throws Exception {
        storage.addWord(source1, word1);
        storage.addWord(source1, word2);

        storage.addWord(source2, word1);
        storage.addWord(source2, word1);
    }

    @Test
    public void getDifferentWords() throws Exception {
        assertEquals(storage.getDifferentWords(), (Integer) 2);
    }

    @Test
    public void getSources() throws Exception {
        assertEquals(storage.getSources(word1).get(source1), (Integer) 1);
        assertEquals(storage.getSources(word1).get(source2), (Integer) 2);

        assertNull(storage.getSources(word2).get(source2));
    }

}