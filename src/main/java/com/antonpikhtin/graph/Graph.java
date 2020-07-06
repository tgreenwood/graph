package com.antonpikhtin.graph;

import java.util.List;

public interface Graph {

    void addVertex(Vertex vertex);
    void addEdge(Vertex sourceVertex, Vertex targetVertex);
    List<Edge> getAnyPath(Vertex sourceVertex, Vertex targetVertex);

}
