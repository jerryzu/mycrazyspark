package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.core.Mapper;
import org.rdb.beans.Field;
import org.rdb.beans.Table;

public interface FieldDao  extends Mapper{
	@Select("select * from sys_fields  order by tableID, fieldID")
	public List<Field> selectAll();	

	@Select("select * from sys_fields WHERE tableName = #{tableName} order by tableID, fieldID")
	public List<Field> findByTable(String tableName);
	@Select("select * from sys_fields WHERE tableID = #{tableID} order by tableID, fieldID")
	public List<Field> findByTableID(Integer TableID);
		
	@Select("select * from sys_fields WHERE STATUS = 1 AND tableName like #{tableName} order by tableID, fieldID asc")
	public List<Field> findByRenewTablelike(String tableName);	
	
	@Select("select * from sys_fields WHERE STATUS = 2 AND fieldName like #{fieldName} order by tableID, fieldID asc")
	public List<Field> findByRenewFieldLike(String tableName);
	
	@Select("select FieldID, FieldName, FieldDesc, DataType, FieldLen, Remark from SYS_FIELDSTEMPLATE WHERE TABLELAYER = #{tableLayer}  order by tableID, fieldID")
	public List<Field> findFromTemplate(String tableLayer);

	@Insert("insert into sys_fields(schemaName, tableID, tableName, fieldID, fieldName"
			+ ", dataType, fieldLen, precision_, scale_, charused, deliverTableID, deliverFieldID, remark) "
			+ "values(#{schemaName}, #{tableID}, #{tableName}, #{fieldID}, #{fieldName}"
			+ ", #{dataType}, #{fieldLen}, #{precision}, #{scale}, #{charUsed}, #{deliverTableID}, #{deliverFieldID}, #{remark})")
	public int insert(Field field);

	@Update("update sys_fields set schemaName =#{schemaName}, tableID =#{tableID}, tableName =#{tableName}, "
			+ "fieldName =#{fieldName}, fieldID =#{fieldID},"
			+ "dataType =#{dataType}, fieldLen =#{fieldLen}, "
			+ "precision_ =#{precision}, scale_ =#{scale}, "
			+ "charused =#{charUsed}, remark = #{remark} "
			+ "where tableName =#{tableName} and fieldName = #{fieldName} ")
	public int update(Field field);
}