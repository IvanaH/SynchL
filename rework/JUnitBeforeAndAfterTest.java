package rework;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class JUnitBeforeAndAfterTest {
	private static List<String> list;
	
	@BeforeClass
	public static void init(){
		System.out.println("Startup...");
		list = new ArrayList<String>();
		list.add("Init");
	}
	
	@Test
	public void addEleTest(){
		list.add("Test1");
		System.out.println(list.size());
		assertEquals(2, list.size());
	}
	
	@Test
	public void anotherTest(){
		System.out.println(list.size());
		assertEquals(1, list.size());
	}
	
	@After
	public void destory(){
		System.out.println("Destorying ...\n");
		list.clear();
	}

}
