(() => {
    'use strict';

    angular.module('userModule').controller('WatchlistController', ['UserService', function (UserService) {
        const self = this;

        /**
         * Indicates if the series listing will be able to provide details about the series.
         * @type {boolean}
         */
        this.showDetails = false;

        this.watchlist = UserService.watchlist;
    }]);
})();
