package telran.java40.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java40.accounting.dto.RolesResponseDto;
import telran.java40.accounting.dto.UserAccountResponseDto;
import telran.java40.accounting.dto.UserRegisterDto;
import telran.java40.accounting.dto.UserUpdateDto;
import telran.java40.accounting.service.UserAccountService;

@RestController
@RequestMapping("/account")
public class UserAccountController {

	UserAccountService accountService;

	@Autowired
	public UserAccountController(UserAccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/register")
	public UserAccountResponseDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return accountService.addUser(userRegisterDto);
	}

	//FIXME
	@PostMapping("/login")
	public UserAccountResponseDto login(String login) {	
		return accountService.getUser(login);
	}

	@PutMapping("/user/{login}")
	public UserAccountResponseDto updateUser(@PathVariable String login, @RequestBody UserUpdateDto userUpdateDto) {
		return accountService.editUser(login, userUpdateDto);
	}

	@DeleteMapping("/user/{login}")
	public UserAccountResponseDto removeUser(@PathVariable String login) {
		return accountService.removeUser(login);
	}

	@PutMapping("/user/{login}/role/{role}")
	public RolesResponseDto addRole(@PathVariable String login, @PathVariable String role) {
		return accountService.changeRolesList(login, role, true);
	}

	@DeleteMapping("/user/{login}/role/{role}")
	public RolesResponseDto removeRole(@PathVariable String login, @PathVariable String role) {
		return accountService.changeRolesList(login, role, false);
	}

	//FIXME
	@PutMapping("/password")
	public void changePassword(String login,  String password) {
		accountService.changePassword(login, password);
	}


}
