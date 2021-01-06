package bank.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bank.entity.User;
import bank.entity.UserData;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory factory;

	@Override
	public List<User> getUsers() {
		
		Session session = factory.getCurrentSession();
		
		Query<User> query= session.createQuery("from User", User.class);
		
		List<User> tempList = query.getResultList();
		
		return tempList;
	}

	@Override
	public void saveUser(User theUser) {
		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(theUser);
		
	}

	@Override
	public User getUser(int theId) {
		Session session = factory.getCurrentSession();
		
		User tempUser = session.get(User.class, theId);
		
		return tempUser;
	}

	@Override
	public void deleteUser(int theId) {
		Session session = factory.getCurrentSession();
		
		User tempUser = session.get(User.class, theId);
		
		session.delete(tempUser);
	}

	@Override
	public User queryLoginPassword(User user) {
		
		Session session = factory.getCurrentSession();
		
		Query<User> query= session.createQuery("from User where login='"+user.getLogin()+"' AND password='"+user.getPassword()+"'", User.class);
		
		return query.uniqueResult();
	}

	@Override
	public UserData getUserData(User user) {
		
		Session session = factory.getCurrentSession();
		
		Query<UserData> query= session.createQuery("from UserData where user_id='"+user.getId()+"'", UserData.class);
		
		return query.uniqueResult();
	}


	@Override
	public List<User> searchUser(User user) {
		
		Session session = factory.getCurrentSession();
		
		Query<User> query1 = session.createQuery("from User where login='"+user.getLogin()+"'",User.class);
		Query<UserData> query2 = session.createQuery("from UserData where email='"+user.getLogin()+
				"' or firstName='"+user.getLogin()+"' or lastName='"+user.getLogin()+
				"' or pesel='"+user.getLogin()+"' or phoneNumber='"+user.getLogin()+"'",UserData.class);
		
		List<User> list = query1.getResultList();
		
		if(list==null) {
			list= new ArrayList<User>();
		}
		for(UserData tempUserData : query2.getResultList()) {
			list.add(tempUserData.getUser());
			System.out.println(tempUserData);
		}
	
			
		return list;
	}
	
}
