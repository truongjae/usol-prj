package orm;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import orm.querybuilder.Query;

public interface JpaRepository<T,ID> {
	Long save(T t) throws IllegalArgumentException, IllegalAccessException;
	List<T> findAll();
	List<T> findAll(Query<T> query);
	List<T> joinTableQuery(Class<?> ...classes) throws SQLException;
	List<T> joinTableQuery(Query<T> queryCondition,Class<?> ...classes) throws SQLException;
	Optional<T> findById(ID id) throws SQLException, InstantiationException, IllegalAccessException;
	void update(T t) throws IllegalArgumentException, IllegalAccessException, SQLException;
	void deleteById(ID id);
}
