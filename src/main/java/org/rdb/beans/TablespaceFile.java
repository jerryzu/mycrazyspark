package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_TABLESPACEFILES")
public class TablespaceFile {
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tablespace;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String fileType;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tablespaceStatus;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String fileStatus;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String datafileName;
	@SQLInteger(constrains = @Constrains(allowNull = true))
	private Integer fileSize;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sizeUnit;

	public String getTablespace() {
		return tablespace;
	}

	public void setTablespace(String tablespace) {
		this.tablespace = tablespace;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getTablespaceStatus() {
		return tablespaceStatus;
	}

	public void setTablespaceStatus(String tablespaceStatus) {
		this.tablespaceStatus = tablespaceStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getDatafileName() {
		return datafileName;
	}

	public void setDatafileName(String datafileName) {
		this.datafileName = datafileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getSizeUnit() {
		return sizeUnit;
	}

	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}
}
