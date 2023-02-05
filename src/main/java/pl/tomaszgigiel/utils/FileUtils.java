package pl.tomaszgigiel.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;

import pl.tomaszgigiel.lambda.exceptions.LambdaExceptionWrappers;

public class FileUtils {

	public static boolean isFilesIdentical(String... paths) throws IOException {
		if (paths.length <= 1) {
			return true;
		}
		Path first = Paths.get(paths[0]);
		for (int i = 1; i < paths.length; i++) {
			Path other = Paths.get(paths[i]);
			if (Files.mismatch(first, other) >= 0) {
				return false;
			}
		}
		return true;
	}

	public static boolean isDirsIdentical(String... paths) throws IOException {
		if (paths.length <= 1) {
			return true;
		}

		List<File> first = new ArrayList<File>(org.apache.commons.io.FileUtils.listFilesAndDirs(new File(paths[0]), TrueFileFilter.TRUE, TrueFileFilter.TRUE));
		Collections.sort(first, byAbsolutePath);
		first.remove(0);

		for (int i = 1; i < paths.length; i++) {
			List<File> other = new ArrayList<File>(org.apache.commons.io.FileUtils.listFilesAndDirs(new File(paths[i]), TrueFileFilter.TRUE, TrueFileFilter.TRUE));
			Collections.sort(other, byAbsolutePath);
			other.remove(0);

			if (first.size() != other.size()) {
				return false;
			}

			for (int j = 0; j < first.size(); j++) {
				if (!equals(first.get(j), other.get(j))) {
					return false;
				}
			}
		}

		return true;
	}

	public static void replaceAll(Path path, String absolutePathRegex, Charset charset, String regex, String replacement) throws IOException {
		try (Stream<Path> paths = Files.walk(path)) {
			paths //
					.filter(Files::isRegularFile) //
					.filter(file -> {
						return file.toAbsolutePath().toString().matches(absolutePathRegex);
					}) //
					.forEach( //
							LambdaExceptionWrappers.handlingConsumerWrapper(file -> {
								String content = new String(Files.readAllBytes(file), charset);
								content = content.replaceAll(regex, replacement);
								Files.write(file, content.getBytes(charset));
							}, IOException.class));
		}
	}

	private static Comparator<File> byAbsolutePath = Comparator.comparing(File::getAbsolutePath);

	private static boolean equals(File a, File b) throws IOException {
		boolean result = //
				0 == StringUtils.compare(a.getName(), b.getName()) //
						&& ((a.isDirectory() && b.isDirectory()) || (a.isFile() && b.isFile())) //
						&& a.isDirectory() || isFilesIdentical(a.getAbsolutePath(), b.getAbsolutePath());

		return result;
	}
}
