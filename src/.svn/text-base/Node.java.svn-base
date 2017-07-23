import utilities.Reaction;

import java.util.HashMap;

/**
 * Created by Alexander, Gabriel, Marc & Marcus on 02/03/16.
 */
public class Node implements Comparable<Node> {

    // Fields
    HashMap<String, Integer> state = new HashMap<>();
    Node parent;
    Reaction reaction;
    int pathCost;
    int depth;

    // Constructor if node is defined without parent.
    public Node(HashMap state, Reaction reaction, int pathCost, int depth) {
        this.state = state;
        this.parent = null;
        this.reaction = reaction;
        this.pathCost = pathCost;
        this.depth = depth;
    }

    // Constructor if node is defined with parent.
    public Node(HashMap state, Node parent, Reaction reaction, int pathCost, int depth) {
        this.state = state;
        this.parent = parent;
        this.reaction = reaction;
        this.pathCost = pathCost;
        this.depth = depth;
    }

    // Compares path cost of two nodes.
    @Override
    public int compareTo(Node node) {
        if (this.pathCost < node.pathCost) return -1;
        if (this.pathCost > node.pathCost) return 1;
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    // Compares if two states are equal.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }
}
