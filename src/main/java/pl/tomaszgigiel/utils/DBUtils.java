package pl.tomaszgigiel.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBUtils {

	public static String createColumnsSQL(int columnsCount) {
		String result = "columns";

		result = Stream.iterate(1, i -> i + 1) //
				.limit(columnsCount) //
				.map(i -> String.format("column_%03d VARCHAR", i)) //
				.collect(Collectors.joining(", "));

		return result;
	}

	public static String createTableSQL(String tableName, int columnsCount) {
		String result = "";

		result = String.format("CREATE TABLE IF NOT EXISTS %1$s (%2$s);", tableName, createColumnsSQL(columnsCount));

		return result;
	}

	public static void createTable(Connection connection, String tableName, int columnsCount) throws SQLException {
		String sql = createTableSQL(tableName, columnsCount);
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.executeUpdate();
		}
	}
}
