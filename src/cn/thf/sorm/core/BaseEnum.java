package cn.thf.sorm.core;

/**
 * @author tianhf
 * @date 2020/5/23 22:43
 * @Version 1.0
 */
public class BaseEnum {
    public enum keyType{
        ZERO("普通键"),
        ONE("主键"),
        TWO("外键");
        private String desc;//中文描述
        private keyType(String desc){
            this.desc=desc;
        }
        public String getDesc(){
            return this.desc;
        }
    }
}
