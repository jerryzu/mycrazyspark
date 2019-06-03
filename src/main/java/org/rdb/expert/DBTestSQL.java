package org.rdb.expert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DBTestSQL {
	public static void main(String[] args) {

		try {
			Properties props = Resources
					.getResourceAsProperties("resources/jdbc.properties");
			String url = props.getProperty("url");
			String driver = props.getProperty("driver");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			System.out.println(url);
			if (url.equals("jdbc:oracle:thin:@localhost:1521:orcl")) {
				Class.forName(driver).newInstance();
				Connection conn = (Connection) DriverManager.getConnection(url,
						username, password);
				ScriptRunner runner = new ScriptRunner(conn);
//				显示错误
//				runner.setErrorLogWriter(null);
//				runner.setLogWriter(null);
				runner.runScript(Resources
						.getResourceAsReader("ddl/oracle/om-oracle-schema.sql"));
				runner.runScript(Resources
						.getResourceAsReader("ddl/oracle/om-oracle-dataload.sql"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}