package mypack;

import java.io.*;

class FileHelp {
	public static void main(String args[]){
		//First make sure that a file has been specified
		if(args.length != 1){
			System.out.println("Usage: FileHelp Filename");
			return;
		}
		
		String addr = args[0];
		
		Help hlp = new Help(addr);
		String topic;
		
		System.out.println("Try the help system, and enter 'stop' to end.");
		
		do{
			topic = hlp.getSelection();
			
			if(!hlp.helpOn(topic) && (topic.compareToIgnoreCase("stop")!=0)){
				System.out.println("Topic not found");
				hlp.addOn(addr);
			}
		}while(topic.compareToIgnoreCase("stop")!=0);
	}
}


class Help{
	String helpfile;
	
	Help(String fname){
		helpfile = fname;
	}
	
	//show the info if a topic is found
	boolean helpOn(String what){
		int ch;
		String topic,info;
		
		//open the help file
		try(BufferedReader helpRdr = 
				new BufferedReader(new FileReader(helpfile)))
		{
			do{
				//read characters until a# is found
				ch = helpRdr.read();
				
				//see if topic matches
				if(ch == '#'){
					topic = helpRdr.readLine();
					if(what.compareToIgnoreCase(topic) == 0){ //matches
						do{
							info = helpRdr.readLine();
							if(info != null)
								System.out.println(info);
						}while((info != null) && info.compareTo("")!=0);
						return true;
					}
				}
			}while(ch != -1);			
		}catch(IOException ext){
			System.out.println("Error occurred: "+ ext);
			return false;
		}
		return false; //no matching topic
	}
	
	//add new statement
	void addOn(String addr){
		
		char choice = 'Q';
		
		System.out.println("Do you want to add the new topic in file? (Y/N) ");
		
		//make sure the selection is valid
		try{
			do{
				choice = (char)System.in.read();
				if(choice !='N' && choice != 'Y'){
					System.out.println("Invalid Input. ");
					System.out.println("Do you want to add the new topic in file? (Y/N) ");
					choice = 'Q';
				}
			}while(choice == 'Q');
		}catch(IOException exc){
			System.out.println("Error occurred. "+exc);
		}
		
		
		//add the input to the file
		if(choice == 'N')
			return;
		else{
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					FileWriter fw = new FileWriter(addr)){
				System.out.println("Please input the topic and statements, enter 'stop' to quit");
				
				String str;
				
				fw.write("#");
				
				do{
					System.out.print(": ");
					str = br.readLine();
					
					if(str.compareTo("stop") == 0)
						break;
					
					str = str + "\r\n"; // add new line
					
					fw.write(str);
					
				}while(str.compareTo("stop") != 0);
				
			}catch(IOException exc){
				System.out.println("Error occurred. "+exc);
			}
		}
		
	}
	
	//Get a help topic from console
	String getSelection(){
		String topic = "";
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		System.out.println("Enter topic: ");
		
		try{
			topic = br.readLine();
		}catch(IOException exc){
			System.out.println("Error occurred");
		}
		
		return topic;
	}
}
