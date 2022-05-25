package com.kpaw.world.dao;

import com.kpaw.world.entity.City;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CityRepositoryJPA implements CityRepository{

   private final EntityManager em;

   @Autowired
    public CityRepositoryJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<City> findAll() {
        Query query = em.createQuery("from City", City.class);
        return query.getResultList();
    }

    @Override
    public void save(City theCity) {
        Session currentSession = em.unwrap(Session.class);
        currentSession.saveOrUpdate(theCity);
    }

    @Override
    public void deleteCityById(int theId) {
        Query query = em.createQuery("delete from City where id=:cityID", City.class);
        query.setParameter("cityID", theId);
        query.executeUpdate();
    }

    @Override
    public City findById(int theId) {
        return em.find(City.class,theId);
    }

    @Override
    public List<City> findByNameAndCountry(String theName, String theCountry) {
        Query query = em.createQuery("from City where name like :cityName and country.name like :countryName", City.class);
        query.setParameter("cityName", "%" + theName + "%");
        query.setParameter("countryName", "%" + theCountry + "%");
        return query.getResultList();
   }

    @Override
    public List<City> sortByNameAsc() {
        Query query = em.createQuery("from City order by name", City.class);
        return query.getResultList();
    }

    @Override
    public List<City> sortByCountryNameAsc() {
        Query query = em.createQuery("from City order by country.name", City.class);
        return query.getResultList();
    }

    @Override
    public List<City> sortByPopulationAsc() {
        Query query = em.createQuery("from City order by population asc", City.class);
        return query.getResultList();
    }
}
