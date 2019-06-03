package org.rdb.beans;


import java.util.List;

import org.rdb.factory.IFactory;
import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_USERS")
public class User  implements IBean {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer UserID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String UserName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Password;
	@SQLInteger
	private Integer IsLock;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String DefaultTableSpace;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String TemporaryTableSpace;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Remark;	
	
	private List<Right> userRightlist;

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(Integer userID) {
		this.UserID = userID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public Integer getIsLock() {
		return IsLock;
	}

	public void setIsLock(Integer isLock) {
		this.IsLock = isLock;
	}

	public String getDefaultTableSpace() {
		return DefaultTableSpace;
	}

	public void setDefaultTableSpace(String defaultTableSpace) {
		this.DefaultTableSpace = defaultTableSpace;
	}

	public String getTemporaryTableSpace() {
		return TemporaryTableSpace;
	}

	public void setTemporaryTableSpace(String temporaryTableSpace) {
		this.TemporaryTableSpace = temporaryTableSpace;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}

	public List<Right> getuserRightlist() {
		return userRightlist;
	}

	public void setuserRightlist(List<Right> userRightlist) {
		this.userRightlist = userRightlist;
	}
}
