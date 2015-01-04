app.run(function ($rootScope, $http, $location, Base64Service, AuthenticationService, localStorageService, editableOptions) {

    editableOptions.theme = 'bs3';


    $rootScope.errors = [];
    $rootScope.requests401 = [];
    $rootScope.navigateTo = "/main";

    $rootScope.$on('$routeChangeSuccess', function (event, next, current) {
        $rootScope.user = localStorageService.get('localStorageUser');
    });


    /**
     * Holds all the requests which failed due to 401 response.
     */
    $rootScope.$on('event:loginRequired', function () {
        $rootScope.requests401 = [];

        if ($location.path().indexOf("/login") == -1) {
            $rootScope.navigateTo = $location.path();
        }

        if (!containsError("LOGIN_REQ")) {
            $rootScope.errors.push({code: "LOGIN_REQ", message: "Please enter a valid username / password"});
        }

        $location.path('/login');
    });

    /**
     * On 'event:loginConfirmed', resend all the 401 requests.
     */
    $rootScope.$on('event:loginConfirmed', function () {
        var i,
            requests = $rootScope.requests401,
            retry = function (req) {
                $http(req.config).then(function (response) {
                    req.deferred.resolve(response);
                });
            };

        for (i = 0; i < requests.length; i += 1) {
            retry(requests[i]);
        }

        $rootScope.requests401 = [];
        $rootScope.errors = [];
    });

    /**
     * On 'event:loginRequest' send credentials to the server.
     */
    $rootScope.$on('event:loginRequest', function (event, username, password) {
        // set the basic authentication header that will be parsed in the next request and used to authenticate
        $http.defaults.headers.common['Authorization'] = 'Basic ' + Base64Service.encode(username + ':' + password);

        AuthenticationService.login().then(
            function success() {
                $rootScope.user = localStorageService.get('localStorageUser');

                $rootScope.$broadcast('event:loginConfirmed');

                console.log("You have been successfully logged in.")
            });
    });

    /**
     * On 'logoutRequest' invoke logout on the server.
     */
    $rootScope.$on('event:logoutRequest', function () {
        $http.defaults.headers.common.Authorization = null;

        AuthenticationService.logout().then(
            function success() {
                $rootScope.user = localStorageService.get('localStorageUser');

                console.log("You have been successfully logged out.")
                $location.path('/main');
            });
    });


    function containsError(code) {
        for (var i in $rootScope.errors) {
            if ($rootScope.errors[i].code == code) {
                return true;
            }
        }
        return false;
    }
});