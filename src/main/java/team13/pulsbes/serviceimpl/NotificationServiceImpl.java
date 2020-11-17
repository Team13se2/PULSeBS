package team13.pulsbes.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import team13.pulsbes.services.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    JavaMailSender emailSender;


    @Override
    public void sendMessage(String address, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("team13se2@gmail.com");
        message.setTo(address);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
      
    }
}
