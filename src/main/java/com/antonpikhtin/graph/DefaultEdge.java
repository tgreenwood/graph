package com.antonpikhtin.graph;

import java.util.Objects;

public class DefaultEdge implements Edge {

    private final Vertex targetVertex;

    public DefaultEdge(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    @Override
    public Vertex getTargetVertex() {
        return targetVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DefaultEdge that = (DefaultEdge) o;
        return Objects.equals(targetVertex, that.targetVertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetVertex);
    }

}
