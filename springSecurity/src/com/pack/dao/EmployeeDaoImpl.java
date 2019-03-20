package com.pack.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pack.model.Employee;
import com.pack.model.Login;
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

@Autowired
private SessionFactory sessionFactory;
	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stubSession s=sessionFactory.getCurrentSession();
		Session s=sessionFactory.getCurrentSession();
		Employee c=(Employee)s.get(Employee.class,id);
		if(c!=null)
			s.delete(c);		
	}

	//@Override
	public void addEmployee(Employee e) {
		sessionFactory.getCurrentSession().save(e);
		
	}

	//@Override
	public List<Employee> listEmployee() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("From Employee").list();
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException , DataAccessException
	{
		System.out.println("getting access details from employee dao !!");
		System.out.println(username);
		Session s=this.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("from Login l where l.username=:user");
		q.setParameter("user", username);
		Login l1=(Login)q.uniqueResult();
		UserDetails user=null;
		if(l1 !=null)
		{
			user=new User(l1.getUsername(),l1.getPassword(),true,true,true,true,new GrantedAuthority[]{new GrantedAuthorityImpl(l1.getRole())});
		}
		t.commit();
		return user;
		}
	}
	

