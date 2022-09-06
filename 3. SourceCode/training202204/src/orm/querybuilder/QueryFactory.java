package orm.querybuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface QueryFactory {
	static <T> Query<T> equal(String column, Object value) {
		return new QueryImpl(column, Expression::equal, value);
	}

	static <T> Query<T> notEqual(String column, Object value) {
		return new QueryImpl<T>(column, Expression::notEqual, value);
	}

	static <T> Query<T> gt(String column, Object value) {
		return new QueryImpl<T>(column, Expression::gt, value);
	}

	static <T> Query<T> gte(String column, Object value) {
		return new QueryImpl<T>(column, Expression::gte, value);
	}

	static <T> Query<T> lt(String column, Object value) {
		return new QueryImpl<T>(column, Expression::lt, value);
	}

	static <T> Query<T> lte(String column, Object value) {
		return new QueryImpl<T>(column, Expression::lte, value);
	}

	static <T> Query<T> like(String column, Object value) {
		return new QueryImpl<T>(column, Expression::like, value);
	}

	static <T> Query<T> isNull(String column) {
		return new QueryImpl<T>(column, Expression::isNull);
	}

	static <T> Query<T> isNotNull(String column) {
		return new QueryImpl<T>(column, Expression::isNotNull);
	}

	static <T> Query<T> and(Query<T> query1, Query<T> query2) {
		assert query1 != null;
		assert query2 != null;
		StringBuilder conditionBuilder = new StringBuilder(query1.condition());
		conditionBuilder.append(Expression.and()).append(query2.condition());
		List<Object> objects = new ArrayList<Object>();
		objects.add(query1.value());
		objects.add(query2.value());
		return new QueryImpl<T>(conditionBuilder.toString(), objects);
	}

	static <T> Query<T> or(Query<T> query1, Query<T> query2) {
		assert query1 != null;
		assert query2 != null;
		StringBuilder conditionBuilder = new StringBuilder(query1.condition());
		conditionBuilder.append(Expression.or()).append(query2.condition());
		List<Object> objects = new ArrayList<Object>();
		objects.add(query1.value());
		objects.add(query2.value());
		return new QueryImpl<T>(conditionBuilder.toString(), objects);
	}

	static <T> Query<T> and(List<Query<T>> queries) {
		List<Query<T>> rs = queries.stream().filter(query -> query != null).collect(Collectors.toList());

		if (rs.size() == 1) {
			return new QueryImpl<T>(rs.get(0).condition(), rs.get(0).value());
		}
		String condition = rs.stream()
				.filter(r-> r.value() != null && r.value() != "")
				.map(Query::condition).collect(Collectors.joining(Expression.and()));
		List<Object> objects = rs.stream()
				.filter(r-> r.value() != null && r.value() != "")
				.map(Query::value).collect(Collectors.toList());
		return new QueryImpl<T>(condition, objects);

	}

	static <T> Query<T> or(List<Query<T>> queries) {
		List<Query<T>> rs = queries.stream().filter(query -> query != null).collect(Collectors.toList());

		if (rs.size() == 1) {
			return new QueryImpl<T>(rs.get(0).condition(), rs.get(0).value());
		}
		String condition = rs.stream().map(Query::condition).collect(Collectors.joining(Expression.or()));
		List<Object> objects = rs.stream().map(Query::value).collect(Collectors.toList());
		return new QueryImpl<T>(condition, objects);
	}
}
