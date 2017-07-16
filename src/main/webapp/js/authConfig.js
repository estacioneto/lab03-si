(function () {
    'use strict';
    angular.module('siApp').config(['$urlRouterProvider', '$provide', '$httpProvider',
        function ($urlRouterProvider, $provide, $httpProvider) {

            $provide.factory('redirect', ['$q', '$injector', 'store', function ($q, $injector, store) {
                return {
                    responseError: function (rejection) {
                        if (rejection.status === 401) {
                            store.remove('user');
                            store.remove('token');
                            $injector.get('$state').go('init');
                        }
                        return $q.reject(rejection);
                    }
                };
            }]);

            $provide.factory('APIInterceptor', ['store', function (store) {
                return {
                    request: function (config) {
                        const idToken = store.get('token');
                        if (idToken) {
                            config.headers.Authorization = 'Bearer ' + idToken;
                        }
                        return config;
                    }
                };
            }]);

            $httpProvider.interceptors.push('redirect');
            $httpProvider.interceptors.push('APIInterceptor');

        }])
        .run(['$rootScope', 'store', '$state', 'APP_STATES', function ($rootScope, store, $state, APP_STATES) {
            $rootScope.$on('$stateChangeStart', function (event, toState) {
                // if (!store.get('token') && (toState.name !== APP_STATES.HOME.name)) {
                //     event.preventDefault();
                //     $state.go(APP_STATES.HOME.name);
                // }
            });
        }]);
}());
