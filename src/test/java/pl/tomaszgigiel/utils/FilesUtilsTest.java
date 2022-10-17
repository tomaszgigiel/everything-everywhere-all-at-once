package pl.tomaszgigiel.utils;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilesUtilsTest {

	@Test
	void isFilesIdentical() {
		try {
			String a = Paths.get("src", "test", "resources", "filesutils", "sample-01", "a.txt").toString();
			String b = Paths.get("src", "test", "resources", "filesutils", "sample-01", "b.txt").toString();
			String c = Paths.get("src", "test", "resources", "filesutils", "sample-01", "c.txt").toString();

			Assertions.assertTrue(FilesUtils.isFilesIdentical());
			Assertions.assertTrue(FilesUtils.isFilesIdentical(a));
			Assertions.assertTrue(FilesUtils.isFilesIdentical(a, a));
			Assertions.assertTrue(FilesUtils.isFilesIdentical(a, b));
			Assertions.assertTrue(FilesUtils.isFilesIdentical(a, b, c));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void isFilesIdenticalNot() {
		try {
			String a = Paths.get("src", "test", "resources", "filesutils", "sample-01", "a.txt").toString();
			String x = Paths.get("src", "test", "resources", "filesutils", "sample-01", "x.txt").toString();

			Assertions.assertFalse(FilesUtils.isFilesIdentical(a, x));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void isDirsIdentical() {
		try {
			String a = Paths.get("src", "test", "resources", "filesutils", "sample-02", "a").toString();
			String b = Paths.get("src", "test", "resources", "filesutils", "sample-02", "b").toString();
			String c = Paths.get("src", "test", "resources", "filesutils", "sample-02", "c").toString();

			Assertions.assertTrue(FilesUtils.isDirsIdentical());
			Assertions.assertTrue(FilesUtils.isDirsIdentical(a));
			Assertions.assertTrue(FilesUtils.isDirsIdentical(a, a));
			Assertions.assertTrue(FilesUtils.isDirsIdentical(a, b));
			Assertions.assertTrue(FilesUtils.isDirsIdentical(a, b, c));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void isDirsIdenticalNot() {
		try {
			String a = Paths.get("src", "test", "resources", "filesutils", "sample-02", "a").toString();
			String x = Paths.get("src", "test", "resources", "filesutils", "sample-02", "x").toString();
			String y = Paths.get("src", "test", "resources", "filesutils", "sample-02", "y").toString();

			Assertions.assertFalse(FilesUtils.isDirsIdentical(a, x));
			Assertions.assertFalse(FilesUtils.isDirsIdentical(a, y));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
