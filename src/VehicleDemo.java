class VehicleDemo {
	public static void main(String args[])
	{
		Vehicle minivan = new Vehicle();
		Vehicle sportcar = new Vehicle();
		int dist = 252;
		
					
		//asign values to fiels in minivan	
		minivan.passengers = 7;	
		minivan.fuelcap = 16;
		minivan.mpg = 21;
		
		//asign values to fiels in minivan	
		sportcar.passengers = 2;
		sportcar.fuelcap = 14;
		sportcar.mpg = 12;
		
		//compute the range assuming a full tank of gas
		System.out.print("Minivan can carry "+minivan.passengers+" with a range of "+ minivan.range()+" miles, ");
		System.out.println("and to go "+dist+" miles minivan needs "+ minivan.fuelneeded(dist)+" gallons of fuel.");
		System.out.print("Sportcar can carry "+sportcar.passengers+" with a range of "+ sportcar.range()+" milees, ");
		System.out.println("and to go "+dist+" miles sportcar needs "+ sportcar.fuelneeded(dist)+" gallons of fuel.");
		
	}
}
