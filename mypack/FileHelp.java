package mypack;

import java.io.*;

class FileHelp {
	public static void main(String args[]){
		//First make sure that a file has been specified
		if(args.length != 1){
			System.out.println("Usage: FileHelp Filename");
			return;
		}
		
		Help hlp = new Help(args[0]);
		String topic;
		
		System.out.println("Try the help system, and enter 'stop' to end.");
		
		do{
			topic = hlp.getSelection();
			
			if(!hlp.helpOn(topic) && (topic.compareToIgnoreCase("stop")!=0))
				System.out.println("Topic not found");
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
