package telran.java40.accounting.service;

import telran.java40.accounting.dto.RolesResponseDto;
import telran.java40.accounting.dto.UserAccountResponseDto;
import telran.java40.accounting.dto.UserRegisterDto;
import telran.java40.accounting.dto.UserUpdateDto;

public interface UserAccountService {
	UserAccountResponseDto addUser(UserRegisterDto userRegisterDto);
	
	UserAccountResponseDto getUser(String login);
	
	UserAccountResponseDto removeUser(String login);
	
	UserAccountResponseDto editUser(String login, UserUpdateDto updateDto);
	
	RolesResponseDto changeRolesList(String login, String role, boolean isAddRole);
	
	void changePassword(String login, String password);
	
	String checkToken(String token);
}
