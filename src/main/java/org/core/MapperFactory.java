package org.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.rdb.core.DataSourceSqlSessionFactory;

public final class MapperFactory {

	@SuppressWarnings("unchecked")
	public static <T> T createMapper(Class<? extends Mapper> clazz,
			DataSourceEnvironment environment) {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment);
		sqlSessionFactory.getConfiguration().addMapper(clazz);
//modi by zu 20181025
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Mapper mapper = sqlSession.getMapper(clazz);
		return (T) MapperProxy.bind(mapper, sqlSession);
	}

	@SuppressWarnings("unchecked")
	public static <T> T createRegistedMapper(Class<? extends Mapper> clazz,
			DataSourceEnvironment environment) {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Mapper mapper = sqlSession.getMapper(clazz);
		return (T) MapperProxy.bind(mapper, sqlSession);
	}

	private static class MapperProxy implements InvocationHandler {
		private Mapper mapper;
		private SqlSession sqlSession;

		private MapperProxy(Mapper mapper, SqlSession sqlSession) {
			this.mapper = mapper;
			this.sqlSession = sqlSession;
		}

		@SuppressWarnings("unchecked")
		private static Mapper bind(Mapper mapper, SqlSession sqlSession) {
			return (Mapper) Proxy.newProxyInstance(mapper.getClass()
					.getClassLoader(), mapper.getClass().getInterfaces(),
					new MapperProxy(mapper, sqlSession));
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			Object object = null;
			try {
				object = method.invoke(mapper, args);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
//				sqlSession.close();
			}
			return object;
		}
	}

	private static SqlSessionFactory getSqlSessionFactory(
			DataSourceEnvironment environment) {
		return DataSourceSqlSessionFactory.getSqlSessionFactory(environment);
	}
}
