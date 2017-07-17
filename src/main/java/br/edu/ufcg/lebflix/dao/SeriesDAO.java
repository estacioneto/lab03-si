package br.edu.ufcg.lebflix.dao;

import br.edu.ufcg.lebflix.entities.Series;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Series database handler.
 *
 * @author Estácio Pereira
 */
@Repository
public class SeriesDAO {

    private static final String QUERY_PROFILE_SERIES = new StringBuilder()
            .append("SELECT s ")
            .append("FROM Series s ")
            .append("WHERE s.idUser=:idUser ")
            .append("AND s.onProfile IS TRUE ")
            .toString();

    private static final String QUERY_WATCHLIST_SERIES = new StringBuilder()
            .append("SELECT s ")
            .append("FROM Series s ")
            .append("WHERE s.idUser=:idUser ")
            .append("AND s.onWatchlist IS TRUE ")
            .toString();

    private static final String QUERY_SERIES_EXISTS = new StringBuilder()
            .append("SELECT COUNT(1) ")
            .append("FROM Series s ")
            .append("WHERE s.idUser=:idUser ")
            .append("AND s.imdbID=:imdbID ")
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
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idUser", idUser);
        return entityManager.find(Series.class, idSeries, parameters) != null;
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

    public boolean existsSeries(Long idUser, String imdbID) {
        TypedQuery<Long> getUserQuery = entityManager.createQuery(QUERY_SERIES_EXISTS, Long.class);
        getUserQuery.setParameter("idUser", idUser);
        getUserQuery.setParameter("imdbID", imdbID);
        return EXISTS.equals(getUserQuery.getSingleResult());
    }
}
