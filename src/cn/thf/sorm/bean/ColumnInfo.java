package cn.thf.sorm.bean;

import cn.thf.sorm.core.BaseEnum;

/**封装表中一个字段信息
 * @author tianhf
 * @date 2020/5/23 16:59
 * @Version 1.0
 */
public class ColumnInfo {
    private String name;
    private String dataType;
    /**
     * 键类型（0 普通键   1 主键   2 外键）
     */
    private BaseEnum.keyType keyType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public BaseEnum.keyType getKeyType() {
        return keyType;
    }

    public void setKeyType(BaseEnum.keyType keyType) {
        this.keyType = keyType;
    }

    public ColumnInfo(String name, String dataType, BaseEnum.keyType keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public ColumnInfo() {
    }
}
