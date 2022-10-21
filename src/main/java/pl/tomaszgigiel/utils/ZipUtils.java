package pl.tomaszgigiel.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.Deflater;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

public class ZipUtils {

       public static void unzip(String inputFile, String destinationDir) throws IOException {
               try (ZipArchiveInputStream archive = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(inputFile)))) {

                       ZipArchiveEntry entry;
                       while ((entry = archive.getNextZipEntry()) != null) {
                               if (entry.isDirectory()) {
                                       Files.createDirectories(Paths.get(destinationDir, entry.getName()));
                               } else {
                                       IOUtils.copy(archive, new FileOutputStream(Paths.get(destinationDir, entry.getName()).toFile()));
                               }
                       }
               }
       }

       public static void zip(String sourceDir, String outputFile, FileTime lastModifiedTime) throws IOException {
               try (ZipArchiveOutputStream archive = new ZipArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)))) {
                       archive.setMethod(ZipArchiveOutputStream.DEFLATED);
                       archive.setLevel(Deflater.DEFAULT_COMPRESSION);
                       Path srcPath = Paths.get(sourceDir);
                       zipDirectory(srcPath.getParent().toString(), srcPath.getFileName().toString(), archive, lastModifiedTime);
               }
       }

       private static void zipDirectory(String rootDir, String sourceDir, ZipArchiveOutputStream out, FileTime lastModifiedTime) throws IOException {

               ZipArchiveEntry entryDir = new ZipArchiveEntry(sourceDir + File.separator);
               entryDir.setTime(lastModifiedTime);
               out.putArchiveEntry(entryDir);
               out.closeArchiveEntry();

               String dir = Paths.get(rootDir, sourceDir).toAbsolutePath().toString();
               var files = new File(dir).listFiles();
               Arrays.sort(files, Comparator.comparing(File::getAbsolutePath));
               for (File file : files) {
                       if (file.isDirectory()) {
                               zipDirectory(rootDir, Paths.get(sourceDir, file.getName()).toString(), out, lastModifiedTime);
                       } else {
                               ZipArchiveEntry entryFile = new ZipArchiveEntry(Paths.get(sourceDir, file.getName()).toString());
                               entryFile.setTime(lastModifiedTime);
                               out.putArchiveEntry(entryFile);

                               try (FileInputStream in = new FileInputStream(Paths.get(rootDir, sourceDir, file.getName()).toString())) {
                                       IOUtils.copy(in, out);
                               }
                               out.closeArchiveEntry();
                       }
               }
       }
}
