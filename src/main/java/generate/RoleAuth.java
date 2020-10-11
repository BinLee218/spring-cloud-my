package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * role_auth
 * @author 
 */
@Data
public class RoleAuth implements Serializable {
    private Integer rAId;

    private Integer roleId;

    private Integer authId;

    private static final long serialVersionUID = 1L;
}