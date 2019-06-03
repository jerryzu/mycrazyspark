package org.rdb.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.core.DataSourceEnvironment;

public final class DataSourceSqlSessionFactory {
	static String resource = "Configuration.xml";
	static org.apache.logging.log4j.Logger logger = LogManager.getLogger(DataSourceSqlSessionFactory.class.getName());

	private static final Map<DataSourceEnvironment, SqlSessionFactory> SqlSessionFactoryMap = new HashMap<DataSourceEnvironment, SqlSessionFactory>();

	public static SqlSessionFactory getSqlSessionFactory(DataSourceEnvironment environment) {
		SqlSessionFactory sqlSessionFactory = SqlSessionFactoryMap.get(environment);

		if (ObjectUtils.IsNotNull(sqlSessionFactory))
			return sqlSessionFactory;
		else {
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.name());
				logger.info("Get {" + environment.name() + "} SqlSessionFactory successfully.");
			} catch (IOException e) {
				logger.warn("Get {} SqlSessionFactory error.");
				logger.error(e.getMessage(), e);
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			SqlSessionFactoryMap.put(environment, sqlSessionFactory);
			return sqlSessionFactory;
		}
	}
}
