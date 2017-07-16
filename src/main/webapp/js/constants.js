(() => {
    'use strict';
    const apiRoot = '/api';
    angular.module('siApp')
        .constant('API_URIS', {
            SIGNUP: apiRoot + '/users'
        })
        .constant('APP_STATES', {
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
