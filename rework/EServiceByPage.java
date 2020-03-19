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
		Mobiles mobiles = new Mobiles();
		
		CheckByPage checkByPage = new CheckByPage();
//		checkByPage.doByPage(checkByPage.doPaging(mobiles.mobileList(), 3));
		checkByPage.doPaging(mobiles.mobileList(), 3);
//		checkByPage.doByPage(checkByPage.doPaging(mobiles.mobileList(), 4));
	}
}

class CheckByPage{
	public static ExecutorService pool1 = Executors.newFixedThreadPool(2);
	
	public List<List<String>> doPaging(List<String> mobiles,int pageSize) {
		List<List<String>> mobilePages = new ArrayList<>();

		if (mobiles.size() > 0){
			int pageNum = (mobiles.size()%pageSize==0)?mobiles.size()/pageSize:mobiles.size()/pageSize+1;
			int currentIndex = 0;
			
			for(int i=0;i<pageNum;i++){
				//create a new page
				List<String> singlePage = new ArrayList<>();
				
				for(;currentIndex<mobiles.size();){
					singlePage.add(mobiles.get(currentIndex));
					currentIndex++;
					
					//next data is put in currentPage or next page  
					if(currentIndex%pageSize ==0)
						break;
				}
		        
				mobilePages.add(singlePage);
			}
		}else 				
			mobilePages = null;
			
		System.out.println("\n--- After by page: " + mobilePages);
		return mobilePages;		
	}
	
	public void doByPage(List<List<String>> mobilePages){			
		for(List<String> currentPage:mobilePages){				
			if (currentPage.size()>0){
				CountDownLatch countDownLatch = new CountDownLatch(currentPage.size());
				System.out.println(countDownLatch.toString());

				for(String mobile:currentPage){
					System.out.println("--- CurrentPage: " + (mobilePages.indexOf(currentPage)+1)+" - mobile : " + mobile);
					pool1.execute(new CheckByThread(mobile, countDownLatch));
				}
				try {
					countDownLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else
				continue;
		}	
		System.out.println("Done");
	}
}


class CheckByThread implements Runnable{
	private CountDownLatch countDownLatch;	
	private String mobile;
	
	public CheckByThread(String mobile, CountDownLatch downLatch) {
		this.mobile = mobile;
		this.countDownLatch = downLatch;
	}
	
	@Override
	public void run() {
		CheckSVIP checkSVIP = new CheckSVIP();
		checkSVIP.doCheck(mobile);
		countDownLatch.countDown();		
		System.out.println(Thread.currentThread()+countDownLatch.toString());
	}
}


class Mobiles{
	public List<String> mobileList() {
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
}

	
class CheckSVIP{
	public static String userName = "root";
	public static String password = "shinemo123";
	public static String url = "jdbc:mysql://10.0.10.41:3306/shinemo_migu_activity";
	public static DbUtil dbUtil = new DbUtil(userName, password, url);
	
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