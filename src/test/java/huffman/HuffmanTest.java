package test.java.huffman;

import main.java.huffman.HuffmanCoding;
import main.java.huffman.TreeNode;
import org.junit.jupiter.api.Test;
import test.java.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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
        assertEquals("TreeNode{frequency=6, text='null', left=TreeNode{frequency=4, text='null', left=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='f', left=null, right=null}, right=TreeNode{frequency=1, text='d', left=null, right=null}}, right=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='c', left=null, right=null}, right=TreeNode{frequency=1, text='a', left=null, right=null}}}, right=TreeNode{frequency=2, text='null', left=TreeNode{frequency=1, text='e', left=null, right=null}, right=TreeNode{frequency=1, text='b', left=null, right=null}}}",
                tree.toString());
    }

    @Test
    public void testHuffmanMapSimple01() {
        TreeNode tree = HuffmanCoding.generateTree("abbcccddddeeeee");
        assertEquals("{a=101, b=100, c=11, d=01, e=00}", HuffmanCoding.generateTreeMap(tree).toString());
    }

    @Test
    public void testHuffmanMapSimple02() {
        TreeNode tree = HuffmanCoding.generateTree("ababaacabadeadb");
        assertEquals("{a=1, b=01, c=0001, d=001, e=0000}", HuffmanCoding.generateTreeMap(tree).toString());
    }

    @Test
    public void testHuffmanMapSimple03() {
        TreeNode tree = HuffmanCoding.generateTree("abcdef");
        assertEquals("{a=001, b=000, c=11, d=10, e=011, f=010}", HuffmanCoding.generateTreeMap(tree).toString());
    }

    @Test
    public void testHuffmanEncodeSimple01() {
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode("aaabbbbbbb");
        assertEquals("aaabbbbbbb", coder.decode(encoded));
        assertEquals(10, encoded.length());
    }

    @Test
    public void testHuffmanEncodeSimple02() {
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode("asdasdhsadhpasdfhasdfjasdouewrulfjasdupasdyasyd");
        assertEquals("asdasdhsadhpasdfhasdfjasdouewrulfjasdupasdyasyd", coder.decode(encoded));
        assertEquals(158 , encoded.length());
    }

    @Test
    public void testHuffmanEncodeSimple03() {
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode("Pe3mxlLK52zuxHxuVReWkhxhKRTfG8ycXp1XuizbU9sVQCanGPYYfjE5wkSpvNGMeCwiD8i5U2kfVLRsT0kOBeBl5uvgMf1NyGs1x1xfEuJF6jbbixkm9Ybu2wf9KDT2hImwghWt2QzNixYMfeZQtQrQMU98su0fw8Y1dKiPf0vbVw4rhKxKcqWPLGyZXXUuZ45Iaa31");
        assertEquals("Pe3mxlLK52zuxHxuVReWkhxhKRTfG8ycXp1XuizbU9sVQCanGPYYfjE5wkSpvNGMeCwiD8i5U2kfVLRsT0kOBeBl5uvgMf1NyGs1x1xfEuJF6jbbixkm9Ybu2wf9KDT2hImwghWt2QzNixYMfeZQtQrQMU98su0fw8Y1dKiPf0vbVw4rhKxKcqWPLGyZXXUuZ45Iaa31", coder.decode(encoded));
        assertEquals(1139, encoded.length());
    }

    @Test
    public void testHuffmanEncodeApollo() throws IOException {
        String data = Util.readString("data/apollo.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(25085388, encoded.length());
    }

    @Test
    public void testHuffmanEncodeLenna() throws IOException {
        String data = Util.readString("data/lenna.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(1240298, encoded.length());
    }

    @Test
    public void testHuffmanEncodePi() throws IOException {
        String data = Util.readString("data/pi.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(3549063, encoded.length());
    }

    @Test
    public void testHuffmanEncodeTaisho() throws IOException {
        String data = Util.readString("data/taisho.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(12341254, encoded.length());
    }

    @Test
    public void testHuffmanEncodeWarAndPeace() throws IOException {
        String data = Util.readString("data/war_and_peace.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(14788787, encoded.length());
    }

    @Test
    public void testHuffmanEncodeAsciiGarbage() throws IOException {
        String data = Util.readString("data/asciigarbage.txt");
        HuffmanCoding coder = new HuffmanCoding();
        String encoded = coder.encode(data);
        assertEquals(data, coder.decode(encoded));
        assertEquals(5983068, encoded.length());
    }
}
