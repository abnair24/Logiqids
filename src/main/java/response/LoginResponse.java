package response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse{

    private int user_id;
    private String session_token;
}
