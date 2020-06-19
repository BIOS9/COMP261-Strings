package main.java.huffman;

public class TreeNode implements Comparable<TreeNode> {
    public final int frequency;
    public final String text;
    public final TreeNode left, right;

    public TreeNode(String text, int frequency) {
        this.frequency = frequency;
        this.text = text;
        this.left = null;
        this.right = null;
    }

    public TreeNode(TreeNode left, TreeNode right, int frequency) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
        this.text = null;
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
