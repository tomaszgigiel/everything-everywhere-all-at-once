package pl.tomaszgigiel.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfFileSpecification;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.val;
import lombok.extern.log4j.Log4j2;
import pl.tomaszgigiel.utils.EicarUtils;

// https://www.baeldung.com/java-pdf-creation
@Log4j2
public class EicarPDFApp {

	public static final String PSEUDO_VIRUS_EICAR_TXT = "pseudo-virus-eicar.txt";
	public static final File PSEUDO_VIRUS_EICAR_TXT_PDF_FILE = Paths.get(Paths.get("").toAbsolutePath().toString(), "target", "pseudo-virus-eicar-txt.pdf").toFile();
	public static final String PSEUDO_VIRUS_EICAR_ZIP = "pseudo-virus-eicar.zip";
	public static final File PSEUDO_VIRUS_EICAR_ZIP_PDF_FILE = Paths.get(Paths.get("").toAbsolutePath().toString(), "target", "pseudo-virus-eicar-zip.pdf").toFile();

	public static ByteArrayOutputStream eicarPDF() throws Exception {
		val pdf = new ByteArrayOutputStream();

		Document document = new Document();
		PdfWriter.getInstance(document, pdf);

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("EICAR (click the pin)", font);
		document.add(chunk);
		document.close();

		PdfReader reader = new PdfReader(new ByteArrayInputStream(pdf.toByteArray()));
		PdfStamper stamper = new PdfStamper(reader, pdf);
		PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(stamper.getWriter(), null, PSEUDO_VIRUS_EICAR_TXT, EicarUtils.EICAR_COM);
		Rectangle rect = new Rectangle(40, 800, 60, 780);
		PdfAnnotation attachment = PdfAnnotation.createFileAttachment(stamper.getWriter(), rect, PSEUDO_VIRUS_EICAR_TXT, fs);
		stamper.addAnnotation(attachment, 1);
		stamper.close();

		return pdf;
	}

	public static ByteArrayOutputStream eicarZipPDF() throws Exception {
		val pdf = new ByteArrayOutputStream();

		Document document = new Document();
		PdfWriter.getInstance(document, pdf);

		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("EICAR (click the pin)", font);
		document.add(chunk);
		document.close();

		PdfReader reader = new PdfReader(new ByteArrayInputStream(pdf.toByteArray()));
		PdfStamper stamper = new PdfStamper(reader, pdf);
		PdfFileSpecification fs = PdfFileSpecification.fileEmbedded(stamper.getWriter(), null, PSEUDO_VIRUS_EICAR_ZIP, EicarUtils.EICAR_COM_ZIP);
		Rectangle rect = new Rectangle(40, 800, 60, 780);
		PdfAnnotation attachment = PdfAnnotation.createFileAttachment(stamper.getWriter(), rect, PSEUDO_VIRUS_EICAR_ZIP, fs);
		stamper.addAnnotation(attachment, 1);
		stamper.close();

		return pdf;
	}

	public static void main(String[] args) throws Exception {
		FileUtils.writeByteArrayToFile(PSEUDO_VIRUS_EICAR_TXT_PDF_FILE, eicarPDF().toByteArray());
		FileUtils.writeByteArrayToFile(PSEUDO_VIRUS_EICAR_ZIP_PDF_FILE, eicarPDF().toByteArray());
		log.info("EicarPDFApp: ok");
	}

}
