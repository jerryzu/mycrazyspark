package org.rdb.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.core.DataSourceEnvironment;
import org.core.MapperFactory;
import org.rdb.beans.Right;
import org.rdb.beans.User;
import org.rdb.build.Userbuilder;
import org.rdb.core.FreeMarkerExportWay;
import org.rdb.core.FreeMarkerFileType;
import org.rdb.core.SimpleFreeMarkerFactory;
import org.rdb.dao.UserDao;

public class UserFactory implements IFactory {
	static Map<String, Object> root = new HashMap<String, Object>();
	static private List<User> users;

	public static List<User> getUsers() {
		return users;
	}

	public static void main(String[] args) {
		Build();
	}	

	public static void done() {
		users = Userbuilder.build();
		root.put("userlist", users);
	}

	public static void Build() {
		done();
		SimpleFreeMarkerFactory.setExportInfo(FreeMarkerFileType.ora,
				FreeMarkerExportWay.screen);
		SimpleFreeMarkerFactory.analysisTemplate("user", "user-demo.ftl", root);
	}
}