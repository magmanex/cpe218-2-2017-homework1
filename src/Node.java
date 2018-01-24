public class Node {
    char value_node;
    Node left;
    Node right;

    Node(){
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return Character.toString(value_node);
    }
}
