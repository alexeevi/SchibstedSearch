package ivan.textsearch.parser;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EnglishTextParserTest {

    private EnglishTextParser parser = new EnglishTextParser();

    @Test
    public void parseAll() throws Exception {
        String[] inputs = {"a bb rrr", "I'd like to check: whether this so-called parser really works, or not!"};
        for (String input : inputs) {
            List<String> inputList = Arrays.asList(input.toLowerCase().split(parser.getDelimiterPattern().pattern()));
            List<String> outputList = new ArrayList<>();
            parser.parseAll(new ByteArrayInputStream(input.getBytes()), wordAndContext -> outputList.add(wordAndContext.getWord()));
            assertEquals(inputList, outputList);
        }
    }

    @Test
    public void parseAllEmpty() throws Exception {
        String[] inputs = {"", ",- "};
        for (String input : inputs) {
            List<String> outputList = new ArrayList<>();
            parser.parseAll(new ByteArrayInputStream(input.getBytes()), wordAndContext -> outputList.add(wordAndContext.getWord()));
            assertEquals(outputList.size(), 0);
        }
    }

}