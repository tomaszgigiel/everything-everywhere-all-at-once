package pl.tomaszgigiel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class EicarUtils {
	public static final byte[] EICAR_COM = eicarCom();
	public static final byte[] EICAR_COM_TXT = eicarComTxt();
	public static final byte[] EICAR_COM_ZIP = eicarComZip();
	public static final byte[] EICAR_COM2_ZIP = eicarCom2Zip();

	private static byte[] load(String first, String second) {
		byte[] result = null;
		try (InputStream aa = EicarUtils.class.getClassLoader().getResourceAsStream(first); //
				InputStream ab = EicarUtils.class.getClassLoader().getResourceAsStream(second);) {
			int a = aa.available();
			int b = ab.available();
			result = new byte[a + b];
			aa.read(result);
			ab.read(result, a, b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	private static byte[] eicarCom() {
		return load("eicar/eicar_splitted/eicar_com/eicar.comaa", //
				"eicar/eicar_splitted/eicar_com/eicar.comab");
	}

	private static byte[] eicarComTxt() {
		return load("eicar/eicar_splitted/eicar_com_txt/eicar.com.txtaa", //
				"eicar/eicar_splitted/eicar_com_txt/eicar.com.txtab");
	}

	private static byte[] eicarComZip() {
		return load("eicar/eicar_splitted/eicar_com_zip/eicar_com.zipaa", //
				"eicar/eicar_splitted/eicar_com_zip/eicar_com.zipab");
	}

	private static byte[] eicarCom2Zip() {
		return load("eicar/eicar_splitted/eicarcom2_zip/eicarcom2.zipaa", //
				"eicar/eicar_splitted/eicarcom2_zip/eicarcom2.zipab");
	}

	public static void main(String[] args) {
		log.info(Arrays.toString(EICAR_COM));
		log.info(Arrays.toString(EICAR_COM_TXT));
		log.info(Arrays.toString(EICAR_COM_ZIP));
		log.info(Arrays.toString(EICAR_COM2_ZIP));

		log.info("EicarUtils: ok");
	}
}
