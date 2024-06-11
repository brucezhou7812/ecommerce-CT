package cn.chinatelecom.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bruce Zhou
 * @since 2024-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("address")
public class AddressDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * user id
     */
    private Long userId;

    /**
     * 0:false,1:true
     */
    private Integer defaultStatus;

    /**
     * the name of the pepole who receives the goods
     */
    private String receiveName;

    /**
     * phone number
     */
    private String phone;

    private String province;

    private String city;

    private String region;

    private String detailAddress;

    private Date createTime;


}
