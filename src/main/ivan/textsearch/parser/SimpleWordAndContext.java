package ivan.textsearch.parser;

import ivan.textsearch.WordAndContext;

import java.util.Map;

public class SimpleWordAndContext extends WordAndContext {
    public SimpleWordAndContext(String word, Map properties) {
        this.word = word;
        this.properties = properties;
    }
}
