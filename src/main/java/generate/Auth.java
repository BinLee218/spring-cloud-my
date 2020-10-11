package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * auth
 * @author 
 */
@Data
public class Auth implements Serializable {
    /**
     * 主键
     */
    private Integer authId;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限值
     */
    private String authValue;

    /**
     * 状态
     */
    private Byte state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}