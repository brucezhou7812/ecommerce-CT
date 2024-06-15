package cn.chinatelecom.service.impl;

import cn.chinatelecom.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.from}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendMail(String to, String content, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(content);
        message.setTo(to);
        try {
            mailSender.send(message);
            log.info("Mail is sent. "+message);
        }catch(MailException e){
            log.error("Mail sending error: "+e);
        }

    }
}
