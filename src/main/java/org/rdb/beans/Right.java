package org.rdb.beans;


import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_RIGHTS")
public class Right {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer RightID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String RightName;
	@SQLInteger
	private Integer Status;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Remark;

	public Integer getRightID() {
		return RightID;
	}

	public void setRightID(Integer RightID) {
		this.RightID = RightID;
	}

	public String getRightName() {
		return RightName;
	}

	public void setRightName(String RightName) {
		this.RightName = RightName;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		this.Status = status;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		this.Remark = remark;
	}
}
