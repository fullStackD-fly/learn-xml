package cn.thf.sorm.bean;

import java.util.List;
import java.util.Map;

/**存储表结构信息
 * @author tianhf
 * @date 2020/5/23 23:11
 * @Version 1.0
 */
public class TableInfo {
    private String tname;
    private Map<String,ColumnInfo> columns;
    private ColumnInfo onlyPriKey;

    public TableInfo(String tname, Map<String, ColumnInfo> columns, List<ColumnInfo> priKeys) {
        this.tname = tname;
        this.columns = columns;
        this.priKeys = priKeys;
    }

    /**
     * 如果联合主键，则在这里存储
     */
    private List<ColumnInfo> priKeys;

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }
    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }
    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public TableInfo(String tname, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
        this.tname = tname;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }
    public TableInfo() {
    }
}
