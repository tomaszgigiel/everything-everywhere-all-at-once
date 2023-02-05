package pl.tomaszgigiel.sendemail;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.val;
import lombok.extern.log4j.Log4j2;
import pl.tomaszgigiel.pdf.EicarPDFApp;

// https://www.baeldung.com/java-email
// https://www.baeldung.com/java-properties
@Log4j2
public class SendEmailApp {

	public static final File PROPERTIES = Paths.get(Paths.get("").toAbsolutePath().toString(), "_confidential", "my.mail.properties").toFile();

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		try (val f = new FileInputStream(PROPERTIES)) {
			props.load(f);
		}

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(props.getProperty("mail.from")));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(props.getProperty("mail.to")));
		message.setSubject("MAIL SUBJECT");

		String msg = "MAIL MESSAGE";
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		// attachmentBodyPart.attachFile(EicarPDFApp.PSEUDO_VIRUS_EICAR_TXT_PDF_FILE);
		attachmentBodyPart.attachFile(EicarPDFApp.PSEUDO_VIRUS_EICAR_ZIP_PDF_FILE);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		multipart.addBodyPart(attachmentBodyPart);

		message.setContent(multipart);

		Transport.send(message);

		log.info("SendEmailApp: ok");
	}

}
