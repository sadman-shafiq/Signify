package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class forgot_password {

    @FXML
    private TextField emailField;

    private static String userName;
    public void sendmail(ActionEvent event) throws IOException {
        String userEmail = emailField.getText();

        if (!userEmail.isEmpty()) {
             userName = userEmail.substring(0, userEmail.indexOf("@"));
            sendOTP(userEmail);
        } else {
            System.out.println("Please enter your email.");
        }


    }


    public static String otp;
    public static int sendOTP(String userEmail) {
        final String username = "signifylearningapp@gmail.com";
        final String password = "xybxhshiikhrvvvj";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("signifylearningapp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));

            otp = generateOTP();

            message.setSubject("Signify password reset");
            message.setText("Dear " + userName + ",\n\nWe've received information that you've encountered issues accessing your Signify account due to a lost password. We understand the inconvenience this may cause and are here to help you through the process.\n" +
                    "\n" +
                    "To assist you in resetting your password, please utilize the following one-time passcode (OTP). Ensure you keep this code confidential and use it during the password recovery process.\n" +
                    "\n" + otp +  "\n" +
                    "If you encounter any difficulties or require further assistance, do not hesitate to reach out to our support team.\n" +
                    "\n" +
                    "Thanks for your understanding and cooperation.\n" +
                    "\n" +
                    "Best regards,\n" +
                    "\n" +
                    "The Signify Team\n\n" );

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(otp);
    }

    private static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return Integer.toString(otp);
    }

















}
