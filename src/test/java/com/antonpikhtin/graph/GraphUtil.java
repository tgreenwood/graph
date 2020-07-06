package com.antonpikhtin.graph;

import java.util.List;
import java.util.stream.Collectors;

final public class GraphUtil {
    private GraphUtil() {}

    /**
     * Method converts {@code List<Edge>} to a string representation (labels an arrow separated, for instance'1->2->3')
     * @param edges the path consists of
     * @return string representation of path
     */
    public static String pathToString(List<Edge> edges) {
        return edges.stream()
                .map(e -> e.getTargetVertex().getLabel())
                .collect( Collectors.joining( "->" ) );
    }
}
