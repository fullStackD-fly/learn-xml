package cn.thf.sorm.utils;

import cn.thf.sorm.bean.ColumnInfo;
import cn.thf.sorm.bean.JavaFieldGetSet;
import cn.thf.sorm.bean.TableInfo;
import cn.thf.sorm.core.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author tianhf
 * @date 2020/5/23 16:58
 * @Version 1.0
 */
public class JavaFileUtils {
    /**
     * 根据字段信息生成Java属性信息及getset方法
     * @param columnInfo
     * @param convertor
     * @return
     */
    public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo columnInfo, TypeConvertor convertor){
        JavaFieldGetSet javaFieldGetSet=new JavaFieldGetSet();
        String javaFieldType= convertor.databaseType2JavaType(columnInfo.getDataType());
        javaFieldGetSet.setFieldInfo( "\tprivate "+javaFieldType+""+columnInfo.getName()+";/n");
        StringBuilder getSrc=new StringBuilder();
        getSrc.append("public "+javaFieldType+" get"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"(){\n");
        getSrc.append("\t\treturn "+columnInfo.getName()+"\n");
        getSrc.append("\t}\n");
        javaFieldGetSet.setGetInfo(getSrc.toString());

        StringBuilder setSrc=new StringBuilder();
        setSrc.append("public void set"+StringUtils.firstChar2UpperCase(columnInfo.getName())+"(");
        setSrc.append(javaFieldType+""+columnInfo.getName()+"){\n");
        setSrc.append("\t\tthis."+columnInfo.getName()+"="+columnInfo.getName()+";\n");
        setSrc.append("\t}\n");
        javaFieldGetSet.setSetInfo(setSrc.toString());

        return javaFieldGetSet;
    }

    /**
     * 通过表结构生成实体类
     * @param tableInfo
     * @param convertor
     */
    public static  void createJavaPOFile(TableInfo tableInfo ,TypeConvertor convertor ){
       String src = CreateJavaSrc(tableInfo,convertor);
       String srcpath= DBManager.getConf().getSrcPath()+"\\";

       String poPackagePath=DBManager.getConf().getPoPackage().replaceAll("\\.","/");
       File f = new File(srcpath+poPackagePath);
       if (!f.exists())
           f.mkdirs();
       BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firstChar2UpperCase(tableInfo.getTname())+".java"));
            bw.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                  if (bw != null)
                      bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String CreateJavaSrc(TableInfo tableInfo, TypeConvertor convertor) {
        return  null;
    }


    public static void main(String[] args){
      //  ColumnInfo columnInfo = new ColumnInfo("username","varchar",BaseEnum.keyType.ONE);

      //  System.out.println(createFieldGetSetSRC(columnInfo, new MySqlTypeConvertor()));
       // System.out.println(createJavaPOFile());
        Map<String,TableInfo> map= TableContext.tables;
    }
}
