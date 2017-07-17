(() => {
    'use strict';

    /**
     * The series main business logic service. Initially, loads the main entities of the app.
     *
     * @author Estácio Pereira.
     */
    angular.module('seriesModule', []).service('SeriesService', ['$http', 'UserService', 'API_URIS', function ($http, UserService, API_URIS) {
        const self = this;
        const seriesListUrl = 'https://omdbapi.com/?s=TITLE&apikey=93330d3c&type=series',
            singleSeriesUrl = 'https://omdbapi.com/?t=TITLE&apikey=93330d3c&type=series';

        /**
         * Loads the list of the series whose title matches with the given one.
         *
         * @param  {string}  title Title to match the series.
         * @return {Promise} Load promise
         */
        this.loadSeriesList = function (title) {
            const uri = encodeURI(seriesListUrl.replace(/TITLE/, title));
            return $http.get(uri);
        };

        /**
         * Loads the series (singular) whose title matches with the given one.
         *
         * @param  {string}  title Title of the series.
         * @return {Promise} Load promise
         */
        this.loadSingleSeries = function (title) {
            const uri = encodeURI(singleSeriesUrl.replace(/TITLE/, title));
            return $http.get(uri);
        };

        this.loadProfileSeriesList = function () {
            return $http.get(API_URIS.PROFILE_SERIES);
        };

        this.loadWatchlistSeriesList = function () {
            return $http.get(API_URIS.WATCHLIST_SERIES);
        };

        this.addToProfile = function (series) {
            series.onProfile = true;
            series.onWatchlist = false;
            return saveSeries(series);
        };

        this.addToWatchlist = function (series) {
            series.onProfile = false;
            series.onWatchlist = true;
            return saveSeries(series);
        };

        function saveSeries(series) {
            const method = series.id ? $http.put : $http.post;
            return method(API_URIS.SERIES, series);
        }

        this.removeFromProfile = function (series) {
            return $http.delete(API_URIS.SERIES + `/${series.id}`, series);
        };
    }]);
})();
