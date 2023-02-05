package pl.tomaszgigiel.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DBUtilsTest {

	@Test
	void createColumnsSQL() {
		String actual = DBUtils.createColumnsSQL(3);
		String expected = "column_001 VARCHAR, column_002 VARCHAR, column_003 VARCHAR";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void createTableSQL() {
		String actual = DBUtils.createTableSQL("tablename", 3);
		String expected = "CREATE TABLE IF NOT EXISTS tablename (column_001 VARCHAR, column_002 VARCHAR, column_003 VARCHAR);";
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void loadCSV() {
		final String fileName = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.DBUtils", "files", "sample-01-colon.csv").toString();
		final CSVFormat format = CSVFormat.DEFAULT;

		try (Reader reader = new FileReader(fileName); //
				CSVParser parser = format.parse(reader); //
				Connection connection = connection(); //
				PreparedStatement pstmt = connection.prepareStatement("");) {

			System.out.println(parser.iterator().next());
			System.out.println(parser.iterator().next());
			parser.iterator().forEachRemaining(System.out::println);

			// Stream<CSVRecord> stream = StreamSupport.stream(records.spliterator(), false);
			// stream.
			// System.out.println(stream.findFirst().get());
			// stream.limit(1).forEach(System.out::println);
			// stream.forEach(System.out::println);

			// final Iterable<CSVRecord> first =
			// records.iterator().forEachRemaining(System.out::println);
			// Spliterators.spliterator(records, 1);

			// DBUtils.createTable(connection, "aa", records.iterator().next().size());
			// records.forEach(System.out::println);

			// StreamSupport.stream(format.parse(reader).spliterator(), false)

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private Connection connection() throws IOException, SQLException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("pl.tomaszgigiel.utils.DBUtils/H2/mem.properties");

		Properties properties = new Properties();
		properties.load(stream);
		@SuppressWarnings("unused")
		String driverClassName = properties.getProperty("datasource.driverClassName");
		String url = properties.getProperty("datasource.url");
		String username = properties.getProperty("datasource.username");
		String password = properties.getProperty("datasource.password");

		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

}
