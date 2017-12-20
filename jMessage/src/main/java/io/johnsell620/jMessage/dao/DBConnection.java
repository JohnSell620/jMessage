package io.johnsell620.jMessage.dao;

import org.hibernate.cfg.Configuration;

import io.johnsell620.jMessage.model.Message;

import org.hibernate.SessionFactory;

import org.hibernate.Session;

public class DBConnection {
	Configuration con = new Configuration().configure().addAnnotatedClass(Message.class);
	SessionFactory sf = con.buildSessionFactory();
	Session session	  = sf.openSession();
	
	
}
