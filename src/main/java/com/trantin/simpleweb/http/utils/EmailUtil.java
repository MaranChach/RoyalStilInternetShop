package com.trantin.simpleweb.http.utils;

import com.trantin.simpleweb.http.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

    private static final String fromEmailOld = "trantin2003@mail.ru";

    private static final String fromEmail = "postmaster@dmosk.ru";
    private final static String passwordOld = "sdXCmwa83MpUwCjfiHHD"; // correct password for gmail id

    private final static String password = "qwerty";
    private static final String username = "maran";

    private static final String usernameOld = "trantin2003@mail.ru";

    public static void sendAccountInfo(String toEmail, Client client, User user, String decryptedPassword){
        System.out.println("SSLEmail Start");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(getPropsLocal(), auth);
        System.out.println("Session created");

        String emailBody = "";

        emailBody += "Здравсвуйте, " + client.getName() + ", для Вас создан новый аккаунт \n\n";
        emailBody += "Данные аккаунта: \n\n";

        emailBody += "Логин: " + user.getUsername() + "\n";
        emailBody += "Пароль: " + decryptedPassword + "\n\n";


        sendEmail(session, toEmail,"Аккаунт создан", emailBody);
    }

    public static void sendOrderInfo(String toEmail, Order order){
        System.out.println("SSLEmail Start");

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getDefaultInstance(getPropsLocal(), auth);
        System.out.println("Session created");

        String emailBody = "";

        emailBody += "Здравсвуйте, " + order.getClient().getName() + ", Ваш заказ принят в обработку \n\n";
        emailBody += "Состав заказа: \n";

        for (OrderCartItem item : order.getOrderCart().getItems()) {
            Product product = item.getProduct();
            emailBody += item.getNumber() + " x " + product.getName() + " " + product.getCost() + " руб.\n";
        }

        emailBody += "Сумма заказа: " + order.orderSum() + "\n\n";

        emailBody += "Для уточнения деталей с вами свяжется наш менеджер";

        sendEmail(session, toEmail,"Заказ №" + order.getId(), emailBody);
    }

    private static void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, "Royal Steel"));

            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties getProps(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port

        return props;
    }

    private static Properties getPropsLocal(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost"); //SMTP Host
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "25"); //SMTP Port

        return props;
    }
}
