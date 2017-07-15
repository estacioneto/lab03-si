(() => {
    'use strict';

    /**
     * The Profile page Controller.
     *
     * @author Estácio Pereira.
     */
    angular.module('userModule').controller('ProfileController', ['UserService', function (UserService) {
        const self = this;

        /**
         * Indicates if the series listing will be able to provide details about the series.
         * @type {boolean}
         */
        this.showDetails = true;

        this.profile = UserService.profile;

    }]);
})();
