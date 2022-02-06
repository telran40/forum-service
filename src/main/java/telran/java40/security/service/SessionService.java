package telran.java40.security.service;

import telran.java40.accounting.model.UserAccount;

public interface SessionService {
	UserAccount addUser(String sessionId, UserAccount userAccount);

	UserAccount getUser(String sessionId);

	UserAccount removeUser(String sessionId);

}
