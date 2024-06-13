package cn.chinatelecom.biz.service;

import cn.chinatelecom.UserApplication;
import cn.chinatelecom.models.AddressVO;
import cn.chinatelecom.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
@Slf4j
public class AddressTest {
    @Autowired
    private AddressService addressService;

    @Test
    public void testAddressDetail(){
        AddressVO addressVO = addressService.detail(2L);
        if(addressVO != null)
            log.info(addressVO.toString());
        Assert.assertNotNull(addressVO);
    }
}
