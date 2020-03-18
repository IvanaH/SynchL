package rework;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ExecutorServicePracTest {
	private EService eService;
	
	@Before
	public void init(){
		eService = new EService();
	}
	
	@Test
	public void testDoCheckVip(){
		VipRelated vipRelated = new VipRelated();
		Map<String, String> smInfo = new HashMap<String, String>();
		smInfo.put("subscribe_time", null);
		smInfo.put("is_preferential", Integer.toString(1));
//		eService.doCheckVip(vipRelated, smInfo);		
	}

}
