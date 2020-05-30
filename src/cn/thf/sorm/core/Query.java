package cn.thf.sorm.core;

import cn.thf.sorm.bean.TableInfo;
import cn.thf.sorm.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@SuppressWarnings("all")
public abstract class Query {
    /**
     * 直接执行 sql
     * @param spl
     * @param params
     * @return 记录数
     */
    public int excuteDML(String sql,Object[] params){
        Connection conn = DBManager.getConn();
        int count=0;
        PreparedStatement ps=null;
        try {
            ps = conn.prepareStatement(sql);
            JDBCUtils.handleParamms(ps,params);
            count=ps.executeUpdate();
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            DBManager.close(ps,conn);
        }
        return count;
    }

    /**
     * 将对象存储到数据库
     * @param obj 存储的对象
     */
    public abstract void insert(Object obj);

    /**
     * 指定类的对应主键删除
     * @param clazz
     * @param Object
     * @return
     */
    public void delete(Class clazz, Object id) {
        TableInfo tableInfo =TableContext.poClassTableMap.get(clazz);
        String sql="delete from "+tableInfo.getTname()+" where "+tableInfo.getOnlyPriKey().getName()+"=?";
        excuteDML(sql,new Object[]{id});
    }
    /**
     * 删除对象在数据库中的记录
     * @param obj
     * @return
     */
    public abstract int delete(Object obj);

    /**
     * 更新对象记录并且只更新指定字段值
     * @param obj
     * @param fieldNames  更新的属性列表
     * @return
     */
    public abstract int  update(Object obj,String[] fieldNames);

    /**
     *多记录
     * @param sql
     * @param clazz  封装数据的javabean类的class对象
     * @param params
     * @return
     */
    public abstract List queryRows (String sql,Class clazz ,Object[] params);

    /**
     * 单记录
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    public abstract Object queryOne(String sql,Class clazz ,Object[] params);

    /**
     * 单记录(一行一列)
     * @param sql
     * @param params
     * @return
     */
    public abstract Object queryValue(String sql,Object[] params);

    /**
     * 单记录(一行一列)
     * @param sql
     * @param params
     * @return
     */
    public abstract Number queryNumber(String sql,Object[] params);
}
