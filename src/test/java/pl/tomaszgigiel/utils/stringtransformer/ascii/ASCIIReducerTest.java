package pl.tomaszgigiel.utils.stringtransformer.ascii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ASCIIReducerTest {

	// https://dencode.com/string/unicode-escape?v=123%20ABC%20abc%20%5B%C2%AD%2C%20%E2%80%93%2C%20%E2%80%99%2C%20%E2%80%9C%2C%20%E2%80%9D%2C%20%E2%96%A0%5D%5B%C2%AD%2C%20%E2%80%93%2C%20%E2%80%99%2C%20%E2%80%9C%2C%20%E2%80%9D%2C%20%E2%96%A0%5D&nl=crlf
	@Test
	public void reduced() {
		String text = "\u0031\u0032\u0033\u0020\u0041\u0042\u0043\u0020\u0061\u0062\u0063\u0020\u005b\u00ad\u002c\u0020\u2013\u002c\u0020\u2019\u002c\u0020\u201c\u002c\u0020\u201d\u002c\u0020\u25a0\u005d\u005b\u00ad\u002c\u0020\u2013\u002c\u0020\u2019\u002c\u0020\u201c\u002c\u0020\u201d\u002c\u0020\u25a0\u005d";
		String expected = "123 ABC abc [ , -, ', \", \", ][ , -, ', \", \", ]";
		String actual = ASCIIReducer.transformed(text);
		Assertions.assertEquals(expected, actual);
	}

	// https://dencode.com/string/unicode-escape?v=ZA%C5%BB%C3%93%C5%81%C4%86%20G%C4%98%C5%9AL%C4%84%20JA%C5%B9%C5%83%20za%C5%BC%C3%B3%C5%82%C4%87%20g%C4%99sl%C4%85%20ja%C5%BA%C5%84&nl=crlf
	@Test
	public void reducedPolish() {
		String text = "\u005a\u0041\u017b\u00d3\u0141\u0106\u0020\u0047\u0118\u015a\u004c\u0104\u0020\u004a\u0041\u0179\u0143\u0020\u007a\u0061\u017c\u00f3\u0142\u0107\u0020\u0067\u0119\u0073\u006c\u0105\u0020\u006a\u0061\u017a\u0144";
		String expected = "ZAZOLC GESLA JAZN zazolc gesla jazn";
		String actual = ASCIIReducer.transformed(text);
		Assertions.assertEquals(expected, actual);
	}
}
