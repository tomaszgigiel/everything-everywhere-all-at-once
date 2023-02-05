package pl.tomaszgigiel.utils.stringtransformer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringTransformer {
	public static String transformed(String input, String split, String delimeter, IStringTransformerWorker worker) {
		String result = Stream.of(input.split(split)).map(worker).collect(Collectors.joining(delimeter));
		return result;
	}
}
