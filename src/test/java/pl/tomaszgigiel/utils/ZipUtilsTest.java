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
                       String expectedZip = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-unzip", "example.zip").toString();
                       String inputDir = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-zip", "example-folder").toString();
                       String destinationZip = Paths.get("target", "example-folder-test.zip").toString();

                       FileTime lastModifiedTime = FileTime.fromMillis(ZonedDateTime.of(2000, Month.JANUARY.getValue(), 1, 19, 30, 0, 0, ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
                       ZipUtils.zip(inputDir, destinationZip, lastModifiedTime);

                       Assertions.assertTrue(FileUtils.isFilesIdentical(expectedZip, destinationZip));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }

       @Test
       void unzip() {
               try {
                       String expectedDir = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-zip").toString();
                       String inputZip = Paths.get("src", "test", "resources", "ziputils", "sample-01", "to-unzip", "example.zip").toString();
                       String destinationDir = Paths.get("target", "example-folder-test").toString();

                       ZipUtils.unzip(inputZip, destinationDir);

                       Assertions.assertTrue(FileUtils.isDirsIdentical(expectedDir, destinationDir));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }
}
