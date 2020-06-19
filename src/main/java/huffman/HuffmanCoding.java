package main.java.huffman;

import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	TreeNode tree;
	Map<String, String> encodingMap;

	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding() {

	}

	public static TreeNode generateTree(String text) {
		Map<String, Long> frequencies = Arrays.stream(text.split("")).collect(Collectors.groupingBy(s -> s, Collectors.counting()));

		PriorityQueue<TreeNode> queue = new PriorityQueue<>();
		queue.addAll(frequencies.entrySet().stream().map(x -> new TreeNode(x.getKey(), x.getValue().intValue())).collect(Collectors.toSet()));

		while (queue.size() > 1) {
			TreeNode right = queue.poll();
			TreeNode left = queue.poll();
			queue.offer(new TreeNode(left, right, right.frequency + left.frequency));
		}

		return queue.poll();
	}

	public static Map<String, String> generateTreeMap(TreeNode tree) {
		Map<String, String> map = new HashMap<>();
		Stack<Pair<TreeNode, String>> fringe = new Stack<>();
		fringe.add(new Pair(tree, ""));

		while (!fringe.isEmpty()) {
			Pair<TreeNode, String> pair = fringe.pop();
			TreeNode node = pair.getKey();
			String path = pair.getValue();

			if(node.isLeaf()) {
				map.put(node.text, path);
			} else {
				fringe.push(new Pair(node.left, path + "0"));
				fringe.push(new Pair(node.right, path + "1"));
			}
		}

		return map;
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		tree = generateTree(text);
		encodingMap = generateTreeMap(tree);
		return Arrays.stream(text.split("")).map(encodingMap::get).collect(Collectors.joining());
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder builder = new StringBuilder();
		TreeNode node = tree;
		for(String s : encoded.split("")) {
			if(s.equals("0"))
				node = node.left;
			else if(s.equals("1"))
				node = node.right;
			else
				throw new IllegalArgumentException("Encoded string contains illegal characters. Only 1 or 0 allowed.");

			if(node.isLeaf()) {
				builder.append(node.text);
				node = tree;
			}
		}
		return builder.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't wan to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
