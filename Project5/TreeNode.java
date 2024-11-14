import org.w3c.dom.Node;

public class TreeNode<E> {
	private E data;
	protected TreeNode<E> left;
	protected TreeNode<E> right;
	
	public TreeNode() 
	{
		this.data = null;
		this.right = null; 
		this.left = null;
	}
	
	public TreeNode(E data) 
	{
		this.data = data;
		this.right = null; 
		this.left = null;
	}
	
	public TreeNode(TreeNode<E> node) {
		this(node.left,node.right,node.getData());
	}
	
	public TreeNode(TreeNode<E> left,TreeNode<E> right,E data) {		
		this.data= data;
		this.left=new TreeNode(left);
		this.right=new TreeNode(right);
	}
	
	public E getData() {
		return data;
	}

}
