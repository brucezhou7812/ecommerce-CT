package cn.chinatelecom.service.impl;

import cn.chinatelecom.mapper.AddressMapper;
import cn.chinatelecom.model.AddressDO;
import cn.chinatelecom.models.AddressVO;
import cn.chinatelecom.service.AddressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public AddressVO detail(Long id) {
        AddressDO addressDO = addressMapper.selectOne(
                new QueryWrapper<AddressDO>().eq("id",id));
        if(addressDO == null) return null;
        AddressVO addressVO = new AddressVO();
        BeanUtils.copyProperties(addressDO,addressVO);
        return addressVO;
    }
}
