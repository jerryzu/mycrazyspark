package org.rdb.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLDate;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_PROCDEBUG")
public class ProcDebug {
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String batch;
	@SQLDate
	private Date startDate;
	@SQLDate
	private Date endDate;

	public ProcDebug() {
		this.startDate = new Date();
		this.endDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		this.batch = "Debug-" + dateFormat.format(startDate);
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
