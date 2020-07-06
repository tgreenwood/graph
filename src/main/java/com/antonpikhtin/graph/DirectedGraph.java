package com.antonpikhtin.graph;

public class DirectedGraph extends AbstractGraph {

    @Override
    public void addEdge(Vertex sourceVertex, Vertex targetVertex) {
        assertVertexExists(targetVertex);
        assertVertexExists(sourceVertex);

        sourceVertex.addEdge(targetVertex);
    }
}
