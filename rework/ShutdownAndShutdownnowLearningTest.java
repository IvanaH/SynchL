package rework;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShutdownAndShutdownnowLearningTest {
	private static SetPool setPool;
	private ThreadPoolExecutor tPoolExecutor = null;
	private static List<Runnable> list;
	private static List<Future<Integer>> fList;
	
	@Before
	public void initPool(){
		setPool = new SetPool();
		tPoolExecutor = setPool.threadPoolExecutor;
	}
	
	
	@Test
	public void testShutdownNowRunnable(){
		setPool.addTasks(0,10);
		list = setPool.shutdownNow();
		assertTrue("threadPool doesn't closed correctly.",tPoolExecutor.isShutdown());
		assertEquals(8, list.size(), 0);	
	}
	
	@Test
	public void testShutdownRunnable(){
		setPool.addTasks(0,5);
		setPool.shutdown();
		assertFalse(tPoolExecutor.isShutdown());
	}
	
	@Test
	public void testShutdownCallable(){
		fList = setPool.submitTasks(0,5);
		setPool.shutdown();
		assertEquals(5, fList.size(), 0);	
	}
	
	@After
	public void destroy(){
		if (!tPoolExecutor.isTerminated())
		tPoolExecutor.shutdown();
	}
	
}
