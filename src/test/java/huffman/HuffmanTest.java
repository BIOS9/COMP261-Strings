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

    @Test
    public void testHuffmanMapSimple01() {
        TreeNode tree = HuffmanCoding.generateTree("abbcccddddeeeee");
        assertEquals("{a=101, b=100, c=11, d=01, e=00}", HuffmanCoding.createTreeMap(tree).toString());
    }

    @Test
    public void testHuffmanMapSimple02() {
        TreeNode tree = HuffmanCoding.generateTree("ababaacabadeadb");
        assertEquals("{a=1, b=01, c=0001, d=001, e=0000}", HuffmanCoding.createTreeMap(tree).toString());
    }

    @Test
    public void testHuffmanMapSimple03() {
        TreeNode tree = HuffmanCoding.generateTree("abcdef");
        assertEquals("{a=011, b=010, c=11, d=10, e=001, f=000}", HuffmanCoding.createTreeMap(tree).toString());
    }
}
