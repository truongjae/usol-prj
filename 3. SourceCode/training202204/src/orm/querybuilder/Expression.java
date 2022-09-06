package orm.querybuilder;

public interface Expression {
	String expression();

	static String equal() {
		return " = ";
	}

	static String notEqual() {
		return " <> ";
	}

	static String gt() {
		return " > ";
	}

	static String gte() {
		return " >= ";
	}

	static String lt() {
		return " < ";
	}

	static String lte() {
		return " <= ";
	}

	static String between() {
		return " BETWEEN ";
	}

	static String in() {
		return " IN ";
	}

	static String like() {
		return " LIKE ";
	}

	static String isNull() {
		return " IS NULL ";
	}

	static String isNotNull() {
		return " IS NOT NULL ";
	}

	static String and() {
		return " and ";
	}

	static String or() {
		return " or ";
	}
}
