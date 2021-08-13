

package io.github.treech.ui.widget.imageview.crop;

/**
 * Simple class to hold a pair of Edges.
 */
public class EdgePair {

    // Member Variables ////////////////////////////////////////////////////////

    public Edge primary;
    public Edge secondary;

    // Constructor /////////////////////////////////////////////////////////////

    public EdgePair(Edge edge1, Edge edge2) {
        primary = edge1;
        secondary = edge2;
    }
}
