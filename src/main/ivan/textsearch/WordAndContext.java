package ivan.textsearch;

import java.util.Map;

public abstract class WordAndContext {
    protected String word;
    protected Map properties;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Map getContext() {
        return properties;
    }

    public void setContext(Map properties) {
        this.properties = properties;
    }
}
