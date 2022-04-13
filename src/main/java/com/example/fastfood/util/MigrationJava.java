package com.example.fastfood.util;

import com.example.fastfood.annotation.Column;
import com.example.fastfood.annotation.Table;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Set;

public class MigrationJava {
    public static void main(String[] args){
        Reflections reflections = new Reflections("com.example.fastfood");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        for (Class<?> table : annotated){
            createTableFromEntity(table);
        }
    }

    public static void createTableFromEntity(Class clazz) {
        if (!clazz.isAnnotationPresent(Table.class))
            return;

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE");
        sqlStringBuilder.append(" ");

        System.out.println("________");

        String tableName = clazz.getSimpleName().toLowerCase() +"s";
        Table table = (Table) clazz.getDeclaredAnnotation(Table.class);
        if (table.name() != null && table.name().length() > 0){
            tableName = table.name();
        }
        sqlStringBuilder.append(tableName);
        sqlStringBuilder.append(" ");
        sqlStringBuilder.append("(");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++){
            Field field = fields[i];
            String columnName = field.getName();
            String columnType = "";
            if (field.getType().getSimpleName().contains("String")){
                columnType = "VARCHAR(250)";
            }else {
                columnType = field.getType().getSimpleName();
            }
            if (field.isAnnotationPresent(Column.class)){
                Column column = field.getDeclaredAnnotation(Column.class);
                columnName = column.name();
                columnType = column.type();
            }
            sqlStringBuilder.append(columnName);
            sqlStringBuilder.append(" ");
            sqlStringBuilder.append(columnType);

            sqlStringBuilder.append(",");
        }

        sqlStringBuilder.setLength(sqlStringBuilder.length() -1);
        sqlStringBuilder.append(")");
        System.out.println(sqlStringBuilder.toString());
        try {
            Connection connection = ConnectionHelper.getConnection();
            Statement dropStatement = connection.createStatement();
            dropStatement.execute(String.format("DROP TABLE %s", tableName));
            System.out.println(String.format("Drop table %s succsess!",tableName));
        }catch (SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        try{
            Connection connection = ConnectionHelper.getConnection();
            Statement stt = connection.createStatement();
            stt.execute(sqlStringBuilder.toString());
            System.out.println(String.format("Create table %s success!", tableName));
        }catch (SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
}
