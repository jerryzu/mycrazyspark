package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_TABLESPACES")
public class TableSpace {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer TableSpaceID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String TableSpaceName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String fileType;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer Logging;
	@SQLString(value = 64, constrains = @Constrains(allowNull = true))
	private String TableSpaceDesc;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String Remark;

	private List<TablespaceFile> tablespaceFilelist;

	public Integer getTableSpaceID() {
		return TableSpaceID;
	}

	public void setTableSpaceID(Integer tableSpaceID) {
		TableSpaceID = tableSpaceID;
	}

	public String getTableSpaceName() {
		return TableSpaceName;
	}

	public void setTableSpaceName(String tableSpaceName) {
		TableSpaceName = tableSpaceName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Integer getLogging() {
		return Logging;
	}

	public void setLogging(Integer logging) {
		Logging = logging;
	}

	public String getTableSpaceDesc() {
		return TableSpaceDesc;
	}

	public void setTableSpaceDesc(String tableSpaceDesc) {
		TableSpaceDesc = tableSpaceDesc;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public List<TablespaceFile> getTablespaceFilelist() {
		return tablespaceFilelist;
	}

	public void setTablespaceFilelist(List<TablespaceFile> tablespaceFilelist) {
		this.tablespaceFilelist = tablespaceFilelist;
	}
}
