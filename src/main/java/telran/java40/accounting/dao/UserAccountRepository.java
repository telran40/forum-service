package telran.java40.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java40.accounting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

}
