package com.yuan.mgb;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.time.LocalDate;
import java.util.Properties;

/**
 * 可以直接实现CommentGenerator,不过方法太多,没必要.只需要继承DefaultCommentGenerator,重写需要用到的方法即可
 * @author yuan
 * @date 2019/10/11 10:50 上午
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {
    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        super.addConfigurationProperties(properties);
    }

    /**
     * 实体注释
     *
     * @param topLevelClass
     * @param introspectedTable
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //数据库表注释
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        if (null != remarks && !"".equals(remarks)) {
            topLevelClass.addJavaDocLine(" * " + remarks);
            topLevelClass.addJavaDocLine(" *");
        }
        //用户信息
        topLevelClass.addJavaDocLine(" * @author " + System.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * @date   " + LocalDate.now());
        topLevelClass.addJavaDocLine(" */");
    }

    /**
     * 字段注释
     *
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        field.addJavaDocLine("/**");
        if (null != remarks && !"".equals(remarks)) {
            field.addJavaDocLine(" * " + remarks);
        }else{
            field.addJavaDocLine(" * " + "TODO");
        }
        field.addJavaDocLine(" */");
    }
}
