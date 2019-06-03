package org.datax.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "DATAX_JOBS")
public class Job {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer jobID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String jobName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String jsonFileName;
	@SQLBoolean
	private boolean Enabled;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String jobReader;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String jobWriter;
	@SQLString(value = 400, constrains = @Constrains(allowNull = true))
	private String readerUrl;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String readertable;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String readerPassword;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String readerUserName;
	@SQLString(value = 400, constrains = @Constrains(allowNull = true))
	private String writerUrl;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String writertable;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String writerPassword;
	@SQLString(value = 2000, constrains = @Constrains(allowNull = true))
	private String preSql;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String writerUserName;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer channel;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String Remark;

	public Integer getJobID() {
		return jobID;
	}

	public void setJobID(Integer jobID) {
		this.jobID = jobID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public String getJobReader() {
		return jobReader;
	}

	public void setJobReader(String jobReader) {
		this.jobReader = jobReader;
	}

	public String getJobWriter() {
		return jobWriter;
	}

	public void setJobWriter(String jobWriter) {
		this.jobWriter = jobWriter;
	}

	public String getJsonFileName() {
		return jsonFileName;
	}

	public void setJsonFileName(String jsonFileName) {
		this.jsonFileName = jsonFileName;
	}

	public String getReaderUrl() {
		return readerUrl;
	}

	public void setReaderUrl(String readerUrl) {
		this.readerUrl = readerUrl;
	}

	public String getReadertable() {
		return readertable;
	}

	public void setReadertable(String readertable) {
		this.readertable = readertable;
	}

	public String getReaderPassword() {
		return readerPassword;
	}

	public void setReaderPassword(String readerPassword) {
		this.readerPassword = readerPassword;
	}

	public String getReaderUserName() {
		return readerUserName;
	}

	public void setReaderUserName(String readerUserName) {
		this.readerUserName = readerUserName;
	}

	public String getWriterUrl() {
		return writerUrl;
	}

	public void setWriterUrl(String writerUrl) {
		this.writerUrl = writerUrl;
	}

	public String getWritertable() {
		return writertable;
	}

	public void setWritertable(String writertable) {
		this.writertable = writertable;
	}

	public String getWriterPassword() {
		return writerPassword;
	}

	public void setWriterPassword(String writerPassword) {
		this.writerPassword = writerPassword;
	}

	public String getPreSql() {
		return preSql;
	}

	public void setPreSql(String preSql) {
		this.preSql = preSql;
	}

	public String getWriterUserName() {
		return writerUserName;
	}

	public void setWriterUserName(String writerUserName) {
		this.writerUserName = writerUserName;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}
}