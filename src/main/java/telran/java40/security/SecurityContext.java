package telran.java40.security;

public interface SecurityContext {
	boolean addUser(UserProfile user);

	UserProfile removeUser(String login);

	UserProfile getUser(String login);
}
