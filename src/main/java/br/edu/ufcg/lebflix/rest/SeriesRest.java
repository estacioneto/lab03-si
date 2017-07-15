package br.edu.ufcg.lebflix.rest;

import br.edu.ufcg.lebflix.entities.Series;
import br.edu.ufcg.lebflix.entities.User;
import br.edu.ufcg.lebflix.managers.SeriesManager;
import br.edu.ufcg.lebflix.managers.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Series endpoint.
 *
 * @author Estácio Pereira.
 * @value /api/series
 */
@RestController
@RequestMapping(value = "/api/series")
public class SeriesRest {

    @Autowired
    private SeriesManager seriesManager;

    @Autowired
    private UsersManager usersService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public List<Series> getProfileSeries(@RequestHeader String Authorization) {
        User user = usersService.getLoggedUser(Authorization);
        return seriesManager.getProfileSeries(user);
    }

    @RequestMapping(value = "/watchlist", method = RequestMethod.GET)
    public List<Series> getWatchlistSeries(@RequestHeader String Authorization) {
        User user = usersService.getLoggedUser(Authorization);
        return seriesManager.getWatchlistSeries(user);
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Series addSeries(@RequestHeader String Authorization, @RequestBody Series series) {
        User user = usersService.getLoggedUser(Authorization);
        return seriesManager.addSeries(user, series);
    }

    @RequestMapping(value = "/{idSeries}", method = RequestMethod.DELETE)
    public Series deleteSeries(@RequestHeader String Authorization, @PathVariable Long idSeries) {
        User user = usersService.getLoggedUser(Authorization);
        return seriesManager.deleteSeries(user, idSeries);
    }

    @RequestMapping(value = "/{idSeries}", method = RequestMethod.PUT)
    public Series updateSeries(@RequestHeader String Authorization,
                               @PathVariable Long idSeries,
                               @RequestBody Series series) {
        User user = usersService.getLoggedUser(Authorization);
        return seriesManager.updateSeries(user, idSeries, series);
    }
}
