package org.rdb.beans;

import java.util.List;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_DBLINK")
public class DBLink {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer DbLinkID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DbLinkName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String UserName;	
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Password;	
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Host;	
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Port;	
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String SID;	
	@SQLString(value = 2000, constrains = @Constrains(allowNull = true))
	private String Description;
	
	public Integer getDbLinkID() {
		return DbLinkID;
	}
	public void setDbLinkID(Integer dbLinkID) {
		DbLinkID = dbLinkID;
	}
	public String getDbLinkName() {
		return DbLinkName;
	}
	public void setDbLinkName(String dbLinkName) {
		DbLinkName = dbLinkName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sid) {
		SID = sid;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}	}
