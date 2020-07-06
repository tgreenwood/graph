package com.antonpikhtin.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.antonpikhtin.graph.GraphUtil.pathToString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UndirectedGraphTest {
    @Test
    public void testAddingNullVertexToUndirectedGraphThrowsException() {
        UndirectedGraph graph = new UndirectedGraph();

        assertThrows(NullPointerException.class, () -> graph.addVertex(null));
    }

    @Test
    public void testAddingNonNullVertexSucceed() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex root = new DefaultVertex("1");
        graph.addVertex(root);

        assertThat(graph.getVertices()).containsExactly(root);
    }

    @Test
    public void testAddingEdgeWithNonExistingTargetVertexToUndirectedGraphThrowsException() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex root = new DefaultVertex("1");
        graph.addVertex(root);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(root, new DefaultVertex("2")));
    }

    @Test
    public void testAddingValidEdgeSucceed() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex vertex1 = new DefaultVertex("1");
        DefaultVertex vertex2 = new DefaultVertex("2");
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);

        graph.addEdge(vertex1, vertex2);

        assertThat(vertex1.getAdjacentEdges()).hasSize(1);
        assertThat(vertex1.getAdjacentEdges())
                .extracting("targetVertex")
                .containsOnly(vertex2);
    }

    @Test
    public void testGetAnyPathSucceed() {
        //       |<--> 3
        // 1 <--> 2
        //       |<--> 4 <--> 5
        // given
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex v1 = new DefaultVertex("1");
        DefaultVertex v2 = new DefaultVertex("2");
        DefaultVertex v3 = new DefaultVertex("3");
        DefaultVertex v4 = new DefaultVertex("4");
        DefaultVertex v5 = new DefaultVertex("5");

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addVertex(v5);

        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v2, v4);
        graph.addEdge(v4, v5);

        //when
        List<Edge> path = graph.getAnyPath(v1, v5);

        //then
        assertThat(pathToString(path)).isEqualTo("2->4->5");
    }

    @Test
    public void testGetAnyPathFromNonExistingVertexThrowsException() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex root = new DefaultVertex("1");
        graph.addVertex(root);

        assertThrows(IllegalArgumentException.class, () -> graph.getAnyPath( new DefaultVertex("0"), root));
    }

    @Test
    public void testGetAnyPathToNonExistingVertexThrowsException() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex root = new DefaultVertex("1");
        graph.addVertex(root);

        assertThrows(IllegalArgumentException.class, () -> graph.getAnyPath(root, new DefaultVertex("2")));
    }

    @Test
    public void testGetNoPathOnGraphConsistingOfOneVertex() {
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex root = new DefaultVertex("1");
        graph.addVertex(root);

        List<Edge> path = graph.getAnyPath(root, root);

        Assertions.assertTrue(path.isEmpty());
    }

    @Test
    public void testGetPathOnGraphInBothDirections() {
        // 1 <--> 2 <--> 3
        UndirectedGraph graph = new UndirectedGraph();
        DefaultVertex vertex1 = new DefaultVertex("1");
        DefaultVertex vertex2 = new DefaultVertex("2");
        DefaultVertex vertex3 = new DefaultVertex("3");
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addEdge(vertex1, vertex2);
        graph.addEdge(vertex2, vertex3);

        List<Edge> path12 = graph.getAnyPath(vertex1, vertex3);
        assertThat(pathToString(path12)).isEqualTo("2->3");

        List<Edge> path21 = graph.getAnyPath(vertex3, vertex1);
        assertThat(pathToString(path21)).isEqualTo("2->1");
    }


}
