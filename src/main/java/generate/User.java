package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Integer userId;

    /**
     * 关联ID
     */
    private Integer relationId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 是否可用，0否，1是
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 修改人
     */
    private Integer updateBy;

    /**
     * 登录时间
     */
    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;
}