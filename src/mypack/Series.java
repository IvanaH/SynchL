package mypack;

interface Series {
	int getNext();
	void reset();
	void setStart(int x);
	
	// Return an array that contains the next n elements in the series
	// beyond the current elements
	default int[] getNextArray(int n){ //new
		int [] vals = new int[n];
		
		for(int i = 0; i< n;i++)
			vals[i] = getNext();
		
		return vals;
	}	
}
