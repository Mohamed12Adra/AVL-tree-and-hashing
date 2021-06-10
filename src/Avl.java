import java.util.ArrayList;

public class Avl {
	//Encapsulated inner class
	private class AVLNode {
		Country data; // Data in the node
		AVLNode left; // Left child
		AVLNode right; // right child
		int height; // Height
		// Constructors
		public AVLNode (Country d)
		{
		this(d, null, null);
		}
		public AVLNode (Country d, AVLNode lt, AVLNode rt)
		{
		data = d;
		left = lt;
		right = rt;
		height = 0;
		}
		
	}
	//Attributes
	private ArrayList<Country> x;
	private AVLNode root; // The tree root
	//Constructors
	public Avl( ) {
		root = null;
		x= new ArrayList<>();
	}
	//method to make the tree empty
	public void makeEmpty( ) {
		root = null;
	}
	//method to return true or false if the tree is empty
	public boolean isEmpty( ) {
		return root == null;
	}
	//method to return the root
	public AVLNode getRoot() {
		return root;
	}
	//method to change the root
	public void setRoot(AVLNode root) {
		this.root = root;
	}
	//method to print tree
	public void Tree( ) {
		if( !isEmpty( ) )
			printTree( root );

	}
	//recursive printing (inorder)
	private void printTree( AVLNode t ) {
		if( t != null ) {
			printTree( t.left );
			x.add(t.data);
			printTree( t.right );
		}
	}
	//overriding tostring method
	@Override
	public String toString() {
		return x.toString();
	}
	public ArrayList getCountries(){
		return x;
	}
	
	public void deleteX() {
		x.clear();
	}
	//method to insert nodes to tree
	public void insert( Country x ) {
		root = insert( x, root );
	}
	//recursive inserting
	private AVLNode insert( Country x, AVLNode t ) {
		if( t == null )
			return new AVLNode( x, null, null );
		if( x.getCountryName().compareToIgnoreCase(t.data.getCountryName())<0 )
		{
			t.left = insert( x, t.left );
			//balancing
			if( height( t.left ) - height( t.right ) == 2 )
				if( x.getCountryName().compareToIgnoreCase(t.left.data.getCountryName())<0 )
					//right right case
					t = rotateLeft( t );
				else
					//right left case
					t = doubleLeft( t );

		}
		else if( x.getCountryName().compareToIgnoreCase(t.data.getCountryName())>0 )
		{
			t.right = insert( x, t.right );
			//balancing
			if( height( t.right ) - height( t.left ) == 2 )
				if( x.getCountryName().compareToIgnoreCase(t.right.data.getCountryName())>0 )
					//left left case
					t = rotateRight( t );
				else
					//left right case
					t = doubleRight( t );

		}
		else
	; // Duplicate; do nothing
		//update the heights
		t.height = Math.max( height( t.left ), height( t.right )) + 1;
		return t;
	}
	//search method to search for a specific node
	public boolean search( Country x ) {
		return search( x, root );
	}
	//recursive searching
	private boolean search( Country x, AVLNode t ) {
		while( t != null )
		{
			if( x.getCountryName().compareToIgnoreCase(t.data.getCountryName()) < 0 )
				t = t.left;
			else
				if( x.getCountryName().compareToIgnoreCase(t.data.getCountryName()) > 0 )
					t = t.right;
				else
					return true; // Match
		}
		return false;
	}
	//return the height of a specific node
	private int height( AVLNode t ) {
		if( t == null )
			return -1;
		else
			return t.height;

	}
	//rotate left method 
	private AVLNode rotateLeft( AVLNode node2 ) {
		AVLNode node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		node2.height = Math.max(height(node2.left),height(node2.right))+1;
		node1.height = Math.max(height(node1.left), node2.height)+1;
		return node1;
	}
	//rotate right method
	private AVLNode rotateRight( AVLNode node1 ) {
		AVLNode node2 = node1.right;
		node1.right = node2.left;
		node2.left = node1;
		node1.height = Math.max(height(node1.left),height(node1.right))+1;
		node2.height = Math.max(height(node2.right), node1.height)+1;
		return node2;
	}
	//double left method
	private AVLNode doubleLeft( AVLNode node3 ) {
		node3.left = rotateRight( node3.left );
		return rotateLeft( node3 );
	}
	//double right method
	private AVLNode doubleRight( AVLNode node1 ) {
		node1.right = rotateLeft( node1.right );
		return rotateRight( node1 );
	}
	//method to return the minimum element in the tree
	public AVLNode findmin() {
		return findminRec(root);
	}
	//find minimum recursively
	private AVLNode findminRec(AVLNode T) {
		if(T==null)
			return null;
		else
			if(T.left==null)
				return T;
			else
				return findminRec(T.left);
	}
	//method to delete specific node
	public AVLNode delete(Country x) {
		return deleteRec(root,x);
	}
	//deleting recursively
	private AVLNode deleteRec(AVLNode T,Country x) {
		AVLNode temp=null,child=null;
		if(T==null) {
			return null;
		}else
			if(x.getCountryName().compareToIgnoreCase(T.data.getCountryName())<0) {
				T.left=deleteRec(T.left,x);
				//balancing
		if( height( T.left ) - height( T.right ) == 2 )
			//right right case
			if( x.getCountryName().compareToIgnoreCase(T.left.data.getCountryName())<0 )
				T = rotateLeft( T );
			else
				//right left case
				T = doubleLeft( T );
			}
			else
				if(x.getCountryName().compareToIgnoreCase(T.data.getCountryName())>0) {
					T.right=deleteRec(T.right,x);
					//balancing,left left case
					if( height( T.right ) - height( T.left ) == 2 )
						if( x.getCountryName().compareToIgnoreCase(T.right.data.getCountryName())>0 )
							T = rotateRight( T );
					//balancing,left right case	
						else
							T = doubleRight( T );
				}
				//2 childs case
				else 
					if(T.left!=null && T.right!=null) {
						temp=findminRec(T.right);
						T.data=temp.data;
						T.right=deleteRec(T.right,temp.data);
					}else {
						//1 child case or no child case
						if (T.right==null)
							child = T.left;
						if(T.left==null)
							child=T.right;
						
						return child;
					}
		return T;
	}
	//method to return the height of the whole tree
	public int calculateHeightTree(AVLNode T) {
		if(T==null)
			return -1;
		else
			return (Math.max(calculateHeightTree(T.left), calculateHeightTree(T.right))+1);
	}
}
