package main.java.huffman;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
	/**
	 * This would be a good place to compute and store the tree.
	 */
	public HuffmanCoding(String text) {
		// TODO fill this in.
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

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
		// TODO fill this in.
		return "";
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		// TODO fill this in.
		return "";
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
