package pl.tomaszgigiel.utils.stringtransformer.ascii;

import pl.tomaszgigiel.utils.stringtransformer.IStringTransformerWorker;

public class ASCIIReducerWorker implements IStringTransformerWorker {

	private static final int ASCII_MIN = 0;
	private static final int ASCII_MAX = 127;
	//
	public static final String SPLIT = "";
	public static final String DELIMETER = "";

	@Override
	public String apply(String input) {
		String result = input;
		int codePoint = input.codePointAt(0);
		if (codePoint < ASCII_MIN || codePoint > ASCII_MAX) {
			result = ASCIIMap.valueOfCodePoint(codePoint);
		}
		return result;
	}

}
