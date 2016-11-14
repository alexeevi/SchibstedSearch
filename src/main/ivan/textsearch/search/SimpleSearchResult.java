package ivan.textsearch.search;

import ivan.textsearch.WordAndContext;

import java.util.Collection;

/**
 * Container of search results.
 * Additionally contains collection of particular words which were found.
 */

class SimpleSearchResult extends SearchResult {

    private Collection<WordAndContext> found;

    public Collection<WordAndContext> getFound() {
        return found;
    }

    public void setFound(Collection<WordAndContext> found) {
        this.found = found;
    }
}
