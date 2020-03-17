package rework;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VipRelatedRowMapper implements RowMapper<VipRelated> {
	
	@Override
	public VipRelated mapRow(ResultSet rs, int rowNum) throws SQLException {

        VipRelated vipRelated = new VipRelated();
        
        if (rs != null){
        	vipRelated.setMobile(rs.getString("mobile"));
        	vipRelated.setEffectiveTime(rs.getTimestamp("subscribe_time").toLocalDateTime());
        	vipRelated.setIsPreferential((rs.getInt("is_preferential")==1)?true:false);
        }
        else {
    		System.out.println("result of sql is null.");
		}
        
//        while(rs.next()){
//        	vipRelated.setMobile(rs.getString("mobile"));
//        	vipRelated.setEffectiveTime(rs.getTimestamp("subscribe_time").toLocalDateTime());
//        	vipRelated.setIsPreferential((rs.getInt("is_preferential")==1)?true:false);
//        }
                
        return vipRelated;
    }
}
