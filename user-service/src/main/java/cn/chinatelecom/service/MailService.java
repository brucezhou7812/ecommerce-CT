package cn.chinatelecom.service;

public interface MailService {
    void sendMail(String to,String content,String subject);
}
