package main.java.huffman;

public class TreeNode implements Comparable<TreeNode> {
    public final int frequency;
    public final String text;
    public final TreeNode left, right;
    public final int order; // Used to ensure order is always the same for nodes with the same frequency.
    private int depth = 0;

    private static int currentOrder = 0;

    public TreeNode(String text, int frequency) {
        this.frequency = frequency;
        this.text = text;
        this.left = null;
        this.right = null;
        this.order = currentOrder++;
    }

    public TreeNode(TreeNode left, TreeNode right, int frequency) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.text = null;
        this.order = currentOrder++;
    }

    public boolean isLeaf() {
        return this.text != null;
    }

    @Override
    public int compareTo(TreeNode o) {
        if (frequency < o.frequency)
            return -1;
        else if (frequency > o.frequency)
            return 1;

        return 0;
    }

    // Return the recursive width of the tree node and its children
    public int getWidth() {
        if(isLeaf())
            return 2;
        return left.getWidth() + right.getWidth();
    }

    public int getDepth() {
        return depth;
    }

    private void setDepth(int depth) {
        this.depth = depth;
        if(!isLeaf()) {
            left.setDepth(depth + 1);
            right.setDepth(depth + 1);
        }
    }

    public void setRoot() {
        setDepth(0);
    }

    public String getTextRepresentation() {
        if(isLeaf())
            return text.replace("\n", "\\n");

        return left.getTextRepresentation() + right.getTextRepresentation();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "frequency=" + frequency +
                ", text='" + text + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
