package com;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.beans.Person;

public class PersonService {

	SessionFactory sessionFcatory;
	Session session;
	public PersonService() {
		System.out.println("PersonService constructor called!!!!!");
		
	}

	public void createPerson(Person p) {
		System.out.println("PersonService createPerson called!!!!!");
		
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.save(p);
		tx.commit();
		session.close();
	}

	public void updatePerson(int counter, Person p) {
		System.out.println("PersonService updatePerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		p.setId(counter);	
		session.update(p);
		tx.commit();
		session.close();

	}

	public List<Person> getPersons() {
		System.out.println("PersonService getPersons() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Person");
		List<Person> list =  query.list();
		tx.commit();
		session.close();
		return list;
		
	}
	
	public Person getPerson(int count) {
		System.out.println("PersonService getPerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Person where id = "+count);
		List<Person> list =  query.list();
		tx.commit();
		session.close();
		return (Person)list.get(0);
		
	}

	public void deletePerson(int counter) {
		System.out.println("PersonService deletePerson() called!!!!!");
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Person where id = "+counter);
		Person p =  (Person)query.list().get(0);
		session.delete(p);
		tx.commit();
		session.close();
	}
	
	public Session getSession()
	{
		sessionFcatory = new AnnotationConfiguration().configure().buildSessionFactory();
		return sessionFcatory.openSession();
	}

}
