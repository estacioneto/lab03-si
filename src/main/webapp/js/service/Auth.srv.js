(() => {
    'use strict';

    angular.module('authModule', []).service('AuthService', ['$q', '$http', '$state', 'store', 'APP_STATES', 'API_URIS', function ($q, $http, $state, store, APP_STATES, API_URIS) {
        this.signUp = function (user) {
            const sentUser = angular.copy(user);
            sentUser.password = md5(sentUser.password);
            return $http.post(API_URIS.SIGNUP, sentUser);
        };

        this.isAuthenticated = () => !!(store.get('token'));

        this.logout = function () {
            store.remove('token');
            store.remove('user');
            $state.go(APP_STATES.INIT.name);
        };

        this.login = function (user) {
            const sentUser = angular.copy(user);
            sentUser.password = md5(sentUser.password);
            return $http.post(API_URIS.LOGIN, sentUser)
                .then(info => {
                    store.set('token', info.data.token);
                    return this.getLoggedUser();
                })
        };

        this.getLoggedUser = function () {
            const user = store.get('user');
            if (user) {
                return $q.when(user);
            }
            return $http.get(API_URIS.USERS)
                .then(info => {
                    store.set('user', info.data);
                    return info.data;
                });
        };

    }]);
})();
