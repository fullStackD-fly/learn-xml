package cn.thf.sorm.bean;

/**Java属性和get set方法
 * @author tianhf
 * @date 2020/5/24 8:38
 * @Version 1.0
 */
public class JavaFieldGetSet {
    private String fieldInfo;
    private String getInfo;
    private String setInfo;

    public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
        this.fieldInfo = fieldInfo;
        this.getInfo = getInfo;
        this.setInfo = setInfo;
    }
    public JavaFieldGetSet() {
    }

    public String getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(String fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public String getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(String getInfo) {
        this.getInfo = getInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }
}
