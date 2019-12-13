package thinkingIJ;

import java.util.Arrays;

class vampireNum {
	public static void main(String[] args) {
		int count = 0;
		
		for(int i=10;i<100;i++){
			int from = Math.max(1000/i, i+1);
			int to = Math.min(10000/i, 100);
			
			for(int j=from; j<to;j++){
				int val = i*j;
				if(val%100 == 0 || (val-i-j)%9!=0){  //val = 1000a+100b+10c+d  
					continue;                        //i=10a+b j=10c+d: val-i-j = 990a+99b = 9*(110a+11b)
				}                                    //i=10b+c j=10d+a: val-i-j = 999a+90b+9c-9d = 9*(111a+10b+c-d)
				                                     //.etc
				String[] val_str = String.valueOf(val).split("");
				String[] ij_str = (String.valueOf(i)+ String.valueOf(j)).split("");
				
				Arrays.sort(val_str);
				Arrays.sort(ij_str);
				
				if(Arrays.equals(val_str, ij_str)){
					count ++;
					System.out.println("Group "+count+": "+i+"*"+j+" ="+val);
				}
			}
		}
	}

}
