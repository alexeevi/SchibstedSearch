package ivan.textsearch;

import java.io.InputStream;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Interface which parses input stream and calls the provided consumer for each found word
 */

public interface WordsParser {
    void parseAll(InputStream inputStream, Consumer<WordAndContext> consumer);
}
