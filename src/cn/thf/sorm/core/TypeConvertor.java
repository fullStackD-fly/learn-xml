package cn.thf.sorm.core;

/**
 * @author tianhf
 * @date 2020/5/23 16:43
 * @Version 1.0
 */
public interface TypeConvertor {
    /**
     *将数据库类型转Java类型
     * @param columnType
     * @return
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java类型转数据库类型
     * @param javaTypeData
     * @return
     */
    public String javaType2DatabaseType(String javaTypeData);

}
