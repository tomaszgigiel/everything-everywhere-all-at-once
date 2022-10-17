package pl.tomaszgigiel.utils;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ZipUtilsTest {

	@Test
	void zip() {
		try {
			String expected = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-unzip", "expected.zip").toString();
			String inputDir = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-zip", "example-folder").toString();
			String destinationFile = Paths.get("target", "example-folder-root.zip").toString();

			FileTime lastModifiedTime = FileTime.fromMillis(ZonedDateTime.of(2000, Month.JANUARY.getValue(), 1, 19, 30, 0, 0, ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
			ZipUtils.zip(inputDir, destinationFile, lastModifiedTime);

			Assertions.assertTrue(FilesUtils.isFilesIdentical(expected, destinationFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void unzip() {
		try {
			String expected = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-unzip", "expected.zip").toString();
			String inputDir = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-zip", "example-folder").toString();
			String destinationFile = Paths.get("target", "example-folder-root.zip").toString();

			FileTime lastModifiedTime = FileTime.fromMillis(ZonedDateTime.of(2000, Month.JANUARY.getValue(), 1, 19, 30, 0, 0, ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
			ZipUtils.zip(inputDir, destinationFile, lastModifiedTime);

			Assertions.assertTrue(FilesUtils.isFilesIdentical(expected, destinationFile));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
