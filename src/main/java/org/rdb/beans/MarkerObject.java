package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_MARKEROBJECT")
public class MarkerObject {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer markerObjectID;
	@SQLString(value = 32)
	private String FileStyle;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String ojectType;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String templateFileName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String targetFileName;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String templatePath;
	@SQLString(value = 200, constrains = @Constrains(allowNull = true))
	private String targetPath;
	@SQLBoolean
	private Integer Enabled;
	
	public Integer getMarkerObjectID() {
		return markerObjectID;
	}
	public void setMarkerObjectID(Integer markerObjectID) {
		this.markerObjectID = markerObjectID;
	}
	public String getFileStyle() {
		return FileStyle;
	}
	public void setFileStyle(String fileStyle) {
		FileStyle = fileStyle;
	}
	public String getOjectType() {
		return ojectType;
	}
	public void setOjectType(String ojectType) {
		this.ojectType = ojectType;
	}
	public String getTemplateFileName() {
		return templateFileName;
	}
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}
	public String getTargetFileName() {
		return targetFileName;
	}
	public void setTargetFileName(String targetFileName) {
		this.targetFileName = targetFileName;
	}
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getTargetPath() {
		return targetPath;
	}
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
}