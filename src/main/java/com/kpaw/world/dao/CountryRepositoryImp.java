package com.kpaw.world.dao;

import com.kpaw.world.entity.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CountryRepositoryImp implements CountryRepository {

	private final EntityManager em;

	@Autowired
	public CountryRepositoryImp(EntityManager theEntityManager) {
		em = theEntityManager;
	}
	
	@Override
	public List<Country> findAll() {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country", Country.class);
		return theQuery.getResultList();

	}

	@Override
	public List<Country> searchBy(String theCode, String theName, String theRegion) {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code like :code and name like :name and region like :region", Country.class);
		theQuery.setParameter("code", "%" + theCode + "%");
		theQuery.setParameter("name", "%" + theName + "%");
		theQuery.setParameter("region", "%" + theRegion + "%");
		return theQuery.getResultList();
	}

	@Override
	public List<Country> orderByRegion() {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by region", Country.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Country> orderByName() {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by name", Country.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Country> orderBySurface() {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by surfaceArea asc", Country.class);
		return theQuery.getResultList();
	}

	@Override
	public List<Country> orderByCode() {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country order by code", Country.class);
		return theQuery.getResultList();
	}

	@Override
	public Country findById(String theCountryCode) {
		Session currentSession = em.unwrap(Session.class);
		Query<Country> theQuery = 
				currentSession.createQuery("from Country where code =:countryCode", Country.class);
		theQuery.setParameter("countryCode", theCountryCode);
		return theQuery.getSingleResult();
	}

}
