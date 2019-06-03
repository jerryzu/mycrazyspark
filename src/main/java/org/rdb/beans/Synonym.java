package org.rdb.beans;

import org.rdb.wrapper.Constrains;
import org.rdb.wrapper.DBTable;
import org.rdb.wrapper.SQLBoolean;
import org.rdb.wrapper.SQLInteger;
import org.rdb.wrapper.SQLString;

@DBTable(name = "SYS_SYNONYMS")
public class Synonym implements ICanRegist {
	@SQLInteger(constrains = @Constrains(primaryKey = true, unique = true))
	private Integer synonymID;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String synonymOwner;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String synonymName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableOwner;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String tableName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String dbLink;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String TopicName;
	@SQLString(value = 32, constrains = @Constrains(allowNull = true))
	private String GrantOn;
	@SQLBoolean
	private boolean Enabled;
	
	public Synonym() {
	}

	public Integer getSynonymID() {
		return synonymID;
	}

	public void setSynonymID(Integer synonymID) {
		this.synonymID = synonymID;
	}

	public String getSynonymName() {
		return synonymName;
	}

	public void setSynonymName(String synonymName) {
		this.synonymName = synonymName;
	}

	/* (non-Javadoc)
	 * @see org.rdb.beans.ICanRegist#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return Enabled;
	}

	/* (non-Javadoc)
	 * @see org.rdb.beans.ICanRegist#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public String getSynonymOwner() {
		return synonymOwner;
	}

	public void setSynonymOwner(String synonymOwner) {
		this.synonymOwner = synonymOwner;
	}

	public String getTableOwner() {
		return tableOwner;
	}

	public void setTableOwner(String tableOwner) {
		this.tableOwner = tableOwner;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDbLink() {
		return dbLink;
	}

	public void setDbLink(String dbLink) {
		this.dbLink = dbLink;
	}

	public String getGrantOn() {
		return GrantOn;
	}

	public void setGrantOn(String grantOn) {
		this.GrantOn = grantOn;
	}

	/* (non-Javadoc)
	 * @see org.rdb.beans.ICanRegist#getTopicName()
	 */
	@Override
	public String getTopicName() {
		return TopicName;
	}

	/* (non-Javadoc)
	 * @see org.rdb.beans.ICanRegist#setTopicName(java.lang.String)
	 */
	@Override
	public void setTopicName(String topicName) {
		this.TopicName = topicName;
	}
}