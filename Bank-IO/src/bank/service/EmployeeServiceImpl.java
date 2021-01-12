package bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.dao.UserDAO;
import bank.entity.User;
import bank.entity.UserData;
import bank.entity.UserType;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		
		List<User> clients = new ArrayList<User>();
		
		for(User tempUser : userDAO.getUsers()) {
			if(tempUser.getType()==UserType.Client) {
				tempUser.setUserData(userDAO.getUserData(tempUser));
				clients.add(tempUser);
			}
		}
		

		
		return clients;
	}

	@Override
	@Transactional
	public void addClient(UserData data) {
		
		User newUser = new User();
		
		int id = searchClient(data.getUser()).size();

		String login = ""+data.getFirstName().charAt(0)+data.getFirstName().charAt(1)+data.getFirstName().charAt(2)+data.getLastName().charAt(0)+data.getLastName().charAt(1)+data.getLastName().charAt(2)+id;
		
		String password = login+data.getPesel().charAt(8)+data.getPesel().charAt(9)+data.getPesel().charAt(10);
		
		newUser.setLogin(login);
		newUser.setPassword(password);
		
		newUser.setType(UserType.Client);
		newUser.setUserData(data);
		data.setUser(newUser);
		
		
		userDAO.saveUser(newUser);
		
		
	}

	@Override
	@Transactional
	public List<User> searchClient(User user) {
		
		
		List<User> list = new ArrayList<User>();
		
		
		for(User tempUser: userDAO.searchUser(user)) {
			if(tempUser.getType()==UserType.Client) {
				list.add(tempUser);
			}
		}
		
		return list;
	}

	@Override
	@Transactional
	public User getClient(int id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id);
	}

	
	
}
