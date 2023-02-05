package pl.tomaszgigiel.utils.stringtransformer.ascii;

import pl.tomaszgigiel.utils.stringtransformer.StringTransformer;

public class ASCIIReducer {

	public static String transformed(String input) {
		return StringTransformer.transformed(input, ASCIIReducerWorker.SPLIT, ASCIIReducerWorker.DELIMETER, new ASCIIReducerWorker());
	}

}
