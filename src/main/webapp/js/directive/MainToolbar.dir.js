(() => {
    'use strict';

    /**
     * Toolbar directive. Initially, is dealing with states and redirecting.
     *
     * @author Estácio Pereira
     */
    angular.module('toolbarModule', []).directive('mainToolbar', ['$state', function ($state) {
        return {
            restrict: 'AE',
            templateUrl: './view/directive/mainToolbar.html',
            scope: {},
            link: function ($scope, element, attrs) {

                const PROFILE_STATE = {
                    name: 'app.profile',
                    icon: 'fa fa-user'
                }, WATCHLIST_STATE = {
                    name: 'app.watchlist',
                    icon: 'fa fa-television'
                }, HOME_STATE = {
                    name: 'app.home',
                    icon: 'fa fa-home'
                };


                /**
                 * The object containing the available states given the current as key.
                 *
                 * @type {{[state]: [{[name]: [string], [icon]: [string]}]}}
                 */
                $scope.availableStates = {
                    'app.home': [PROFILE_STATE, WATCHLIST_STATE],
                    'app.profile': [HOME_STATE, WATCHLIST_STATE],
                    'app.watchlist': [HOME_STATE, PROFILE_STATE]
                };

                /**
                 * Returns the state name to display.
                 *
                 * @param {string} name Displayable state name.
                 */
                $scope.getStateName = function (name) {
                    return name.replace(/APP\./, '').replace(/\./g, ' ');
                };

                /**
                 * Returns the available states by accessing the current one.
                 *
                 * @returns {Array} List of the available states.
                 */
                $scope.getAvailableStates = function () {
                    const currentState = $state.current.name;
                    return $scope.availableStates[currentState];
                };
            }
        };
    }]);
})();
