import java.util.*;

public class HuffmanTreeGenerator {

    static class TreeNode implements Comparable<TreeNode> {
        char symbol;
        int weight;
        TreeNode leftChild, rightChild;

        public TreeNode(char symbol, int weight) {
            this.symbol = symbol;
            this.weight = weight;
        }

        @Override
        public int compareTo(TreeNode other) {
            return this.weight - other.weight;
        }
    }

    public static Map<Character, String> createHuffmanCodes(TreeNode root) {
        Map<Character, String> codeMap = new HashMap<>();
        buildCodeMap(root, "", codeMap);
        return codeMap;
    }

    private static void buildCodeMap(TreeNode node, String currentCode, Map<Character, String> codeMap) {
        if (node == null) return;
        if (node.leftChild == null && node.rightChild == null) { // Check if it's a leaf node
            codeMap.put(node.symbol, currentCode);
        }
        buildCodeMap(node.leftChild, currentCode + "0", codeMap);
        buildCodeMap(node.rightChild, currentCode + "1", codeMap);
    }

    public static TreeNode constructHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<TreeNode> queue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.add(new TreeNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            TreeNode combinedNode = new TreeNode('\0', leftNode.weight + rightNode.weight);
            combinedNode.leftChild = leftNode;
            combinedNode.rightChild = rightNode;
            queue.add(combinedNode);
        }

        return queue.poll();
    }

    public static void main(String[] args) {
        String inputText = "create a huffman tree";
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char character : inputText.toCharArray()) {
            frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
        }

        TreeNode treeRoot = constructHuffmanTree(frequencyMap);
        Map<Character, String> huffmanCodes = createHuffmanCodes(treeRoot);

        // Display the Huffman codes
        System.out.println("Generated Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Encode the input string using the generated codes
        StringBuilder encodedString = new StringBuilder();
        for (char character : inputText.toCharArray()) {
            encodedString.append(huffmanCodes.get(character));
        }
        System.out.println("\nEncoded Text: " + encodedString);

        // Decode the encoded string
        StringBuilder decodedString = new StringBuilder();
        TreeNode currentNode = treeRoot;
        for (char bit : encodedString.toString().toCharArray()) {
            currentNode = bit == '0' ? currentNode.leftChild : currentNode.rightChild;
            if (currentNode.leftChild == null && currentNode.rightChild == null) { // Leaf node check
                decodedString.append(currentNode.symbol);
                currentNode = treeRoot;
            }
        }
        System.out.println("\nDecoded Text: " + decodedString);
    }
}
