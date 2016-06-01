class Block {
	private int a,b,c;
	private int volumn;
	
	Block(int i, int j, int k){
		a = i;
		b = j;
		c = k;
		volumn = a*b*c;
	}
	
	//Return true if ob defines same block
	boolean sameBlock(Block ob){
		if((ob.a == a) & (ob.b == b) & (ob.c == c))
			return true;
		else
			return false;
	}
	
	//Return true if ob has same volume
	boolean sameVolumn(Block ob){
		if(ob.volumn == volumn)
			return true;
		else
			return false;
	}
}


class PassOb {
	public static void main(String args[]){
			Block ob1 = new Block(10,2,5);
			Block ob2 = new Block(10,2,5);
			Block ob3 = new Block(5,4,5);
			
			System.out.println("ob1 same dimensions as ob2: " + ob1.sameBlock(ob2));
			System.out.println("ob1 same dimensions as ob3: " + ob1.sameBlock(ob3));
			System.out.println("ob1 same volumn as ob3: " + ob1.sameVolumn(ob3));
	}
}
