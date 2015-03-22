app.controller('MainController', function ($rootScope, $scope, $location, $log) {

    $scope.logout = function () {
        $scope.$emit('event:logoutRequest');

        $location.path("/athletes");
    };

    $scope.login = function (credentials) {
        $scope.$emit('event:loginRequest', credentials.username, credentials.password);
        $('#login').modal('toggle');
        $location.path($rootScope.navigateTo);
        credentials.username = "";
        credentials.password = "";
    };

    // TODO: app comes in this function lots of times...
    $rootScope.hasRole = function (role) {

        //var auth = $rootScope.user.authorities;

        if ($rootScope.user == null || $rootScope.user.authorities == undefined)
            return false;

        for (var int = 0; int < $rootScope.user.authorities.length; int++) {

            //console.info("Log ", int, " = ",$rootScope.user.authorities[int]);

            if ($rootScope.user.authorities[int].authority == role) {
                //console.info("true");
                return true;
            }
            else {
                //console.info("false");
                return false;
            }
            ;
        }


        return false;
    };

});

app.controller('AthleteController', function ($scope, $log, $routeParams, $upload, AthleteService) {

    $scope.allAcc = function () {
        AthleteService.allAthletes().then(function (response) {
            $scope.athletes = response;
            $log.debug($scope.allA);
        })
    };

    $scope.newAthlete = {
        firstname: "",
        surname: "",
        email: "",
        password: "",
        weight: "",
        height: ""
    };

    $scope.createNewAthlete = function () {

        $log.debug("Athlete:" + $scope.newAthlete);


        AthleteService.createAthlete($scope.newAthlete).then(function (response) {
            if (response) {
                console.info("Customer has been created.");
            }
            else {
                console.error("Customer was unable to be created.");
            }
        });

        $('#addAthlete').modal('toggle');

        $scope.newAthlete = {
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            weight: "",
            height: ""
        }

    };

    $scope.athleteDetails = function () {
        var currentId = $routeParams.id;
        AthleteService.getAthleteDetails(currentId).then(function (response) {
            $scope.athlete = response;
        })
    };

    $scope.accelerationToggle = false;

    $scope.accelerationData = function () {
        AthleteService.getAccelerationData().then(function (response) {
            $scope.accelerations = response;
        })
    };

    //$scope.map = { center: { latitude: 39, longitude: 32 }, zoom: 7 };
    $scope.map = { center: {latitude: 39, longitude: 32}, zoom: 14};

    $scope.polylines = [
        {
            id: 1,
            path: [],
            stroke: {
                color: '#6060FB',
                weight: 3
            },
            editable: true,
            draggable: true,
            geodesic: true,
            visible: true
        }
    ];

    AthleteService.getGpsData().then(function (response) {

        //$log.debug(response);
        $scope.gpsData = response;
        $scope.map.center.latitude = $scope.gpsData[0].latitude;
        $scope.map.center.longitude = $scope.gpsData[0].longitude;

        for(var i = 0; i<$scope.gpsData.length; i++) {
            var g = { latitude : $scope.gpsData[i].latitude,
                longitude : $scope.gpsData[i].longitude };
            $scope.polylines[0].path.push(g) ;
        }
    });


    $scope.upload = function (files) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                $upload.upload({
                    url: '/api/user/upload-file',
                    fields: {
                        'username': $scope.athlete.username
                    },
                    file: file
                }).progress(function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' +
                    evt.config.file.name);
                }).success(function (data, status, headers, config) {
                    /*console.log('file ' + config.file.name + 'uploaded. Response: ' +
                    JSON.stringify(data));*/

                    $log.debug("GPS data debugging.......");
                    $log.debug(data);
                });
            }
        }
    };

});