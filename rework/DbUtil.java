package rework;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class DbUtil {
	
	private String userName;
	private String password;
	private String url;
	
	private DruidDataSource druidDataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public DbUtil() {}
	
	public DbUtil(String userName,String password,String url) {
		this.userName = userName;
		this.password = password;
		this.url = url;
		druidDataSource  = new DruidDataSource();
		druidDataSource.setUsername(userName);
		druidDataSource.setPassword(password);
		druidDataSource.setUrl(url);
		druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		try {
			druidDataSource.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcTemplate = new JdbcTemplate(druidDataSource);
	}
	
	public <T> T queryForObject(String sql, Class<T> requiredType) {
		return jdbcTemplate.queryForObject(sql, requiredType);
	}
	
	public List<Map<String, Object>> queryForList(String sql){
		return jdbcTemplate.queryForList(sql);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DruidDataSource getDruidDataSource() {
		return druidDataSource;
	}

	public void setDruidDataSource(DruidDataSource druidDataSource) {
		this.druidDataSource = druidDataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public static void main(String[] args) {
		new DbUtil("root", "shinemo123", "jdbc:mysql://10.0.10.42:3306/shinemo_migu_activity");
		System.out.println("Done!");
	}
	
}
