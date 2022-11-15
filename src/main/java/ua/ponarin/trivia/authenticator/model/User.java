package ua.ponarin.trivia.authenticator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String login;
    private String role;
    private String firstName;
    private String lastName;
    private Date createdOn;
    private Date updatedOn;
}
