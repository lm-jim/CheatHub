package servicioInternoCheatHub.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	public void mandarEmail(String asunto, String mensaje,String destinatario) {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", "smtp.gmail.gom");
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(properties, null);
		MimeMessage email = new MimeMessage(session);
		
		try {
			email.setFrom(new InternetAddress("cheathuburjc@gmail.com"));
			email.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			email.setSubject(asunto);
			email.setText(mensaje);
			
			Transport t = session.getTransport("smtp");
			t.connect("smtp.gmail.com", "cheathuburjc@gmail.com", "juanmaIsmaLuismi0!");
			t.sendMessage(email, email.getAllRecipients());
			t.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
