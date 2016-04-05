class ErrMsg{
	String msgs[] = {
			"Output Error",
			"Input Error",
			"Disk Full",
			"Index Out-Of-Bounds"
	};
	
	String getErrorMsg(int i){
		if(i>0 & i < msgs.length)
			return msgs[i];
		else
			return "Invaild Error Code";
	}
}

class ErrorMsg {
	public static void main (String args[]){
		ErrMsg err = new ErrMsg();
		
		System.out.println(err.getErrorMsg(2));
		System.out.println(err.getErrorMsg(5));
	}

}
