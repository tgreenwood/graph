package com.antonpikhtin.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultVertexTest {

    @Test
    public void testCreatingDefaultVertexWithNullLabelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> new DefaultVertex(null));
    }

    @Test
    public void testAddingEdgeWithNullTargetVertexThrowsException() {
        DefaultVertex vertex = new DefaultVertex("1");

        Assertions.assertThrows(NullPointerException.class, () -> vertex.addEdge(null));
    }

    @Test
    public void testDirectModifyingSetOfEdgesBelongingToVertexThrowsException() {
        DefaultVertex vertex = new DefaultVertex("1");

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> vertex.getAdjacentEdges().add(new DefaultEdge(new DefaultVertex("2"))));
    }
}
