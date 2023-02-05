package pl.tomaszgigiel.utils.stringtransformer.ascii;

import java.util.HashMap;
import java.util.Map;

public enum ASCIIMap {
	UTF8_SOFT_HYPHEN(0x00AD, " "), //
	UTF8_EN_DASH(0x2013, "-"), //
	UTF8_LEFT_SINGLE_QUOTATION_MARK(0x2018, "'"), //
	UTF8_RIGHT_SINGLE_QUOTATION_MARK(0x2019, "'"), //
	UTF8_LEFT_DOUBLE_QUOTATION_MARK(0x201C, "\""), //
	UTF8_RIGHT_DOUBLE_QUOTATION_MARK(0x201D, "\""), //
	UTF8_BLACK_SQUARE(0x25A0, ""), //
	// UTF-8 polish letters
	UTF8_BIG_OO(0x00D3, "O"), //
	UTF8_BIG_AA(0x0104, "A"), //
	UTF8_BIG_CC(0x0106, "C"), //
	UTF8_BIG_EE(0x0118, "E"), //
	UTF8_BIG_LL(0x0141, "L"), //
	UTF8_BIG_NN(0x0143, "N"), //
	UTF8_BIG_SS(0x015A, "S"), //
	UTF8_BIG_ZZ(0x0179, "Z"), //
	UTF8_BIG_ZZZ(0x017B, "Z"), //
	//
	UTF8_SMALL_OO(0x00F3, "o"), //
	UTF8_SMALL_AA(0x0105, "a"), //
	UTF8_SMALL_CC(0x0107, "c"), //
	UTF8_SMALL_EE(0x0119, "e"), //
	UTF8_SMALL_LL(0x0142, "l"), //
	UTF8_SMALL_NN(0x0144, "n"), //
	UTF8_SMALL_SS(0x015B, "s"), //
	UTF8_SMALL_ZZ(0x017A, "z"), //
	UTF8_SMALL_ZZZ(0x017C, "z"); //

	private static final Map<Integer, String> BY_CODE_POINT = new HashMap<>();

	static {
		for (ASCIIMap a : values()) {
			BY_CODE_POINT.put(a.codePoint, a.substitute);
		}
	}

	public final Integer codePoint;
	public final String substitute;

	private ASCIIMap(Integer codePoint, String substitute) {
		this.codePoint = codePoint;
		this.substitute = substitute;
	}

	public static String valueOfCodePoint(Integer codePoint) {
		String result = BY_CODE_POINT.get(codePoint);
		if (result == null) {
			result = String.format("<<0x%1$04X = %2$s>>", codePoint, Character.getName(codePoint));
		}
		return result;
	}
}
