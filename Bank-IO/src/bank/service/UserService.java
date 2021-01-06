package bank.service;

import bank.entity.User;
import bank.entity.UserData;

public interface UserService {

	public void saveUser(User user);
	
	public User getUser(int theId);
	
	public UserData getUserData(User user);
	
	public User loginUser(User user);

}
