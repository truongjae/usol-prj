package orm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Department;
import entity.Events;
import entity.Users;
import orm.annotation.Column;
import orm.annotation.Id;
import orm.annotation.ManyToOne;
import orm.page.PageAble;
import orm.page.PageRequest;
import orm.querybuilder.Query;
import orm.utils.ReflectionUtils;

public class AbstractQuery<T, ID> implements JpaRepository<T, ID> {
	private String select;
	private String insert;
	private String update;
	private String delete;
	private String tableName;
	private DBConnection dbConnection;
	private Class<T> clazz;

	public AbstractQuery() throws ClassNotFoundException, SQLException {
		dbConnection = new DBConnection();
		this.clazz = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		this.tableName = clazz.getSimpleName();
		this.select = "SELECT * FROM " + tableName;
		this.insert = "INSERT INTO ";
		this.update = "UPDATE ";
		this.delete = "DELETE from " + tableName + " WHERE id = ?";
	}
	public AbstractQuery(String a) {
		dbConnection = new DBConnection();
	}
	@Override
	public List<T> findAll() {
		try (Connection conn = dbConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(this.select);
//			System.out.println(this.select);
			ResultSet rs = ps.executeQuery();
			return ReflectionUtils.convertToEntity(rs, clazz);
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			return null;
		}
	}

	@Override
	public Optional<T> findById(ID id) throws SQLException, InstantiationException, IllegalAccessException {
		StringBuilder findById = new StringBuilder(this.select);
		findById.append(" WHERE id = ?");
		try (Connection conn = dbConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(findById.toString());
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();
			try {
				return Optional.of(ReflectionUtils.convertToEntity(rs, clazz).get(0));
			} catch (IndexOutOfBoundsException e) {
				return Optional.empty();
			}
		}

	}

	@Override
	public Long save(T t) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder query = new StringBuilder(insert);
		StringBuilder param = new StringBuilder();
		query.append(this.tableName).append("(");
		Field fields[] = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if(field.get(t)!=null) {
				String fieldName = field.getName();
				if(field.isAnnotationPresent(Column.class))
					query.append(fieldName);
				if(field.isAnnotationPresent(ManyToOne.class))
					query.append("Id");
				query.append(",");
				param.append("?,");
			}
			/*if (field.get(t) != null && field.isAnnotationPresent(Column.class)) {
				String fieldName = field.getName();
				query.append(fieldName).append(",");
				param.append("?,");
			}*/
		}
		
		query = query.deleteCharAt(query.length() - 1);
		param = param.deleteCharAt(param.length() - 1);
		query.append(") values(");
		query.append(param);
		query.append(")");
		
		System.out.println(query.toString());

		PreparedStatement ps;
		Connection conn = null;
		try {
			conn = dbConnection.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
			ReflectionUtils.setPreparedStatementInsert(ps, t);
			conn.commit();
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
		        if (generatedKeys.next())
		             return generatedKeys.getLong(1);
		        else
		             return null;
		     }
		} catch (Exception e) {
			try {
				e.printStackTrace();
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	public void update(T t) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder query = new StringBuilder(update);
		query.append(this.tableName).append(" SET ");
		Field fields[] = t.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.get(t) != null && !field.isAnnotationPresent(Id.class))
				query.append(field.getName()).append("=?,");
		}
		query = query.deleteCharAt(query.length() - 1);
		query.append(" WHERE id=?");
		System.out.println(query.toString());
		PreparedStatement ps;
		Connection conn = null;
		try {
			conn = dbConnection.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(query.toString());
			ReflectionUtils.setPreparedStatementUpdate(ps, t);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void deleteById(ID id) {
		Connection conn = null;
		PreparedStatement ps;
		try {
			conn = dbConnection.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(this.delete);
			ps.setObject(1, id);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e1) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<T> findAll(Query<T> query) {
		StringBuilder selectWithCondition = new StringBuilder(this.select);
		selectWithCondition.append(" WHERE ").append(query.condition());
//		System.out.println(selectWithCondition.toString());
		try (Connection conn = dbConnection.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(selectWithCondition.toString());
			if (query.value() != null) {
				ps.setObject(1, query.value());
			} else if (query.values() != null) {
				int index = 1;
				for (Object obj : query.values()) {
					ps.setObject(index++, obj);
				}
			}
			ResultSet rs = ps.executeQuery();
			return ReflectionUtils.convertToEntity(rs, clazz);
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<T> joinTableQuery(Class<?>... classes) throws SQLException {
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
//		System.out.println(query.toString());
		String sql = query.toString();
		Connection conn = new DBConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<T> tList = new ArrayList<T>();
		while(rs.next()) {
			T t = (T) ReflectionUtils.mappingToEntity(rs, clazz);
			tList.add(t);
		}
		return tList;
	}
	
	public List<Users> findAll(PageAble ...pageAbles) {
		try (Connection conn = dbConnection.getConnection()) {
			StringBuilder selectQuery = new StringBuilder("select * from users");
			PageAble pageAble=null;
			if(pageAbles.length > 0) {
				pageAble = pageAbles[0];
				if(pageAble.getIndex() != 0 && pageAble.getSize() !=0) {
					selectQuery.append(" ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
				}
			}
//			System.out.println(selectQuery.toString());
			PreparedStatement ps = conn.prepareStatement(selectQuery.toString());
			ps.setInt(1, pageAble.getIndex());
			ps.setInt(2, pageAble.getSize());
			
			ResultSet rs = ps.executeQuery();
			return ReflectionUtils.convertToEntity(rs, Users.class);
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			return null;
		}
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		List<Events> users = joinTableQuery(Department.class,Users.class,Events.class);
//		users.forEach(System.out::println);
//		AbstractQuery<Users, Long> a = new AbstractQuery<Users, Long>("a");
//		a.findAll(PageRequest.of(1, 2)).stream().forEach(System.out::println);
	}
	@Override
	public List<T> joinTableQuery(Query<T> queryCondition, Class<?>... classes) throws SQLException {
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
		
		String sql = query.toString();
		Connection conn = new DBConnection().getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		
		if(queryCondition!=null) {
			query.append(" WHERE ").append(queryCondition.condition());
			sql = query.toString();
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			
			if (queryCondition.value() != null) {
				ps.setObject(1, queryCondition.value());
			} else if (queryCondition.values() != null) {
				int index = 1;
				for (Object obj : queryCondition.values()) {
					if(obj!=null) {
						ps.setObject(index++, obj);
					}
					
				}
			}
		}
		ResultSet rs = ps.executeQuery();
		
		List<T> tList = new ArrayList<T>();
		while(rs.next()) {
			T t = (T) ReflectionUtils.mappingToEntity(rs, clazz);
			tList.add(t);
		}
		return tList;
	}

}
