package telran.java40.accounting.dto;

import lombok.Getter;
import lombok.NonNull;

@Getter
@NonNull
public class UserRegisterDto {
	String login;
	String password;
	String firstName;
	String lastName;
}
