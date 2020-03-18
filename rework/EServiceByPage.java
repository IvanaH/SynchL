package rework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/*** 
* @author Ivana.H
*/
public class EServiceByPage{
	public static void main(String[] args) {
		CheckByPages checkByPages = new CheckByPages(2);
		checkByPages.byPages();
	}
}

class CheckByPages implements Runnable{
	public static ExecutorService pool1 =  Executors.newFixedThreadPool(2);

	private CheckSVIP checkSVIP;
	private CountDownLatch pSize;
	private List<String> mobiles;
	private String mobile;
	private int pageS;
	
	
	public CheckByPages(int pageSize) {
		checkSVIP = new CheckSVIP();
		this.mobiles = checkSVIP.getMobiles();
		this.pageS = (this.mobiles.size()>pageSize)?pageSize:this.mobiles.size();
	}
	
	@Override
	public void run() {
		checkSVIP.doCheck(mobile);
		pSize.countDown();		
	}
	
	public void byPages() {
		int i = 0;
		
		while(i<this.mobiles.size()){
			pSize = new CountDownLatch(pageS);
			
			do{
				if(i<this.mobiles.size()){
					mobile = this.mobiles.get(i);
					System.out.println(mobile);
					pool1.execute(this);
					i ++;
				}else
					break;
			}while(pSize.getCount()>0);
			
			System.out.println("pSize.getCount():  "+ pSize.getCount());
			
			try {
				pSize.await();
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//List<List<String>> arrays = new ArrayList();
//for(List<String> currentPage:arrays){
//	
//	final  CountDownLatch countDown = new CountDownLatch(currentPage.size());
//	for(String mobile:currentPage){
//		Runnable runnable = new Runnable(){
//			mobile,countdown;
//
//			run(){
//				try{
//					mobile -> check
//				}	
//				countdown.latch;
//			}	
//
//		}	
//		pool.submit()
//	}
//	try{
//		countDown.await()
//	}catch(Exception e){
//
//	}
//
//}

	
class CheckSVIP{
	public static String userName = "root";
	public static String password = "shinemo123";
	public static String url = "jdbc:mysql://10.0.10.41:3306/shinemo_migu_activity";
	public static DbUtil dbUtil = new DbUtil(userName, password, url);

	public List<String> getMobiles() {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("15088603364");
		mobiles.add("15068746748");
		mobiles.add("15957193120");
		mobiles.add("15958032925");
		mobiles.add("18458872034");
		mobiles.add("18358185826");
		mobiles.add("15869667586");
		mobiles.add("13989412941");
		mobiles.add("15968541079");
		mobiles.add("19857963611");
		mobiles.add("18757112364");
		mobiles.add("18757114843");
		mobiles.add("17280164421");
		mobiles.add("18267207252");
		mobiles.add("13819782724");
		mobiles.add("13867671921");
		return mobiles;
	}
	
	public VipRelated getSMVipInfo(String mobile) {
		String querySql = "select mobile,gmt_create AS subscribe_time, unsubscribe_offer_id,is_preferential from svip_order_record where mobile = \""+mobile+"\"order by id desc limit 1;";
		List<VipRelated> list = dbUtil.queryForList(querySql,new VipRelatedRowMapper());
		if(list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
	public String getHWSvipInfo(String mobile) {
		String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
		Map<String, String> headers = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", mobile);
		
		return HttpUtil.get(url, headers, params);
	}
	
	public void doCheck(String mobile) {
		System.out.println(Thread.currentThread()+" Start checking: "+ mobile);
		
		VipRelated smInfo = getSMVipInfo(mobile);	
		
		if (smInfo!=null) {
			if(smInfo.getIsPreferential()){
				Map<String,Object> hwRes = (Map<String,Object>)GsonUtil.getJsonMap(this.getHWSvipInfo(mobile));
				List<Map<String, Object>> resList = (List<Map<String, Object>>) hwRes.get("data");
				if (resList.size()>0){
					Boolean MATCH = false;
					for(Map<String, Object> res:resList){
						if (res.get("offerId").equals("600000586153")){
							System.out.println(mobile + " check: Pass.\n");
							MATCH = true;
							break;
						}else
							continue;							
					}
					if(!MATCH)
						System.out.println(mobile + " check: Failed.\n");
				}else
					System.out.println("Package list is null.\n");
			}else{
				System.out.println(mobile + " do not get preferential in ShineMo.\n");
			}
		}else{
			System.out.println(mobile + " has no record in ShineMo.\n");
		}			
	}
}