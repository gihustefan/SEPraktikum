package at.decisionexpert.neo4jentity.dto.user;

import at.decisionexpert.neo4jentity.node.User;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * Created by stefanhaselboeck on 11.10.16.
 */
@QueryResult
public class UserCreationDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public UserCreationDto() {
        // TODO Auto-generated constructor stub
    }

    public UserCreationDto(User user) {
        username = user.getOriginalUsername();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        password = user.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
