package br.edu.ufcg.lebflix.dao;

import br.edu.ufcg.lebflix.entities.Series;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Series database handler.
 *
 * @author Estácio Pereira
 */
@Repository
public class SeriesDAO {

    private static final String QUERY_PROFILE_SERIES = new StringBuilder()
            .append("SELECT Series s ")
            .append("FROM Series s")
            .append("WHERE s.idUser=:idUser")
            .append("AND s.onProfile=TRUE")
            .toString();

    private static final String QUERY_WATCHLIST_SERIES = new StringBuilder()
            .append("SELECT Series s ")
            .append("FROM Series s")
            .append("WHERE s.idUser=:idUser")
            .append("AND s.onWatchlist=TRUE")
            .toString();

    private static final String OWNS_THE_SERIES = new StringBuilder()
            .append("SELECT Series s ")
            .append("FROM Series s")
            .append("WHERE s.idUser=:idUser")
            .append("AND s.id=:id")
            .toString();

    private static final Long EXISTS = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Series> getProfileSeries(Long idUser) {
        TypedQuery<Series> query = entityManager.createQuery(QUERY_PROFILE_SERIES, Series.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

    public List<Series> getWatchlistSeries(Long idUser) {
        TypedQuery<Series> query = entityManager.createQuery(QUERY_WATCHLIST_SERIES, Series.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

    public void persistSeries(Series series) {
        entityManager.persist(series);
    }

    public boolean isSeriesOwner(Long idUser, Long idSeries) {
        TypedQuery<Long> query = entityManager.createQuery(OWNS_THE_SERIES, Long.class);
        query.setParameter("idUser", idUser);
        query.setParameter("id", idSeries);
        return EXISTS.equals(query.getSingleResult());
    }

    public Series getSeries(Long idSeries) {
        return entityManager.find(Series.class, idSeries);
    }

    public void removeSeries(Series series) {
        entityManager.remove(series);
    }

    public Series updateSeries(Series series) {
        series = entityManager.merge(series);
        entityManager.flush();
        entityManager.refresh(series);
        return series;
    }
}
