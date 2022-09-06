package orm.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import entity.Department;
import entity.Events;
import entity.RegistEvents;
import entity.Users;
import orm.DBConnection;
import orm.annotation.Column;
import orm.annotation.Id;
import orm.annotation.Ignore;
import orm.annotation.ManyToOne;
import orm.annotation.OneToMany;
import orm.annotation.Table;

public class ReflectionUtils {
	public static <T> List<T> convertToEntity(ResultSet rs, Class<T> tClass)
			throws SQLException, InstantiationException, IllegalAccessException {
		List<T> objects = new ArrayList<T>();
		T t;
		while (rs.next()) {
			t = tClass.newInstance();
			Field fields[] = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					String fieldName = field.getName();
					System.out.println(fieldName);
					field.set(t, rs.getObject(fieldName));
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			objects.add(t);
		}
		return objects;
	}
	

	public static <T> void setPreparedStatementInsert(PreparedStatement ps, T t)
			throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException, NoSuchFieldException, SecurityException {
		Field fields[] = t.getClass().getDeclaredFields();
		int index = 1;
		Object value;
		for (Field field : fields) {
			if (field.isAnnotationPresent(Column.class)) {
				field.setAccessible(true);
				value = field.get(t);
				if (value != null) {
					if(!field.isAnnotationPresent(ManyToOne.class)) {
						ps.setObject(index++, value);
					}
					else {
						Object objId = value;
						Field fieldObjId = objId.getClass().getDeclaredField("id");
						fieldObjId.setAccessible(true);
						ps.setObject(index++, fieldObjId.get(objId));
					}
				}
					
			}
		}
		ps.executeUpdate();
	}

	public static <T> void setPreparedStatementUpdate(PreparedStatement ps, T t) throws IllegalArgumentException, IllegalAccessException, SQLException {
		Field fields[] = t.getClass().getDeclaredFields();
		Field fieldId = null;
		int index = 1;
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(Id.class)) fieldId = field;
			Object value = field.get(t);
			if (value != null && !field.isAnnotationPresent(Id.class))
				ps.setObject(index++, value);
		}
		ps.setObject(index, fieldId.get(t));
		ps.executeUpdate();
	}

	
	
	/*
	 * get list column name by class
	 * Example: Users.class -> Users_id,Users_userName,Users_fullName,Users_departmentId,Users_email...
	 * 
	 * */
	public static StringBuilder getListColumnName(Class<?> tClass) {
		StringBuilder listColumnName = new StringBuilder();
		String tableName = tClass.getSimpleName();
		Field[] fields = tClass.getDeclaredFields();
		
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
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
	 *
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
			query.append(ReflectionUtils.getListColumnName(tClass));
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
			query.append(ReflectionUtils.conditionJoin(classes[i], classes[i+1]));
		}
		System.out.println(query.toString());
		String sql = query.toString();
		Connection conn = new DBConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<T> tList = new ArrayList<T>();
		while(rs.next()) {
			T t = (T) ReflectionUtils.mappingToEntity(rs,typeClass );
			tList.add(t);
		}
		return tList;
	}
	
	public static String convertObjectToJson(Object object) throws IllegalArgumentException, IllegalAccessException {
		Field fields[] = object.getClass().getDeclaredFields();
		StringBuilder json = new StringBuilder("{");
		Object value; 
		for(Field field : fields) {
			if(!field.isAnnotationPresent(Ignore.class)) {
				try {
					field.setAccessible(true);
					value = field.get(object);
					if(value.getClass().equals(Integer.class)||
						value.getClass().equals(Long.class)||
						value.getClass().equals(Float.class)||
						value.getClass().equals(Double.class)||
						value.getClass().equals(Boolean.class)
						)
						json.append("\"").append(field.getName()).append("\":").append(String.valueOf(value)).append(",");
					else if(value.getClass().equals(String.class))
						json.append("\"").append(field.getName()).append("\":").append("\"").append(String.valueOf(value)).append("\",");
					else if(field.isAnnotationPresent(ManyToOne.class)) {
						json.append("\"").append(field.getName()).append("\":").append(convertObjectToJson(value)).append(",");
					}
				}
				catch(Exception e) {
					json.append("\"").append(field.getName()).append("\":").append("null").append(",");
				}
			}
		}
		json.deleteCharAt(json.length()-1);
		json.append("}");
		return json.toString();
	}
	public static <T> String convertListObjectsToJson(List<T> objects) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder json = new StringBuilder("[");
		for(Object object : objects) {
			json.append(convertObjectToJson(object)).append(",");
		}
		json.deleteCharAt(json.length()-1);
		if(objects.size()>0)
			json.append("]");
		return json.toString();
	}
	public static <T> T convertRequestToEntity(Class<T> tClass,HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, ParseException {
		try {
			T t = tClass.newInstance();
			Field fields[] = tClass.getDeclaredFields();
			String fieldName;
			SimpleDateFormat formatter;
			for(Field field : fields) {
				field.setAccessible(true);
				fieldName = field.getName();
				if(request.getParameter(fieldName)!=null){
					if(field.getType().equals(String.class)) 
						field.set(t, String.valueOf(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Integer.class))
						field.set(t, Integer.parseInt(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Float.class))
						field.set(t, Float.parseFloat(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Long.class))
						field.set(t, Long.parseLong(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Double.class))
						field.set(t, Double.parseDouble(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Boolean.class))
						field.set(t, Boolean.parseBoolean(request.getParameter(fieldName)));
					
					else if(field.getType().equals(Date.class)) {
						if(field.isAnnotationPresent(orm.annotation.Date.class)) {
							formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						}
						else {
							formatter = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
						}
						field.set(t, formatter.parse(request.getParameter(fieldName)));
					}
					else {
						if(field.isAnnotationPresent(ManyToOne.class)) {
							Object obj = field.getType().newInstance();
							Field fieldIdObj = obj.getClass().getDeclaredField("id");
							fieldIdObj.setAccessible(true);
							fieldIdObj.set(obj, Long.parseLong(request.getParameter(fieldName)));
							field.set(t, obj);
						}
					}
				}
			}
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException {
//		convertFieldToObject(Users.class, Department.class);
		getListColumnName(Users.class);
		conditionJoin(Users.class, Department.class);
//		joinTableQuery(Users.class,Department.class);
//		joinTableQuery(Department.class,Users.class);
//		joinTableQuery(Department.class,Users.class,Events.class);
//		List<Users> users = joinTableQuery(Users.class,Users.class,Events.class,RegistEvents.class);
		List<Users> users = joinTableQuery(Users.class,Users.class,Department.class);
//		events.stream().forEach(
//		event->{
//			System.out.println(event.getUsers().getFullName());
//		}
//		);
		System.out.println(convertListObjectsToJson(users));
		
//		List<Department> departments = joinTableQuery(Department.class, Department.class,Users.class);
//		departments.forEach(System.out::println);
	}
	
}
