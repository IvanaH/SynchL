package thinkingIJ;

class LightBulb {
	public static void main(String[] args) {
		Light lg = new Light();
		
		lg.getLightInfo();
		lg.on();
		lg.brighten();
		lg.dim();
		lg.getLightInfo();
		lg.off();
	}	
}

class Light{
	private boolean Lstate = false;
	private int Lbrightness;
	
	void getLightInfo(){
		if(!Lstate)
			System.out.println("The light is off.");
		else{
			System.out.print("The light is on,");
			System.out.println(" and the brightness is " + Lbrightness);
		}
	}
	
	void on(){
		if(Lstate)
			System.out.println("The light is already turned on.");
		else{
			Lstate = true;
			Lbrightness = 1;
			System.out.println("Turning on the light.");
		}
	}
	
	void off(){
		if(Lstate){
			Lstate = true;
			System.out.println("Turning off the light.");
		}
		else
			System.out.println("The light is already turned off.");
	}
	
	void brighten(){
		if(!Lstate)
			System.out.println("The light is already turned off."
					+" You need to turn it on firstly.");
		else{
			if (Lbrightness >= 3) {
				getLightInfo();
				System.out.println("The light cannot be brightened.");				
			}
			else{
				Lbrightness ++;
				System.out.println("Brightening the light.");
			}
		}
	}
	
	void dim(){
		if(!Lstate)
			System.out.println("The light is already turned off."
					+" You need to turn it on firstly.");
		else{
			if (Lbrightness <= 1) {
				getLightInfo();
				System.out.println("The light cannot be dimmer.");				
			}
			else{
				Lbrightness --;
				System.out.println("Diming the light.");
			}
		}
	}
	
}
