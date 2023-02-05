package pl.tomaszgigiel.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileUtilsTest {

       @Test
       void isFilesIdentical() {
               try {
                       String a = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-01", "a.txt").toString();
                       String b = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-01", "b.txt").toString();
                       String c = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-01", "c.txt").toString();

                       Assertions.assertTrue(FileUtils.isFilesIdentical());
                       Assertions.assertTrue(FileUtils.isFilesIdentical(a));
                       Assertions.assertTrue(FileUtils.isFilesIdentical(a, a));
                       Assertions.assertTrue(FileUtils.isFilesIdentical(a, b));
                       Assertions.assertTrue(FileUtils.isFilesIdentical(a, b, c));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }

       @Test
       void isFilesIdenticalNot() {
               try {
                       String a = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-01", "a.txt").toString();
                       String x = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-01", "x.txt").toString();

                       Assertions.assertFalse(FileUtils.isFilesIdentical(a, x));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }

       @Test
       void isDirsIdentical() {
               try {
                       String a = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "a").toString();
                       String b = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "b").toString();
                       String c = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "c").toString();

                       Assertions.assertTrue(FileUtils.isDirsIdentical());
                       Assertions.assertTrue(FileUtils.isDirsIdentical(a));
                       Assertions.assertTrue(FileUtils.isDirsIdentical(a, a));
                       Assertions.assertTrue(FileUtils.isDirsIdentical(a, b));
                       Assertions.assertTrue(FileUtils.isDirsIdentical(a, b, c));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }

       @Test
       void isDirsIdenticalNot() {
               try {
                       String a = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "a").toString();
                       String x = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "x").toString();
                       String y = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-02", "y").toString();

                       Assertions.assertFalse(FileUtils.isDirsIdentical(a, x));
                       Assertions.assertFalse(FileUtils.isDirsIdentical(a, y));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }

       @Test
       void replaceAll() {
               try {
                       Path before = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-03", "before");
                       Path expected = Paths.get("src", "test", "resources", "pl.tomaszgigiel.utils.FilesUtils", "sample-03", "expected");
                       Path after = Paths.get("target", "test", "after");
                       //
                       String ABSOLUTE_PATH_REGEX = ".*to-change.*";
                       String REGEX = "Get up early every day - as early as you can.";
                       String REPLACEMENT = "Every person you meet is an opportunity for kindness.";
                       //
                       org.apache.commons.io.FileUtils.copyDirectory(before.toFile(), after.toFile());
                       FileUtils.replaceAll(after, ABSOLUTE_PATH_REGEX, StandardCharsets.UTF_8, REGEX, REPLACEMENT);
                       //
                       Assertions.assertTrue(FileUtils.isDirsIdentical(expected.toAbsolutePath().toString(), after.toAbsolutePath().toString()));
                       Assertions.assertFalse(FileUtils.isDirsIdentical(before.toAbsolutePath().toString(), after.toAbsolutePath().toString()));
               } catch (IOException e) {
                       throw new RuntimeException(e);
               }
       }
}
