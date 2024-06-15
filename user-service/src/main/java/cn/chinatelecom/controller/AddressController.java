package cn.chinatelecom.controller;


import cn.chinatelecom.enums.BizCodeEnum;
import cn.chinatelecom.models.AddressVO;
import cn.chinatelecom.service.AddressService;
import cn.chinatelecom.utils.JSonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Bruce Zhou
 * @since 2024-06-11
 */
@RestController
@Api(tags = "Address Module")
@RequestMapping("/api/address/v1/")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Get Address By adress id")
    @GetMapping("find/{address_id}")
    public JSonData<AddressVO> detail(@ApiParam(value = "Address id", required = true)
                                      @PathVariable("address_id") Long id) {
        AddressVO addressvO = addressService.detail(id);


        return addressvO != null ? JSonData.buildSuccess(addressvO) : JSonData.buildResult(BizCodeEnum.ADDRESS_NOT_EXIST);
    }
}

