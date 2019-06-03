package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_SEQUENCES")
public class Sequence {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer sequenceID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String sequenceName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String schemaName;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private double minValue;
	@SQLInteger(name = "maxValue_", constrains = @Constrains(primaryKey = true, unique = true))
	private double maxValue_;
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private double increment_;
	@SQLInteger(name = "start_", constrains = @Constrains(primaryKey = true, unique = true))
	private double start_;
	@SQLBoolean
	private boolean noMaxValue;
	@SQLBoolean
	private boolean cacheType;
	@SQLBoolean
	private boolean cycleType;

	public Sequence() {
	}

	public Sequence(Integer SequenceID, String SchemaName, String SequenceName,
			Integer MinValue, Integer MaxValue, Integer Increment,
			Integer Start, boolean NoMaxValue, boolean CacheType,
			boolean CycleType) {
		sequenceID = SequenceID;
		schemaName = SchemaName;
		sequenceName = SequenceName;
		minValue = MinValue;
		maxValue_ = MaxValue;
		increment_ = Increment;
		start_ = Start;
		noMaxValue = NoMaxValue;
		cacheType = CacheType;
		cycleType = CycleType;
	}

	public Integer getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(Integer SequenceID) {
		sequenceID = SequenceID;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String SequenceName) {
		sequenceName = SequenceName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String SchemaName) {
		schemaName = SchemaName;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxValue() {
		return maxValue_;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue_ = maxValue;
	}

	public double getIncrement_() {
		return increment_;
	}

	public void setIncrement_(Integer increment) {
		this.increment_ = increment;
	}

	public double getStart_() {
		return start_;
	}

	public void setStart_(double start) {
		this.start_ = start;
	}

	public boolean isNoMaxValue() {
		return noMaxValue;
	}

	public void setNoMaxValue(boolean noMaxValue) {
		this.noMaxValue = noMaxValue;
	}

	public boolean isCacheType() {
		return cacheType;
	}

	public void setCacheType(boolean cacheType) {
		this.cacheType = cacheType;
	}

	public boolean isCycleType() {
		return cycleType;
	}

	public void setCycleType(boolean cycleType) {
		this.cycleType = cycleType;
	}
}