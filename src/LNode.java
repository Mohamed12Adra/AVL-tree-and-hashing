//linked list node class
public class LNode {
	//attributes
	private LNode next;
	private Object Data;
	//Constructors
	public LNode(Object data) {
		super();
		Data = data;
		next=null;
	}
	//setters and getters
	public LNode getNext() {
		return next;
	}
	public void setNext(LNode next) {
		this.next = next;
	}
	public Object getData() {
		return Data;
	}
	public void setData(Object data) {
		Data = data;
	}
	//overriding tostring method
	@Override
	public String toString() {
		return "LNode [Data=" + Data + "]";
	}
}
