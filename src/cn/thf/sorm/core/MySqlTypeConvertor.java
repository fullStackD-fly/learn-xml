package cn.thf.sorm.core;

/**
 * @author tianhf
 * @date 2020/5/24 10:00
 * @Version 1.0
 */
public class MySqlTypeConvertor implements TypeConvertor {

    @Override
    public String databaseType2JavaType(String columnType) {

        //varchar-->String
        if("varchar".equalsIgnoreCase(columnType)||"char".equalsIgnoreCase(columnType)){
            return "String";
        }else if("int".equalsIgnoreCase(columnType)
                ||"tinyint".equalsIgnoreCase(columnType)
                ||"smallint".equalsIgnoreCase(columnType)
                ||"integer".equalsIgnoreCase(columnType)
        ){
            return "Integer";
        }else if("bigint".equalsIgnoreCase(columnType)){
            return "Long";
        }else if("double".equalsIgnoreCase(columnType)||"float".equalsIgnoreCase(columnType)){
            return "Double";
        }else if("clob".equalsIgnoreCase(columnType)){
            return "java.sql.CLob";
        }else if("blob".equalsIgnoreCase(columnType)){
            return "java.sql.BLob";
        }else if("date".equalsIgnoreCase(columnType)){
            return "java.sql.Date";
        }else if("time".equalsIgnoreCase(columnType)){
            return "java.sql.Time";
        }else if("timestamp".equalsIgnoreCase(columnType)){
            return "java.sql.Timestamp";
        }

        return null;
    }

    @Override
    public String javaType2DatabaseType(String javaDataType) {
        return null;
    }

}

