package bank.dao;

import java.util.List;

import bank.entity.User;
import bank.entity.UserData;

public interface UserDAO {

	public List<User> getUsers();
	public void saveUser(User theUser);
	public User getUser(int theId);
	public UserData getUserData(User user);
	public void deleteUser(int theId);
	public User queryLoginPassword(User user);
	public List<User> searchUser(User user); 
}
