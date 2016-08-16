package mypack;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class PatternEx {
	public static void main(String args[]){
//		String str = "aaaaaahttp://kkkkkkkkkk.com&&&&&https://aaa.com@@@http://333333333";
//		Pattern P3 = Pattern.compile("^http[s]:\\/\\/.+$");
		
//		Pattern p = Pattern.compile("a*b");
//		System.out.println(p.pattern()); //return the string of the pattern
//		
//		Matcher m = p.matcher("aaaab");  //match the whole inputed string for one time
//		System.out.println(m.matches());
//		m = p.matcher("aaaabbbb");
//		System.out.println(m.matches());
//		m = p.matcher("aaaaccccb");
//		System.out.println(m.matches());
//		
//		Matcher mt = p.matcher("aaaab"); 
//		System.out.println(mt.pattern()); // the pattern which create this matcher
//		
//		Matcher m3 = p.matcher("111aaaabbab");
//		System.out.println(m3.matches()); //Attempts to match the entire region against the pattern.
//		System.out.println(m3.find()); //Attempts to find the next subsequence of the input sequence that matches the pattern.
//		System.out.println(m3.lookingAt()); //Attempts to match the input sequence, starting at the beginning of the region, against the pattern.
//		m3 = p.matcher("aaaa111bb");
//		System.out.println(m3.matches()); 
//		System.out.println(m3.find()); 
//		System.out.println(m3.lookingAt()); 
//		m3 = p.matcher("aaaabb1111");
//		System.out.println(m3.matches()); 
//		System.out.println(m3.lookingAt()); 
//		System.out.println(m3.find()); 
//		System.out.println();
//		
//		m3 = p.matcher("aaaabb1111");
//		m3.lookingAt();
//		System.out.println("m3.lookingAt()"); 
//		System.out.println(m3.start());
//		System.out.println(m3.end());
//		System.out.println(m3.group());
//		
//		m3 = p.matcher("aaaabb1111");
//		m3.find();
//		System.out.println("m3.find()"); 
//		System.out.println(m3.start());
//		System.out.println(m3.end());
//		System.out.println(m3.group());
//		
//		Matcher m2 = p.matcher("aaaabb1111");
//		m2.lookingAt();
//		System.out.println("m2.lookingAt(): " + m2.group());
//		m2.find();
//		System.out.println("m2.find(): " +m2.group());
//
//		System.out.println();
//
//		Matcher m3 = p.matcher("aaaabb1111");
//		m3.lookingAt();
//		System.out.println("m3.lookingAt(): " + m3.group());
//		m3 = p.matcher("aaaabb1111");
//		m3.find();
//		System.out.println("m3.find(): " +m3.group());
//		
//		System.out.println();
		
//		Pattern p = Pattern.compile("a*b");
//
//		Matcher m4 = p.matcher("aaaab22abb");
//		while(m4.find()){
//			System.out.println(m4.group()); //Returns the input subsequence matched by the previous match
//		};
//		String[] str = p.split("aaaab22cabb");
//		for(int i = 0; i<str.length;i++){
//			System.out.println(str[i]);
//		}
//		int i = m4.groupCount();
//		System.out.println(i);
//		if(i > 0){
//			for(int j=1;j<=i;j++){
//				System.out.println(m4.start(j));
//				System.out.println(m4.end(j));
//				System.out.println(m4.group(j));
//				System.out.println();
//			}
//		}
//		
//		String str1 = "aaaaaahttp://kkkkkkkkkk.com&&&&&https://aaa.com@@@http://333333333";
//		Pattern P3 = Pattern.compile("^http[s]:\\/\\/.");
//		Matcher M = P3.matcher(str1);
//		while(M.find()){
//			System.out.println(M.group());
//		}
		
//		String text = "This is the text to be searched" 
//		              + "for occurrences of http:a// and https:// pattern";
//		String text = "http://";
//		String text = "John writes about this, and john Doe writes about that," +
//        " and John Wayne writes about everything.";
		String text = "<a href=\"/story/8693071\" "
				+ "class=\"link-button\"><img src=\"http://pic2.zhimg.com/cd80fe754b"
				+"bdfb5dc160377a73a2e009.jpg\"class=\"preview-image\"><span "
				+"class=\"title\">真正的专业烘焙，会为了口感上哪怕 0.01% 的差别努力</span></a></div></div><div class=\"wrap\">"
				+"<div class=\"box\"><a href=\"/story/8693356\" class=\"link-button\"><img";
//		String reg = "(http|https)(:)";
//		String reg = "title\">(.*?)(</span>.*?)href=\"(.*?)(\") ";
		String reg = "href=\"(.*?)(\") ";
		Pattern pa = Pattern.compile(reg);
		Matcher mc = pa.matcher(text);
		
//		System.out.println(mc.matches());
//		System.out.println(mc.lookingAt());
		mc.reset();
		int count = 0;
		while(mc.find()){
			count ++;
			System.out.println("Found "+count+" :"+mc.group() + " || "+mc.group(1)+"|| "+mc.group(2));
			System.out.println("Start - End: "+mc.start()+" - "+mc.end());
			System.out.println();
		}

		System.out.println(); 


	}
}
