package ivan;

import ivan.textsearch.WordAndContext;
import ivan.textsearch.WordsParser;
import ivan.textsearch.WordsSearch;
import ivan.textsearch.WordsStorage;
import ivan.textsearch.parser.EnglishTextParser;
import ivan.textsearch.search.QuantitySearch;
import ivan.textsearch.storage.QuantityStorage;
import ivan.textsearch.search.SearchResult;

import java.io.*;
import java.util.*;

public class Main {

    private final static String COMMAND_QUIT = ":quit";
    private final static int MAX_RESULTS = 10;

    private static void print(String format, Object ...args) {
        System.out.format(format, args);
    }

    public static void main(String[] args) {
        // arguments checking
        if (args.length == 0 ) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        final File indexableDirectory = new File(args[0]);
        if (!indexableDirectory.exists()) {
            throw new IllegalArgumentException("Directory given to index does not exist.");
        }

        // get list of text files
        File[] files = indexableDirectory.listFiles(file -> file.getName().endsWith(".txt"));
        if (files.length == 0) {
            throw new IllegalArgumentException("Directory given to index contains no text files.");
        } else {
            print("Found %d text files%n", files.length);
        }

        // create parser, which will be used to process contents of files and user commands
        WordsParser parser = new EnglishTextParser();

        // create words storage and process files
        WordsStorage storage = new QuantityStorage();
        for (File file : files) {
            try (InputStream is = new FileInputStream(file)) {
                parser.parseAll(is, wordAndContext -> storage.addWord(file.getName(), wordAndContext));
            } catch (IOException ex) {
                print("Error occurred while parsing file %s:%n%s%n", file.getName(), ex.getMessage());
            }
        }

        if (storage.getDifferentWords() == 0) {
            print("Processed files contain no words%n");
        } else {
            // start the prompt interface
            WordsSearch wordsSearch = new QuantitySearch();
            print("Type word of phrase to search or %s to exit%n", COMMAND_QUIT);
            Scanner keyboard = new Scanner(System.in);
            while (true) {
                print("search> ");
                String command = keyboard.nextLine();
                if (command.equals(COMMAND_QUIT)) {
                    break;
                } else {
                    // convert input into list of words using the same parser
                    List<WordAndContext> wordsToSearch = new ArrayList<>();
                    parser.parseAll(new ByteArrayInputStream(command.getBytes()), wordsToSearch::add);
                    if (wordsToSearch.size() == 0) {
                        print("Incorrect request%n");
                    } else {
                        List<SearchResult> results = wordsSearch.findAnyWords(storage, wordsToSearch);
                        if (results.size() == 0) {
                            print("No matches found%n");
                        } else {
                            // sort and limit results, and print them out
                            results.stream()
                                    .sorted()
                                    .limit(MAX_RESULTS)
                                    .forEach(r -> print("%4.0f%%: %s%n", r.getRelevance(), r.getSource()));
                        }
                    }
                }
            }
        }
    }
}
