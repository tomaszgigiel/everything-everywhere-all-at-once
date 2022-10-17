package pl.tomaszgigiel.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;

public class FilesUtils {

	public static boolean isFilesIdentical(String... paths) throws IOException {
		if (paths.length <= 1) {
			return true;
		}
		byte[] first = Files.readAllBytes(Paths.get(paths[0]));
		for (int i = 1; i < paths.length; i++) {
			byte[] other = Files.readAllBytes(Paths.get(paths[i]));
			if (!Arrays.equals(first, other)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isDirsIdentical(String... paths) throws IOException {
		if (paths.length <= 1) {
			return true;
		}

		List<File> first = new ArrayList<File>(FileUtils.listFilesAndDirs(new File(paths[0]), TrueFileFilter.TRUE, TrueFileFilter.TRUE));
		Collections.sort(first, byAbsolutePath);
		first.remove(0);

		for (int i = 1; i < paths.length; i++) {
			List<File> other = new ArrayList<File>(FileUtils.listFilesAndDirs(new File(paths[i]), TrueFileFilter.TRUE, TrueFileFilter.TRUE));
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

	private static Comparator<File> byAbsolutePath = Comparator.comparing(File::getAbsolutePath);

	private static boolean equals(File a, File b) throws IOException {
		boolean result = //
				0 == StringUtils.compare(a.getName(), b.getName()) //
						&& ((a.isDirectory() && b.isDirectory()) || (a.isFile() && b.isFile())) //
						&& a.isDirectory() || isFilesIdentical(a.getAbsolutePath(), b.getAbsolutePath());

		return result;
	}
}
