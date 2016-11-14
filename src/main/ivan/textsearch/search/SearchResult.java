package ivan.textsearch.search;

/**
 * Container of search result with source name and relevance.
 * Implements default descending-relevance comparator.
 */

public abstract class SearchResult implements Comparable {

    private String source;
    private Double relevance;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getRelevance() {
        return relevance;
    }

    public void setRelevance(Double relevance) {
        this.relevance = relevance;
    }

    @Override
    public int compareTo(Object o) {
        SearchResult that = (SearchResult) o;
        return -this.getRelevance().compareTo(that.getRelevance());
    }
}
