(() => {

    angular.module('seriesModule').controller('SeriesDialogController', ['SeriesService', 'UserService', 'ModalService', 'ToastService', 'series', function (SeriesService, UserService, ModalService, ToastService, series) {

        this.series = series;

        /**
         * Returns the displayed card title.
         *
         * @returns {string} Title to be displayed.
         */
        this.getCardTitle = function () {
            return series.Title;
        };

        /**
         * Returns the displayed card subtitle.
         *
         * @returns {string} Subtitle to be displayed.
         */
        this.getCardSubtitle = function () {
            return `( ${series.Year} )`;
        };

        /**
         * Adds the series to the user's profile.
         *
         * @returns {Promise} Dialog or Toast's promise.
         */
        this.addToProfile = function () {
            if (UserService.isOnProfile(series)) {
                return ModalService.error(`${series.Title} is on your profile already!`);
            } else {
                UserService.addToProfile(series);
                return ToastService.showActionToast(`${series.Title} added to profile!`);
            }
        };

        /**
         * Adds the series to the user's watchlist.
         *
         * @returns {Promise} Dialog or Toast's promise.
         */
        this.addToWatchlist = function () {
            if (UserService.isOnWatchlist(series) || UserService.isOnProfile(series)) {
                return ModalService.error(`${series.Title} is on your 
                ${UserService.isOnProfile(series) ? 'profile' : 'watchlist'} 
                already! Cannot be added to your watchlist!`);
            } else {
                UserService.addToWatchlist(series);
                return ToastService.showActionToast(`${series.Title} added to watchlist!`);
            }
        };

        /**
         * Verifies if th series is on the user's watchlist.
         *
         * @returns {boolean} {@code true} if the series is on the user's watchlist.
         */
        this.isOnWatchlist = function () {
            return UserService.isOnWatchlist(series);
        };

        /**
         * Verifies if th series is on the user's profile.
         *
         * @returns {boolean} {@code true} if the series is on the user's profile.
         */
        this.isOnProfile = function () {
            return UserService.isOnProfile(series);
        };

        /**
         * Removes the series from the user's profile if he/she/it confirms.
         *
         * @returns {Promise} Confirmation dialog's promise.
         */
        this.removeFromProfile = function () {
            const message = `Do you really want to remove "${series.Title}" from your profile?`;
            return ModalService.confirm({message})
                .then(confirmation => removeFromProfile());
        };

        /**
         * Removes the series from the user's profile.
         *
         * @returns {Promise} Toast's promise.
         */
        function removeFromProfile() {
            UserService.removeFromProfile(series);
            return ToastService.showActionToast(`${series.Title} removed from profile!`);
        }
    }]);
})();
