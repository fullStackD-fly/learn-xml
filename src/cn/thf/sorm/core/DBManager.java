package cn.thf.sorm.core;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import cn.thf.sorm.bean.Configuration;
import  cn.thf.sorm.pool.DBConnPool;
/**根据配置信息，维持数据库连接信息
 * @author tianhf
 * @date 2020/5/23 16:56
 * @Version 1.0
 */
public class DBManager {
    /**
     * 配置信息
     */
    private static Configuration conf;
    /**
     * 连接池对象
     */
    private static DBConnPool pool;
    static {  //静态代码块
        Properties pros = new Properties();
        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setQueryClass(pros.getProperty("queryClass"));
        conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
        conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
    }

    /**
     * 获得Connection对象
     * @return
     */
    public static Connection getConn(){
        if(pool==null){
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }

    /**
     * 创建新的Connection对象
     * @return
     */
    public static Connection createConn(){
        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(),
                    conf.getUser(),conf.getPwd());     //直接建立连接，后期增加连接池处理，提高效率！！！
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭传入的ResultSet、Statement、Connection对象
     * @param rs
     * @param ps
     * @param conn
     */
    public static void close(ResultSet rs,Statement ps,Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.close(conn);

    }

    /**
     * 关闭传入的Statement、Connection对象
     * @param ps
     * @param conn
     */
    public static void close(Statement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.close(conn);

    }
    public static void close(Connection conn){
        pool.close(conn);
    }

    /**
     * 返回Configuration对象
     * @return
     */
    public static Configuration getConf(){
        return conf;
    }


}

