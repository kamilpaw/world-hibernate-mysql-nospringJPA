package com.kpaw.world.dao;

import com.kpaw.world.entity.City;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class CityRepositoryImp implements CityRepository{

	private final EntityManager em;

	@Autowired
	public CityRepositoryImp(EntityManager theEntityManager) {
		em = theEntityManager;
	}

	@Override
	public List<City> findAll() {
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery = 
				currentSession.createQuery("from City", City.class);
		return theQuery.getResultList();

	}

	
	@Override
	public void save(City theCity) {
		Session currentSession = em.unwrap(Session.class);
		currentSession.saveOrUpdate(theCity);
		
	}

	@Override
	public void deleteCityById(int theId) {
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery =
				currentSession.createQuery("delete from City where id=:cityId", City.class);
		theQuery.setParameter("cityId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public City findById(int theId) {
		Session currentSession = em.unwrap(Session.class);
		return currentSession.get(City.class, theId);
	}
	
	@Override
	public List<City> findByNameAndCountry(String theName, String theCountry){
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery = 
				currentSession.createQuery("from City where name like :theName and country.name like :theCountry", City.class);
		theQuery.setParameter("theName", "%" + theName + "%");
		theQuery.setParameter("theCountry", "%" + theCountry + "%");
		return theQuery.getResultList();
	}

	public List<City> sortByNameAsc(){
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery = 
				currentSession.createQuery("from City order by name", City.class);
		return theQuery.getResultList();

	}

	public List<City> sortByCountryNameAsc(){
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery = 
				currentSession.createQuery("from City order by country.name", City.class);
		return theQuery.getResultList();
	}

	public List<City> sortByPopulationAsc(){
		Session currentSession = em.unwrap(Session.class);
		Query<City> theQuery = 
				currentSession.createQuery("from City order by population asc", City.class);
		return theQuery.getResultList();
	}

}
