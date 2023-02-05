package pl.tomaszgigiel.utils.blob;

import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class BlobUtils {

	public static ArrayList<Item> download(Connection connecton, String select, String id, String name, String content) throws SQLException {
		ArrayList<Item> result = new ArrayList<Item>();
		Statement stmt = connecton.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		while (rs.next()) {
			Blob blob = rs.getBlob(content);
			byte[] bytes = blob.getBytes(1, (int) blob.length());
			result.add(new Item(rs.getString(id), rs.getString(name), bytes));
		}
		return result;
	}

	public static void update(Connection connection, String update, Item item) throws SQLException {
		Blob blob = connection.createBlob();
		try {
			blob.setBytes(0, item.getContents()); // e.g. Informix
		} catch (SQLException ex) {
			blob.setBytes(1, item.getContents()); // e.g. H2
		}

		PreparedStatement pstmt = connection.prepareStatement(update);
		pstmt.setString(1, item.getName());
		pstmt.setBlob(2, blob);
		pstmt.setString(3, item.getId());
		pstmt.executeUpdate();
	}

	public static void save(ArrayList<Item> items, String outputDirectory) {
		items.forEach((item) -> save(outputDirectory, item));
	}

	private static void save(String outputDirectory, Item item) {
		try {
			String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
			FileUtils.writeByteArrayToFile(Path.of(outputDirectory, datetime, item.getId() + "-" + item.getName()).toFile(), item.getContents());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
