package com.antonpikhtin.graph;

import java.util.Set;

public interface Vertex {

    String getLabel();
    Set<Edge> getAdjacentEdges();
    void addEdge(Vertex targetVertex);

}
