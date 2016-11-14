package ivan.textsearch.parser;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.WordsParser;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public abstract class DelimiterParser implements WordsParser {

    abstract protected Pattern getDelimiterPattern();

    @Override
    public void parseAll(InputStream inputStream, Consumer<WordAndContext> consumer) {
        Scanner scanner = new Scanner(inputStream).useDelimiter(getDelimiterPattern());
        while (scanner.hasNext()) {
            String word = scanner.next();
            if ((getDelimiterPattern().flags() & Pattern.CASE_INSENSITIVE) != 0) {
                word = word.toLowerCase();
            }
            consumer.accept(new SimpleWordAndContext(word, null));
        }
    }

}
