package jact.lagaltproject.models.dtos.user;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private boolean hidden;
    private String[] skills;
    private String googleToken;
    private String twitterToken;
    private String portfolio;
    private String description;
}
