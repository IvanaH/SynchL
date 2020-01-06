package rework;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShutdownAndShutdownnowLearningTest {
	private static SetPool setPool;
	private static List<Runnable> list;
	
	@Before
	public void initPool(){
		setPool = new SetPool();
	}
	
//	@Test
//	public void testShutdownRunnable(){
//		setPool.addTasks(0,10);
//		setPool.shutdown();
//	}
	
	@Test
	public void testShutdownNowRunnable(){
		setPool.addTasks(0,10);
		list = setPool.shutdownNow();
		assertEquals(8, list.size(), 0);	
	}
}
