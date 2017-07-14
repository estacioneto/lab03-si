package br.edu.ufcg.lebflix.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Series database handler.
 *
 * @author Estácio Pereira
 */
@Repository
public class SeriesDAO {

    @PersistenceContext
    private EntityManager entityManager;
}
