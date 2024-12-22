package online.proyi.springbot3.entity.query;

import lombok.Data;

@Data
public class UserQueryBean {

    /**
     * contains name pattern.
     */
    private String userName;

    /**
     * contains desc pattern.
     */
    private String description;

    private String phoneNumber;

    private String email;

}
