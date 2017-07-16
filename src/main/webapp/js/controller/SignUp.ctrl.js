(() => {
    'use strict';

    angular.module('authModule').controller('SignUpController', [function () {
        this.user = {};

        this.submit = function () {
            console.log(this.user);
        };
    }]);
})();
