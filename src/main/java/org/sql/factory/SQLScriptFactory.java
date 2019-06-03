package org.sql.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleSQLPipeFactory;
import org.sql.bean.SQLScript;
import org.sql.builder.SQLScriptbuilder;

public class SQLScriptFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<SQLScript> sqlScripts;

	public static List<SQLScript> getsqlScripts() {
		return sqlScripts;
	}

	public static void main(String[] args) {
		// String path =
		// this.getClass().getClassLoader().getResource("1.gif").getPath();
		// System.out.println(path);
		System.out.println(System.getProperty("user.dir"));// user.dir指定了当前的路径
		BuildSQLScripts();
//		BuildSQLScriptsFile();
//		 LoadSQLScriptfromfile(sqlScripts);
	}

	public static void done() {
		sqlScripts = SQLScriptbuilder.build();
		root.put("sqlScriptlist", sqlScripts);
	}

	public static void BuildSQLScripts() {
		done();
		SimpleSQLPipeFactory.setExportInfo(FreeMarkerFileType.script, FreeMarkerExportWay.screen);
		SimpleSQLPipeFactory.analysisTemplate("sqlScriptlist", "SqlScript.ftl", root);
//		SimpleSQLPipeFactory.finish();
	}

	public static void BuildSQLScriptsFile() {
		done();
		for (SQLScript sqlScript : sqlScripts) {
			 SQLScript2file(sqlScript);
//			SQLScript2do(sqlScript);
		}
	}

	public static void LoadSQLScriptsFromFile() {
		LoadSQLScriptfile(sqlScripts);
	}

	@SuppressWarnings("unchecked")
	private static void SQLScript2do(SQLScript sqlScript) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String mySQL = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.110.135:1521:xe";
			String username = "txbs";
			String password = "txbs";
			conn = DriverManager.getConnection(url, username, password);
			mySQL = sqlScript.getSqltxt().replace("V_S", "20190101").replace("V_E", "20191120").replace("V_DPT",
					"and c_dpt_cde like '9973%'");
			pstmt = conn.prepareStatement(mySQL);
			rs = pstmt.executeQuery();
		
			// 使用java.sql.ResultSet输出
			// while (rs.next()) {
			// System.out.println("username=" + rs.getString("username"));
			// }

			// 使用Common BeanUtils中的ResultSetDynaClass类包装
			// System.out.println("-----------分割线---------------------");
			// rs.beforeFirst();// 这里必须使用beforeFirst
			// ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
			// Iterator<DynaBean> rows = rsdc.iterator();
			// while (rows.hasNext()) {
			// DynaBean row = rows.next();
			// System.out.println("id=" + row.get("id") + ",username=" + row.get("username")
			// + ",password="
			// + row.get("password"));
			// }

			// 使用RowSetDnyaClass类
			System.out.println("对像:" + sqlScript.getRemark() + "," + sqlScript.getSeq() + "," + sqlScript.getCde());
			// System.out.println(mySQL);
			// rs.beforeFirst();// 这里必须使用beforeFirst
			// RowSetDynaClass rc = new RowSetDynaClass(rs);
			// List<DynaBean> resultList = rc.getRows();
			// for (DynaBean db : resultList) {
			// System.out.print(db.get("id") + "\t");
			// System.out.print(db.get("username") + "\t");
			// System.out.println(db.get("password"));
			// }
		} catch (ClassNotFoundException e) {
			// System.out.println("-----------ClassNotFoundException---------------------");
			// System.out.println(mySQL);
			// e.printStackTrace();
		} catch (SQLException e) {
			// System.out.println("-----------SQLException---------------------");

			// System.out.println("对像3:" + sqlScript.getSeq() + "," + sqlScript.getCde() +
			// "," +e.getErrorCode() +":" + sqlScript.getRemark() + ",error:"
			// +e.getMessage());
			if (e.getErrorCode() == 942) {
				// 表或视图不存在
				// System.out.println(mySQL);
				System.out.println(
						"Error-942对像:" + sqlScript.getRemark() + "," + sqlScript.getSeq() + "," + sqlScript.getCde());
			} else if (e.getErrorCode() == 933) {
				// SQL命令末正确结束
				// System.out.println("-----------SQLException---------------------");
				System.out.println(mySQL);
				System.out.println(
						"Error-933对像:" + sqlScript.getRemark() + "," + sqlScript.getSeq() + "," + sqlScript.getCde());
			} else if (e.getErrorCode() == 918) {
				// 末明确定义列
				// System.out.println("-----------SQLException---------------------");
				// System.out.println(mySQL);
				System.out.println(
						"Error-918对像:" + sqlScript.getRemark() + "," + sqlScript.getSeq() + "," + sqlScript.getCde());
			} else if (e.getErrorCode() == 904) {
				//
				// System.out.println("-----------SQLException------标识符无效---------------");
				System.out.println(mySQL);
				System.out.println(
						"Error-904对像:" + sqlScript.getRemark() + "," + sqlScript.getSeq() + "," + sqlScript.getCde());
			}
			// System.out.println(mySQL);
			// e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.clearWarnings();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void SQLScript2file(SQLScript sqlScript) {
		String scriptfilepath = "e:/1.数据上报平台/" + sqlScript.getRemark();

		File dir = new File(scriptfilepath);
		if (!dir.exists()) {
			dir.mkdirs(); // 多层目录需要调用mkdirs
		}

		String scriptfilename = scriptfilepath + "/" + sqlScript.getSeq().toString() + "-" + sqlScript.getCde()
				+ ".sql";

		try {
			String content = sqlScript.getSqltxt();

			File file = new File(scriptfilename);
			System.out.println(file.getAbsoluteFile());

			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getAbsoluteFile(), true);
			fileWritter.write(content);
			fileWritter.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void LoadSQLScriptfile(List<SQLScript> sqlScripts) {
		final String file_extension = ".sql";
		try {
			File Dir = new File("E:\\1.数据上报平台\\web_plredr_healthin_dpt");
			File[] Files = Dir.listFiles(new FileFilter() {
				public boolean accept(File f) {
					if (f.getName().toLowerCase().endsWith(file_extension))
						return true;
					return false;
				}
			});
			for (int i = 0; i < Files.length; i++) {
				SQLScript sqlScript = LoadScriptFile(Files[i]);
				UpdateDBSqlScript(sqlScript);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static SQLScript LoadScriptFile(File file) {
		String fileName;
		SQLScript sqlScript = new SQLScript();
		fileName = file.getName();
		fileName = fileName.substring(0, fileName.indexOf("."));

		sqlScript.setSeq(Integer.parseInt(fileName.substring(0, fileName.indexOf("-"))));
		sqlScript.setCde(fileName.substring(fileName.indexOf("-") + 1));
		sqlScript.setRemark("web_plredr_healthin_dpt");

		StringBuffer buffer = new StringBuffer();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		String line;
		System.out.println("--------------------------------");
		try {
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
				// buffer.append("\r\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlScript.setSqltxt(buffer.toString());
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// System.out.println("--------------------------------");
		// sqlScript.setSqltxt(sqltxt);
		// sqlScript.setName(name);
		// System.out.println(" " + sqlScript.getSeq().toString() + ":" +
		// sqlScript.getCde());
		// System.out.println(" " + sqlScript.getSqltxt());

		return sqlScript;
	}

	private static void UpdateDBSqlScript(SQLScript sqlScript) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/txbs";
		String username = "txbs";
		String password = "gemini";
		String mySQL = "update rul_sqlscript set sqltxt = ?, enabled = enabled * 3 where seq = ? and cde = ?";
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, username, password);

			pstmt = conn.prepareStatement(mySQL);
			pstmt.setString(1, sqlScript.getSqltxt());
			pstmt.setInt(2, sqlScript.getSeq());
			pstmt.setString(3, sqlScript.getCde());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.clearWarnings();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}