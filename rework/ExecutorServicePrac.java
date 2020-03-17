package rework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/*** This class is a practice of executorService, httprequst and db ops.
* @author Ivana.H
*/
public class ExecutorServicePrac{
	public static void main(String[] args) {
		EService eService = new EService();
//		eService.getSMVipInfo("15088603364");
//		eService.doCheck("15068746748");
		System.out.println(eService.multiThreadCheck(eService.getMobiles()));
		System.out.println();
	}
}
	
class EService{
	public static ExecutorService pool1 =  Executors.newFixedThreadPool(3);
	
	public static String userName = "root";
	public static String password = "shinemo123";
	public static String url = "jdbc:mysql://10.0.10.41:3306/shinemo_migu_activity";
	public static DbUtil dbUtil = new DbUtil(userName, password, url);
	
	public String multiThreadCheck(List<String> mobiles) {
		for(String mobile:mobiles){
			Runnable runnable = new Runnable() {			
				@Override
				public void run() {
					doCheck(mobile);
				}
			};
			
			pool1.execute(runnable);
		}
		
		pool1.shutdown();
		
		return ("Checked: "+mobiles.size());		
	}

	public List<String> getMobiles() {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("15088603364");
		mobiles.add("15068746748");
		mobiles.add("15957193120");
		mobiles.add("15958032925");
		mobiles.add("18458872034");
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
	
//	public VipRelated getSMVipInfo(String mobile) {
//		String querySql = "select mobile,gmt_create AS subscribe_time, unsubscribe_offer_id,is_preferential from svip_order_record where mobile = \""+mobile+"\"order by id desc limit 1;";
//		return dbUtil.queryForObject(querySql,new VipRelatedRowMapper());		
//	}

//	public VipRelated getSMVipInfo(String mobile) {
//		String querySql = "select mobile,gmt_create AS subscribe_time, unsubscribe_offer_id,is_preferential from svip_order_record where mobile = \""+mobile+"\"order by id desc limit 1;";
//		List<Map<String, Object>> res = dbUtil.queryForList(querySql);
//		VipRelated vipRelated = new VipRelated();
//
//		if (res.size() > 0) {
//			vipRelated.setMobile(res.get(0).get("mobile").toString());
//        	vipRelated.setIsPreferential(Boolean.parseBoolean(res.get(0).get("is_preferential").toString()));
//    		return vipRelated;		
//		}else {
//    		System.out.println("result of sql is null.");
//    		return null;
//		}		
//	}
	
	public String getHWSvipInfo(String mobile) {
		String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
		Map<String, String> headers = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", mobile);
		
		return HttpUtil.get(url, headers, params);
//		System.out.println(HttpUtil.get(url, headers, params));
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