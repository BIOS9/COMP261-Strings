package test.java.huffman;

import main.java.huffman.HuffmanCoding;
import main.java.huffman.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HuffmanTest {
    @Test
    public void testHuffmanTreeSimple01() {
        TreeNode tree = HuffmanCoding.generateTree("abbcccddddeeeee");
        assertEquals("TreeNode{frequency=15, text='null', left=TreeNode{frequency=9, text='null', left=TreeNode{frequency=5, text='e', left=null, right=null}, right=TreeNode{frequency=4, text='d', left=null, right=null}}, right=TreeNode{frequency=6, text='null', left=TreeNode{frequency=3, text='null', left=TreeNode{frequency=2, text='b', left=null, right=null}, right=TreeNode{frequency=1, text='a', left=null, right=null}}, right=TreeNode{frequency=3, text='c', left=null, right=null}}}",
                tree.toString());
    }

    @Test
    public void testHuffmanTreeSimple02() {
        TreeNode tree = HuffmanCoding.generateTree("ababaacabadeadb");
        assertEquals("TreeNode{frequency=15, text='null', left=TreeNode{frequency=8, text='null', left=TreeNode{frequency=4, text='null', left=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='e', left=null, right=null}, right=TreeNode{frequency=1, text='c', left=null, right=null}}, right=TreeNode{frequency=2, text='d', left=null, right=null}}, right=TreeNode{frequency=4, text='b', left=null, right=null}}, right=TreeNode{frequency=7, text='a', left=null, right=null}}",
                tree.toString());
    }

    @Test
    public void testHuffmanTreeSimple03() {
        TreeNode tree = HuffmanCoding.generateTree("abcdef");
        assertEquals("TreeNode{frequency=6, text='null', left=TreeNode{frequency=4, text='null', left=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='f', left=null, right=null}, right=TreeNode{frequency=1, text='e', left=null, right=null}}, right=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='b', left=null, right=null}, right=TreeNode{frequency=1, text='a', left=null, right=null}}}, right=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='d', left=null, right=null}, right=TreeNode{frequency=1, text='c', left=null, right=null}}}",
                tree.toString());
    }
}
