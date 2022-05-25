package com.kpaw.world.dao;

import com.kpaw.world.entity.CountryLanguage;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CountryLanguageRepositoryImp implements CountryLanguageRepository {

    private final EntityManager em;

    @Autowired
    public CountryLanguageRepositoryImp(EntityManager theEntityManager) {
        em = theEntityManager;
    }

    @Override
    public List<CountryLanguage> findAll() {
        Session currentSession = em.unwrap(Session.class);
        Query<CountryLanguage> theQuery = currentSession.createQuery("from CountryLanguage", CountryLanguage.class);
        return theQuery.getResultList();

    }

    @Override
    public List<CountryLanguage> searchBy(String theLanguage, String theCountry) {
        Session currentSession = em.unwrap(Session.class);
        Query<CountryLanguage> theQuery = currentSession.createQuery(
                "from CountryLanguage where language like :language and countryCode.name like :country",
                CountryLanguage.class);
        theQuery.setParameter("language", "%" + theLanguage + "%");
        theQuery.setParameter("country", "%" + theCountry + "%");
        return theQuery.getResultList();
    }

    @Override
    public List<CountryLanguage> orderByCountry() {
        Session currentSession = em.unwrap(Session.class);
        Query<CountryLanguage> theQuery = currentSession.createQuery("from CountryLanguage order by countryCode.name",
                CountryLanguage.class);
        return theQuery.getResultList();
    }

    @Override
    public List<CountryLanguage> orderByLanguage() {
        Session currentSession = em.unwrap(Session.class);
        Query<CountryLanguage> theQuery =
                currentSession.createQuery("from CountryLanguage order by language", CountryLanguage.class);
        return theQuery.getResultList();
    }

}























