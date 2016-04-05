class Vehicle {
 
	int passengers; //number of passengers
	int fuelcap; //fuel capacity in gallons
	int mpg; //fuel consumption in miles per gallon
	
	//Display the range.
	double range(){
		return (double) fuelcap*mpg;
	}
		
	//Compute fuel needed for a given distance
	double fuelneeded(int miles){
		return (double) miles/mpg;
	}
}
