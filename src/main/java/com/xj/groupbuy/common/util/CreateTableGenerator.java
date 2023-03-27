package com.xj.groupbuy.common.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class CreateTableGenerator {

    public static void main(String[] args) {
        // 指定包名，扫描该包下的类
        String packageName = "com.xj.groupbuy.entity";
        // 指定生成的表名前缀
        String tablePrefix = "";

        List<Class<?>> classes = getClasses(packageName);
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(TableName.class)) {
                TableName tableNameAnnotation = clazz.getAnnotation(TableName.class);
                String tableName = tablePrefix + tableNameAnnotation.value();
                StringBuilder sb = new StringBuilder("CREATE TABLE ");
                sb.append(tableName).append(" (");

                boolean hasPrimaryKey = false;
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(TableField.class) && field.getAnnotation(TableField.class).exist()) {
                        TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
                        String fieldName = tableFieldAnnotation.value();
                        if (fieldName.isEmpty()) {
                            fieldName = field.getName();
                        }
                        sb.append(fieldName).append(" ");
                        if (field.isAnnotationPresent(TableId.class)) {
                            TableId tableIdAnnotation = field.getAnnotation(TableId.class);
                            sb.append(tableIdAnnotation.type().name()).append(" ");
                            hasPrimaryKey = true;
                        } else {
                            sb.append(getColumnType(field)).append(" ");
                        }
                        sb.append("COMMENT '").append(tableFieldAnnotation.value()).append("', ");
                    }
                }

                if (!hasPrimaryKey) {
                    // 如果没有主键，则自动创建一个id作为主键
                    sb.append("id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键', ");
                }

                sb.delete(sb.length() - 2, sb.length() - 1).append(")");
                System.out.println(sb.append(";"));
            }
        }
    }

    /**
     * 获取指定包名下的所有类
     * @param packageName 包名
     * @return 类列表
     */
    private static List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace(".", "/");
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File directory = new File(resource.getFile());
                if (directory.exists()) {
                    File[] files = directory.listFiles();
                    for (File file : files) {
                        String fileName = file.getName();
                        if (fileName.endsWith(".class")) {
                            String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                            Class<?> clazz = Class.forName(className);
                            classes.add(clazz);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 获取字段类型对应的MySQL数据类型
     * @param field 字段类型
     * @return MySQL数据类型
     */
    private static String getColumnType(Field field) {
        Class<?> fieldType = field.getType();
        if (String.class.equals(fieldType)) {
            return "VARCHAR(255)";
        } else if (Integer.class.equals(fieldType) || int.class.equals(fieldType)) {
            return "INT";
        } else if (Long.class.equals(fieldType) || long.class.equals(fieldType)) {
            return "BIGINT";
        } else if (Double.class.equals(fieldType) || double.class.equals(fieldType)) {
            return "DOUBLE";
        } else if (Float.class.equals(fieldType) || float.class.equals(fieldType)) {
            return "FLOAT";
        } else if (Boolean.class.equals(fieldType) || boolean.class.equals(fieldType)) {
            return "BIT";
        } else if (BigDecimal.class.equals(fieldType)) {
            return "DECIMAL(19,4)";
        } else if (Date.class.equals(fieldType)) {
            return "DATETIME";
        } else {
            throw new RuntimeException("Unsupported field type: " + fieldType.getSimpleName());
        }
    }

    /**
     * 生成建表语句
     */
    private static String generateCreateTableSql(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        //获取表名
        TableName tableAnnotation = clazz.getAnnotation(TableName.class);
        String tableName = tableAnnotation.value();
        sb.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (\n");

        //获取主键字段
        List<Field> idFields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(TableId.class)) {
                idFields.add(field);
            }
        }
        if (idFields.isEmpty()) {
            throw new RuntimeException("No @TableId annotation found on class " + clazz.getSimpleName());
        }
        //只处理第一个主键字段
        Field idField = idFields.get(0);
        String idColumnName = idField.getName();
        String idColumnType = getColumnType(idField);
        sb.append("\t").append(idColumnName).append(" ").append(idColumnType).append(" NOT NULL AUTO_INCREMENT,\n");

        //获取其他字段
        for (Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue; //忽略静态字段
            }
            if (field.isAnnotationPresent(TableField.class)) {
                TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
                String columnName = tableFieldAnnotation.value();
                String columnType = getColumnType(field);
                sb.append("\t").append(columnName).append(" ").append(columnType).append(",\n");
            }
        }
        sb.append("\tPRIMARY KEY (").append(idColumnName).append(")\n");
        sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;");
        return sb.toString();
    }
}