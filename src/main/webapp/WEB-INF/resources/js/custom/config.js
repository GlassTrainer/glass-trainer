app
    .config([
        '$routeProvider',
        '$httpProvider',
        'localStorageServiceProvider',
        'uiGmapGoogleMapApiProvider',
        function ($routeProvider, $httpProvider,
                  localStorageServiceProvider, uiGmapGoogleMapApiProvider) {

            // ======= Google Maps configuration ========

            uiGmapGoogleMapApiProvider.configure({
                key: 'AIzaSyCHbcTNWIoU52ZTZbSrQyJPXeIwt2K4xmQ',
                v: '3.17',
                libraries: 'weather,geometry,visualization'
            });

            // ======= local storage configuration ========

            localStorageServiceProvider.prefix = 'example';

            // ======= router configuration =============

            $routeProvider
                .when(
                '/',
                {
                    templateUrl: 'resources/html/partials/view/main.html'
                })
                .when(
                '/athletes',
                {
                    controller: 'AthleteController',
                    templateUrl: 'resources/html/partials/view/athletes.html'

                })
                .when(
                '/athlete/create',
                {
                    controller: 'AthleteController',
                    templateUrl: 'resources/html/partials/view/athlete_create.html'
                })
                .when(
                '/athlete/:id',
                {
                    controller: 'AthleteController',
                    templateUrl: 'resources/html/partials/view/athlete_detail.html'
                })
                .when(
                '/realTime/monitor',
                {
                    controller: 'RealTimeController',
                    templateUrl: 'resources/html/partials/view/realTime.html'
                })
                /*.when(
                 '/login',
                 {
                 templateUrl : 'resources/html/partials/view/login.html'
                 })*/
                .otherwise({
                    redirectTo: "/"
                });

            // ======== http configuration ===============

            // configure $http to view a login whenever a 401
            // unauthorized response arrives
            $httpProvider.responseInterceptors
                .push(function ($rootScope, $q, $location) {
                    return function (promise) {
                        return promise
                            .then(
                            // success -> don't
                            // intercept
                            function (response) {
                                return response;
                            },
                            // error -> if 401 save the
                            // request and broadcast an
                            // event
                            function (response) {
                                if (response.status === 401) {
                                    var deferred = $q
                                        .defer(), req = {
                                        config: response.config,
                                        deferred: deferred
                                    };

                                    $httpProvider.defaults.headers.common.Authorization = null;

                                    $rootScope.requests401
                                        .push(req);
                                    $rootScope
                                        .$broadcast('event:loginRequired');

                                    return deferred.promise;
                                }
                                // if error is
                                // authorization related
                                if (response.status == 403) {
                                    $location
                                        .path("/main");


                                    var deferred =
                                        $q.defer(), req = {
                                        config: response.config,
                                        deferred: deferred
                                    };

                                    $rootScope.requests403.push(req);
                                    $rootScope.$broadcast('event:noAuthorization');

                                    return
                                    deferred.promise;

                                }

                                return $q
                                    .reject(response);
                            });
                    };
                });
        }]);