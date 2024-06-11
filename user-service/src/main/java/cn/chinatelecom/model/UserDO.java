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
@TableName("user")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * nick name
     */
    private String name;

    /**
     * password
     */
    private String pwd;

    /**
     * portait
     */
    private String headImg;

    /**
     * signature
     */
    private String slogan;

    /**
     * 1 male, 0 female
     */
    private Integer sex;

    /**
     * points
     */
    private Integer points;

    /**
     * create time 
     */
    private Date createTime;

    /**
     * email
     */
    private String mail;

    /**
     * private key
     */
    private String secret;


}
