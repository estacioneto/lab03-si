package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.Series;
import br.edu.ufcg.lebflix.entities.User;

import java.util.List;

/**
 * The Series manager/service/business logic handler.
 *
 * @author Estácio Pereira
 */
public interface SeriesManager {
    List<Series> getProfileSeries(User user);
}
