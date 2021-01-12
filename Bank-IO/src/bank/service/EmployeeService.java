package bank.service;

import java.util.List;

import bank.entity.User;
import bank.entity.UserData;

public interface EmployeeService {

	public List<User> getUsers();
	public void addClient(UserData data);
	public List<User> searchClient(User user);
	public User getClient(int id);
}
