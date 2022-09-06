package orm.querybuilder;

import java.util.List;

public class QueryImpl<T> implements Query<T>{
	private String condition;
	private Object value;
	private List<Object> values;
	public QueryImpl(String condition, Expression expression) {
		this.condition = new StringBuilder(condition).append(expression.expression()).toString();
	}
	public QueryImpl(String condition, Expression expression,Object value) {
		if(value!=null && value != "") {
			this.condition = new StringBuilder(condition).append(expression.expression()).append("?").toString();
			this.value = value;
//			if(expression.expression().equals(Expression.like())) {
//				this.condition = new StringBuilder(condition).append(expression.expression()).append("?").toString();
//				this.value = value;
////				System.out.println("haha");
//			}
		}
		else {
			this.condition = null;
			this.value = null;
		}
	}
	public QueryImpl(String condition, Object value) {
		this.condition = condition;
		this.value = value;
	}
	public QueryImpl(String condition,List<Object> values) {
		this.condition = condition;
		this.values = values;
	}
	@Override
	public String condition() {
		// TODO Auto-generated method stub
		return condition;
	}
	@Override
	public Object value() {
		// TODO Auto-generated method stub
		return value;
	}
	@Override
	public List<Object> values() {
		// TODO Auto-generated method stub
		return values;
	}

}
