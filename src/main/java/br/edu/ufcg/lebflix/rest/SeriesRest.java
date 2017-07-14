package br.edu.ufcg.lebflix.rest;

import br.edu.ufcg.lebflix.entities.Series;
import br.edu.ufcg.lebflix.entities.User;
import br.edu.ufcg.lebflix.managers.SeriesManager;
import br.edu.ufcg.lebflix.managers.UsersManager;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public List<Series> getProfileSeries(@RequestHeader String userToken) {
        User user = usersService.getLoggedUser(userToken);
        return seriesManager.getProfileSeries(user);
    }

}
