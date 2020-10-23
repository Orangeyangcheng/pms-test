package mybatis.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public final class MapperFactory {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MapperFactory.class);
    /**
     * Create a mapper of environment by Mapper class
     * @param clazz Mapper class
     * @param environment A datasource environment
     * @return a Mapper instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T createMapper(Class<? extends Mapper> clazz, DataSourceSqlSessionFactory.DataSourceEnvironment environment) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment);
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        Mapper mapper = sqlSession.getMapper(clazz);
        return (T)MapperProxy.bind(mapper, sqlSession);
    }

    /**
     * Mapper Proxy
     * executing mapper method and close sqlsession
     * @author boyce
     * @version 2014-4-9
     */
    private static class MapperProxy implements InvocationHandler {
        private Mapper mapper;
        private SqlSession sqlSession;

        private MapperProxy(Mapper mapper, SqlSession sqlSession) {
            this.mapper = mapper;
            this.sqlSession = sqlSession;
        }

        @SuppressWarnings("unchecked")
        private static Mapper bind(Mapper mapper, SqlSession sqlSession) {
            return (Mapper) Proxy.newProxyInstance(mapper.getClass().getClassLoader(),
                    mapper.getClass().getInterfaces(), new MapperProxy(mapper, sqlSession));
        }

        /**
         * execute mapper method and finally close sqlSession
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object object = null;
            try {
                object = method.invoke(mapper, args);
            } catch(Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
            } finally {
                sqlSession.close();
            }
            return object;
        }
    }

    //Get a SqlSessionFactory of environment
    private static SqlSessionFactory getSqlSessionFactory(DataSourceSqlSessionFactory.DataSourceEnvironment environment) throws IOException {
        return DataSourceSqlSessionFactory.getSqlSessionFactory(environment);
    }
}
