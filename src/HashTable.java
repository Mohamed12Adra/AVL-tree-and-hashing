import java.util.Arrays;
//hash table class
public class HashTable {
	//attributes
	private int tableSize;
	private int currSize=0;
	private City[] x;
	//Constructors
	public HashTable(int tableSize) {
		super();
		this.tableSize = nextPrime(2*tableSize);
		x= new City[this.tableSize];
		for(int i=0; i<x.length; i++) {
			x[i]=null;
		}
	}
	
	public int getTableSize() {
		return tableSize;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = nextPrime(2*tableSize);
	}

	//method to return the hash of the string
	public int hash(String key, int hashSize) {
		int hashVal=0;
		int i=0;
		while(i<key.length()) {
			hashVal =(hashVal<<5)+key.charAt(i++);
		}
		return (hashVal%hashSize);
	}
	//method to return if the number is prime or not
	public boolean isPrime(int n) {
		if(n==2 || n==3) {
			return true;
		}
		if(n==1 || n%2==0) {
			return false;
		}
		for(int i=3; i*i<=n/2; i+=2) {
			if(n%i==0) {
				return false;
			}
		}
		return true;
	}
	//method to return the first nextprime
	public int nextPrime(int n) {
		if(n%2==0) {
			n++;
		}
		for(; !isPrime(n); n+=2) {
		
		}
		return n;
	}
	//method to rehash
	public void rehash() {
		HashTable list= new HashTable(nextPrime(2*tableSize));
		for(int i=0; i<x.length; i++) {
			if(x[i]!=null ) {
				list.addRecord(x[i]);
			}
		}
		x=list.x;
		tableSize =list.tableSize;
	}
	//method to add records
	public void addRecord(City g) {
		if(currSize>=tableSize/2) {
			rehash();
		}
		int x1 = hash(g.getCityName().trim().toLowerCase(),tableSize);
		int i=1;
		if(x1<0) {
			x1=x1*-1;
		}
		//quadratic proping
		while(x[x1]!=null && x[x1].getDelete()!=0 ) {
			x1 =(x1+(i*i))%tableSize;
			i+=1;
		}
		if(x[x1]==null) {
			currSize++;
			x[x1]=g;
		}else {
			System.out.println("collision");
		}
	}
	//method to return the size of the table
	public int returnSize() {
		return tableSize;
	}
	//method to return the hash table as a string
	public String returnHash() {
	
		return Arrays.toString(x);
	}
	//method for searching
	public boolean search(String x1) {
		int y = hash(x1,tableSize);
		int i=1;
		if(y<0) {
			y=y*-1;
		}
		while(x[y]!=null && x[y].getDelete()!=0 && x[y].getCityName().contains(x1)==false) {
			y=(y+(i*i))%tableSize;
		
		}
		if(x[y]==null) {
			return false;
		}
		if(x[y].getCityName().trim().toLowerCase().contains(x1)) {
			return true;
		}else {
			return false;
		}
	}
	//method to delete a record
	public void delete(City x1) {
		int y = hash(x1.getCityName().trim().toLowerCase(),tableSize);
		if(y<0) {
		y=y*(-1);
		}
		if(x[y].getCityName().trim().toLowerCase().contains(x1.getCityName().trim().toLowerCase())) {
			x[y]=null	;
		}else {
			int i=1;
			while(x[y]!=null && x[y]!=x1) {
				y=(y+(i*i))%tableSize;
				i++;
			}
				x[y]=null;
		}
	}
}
