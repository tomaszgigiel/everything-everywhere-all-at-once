package pl.tomaszgigiel.utils.blob;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BlobUtilsTest {

	private Connection connection() throws IOException, SQLException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream("blobutils/H2/mem.properties");

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

	@Test
	void jdbc() {
		try (Connection connection = connection()) {
			PreparedStatement statement = connection.prepareStatement("SELECT contents, UTF8TOSTRING(contents), count(*) over () total_rows FROM table_with_blob", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Blob blob = rs.getBlob(1);
					byte[] bytes = IOUtils.toByteArray(blob.getBinaryStream());
					String string = new String(bytes, StandardCharsets.UTF_8);
					Assertions.assertEquals(rs.getString(2), string);

					Assertions.assertEquals(6, rs.getInt(3));
				}
				rs.first();
				Assertions.assertEquals(6, rs.getInt(3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void download() {
		try (Connection connection = connection()) {
			ArrayList<Item> items = BlobUtils.download(connection, "SELECT id, name, contents FROM table_with_blob ORDER BY id", "id", "name", "contents");
			Assertions.assertEquals(6, items.size());

			Item item01 = items.get(0);
			Item item02 = items.get(1);
			Item item03 = items.get(2);
			Item item11 = items.get(3);
			Item item12 = items.get(4);
			Item item13 = items.get(5);

			Assertions.assertEquals("1", item01.getId());
			Assertions.assertEquals("sample-01.txt", item01.getName());
			Assertions.assertArrayEquals("Stillness is the key.".getBytes(), item01.getContents());

			Assertions.assertEquals("2", item02.getId());
			Assertions.assertEquals("sample-02.txt", item02.getName());
			Assertions.assertArrayEquals("Ego is the enemy.".getBytes(), item02.getContents());

			Assertions.assertEquals("3", item03.getId());
			Assertions.assertEquals("sample-03.txt", item03.getName());
			Assertions.assertArrayEquals("The obstacle is the way.".getBytes(), item03.getContents());

			Assertions.assertArrayEquals(item01.getContents(), item11.getContents());
			Assertions.assertArrayEquals(item02.getContents(), item12.getContents());
			Assertions.assertArrayEquals(item03.getContents(), item13.getContents());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void update() {
		final String EVERY_PERSON_IS_AN_OPPORTUNITY_FOR_KINDNESS = "Every person is an opportunity for kindness";
		final String SAMPLE_99_TXT = "sample-99.txt";

		try (Connection connection = connection()) {

			Item item = new Item("1", SAMPLE_99_TXT, EVERY_PERSON_IS_AN_OPPORTUNITY_FOR_KINDNESS.getBytes());
			BlobUtils.update(connection, "UPDATE table_with_blob SET name = ?, contents = ? WHERE id = ?", item);

			ArrayList<Item> items = BlobUtils.download(connection, "SELECT id, name, contents FROM table_with_blob WHERE id = 1", "id", "name", "contents");
			Assertions.assertEquals(1, items.size());
			Item item01 = items.get(0);

			Assertions.assertEquals("1", item01.getId());
			Assertions.assertEquals(SAMPLE_99_TXT, item01.getName());
			Assertions.assertArrayEquals(EVERY_PERSON_IS_AN_OPPORTUNITY_FOR_KINDNESS.getBytes(), item01.getContents());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
