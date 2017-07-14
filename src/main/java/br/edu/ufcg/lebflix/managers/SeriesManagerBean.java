package br.edu.ufcg.lebflix.managers;

import br.edu.ufcg.lebflix.entities.Series;
import br.edu.ufcg.lebflix.entities.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The Series manager/service/business logic handler implementation.
 *
 * @author Estácio Pereira
 */
@Service
@Transactional
public class SeriesManagerBean implements SeriesManager {
    @Override
    public List<Series> getProfileSeries(User user) {
        return null;
    }
}
