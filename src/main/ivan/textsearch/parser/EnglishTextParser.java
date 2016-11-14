package ivan.textsearch.parser;

import java.util.regex.Pattern;

public class EnglishTextParser extends DelimiterParser {

    // delimiter pattern excludes letters and apostrophe
    private static final Pattern delimiterPattern = Pattern.compile("[^a-z']+", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);

    @Override
    protected Pattern getDelimiterPattern() {
        return delimiterPattern;
    }
}
