import java.util.ArrayList;

/**
 * MorseCodeTree class that builds a binary tree representing Morse code
 * conversion. Implements LinkedConverterTreeInterface for operations on
 * the tree.
 * 
 * Author: Naol Gobena
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

    // Root node of the MorseCodeTree
    private TreeNode<String> root;

    /**
     * Default constructor for MorseCodeTree class. Initializes the root and
     * builds the Morse code tree.
     */
    public MorseCodeTree() {
        root = null;
        buildTree();
    }

    /**
     * Returns a reference to the root.
     * @return reference to root node.
     */
    @Override
    public TreeNode<String> getRoot() {
        return root;
    }

    /**
     * Sets the root of the tree.
     * @param newNode a TreeNode<T> that will be the new root.
     */
    @Override
    public void setRoot(TreeNode<String> newNode) {
        root = new TreeNode<>(newNode);
    }

    /**
     * Adds a result to the correct position in the tree based on the code.
     * Calls the recursive addNode method.
     * 
     * @param code the code for the new node to be added.
     * @param result the data to store in the new node.
     */
    @Override
    public void insert(String code, String result) {
        if (root == null) {
            root = new TreeNode<>(result);
        } else {
            addNode(root, code, result);
        }
    }

    /**
     * Recursively adds an element to the correct position in the tree based on the code.
     * 
     * @param root the root of the tree for this particular recursive instance of addNode.
     * @param code the code for this particular recursive instance of addNode.
     * @param letter the data of the new TreeNode to be added.
     */
    @Override
    public void addNode(TreeNode<String> root, String code, String letter) {
        if (code.length() > 1) {
            if (code.charAt(0) == '-') {
                addNode(root.right, code.substring(1), letter);
            } else {
                addNode(root.left, code.substring(1), letter);
            }
        } else {
            if (code.equals(".")) {
                root.left = new TreeNode<>(letter);
            } else {
                root.right = new TreeNode<>(letter);
            }
        }
    }

    /**
     * Fetches the data in the tree based on the code. Calls the recursive
     * fetchNode method.
     * 
     * @param code the code that describes the traversals within the tree.
     * @return the result that corresponds to the code.
     */
    @Override
    public String fetch(String code) {
        return fetchNode(root, code);
    }

    /**
     * Recursively fetches the data of the TreeNode that corresponds with the code.
     * 
     * @param root the root of the tree for this particular recursive instance of fetchNode.
     * @param code the code for this particular recursive instance of fetchNode.
     * @return the data corresponding to the code.
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code) {
        if (code.length() > 1) {
            if (code.charAt(0) == '.') {
                return fetchNode(root.left, code.substring(1));
            } else {
                return fetchNode(root.right, code.substring(1));
            }
        } else {
            return code.equals(".") ? root.left.getData() : root.right.getData();
        }
    }

    /**
     * This operation is not supported for a LinkedConverterTree.
     * @param data data of node to be deleted.
     * @return reference to the current tree.
     * @throws UnsupportedOperationException if the operation is called.
     */
    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * This operation is not supported for a LinkedConverterTree.
     * @return reference to the current tree.
     * @throws UnsupportedOperationException if the operation is called.
     */
    @Override
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * Builds the Morse code tree by inserting TreeNodes into their proper locations.
     */
    @Override
    public void buildTree() {
        // Root level
        insert("", "");
        // Level 1
        insert(".", "e");
        insert("-", "t");
        // Level 2
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        // Level 3
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        // Level 4
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    /**
     * Returns an ArrayList of the items in the linked converter tree in LNR (Inorder)
     * traversal order. Used for testing to make sure the tree is built correctly.
     * 
     * @return an ArrayList of the items in the linked tree.
     */
    @Override
    public ArrayList<String> toArrayList() {
        ArrayList<String> result = new ArrayList<>();
        LNRoutputTraversal(root, result);
        return result;
    }

    /**
     * The recursive method to put the contents of the linked converter tree in an
     * ArrayList in LNR (Inorder) order.
     * 
     * @param root the root of the tree for this particular recursive instance.
     * @param list the ArrayList that will hold the contents of the tree in LNR order.
     */
    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
        if (root != null) {
            if (root.left != null) {
                LNRoutputTraversal(root.left, list);
            }
            list.add(root.getData() + " ");
            if (root.right != null) {
                LNRoutputTraversal(root.right, list);
            }
        }
    }
}
