package cn.chinatelecom.biz.service;

import cn.chinatelecom.UserApplication;
import cn.chinatelecom.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class MailTest {
    @Autowired
    private MailService mailService;

    @Test
    public void testSendMail(){
        mailService.sendMail("brucezhou7812@gmail.com","This is a hello world mail","hello world");
    }
}
