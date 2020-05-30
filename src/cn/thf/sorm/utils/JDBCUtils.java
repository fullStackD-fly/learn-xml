package cn.thf.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author tianhf
 * @date 2020/5/23 16:57
 * @Version 1.0
 */
public class JDBCUtils {
    public  static  void  handleParamms(PreparedStatement ps,Object[] params){
        if(params !=null){
            int i=0;
            for(Object obj:params){
                try {
                    ps.setObject(++i,obj);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
