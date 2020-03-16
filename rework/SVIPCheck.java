package rework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*** This class is a base practice of http and db ops.
* @author Ivana.H
*/
public class SVIPCheck{	
	public List<String> getMobiles() {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add("15088603364");
		mobiles.add("15068746748");
		mobiles.add("15957193120");
		mobiles.add("15958032925");
		mobiles.add("18458872034");
		return mobiles;
	}

	
	public List<Map<String, Object>> getSMVipInfo(String mobile) {
		String userName = "root";
		String password = "shinemo123";
		String url = "jdbc:mysql://10.0.10.41:3306/shinemo_migu_activity";
		
		DbUtil dbUtil = new DbUtil(userName, password, url);
		String querySql = "select mobile,gmt_create AS subscribe_time, unsubscribe_offer_id,is_preferential from svip_order_record where mobile = \""+mobile+"\"order by id desc limit 1;";
		List<Map<String, Object>> res= dbUtil.queryForList(querySql);
		
		return res;
//		System.out.println(res);
	}
	
	public String getHWSvipInfo(String mobile) {
		String url = "http://10.0.10.83:7804/migu-activity-thirdapi/backdoor/getPackageList";
		Map<String, String> headers = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", mobile);
		
		return HttpUtil.get(url, headers, params);
//		System.out.println(HttpUtil.get(url, headers, params));
	}
	
	public void doCheck(String mobile) {
		List<Map<String, Object>> smInfos = this.getSMVipInfo(mobile);

		if (smInfos.size() > 0){
			for(Map<String, Object> smInfo:smInfos){
				if (Boolean.parseBoolean(smInfo.get("is_preferential").toString())){
					System.out.println(GsonUtil.getJsonMap(this.getHWSvipInfo(mobile)));
					Map<String,Object> hwRes = (Map<String,Object>)GsonUtil.getJsonMap(this.getHWSvipInfo(mobile));
					System.out.println(hwRes);
					List<Map<String, Object>> resList = (List<Map<String, Object>>) hwRes.get("data");
					if (resList.size()>0){
						Boolean MATCH = false;
						for(Map<String, Object> res:resList){
							if (res.get("offerId").equals("600000586153")){
								System.out.println(mobile + " check: Pass.");
								MATCH = true;
								break;
							}else
								continue;							
						}
						if(!MATCH)
							System.out.println(mobile + " check: Failed.");
					}else
						System.out.println("Package list is null.");
				}
			}
		}		
	}
	
	public static void main(String[] args) {
		SVIPCheck svipCheck = new SVIPCheck();
		String mobile = "15088603364";
//		System.out.println(svipCheck.getSMVipInfo(mobile));
//		Map<String,Object> hwRes = (Map<String,Object>)GsonUtil.getJsonMap(svipCheck.getHWSvipInfo(mobile));
//		System.out.println(hwRes.get("data"));
		svipCheck.doCheck(mobile);

	}
}





