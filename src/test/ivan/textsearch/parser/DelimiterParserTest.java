package ivan.textsearch.parser;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class DelimiterParserTest {

    private DelimiterParser parser = new DelimiterParser() {
        @Override
        protected Pattern getDelimiterPattern() {
            return Pattern.compile(" ");
        }
    };

    @Test
    public void parseAll() throws Exception {
        String[] inputs = {"qwe rty uio"};
        for (String input : inputs) {
            List<String> inputList = Arrays.asList(input.split(" "));
            List<String> outputList = new ArrayList<>();
            parser.parseAll(new ByteArrayInputStream(input.getBytes()), wordAndContext -> outputList.add(wordAndContext.getWord()));
            assertEquals(inputList, outputList);
        }
    }

}