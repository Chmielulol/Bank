package bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.dao.UserDAO;
import bank.entity.User;
import bank.entity.UserData;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserDAO userDAO;
	

	@Override
	@Transactional
	public void saveUser(User user) {
		
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(int theId) {
		
		return userDAO.getUser(theId);
	}

	@Override
	@Transactional
	public UserData getUserData(User user) {
		
		return userDAO.getUserData(user);
	}

	public UserServiceImpl() {}

	@Override
	@Transactional
	public User loginUser(User user) {
		
		user = userDAO.queryLoginPassword(user);

		return user;
	}

}
