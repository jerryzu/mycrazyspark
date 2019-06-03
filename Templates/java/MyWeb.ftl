Table.java

import java.util.List;
import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_TABLES")
public class Table {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer tableID;
	public Table() {
	}

  public Table(Integer TableID, String SchemaName, String TableName, String MasterKey,
			String TableDesc, String Remark, String distributionKey, Partition partition) {
	this.tableID = TableID;
	this.schemaName = SchemaName;
	}

	public Integer getTableID() {
		return tableID;
	}

	public void setTableID(Integer TableID) {
		this.tableID = TableID;
	}

	public List<Field> getFieldlist() {
		return fieldlist;
	}

	public void setFieldlist(List<Field> fieldlist) {
		this.fieldlist = fieldlist;
	}
}


TableDao.java

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.core.Mapper;
import org.rdb.beans.Function;
import org.rdb.beans.Table;

public interface TableDao extends Mapper {
	@Select({ "<script>", "select * from SYS_TABLES WHERE tableID in ",
			"<foreach collection='tabLists' item='tab' index='index' separator=','  open='(' close=')' >",
			"#{tab}", "</foreach>", " order by tableID asc",
			"</script>"})
	public List<Table> findByID(@Param(value = "tabLists") Integer[] tables);
}

DBLinkbuilder.java

import java.util.List;
import org.core.DataSourceEnvironment;
import org.core.MapperFactory;

import org.rdb.beans.DBLink;
import org.rdb.dao.DBLinkDao;

public class DBLinkbuilder {
	static DBLinkDao dbLinkDao;
	static List<DBLink> dbLinks;
	public static void init() {
		dbLinkDao = MapperFactory.createMapper(DBLinkDao.class, DataSourceEnvironment.platform);
	}

	public static List<DBLink> buildbyID(Integer[] dbLinkIDs) {
		init();
		dbLinks = dbLinkDao.findByID(dbLinkIDs);
		return dbLinks;
	}
}

TableFactory.java

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.rdb.beans.Table;
import org.rdb.build.Tablebuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;

public class TableFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static List<Table> tables;

	public static void main(String[] args) {
		BuildTables();
	}

	public static void done() {
		tables = Tablebuilder.build();
		root.put("tablelist", tables);
	}

	public static void BuildTables() {
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora, FreeMarkerExportWay.screen, null);
		
		tables = Tablebuilder.buildbyTopic("AIO");
		root.put("tablelist", tables);
		SimpleFreeMarkerFactory.doanalysis("table", "table.ftl", root);

		SimpleFreeMarkerFactory.finish();		
	}
}

FreemarkController.java

	@RequestMapping("/table")
	public void toPrintTable(String tableid, String action, HttpServletResponse response, HttpServletRequest request) {
		ServletOutputStream out = null;
		Map<String, Object> root = new HashMap<String, Object>();
		String[] tableIdStringArray = tableid.split(",");
		Integer[] tableIdArray = (Integer[]) ConvertUtils.convert(tableIdStringArray, Integer.class);

		try {
			List<Table> tables = DBObjectAccessURL.TableDefine(tableIdArray);
			root.put("tablelist", tables);
			out = response.getOutputStream();
			freemarkit(out, root, "table.ftl", null);
			out.flush();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

DBObjectAccessURL.java

import java.util.List;
import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Category;
import org.rdb.beans.Function;
import org.rdb.beans.Procedure;
import org.rdb.beans.Table;
import org.rdb.beans.User;
import org.rdb.build.Functionbuilder;
import org.rdb.build.Tablebuilder;
import org.rdb.build.Userbuilder;
import org.rdb.build.procedure.Procedurebuilder;
import org.rdb.dao.CategoryDao;
import org.rdb.dao.FunctionDao;
import org.rdb.dao.ProcedureDao;
import org.rdb.dao.TableDao;
import org.rdb.dao.UserDao;

public class DBObjectAccessURL {
	static CategoryDao categoryDao;
	static TableDao tableDao;
	static FunctionDao functionDao;
	static ProcedureDao procedureDao;
	static UserDao userDao;
	
	static public List<User> UserDisplay() {
		userDao = MapperFactory.createMapper(UserDao.class, DataSourceEnvironment.platform);
		List<User> procedures = userDao.selectAll();
		return procedures;
	}

	static public List<Table> TableDefine(Integer[]  tableid) {
		List<Table> tables = Tablebuilder.buildbyID(tableid);
		return tables;
	}
}
