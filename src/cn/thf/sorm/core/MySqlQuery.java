package cn.thf.sorm.core;

import cn.thf.sorm.bean.TableInfo;
import cn.thf.sorm.utils.JDBCUtils;
import cn.thf.sorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tianhf
 * @date 2020/5/29 22:22
 * @Version 1.0
 */
public class MySqlQuery extends Query {


    /**
     * 将对象中不为null的属性往数据库中进行insert
     * @param obj 存储的对象
     */
    @Override
    public void insert(Object obj) {
        List params=new ArrayList();
        Class clazz=obj.getClass();
        TableInfo tableInfo =TableContext.poClassTableMap.get(obj.getClass());
        Field[] fs= clazz.getDeclaredFields();
        StringBuilder sql=new StringBuilder("insert into "+tableInfo.getTname()+"(");
        for(Field field:fs){
            String fieldName = field.getName();
            Object fieldValue=ReflectUtils.invokeGet(fieldName,obj);
            if(fieldValue!=null){
                sql.append(fieldName+",");
                params.add(fieldValue);
            }
        }
        sql.setCharAt(sql.length()-1,')');
        sql.append(" values (");
        for(int i=0;i<params.size();i++){
            sql.append("?,");
        }
        sql.setCharAt(sql.length()-1,')');
        excuteDML(sql.toString(),params.toArray());
    }



    @Override
    public int delete(Object obj) {
        TableInfo tableInfo =TableContext.poClassTableMap.get(obj.getClass());
        Object PriKeyValue= ReflectUtils.invokeGet(tableInfo.getOnlyPriKey().getName(),obj);
        delete(obj.getClass(),PriKeyValue);
        return 0;
    }

    @Override
    public int update(Object obj, String[] fieldNames) {
        TableInfo tableInfo =TableContext.poClassTableMap.get(obj.getClass());
        Object PriKeyValue= ReflectUtils.invokeGet(tableInfo.getOnlyPriKey().getName(),obj);
        List params=new ArrayList();
        StringBuilder sql=new StringBuilder("update "+tableInfo.getTname()+" set ");
        for(String fieldName:fieldNames){
            Object fieldValue = ReflectUtils.invokeGet(fieldName, obj);
            params.add(fieldValue);
            sql.append(fieldName+"=?,");
        }
        sql.setCharAt(sql.length()-1,' ');
        sql.append("where ");
        sql.append(tableInfo.getOnlyPriKey().getName()+"=?");
        params.add(ReflectUtils.invokeGet(tableInfo.getOnlyPriKey().getName(), obj));
        excuteDML(sql.toString(),params.toArray());
        return 0;
    }

    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
         Connection conn = DBManager.getConn();
         List list=new ArrayList();
         PreparedStatement ps=null;
         ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            JDBCUtils.handleParamms(ps,params);
            rs=ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()){
                Object object=clazz.newInstance();
                for (int i=0;i<metaData.getColumnCount();i++) {
                   String columnName=metaData.getColumnLabel(i+1);
                   Object columnValue=rs.getObject(i+1);
                   ReflectUtils.invokeSet(object,columnName,columnValue);
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Object queryOne(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);
        return (list==null||list.size()==0)?null:list.get(0);
    }

    @Override
    public Object queryValue(String sql, Object[] params) {

        Connection conn = DBManager.getConn();
        Object obj=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            JDBCUtils.handleParamms(ps,params);
            rs=ps.executeQuery();
            while (rs.next()){
                obj=rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Number queryNumber(String sql, Object[] params) {

        return (Number) queryValue(sql,params);
    }
}
