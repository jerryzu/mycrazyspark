package org.rdb.build;

import java.util.Iterator;
import java.util.List;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Right;
import org.rdb.beans.TableSpace;
import org.rdb.beans.TablespaceFile;
import org.rdb.beans.User;
import org.rdb.dao.UserDao;

public class Userbuilder {
	static UserDao userDao;
	static private List<User> users;


	public static void GetRights(User user) {
		List<Right> userRight = userDao.findRightByUserName(user.getUserName());
		user.setuserRightlist(userRight);
	}
	
  public static void init() {
	  userDao = MapperFactory.createMapper(UserDao.class,
				DataSourceEnvironment.platform);
	}

	public static List<User> build() {
		init();
		users = userDao.selectAll();
		Iterator<User> iter = users.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			GetRights(user);
		}
		return users;
	}
	public static List<User> buildbyName(String userName) {
		init();
//		Users = UserDao.findByName(userName);
		return users;
	}

	public static List<User> buildbyID(Integer[] userIDs) {
		init();
		users = userDao.findByID(userIDs);
		Iterator<User> iter = users.iterator();
		while (iter.hasNext()) {
			User user = iter.next();
			GetRights(user);
		}
		return users;
	}
}
