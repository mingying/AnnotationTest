package com.chl.sql;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.chl.anno.Column;
import com.chl.anno.Table;
import com.chl.classes.NameAndType;

public class Util {

	public static String getTableName(Class<?> bean){
		String tableName = null;
		//判读是否含有Table注解
		if(bean.isAnnotationPresent(Table.class)){
			//获取注解对象
			Table table = bean.getAnnotation(Table.class);
			tableName = table.name();
		}
		return tableName;
	}
	/**
	 * 逐个分析Bean的成员变量是否有被@Column注解，有则获取其对应的字段名与类型
	 * @param bean
	 * @return
	 */
	public static List<NameAndType> getNameAndType(Class<?> bean){
		List<NameAndType> list = new ArrayList<NameAndType>();
		Field[] fields = bean.getDeclaredFields();
		if(fields != null && fields.length > 0){
			for(int i = 0; i < fields.length; i ++){
				Field field = fields[i];
				if(field.isAnnotationPresent(Column.class)){
					// 字段名
					Column column = field.getAnnotation(Column.class);
					String name = column.name();
					String type = null;
					if(int.class.isAssignableFrom(field.getType())){
						type = "integer";
					} else if(String.class.isAssignableFrom(field.getType())){
						type  = "text";
					} else {
						throw new RuntimeException("Unsupport type = "+ field.getType().getSimpleName());
					}
					list.add(new NameAndType(type, name));
				}
			}
		}
		return list;
	}
	
	public static String createTable(Class<?> bean) {
	    String tableName = getTableName(bean);
	    List<NameAndType> columns = getNameAndType(bean);
	    if (tableName != null && !tableName.equals("") && !columns.isEmpty()) {
	        StringBuilder createTableSql = new StringBuilder("create table ");
	        //加表名
	        createTableSql.append(tableName);
	        createTableSql.append("(");

	        //加表中字段
	        for (int i = 0; i < columns.size(); i++) {
	            NameAndType column = columns.get(i);
	            createTableSql.append(column.name);
	            createTableSql.append(" ");
	            createTableSql.append(column.type);
	            // 追加下一个字段定义前需要添加逗号
	            if (i != columns.size() - 1) {
	                createTableSql.append(",");
	            }
	        }
	        createTableSql.append(")");
	        return createTableSql.toString();
	    }

	    return null;
	}
}
