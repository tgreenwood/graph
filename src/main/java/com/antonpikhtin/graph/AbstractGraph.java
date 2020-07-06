package com.antonpikhtin.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

abstract public class AbstractGraph implements Graph {

    private final static String VERTEX_DOES_NOT_EXIST = "There is no such a vertex in graph: %s";
    private final static String VERTEX_MUST_NOT_BE_NULL = "Vertex must not be null";

    private final Set<Vertex> vertices;

    public AbstractGraph() {
        this.vertices = new HashSet<>();
    }

    public void addVertex(Vertex vertex) {
        if (vertex == null) {
            throw new NullPointerException(VERTEX_MUST_NOT_BE_NULL);
        }
        this.vertices.add(vertex);
    }

    /**
     * Method to find an existing path from {@code sourceVertex} to {@code targetVertex}.
     * This method does not guarantee the only path as a result from run to run
     * if there are several options to go from one vertex to another.
     * @param sourceVertex to define path start
     * @param targetVertex to define path end
     * @return list of edges from the start vertex to the end one
     */
    public List<Edge> getAnyPath(Vertex sourceVertex, Vertex targetVertex) {
        assertVertexExists(sourceVertex);
        assertVertexExists(targetVertex);

        List<Edge> path = new ArrayList<>();
        Set<Vertex> visitedVertices = new HashSet<>();
        hasPath(sourceVertex, targetVertex, path, visitedVertices);

        return path;
    }

    protected void assertVertexExists(Vertex vertex) {
        if (!vertices.contains(vertex)) {
            throw new IllegalArgumentException(String.format(VERTEX_DOES_NOT_EXIST, vertex.getLabel()));
        }
    }

    protected Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    private boolean hasPath(Vertex currentVertex, Vertex targetVertex, List<Edge> path, Set<Vertex> visitedVertices) {
        if (!visitedVertices.contains(currentVertex)) {
            visitedVertices.add(currentVertex);
            if (currentVertex.equals(targetVertex)) {
                return true;
            }
            for (Edge edge: currentVertex.getAdjacentEdges()) {
                if (hasPath(edge.getTargetVertex(), targetVertex, path, visitedVertices)) {
                    path.add(0, edge);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractGraph that = (AbstractGraph) o;
        return Objects.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }
}
