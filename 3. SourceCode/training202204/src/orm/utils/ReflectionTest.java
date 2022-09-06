package orm.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Events;
import entity.RegistEvents;
import entity.Users;
import orm.DBConnection;
import orm.annotation.Column;
import orm.annotation.Id;
import orm.annotation.ManyToOne;
import orm.annotation.OneToMany;

public class ReflectionTest {
	/*
	 * get list column name by class
	 * Example: Users.class -> Users.id,Users.userName,Users.fullName,Users.departmentId,Users.email...
	 * */
	public static StringBuilder getListColumnName(Class<?> tClass) {
		StringBuilder listColumnName = new StringBuilder();
		String tableName = tClass.getSimpleName();
		Field[] fields = tClass.getDeclaredFields();
		
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
//				if(!field.isAnnotationPresent(OneToMany.class))
//					listColumnName.append(tableName).append(".").append(field.getName());
//				if(field.isAnnotationPresent(ManyToOne.class))
//					listColumnName.append("Id");
//				
//				listColumnName.append(" AS ");
//				listColumnName.append(tableName).append("_").append(field.getName());
//				
//				if(field.isAnnotationPresent(ManyToOne.class))
//					listColumnName.append("Id");
//				
//				listColumnName.append(",");
				if(!field.isAnnotationPresent(OneToMany.class)) {
					listColumnName.append(tableName).append(".").append(field.getName());
					if(field.isAnnotationPresent(ManyToOne.class))
						listColumnName.append("Id");
					
					listColumnName.append(" AS ");
					listColumnName.append(tableName).append("_").append(field.getName());
					
					if(field.isAnnotationPresent(ManyToOne.class))
						listColumnName.append("Id");
					
					listColumnName.append(",");
				}
			}
		}
		return listColumnName;
	}
	
	/*
	 * Create condition join two table by class
	 * Example: Users.class join Department.class -> Users.departmentId = Department.id 
	 * */
	public static StringBuilder conditionJoin(Class<?> tClassLeft, Class<?> tClassRight) {
		String classLeftName = tClassLeft.getSimpleName();
		String classRightName = tClassRight.getSimpleName();
		String classLeftNameLower = classLeftName.toLowerCase();
		String classRightNameLower = classRightName.toLowerCase();
		
		StringBuilder query = new StringBuilder();
		
		query.append(classLeftName).append(".");
		
		if(isOneToMany(tClassLeft, tClassRight)) {
			query.append("id");
			query.append("=");
			query.append(classRightName).append(".").append(classLeftNameLower).append("Id");
		}
		else if(isManyToOne(tClassLeft, tClassRight)) {
			query.append(classRightNameLower).append("Id");
			query.append("=");
			query.append(classRightName).append(".id");
		}
		return query;
	}
	
	/*
	 * check field is one to many
	 */
	public static boolean isOneToMany(Class<?> tClassLeft,Class<?> tClassRight) {
		String fieldName = tClassRight.getSimpleName();
		fieldName= fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), String.valueOf(fieldName.charAt(0)).toLowerCase());
		try {
			Field field = tClassLeft.getDeclaredField(fieldName);
			if(field.isAnnotationPresent(OneToMany.class))
				return true;
		} catch (NoSuchFieldException | SecurityException e) {
		}
		return false;
	}
	/*
	 * check field is many to one
	 */
	public static boolean isManyToOne(Class<?> tClassLeft,Class<?> tClassRight) {
		String fieldName = tClassRight.getSimpleName();
		fieldName= fieldName.replaceFirst(String.valueOf(fieldName.charAt(0)), String.valueOf(fieldName.charAt(0)).toLowerCase());
		try {
			Field field = tClassLeft.getDeclaredField(fieldName);
			if(field.isAnnotationPresent(ManyToOne.class))
				return true;
		} catch (NoSuchFieldException | SecurityException e) {
		}
		return false;
	}
	public static <T> T mappingToEntity(ResultSet rs, Class<T> tClass) {
		T t=null;
		try {
			t = tClass.newInstance();
			Field[] fields = tClass.getDeclaredFields();
			String columnName = tClass.getSimpleName();
			StringBuilder resultSetfieldName = new StringBuilder();
			for(Field field : fields) {
				field.setAccessible(true);
				if((field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) && !(field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToOne.class)))  {
					resultSetfieldName.append(columnName).append("_").append(field.getName());
					try {
						field.set(t,rs.getObject(resultSetfieldName.toString()));
					}
					catch(Exception e) {	
					}
					resultSetfieldName.setLength(0);
				}
				if((field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToMany.class))) {
					if(field.isAnnotationPresent(ManyToOne.class)) {
						T tColumn = (T) mappingToObjectEntity(rs,field.getAnnotation(ManyToOne.class).mapping());
						field.set(t, tColumn);
					}
					if(field.isAnnotationPresent(OneToMany.class)) {
						T temp = (T) mappingToObjectEntity(rs, field.getAnnotation(OneToMany.class).mapping());
						field.set(t,temp);
					}
				}
			}
		} catch (InstantiationException | IllegalAccessException |IllegalArgumentException e) {
			e.printStackTrace();
			
		}
		return t;
	}
	public static <T> T mappingToObjectEntity(ResultSet rs, Class<T> tClass) {
		T t=null;
		try {
			t = tClass.newInstance();
			Field[] fields = tClass.getDeclaredFields();
			String columnName = tClass.getSimpleName();
			StringBuilder resultSetfieldName = new StringBuilder();
			for(Field field : fields) {
				field.setAccessible(true);
				if((field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) && !(field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToOne.class)))  {
					resultSetfieldName.append(columnName).append("_").append(field.getName());
					try {
						field.set(t,rs.getObject(resultSetfieldName.toString()));
					} catch (Exception e) {
						// TODO: handle exception
					}
					resultSetfieldName.setLength(0);
				}
			}
			return t;
		} catch (InstantiationException | IllegalAccessException |IllegalArgumentException | StackOverflowError e) {
			e.printStackTrace();
			return t;
		}
	}
	
	public static <T> List<T> joinTableQuery(Class<T> typeClass,Class<?>... classes) throws SQLException {
		StringBuilder query = new StringBuilder("SELECT ");
		for(Class<?> tClass : classes) {
			query.append(getListColumnName(tClass));
		}
		query.deleteCharAt(query.length()-1);
		query.append(" FROM ");
		for(int i=0;i<classes.length-1;i++) {
			if(i==0) {
				query.append(classes[i].getSimpleName()).append(" ").append(classes[i].getSimpleName());
			}	
			query.append(" INNER JOIN ");
			query.append(classes[i+1].getSimpleName()).append(" ").append(classes[i+1].getSimpleName());
			query.append(" ON ");
			query.append(conditionJoin(classes[i], classes[i+1]));
		}
		System.out.println(query.toString());
		String sql = query.toString();
		Connection conn = new DBConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<T> tList = new ArrayList<T>();
		while(rs.next()) {
			T t = (T) mappingToEntity(rs,typeClass );
			tList.add(t);
		}
		return tList;
	}
	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException {
		List<Events> events = joinTableQuery(Events.class,RegistEvents.class,Events.class,Users.class);
		events.stream().forEach(
				event->{
					System.out.println(event);
				}
				);
	}
	
}
