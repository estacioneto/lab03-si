(() => {
    'use strict';

    angular.module('siApp').constant('APP_STATES', {
        PROFILE: {
            name: 'app.profile',
            icon: 'person'
        }, WATCHLIST: {
            name: 'app.watchlist',
            icon: 'tv'
        }, HOME: {
            name: 'app.home',
            icon: 'home'
        }, SIGNUP: {
            name: 'app.signup',
            icon: 'person_add'
        }
    });
})();
