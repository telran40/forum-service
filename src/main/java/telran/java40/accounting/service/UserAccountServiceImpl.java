package telran.java40.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java40.accounting.dao.UserAccountRepository;
import telran.java40.accounting.dto.RolesResponseDto;
import telran.java40.accounting.dto.UserAccountResponseDto;
import telran.java40.accounting.dto.UserRegisterDto;
import telran.java40.accounting.dto.UserUpdateDto;
import telran.java40.accounting.dto.exceptions.UserExistsException;
import telran.java40.accounting.dto.exceptions.UserNotFoundException;
import telran.java40.accounting.model.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	UserAccountRepository repository;
	ModelMapper modelMapper;

	@Autowired
	public UserAccountServiceImpl(UserAccountRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto) {
		if (repository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsException(userRegisterDto.getLogin());
		}
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		userAccount.addRole("USER".toUpperCase());
		userAccount.setPassword(userRegisterDto.getPassword());
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);

	}

	@Override
	public UserAccountResponseDto getUser(String login) {
		UserAccount userAccount = repository.findById(login)
				.orElseThrow(() -> new UserNotFoundException(login));
		return modelMapper.map(userAccount, UserAccountResponseDto.class);

	}

	@Override
	public UserAccountResponseDto removeUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		repository.deleteById(login);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);

	}

	@Override
	public UserAccountResponseDto editUser(String login, UserUpdateDto updateDto) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		if (updateDto.getFirstName() != null) {
			userAccount.setFirstName(updateDto.getFirstName());
		}
		if (updateDto.getLastName() != null) {
			userAccount.setLastName(updateDto.getLastName());
		}
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);

	}

	@Override
	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String login, String password) {
		// TODO Auto-generated method stub

	}

}
