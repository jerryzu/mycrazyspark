package org.rdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.core.Mapper;
import org.rdb.beans.Right;
import org.rdb.beans.Table;
import org.rdb.beans.User;

public interface UserDao extends Mapper {
	@Insert("insert into SYS_USERS(userid,userName,password,IsLock,DefaultTableSpace,TemporaryTableSpace,Remark) "
			+ "values(#{UserID},#{userName},#{password},#{IsLock},#{DefaultTableSpace},#{TemporaryTableSpace},#{Remark})")
	public int insert(User user);

	@Update("update SYS_USERS set userName=#{userName},password=#{password}, IsLock=#{IsLock}, "
			+ "	DefaultTableSpace=#{DefaultTableSpace}, TemporaryTableSpace=#{TemporaryTableSpace}, Remark=#{Remark} "
			+ " where userName=#{userName}")
	public int update(User user);

	@Delete("delete from SYS_USERS where userName=#{userName}")
	public int delete(String userName);

	@Select("select * from SYS_USERS order by userID asc")
	public List<User> selectAll();

	@Select("select count(*) c from SYS_USERS")
	public int countAll();

	@Select("select * from SYS_USERS where userName=#{userName}")
	public User findByUserName(String userName);

	@Select("select * from SYS_RIGHTS WHERE STATUS = 1")
	public List<Right> findRightByUserName(String userName);

	@Select("select * from SYS_RIGHTS WHERE STATUS = 1")
	public List<Right> findAllRightByValidUserName(String userName);

	@Select({ "<script>", "select * from SYS_USERS WHERE userid in ",
			"<foreach collection='userLists' item='user' index='index' separator=','  open='(' close=')' >", "#{user}",
			"</foreach>", " order by userid asc", "</script>" })
	public List<User> findByID(@Param(value = "userLists") Integer[] users);
}