package com.antonpikhtin.graph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class describes simple labeled vertex. Label is supposed to be unique in a graph.
 */
public class DefaultVertex implements Vertex {

    private final static String LABEL_MUST_NOT_BE_NULL = "Label must not be null";
    private final static String TARGET_VERTEX_MUST_NOT_BE_NULL = "Target vertex must not be null";

    private final String label;
    private final Set<Edge> adjacentEdges;

    public DefaultVertex(String label) {
        if (label == null) {
            throw new NullPointerException(LABEL_MUST_NOT_BE_NULL);
        }
        this.label = label;
        this.adjacentEdges = new HashSet<>();
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Set<Edge> getAdjacentEdges() {
        return Collections.unmodifiableSet(adjacentEdges);
    }

    @Override
    public void addEdge(Vertex targetVertex) {
        if (targetVertex == null) {
            throw new NullPointerException(TARGET_VERTEX_MUST_NOT_BE_NULL);
        }
        adjacentEdges.add(new DefaultEdge(targetVertex));
    }

    /**
     * Equality method based on labels comparison.
     * @param o to compare with current vertex
     * @return {@code true} if vertices are of the same type and labels or references are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultVertex that = (DefaultVertex) o;
        return label.equals(that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
}
