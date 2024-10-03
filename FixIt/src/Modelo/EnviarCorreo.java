package Modelo;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author rdlfp
 */
public class EnviarCorreo {

    public static void enviarCorreo(String recipient, String subject, String content) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        //credenciales de la cuenta fixit
        final String myAccountEmail = "asistenciafixit@gmail.com";
        final String password = "ynsk wpub yylq ujkh";

        //se crea la session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        //Aqui enviamos el correo
        try {
            //se crea el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);
            //se envia rl mensaje
            Transport.send(message);
            System.out.println("Correo enviado con exito");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
