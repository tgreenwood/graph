package com.antonpikhtin.graph;

public class UndirectedGraph extends AbstractGraph {

    @Override
    public void addEdge(Vertex sourceVertex, Vertex targetVertex) {
        assertVertexExists(targetVertex);
        assertVertexExists(sourceVertex);

        targetVertex.addEdge(sourceVertex);
        sourceVertex.addEdge(targetVertex);
    }
}
