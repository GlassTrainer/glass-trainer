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

app.controller('AthleteController', function ($scope, $log, AthleteService, $routeParams) {

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
    }

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
});

app.controller('CustomerController', function ($scope, CustomerService, $routeParams) {

    $scope.accelerationToggle = false;

    $scope.accelerationData = function () {
        CustomerService.getAccelerationData().then(function (response) {
            $scope.accelerations = response;
        })
    };

    $scope.delete = function (id) {
        CustomerService.deleteCustomer(id).then(function (response) {
            if (response) {
                angular.forEach($scope.customers, function (customer, index) {
                    if (id == customer.id) {
                        $scope.customers.splice(index, 1);

                        console.info("Customer " + id + " has been deleted.")
                    }
                });
            }
            else {
                console.error("Customer " + id + " was unable to be deleted.")
            }
        });
    };

    $scope.save = function (id) {
        angular.forEach($scope.customers, function (customer) {
            if (id == customer.id) {
                CustomerService.saveCustomer(customer).then(function (response) {
                    if (response) {
                        console.info("Customer " + id + " has been saved.");
                    }
                    else {
                        console.error("Customer " + id + " was unable to be saved.");
                    }
                });
            }
            ;
        });
    };


    $scope.create = function (currentCustomer) {

        $scope.currentCustomer = currentCustomer;

        // custom will be filled later...
        var emptyCreateNewCustomerForm = {
            firstName: "",
            lastName: "",
            email: "",
            address: "",
            phone: ""
        };

        CustomerService.createCustomer(currentCustomer).then(function (response) {
            if (response) {
                console.info("Customer has been created.");
            }
            else {
                console.error("Customer was unable to be created.");
            }
        });

        $scope.createNewCustomerForm.$setPristine();
        $scope.currentCustomer = emptyCreateNewCustomerForm;

    };

});