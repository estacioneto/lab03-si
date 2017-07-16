(() => {
    'use strict';

    angular.module('authModule', []).service('AuthService', ['$http', 'API_URIS', function ($http, API_URIS) {
        this.signUp = function (user) {
            return $http.post(API_URIS.SIGNUP, user);
        };
    }]);
})();
