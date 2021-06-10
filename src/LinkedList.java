//linked list class
public class LinkedList {
	//attributes
	private LNode first;
	private LNode last;
	LNode previous;
	private int count=0;
	//Constructors
	public LinkedList() {
		first=null;
		last=null;
	}
	//method to return if the list is empty
	public boolean isEmpty() {
		return (first==null);
	}
	//method to insert new node
	public void insertNode(Object data) {
		LNode x = new LNode(data);
		LNode curr = first;
		if(first==null) {
			x.setNext(first);
			first=x;
			count+=1;
		}else {
			while(curr.getNext()!=null) {
				curr=curr.getNext();
			}
			x.setNext(curr.getNext());
			curr.setNext(x);
			count+=1;
		}
	}
	//method to return the number of nodes in the list
	public int getCount() {
		return count;
	}
	//method to delete the first element 
	public LNode deleteFirst() {
		if(first==null) {
			return null;
		}else {
			LNode temp=first;
			first=first.getNext();
			count-=1;
			return temp;
		}
	}
	//method to delete an element using a key
	public LNode deleteByKey(Object data) {
		if(first.getData()==data) {
			return deleteFirst();
		}else {
			LNode temp=first;
			while(temp.getNext()!=null && temp.getData()!=data) {
				previous = temp;
				temp=temp.getNext();
			}
			if(temp!=null) {
				previous.setNext(temp.getNext());
				count-=1;
			}
			return temp;
		}
	}
	//method to search using an index
	public LNode Search(int index) {
		LNode n = first;
		if(index>this.getCount() || index<0) {
			return null;
		}else {
			for(int i=0; i<index; i++) {
				n=n.getNext();
			}
			return n;
		}
	}

}
