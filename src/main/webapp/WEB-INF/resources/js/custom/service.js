app.service('AthleteService', function ($http, $q) {
    this.createAthlete = function (athlete) {

        var d = $q.defer();
        $http.post('"api/user/create', athlete).success(function (response) {
            d.resolve(athlete);
        });

        return d.promise;
    };

    this.allAthletes = function () {
        var d = $q.defer();
        $http.get('api/user/all').success(function (response) {
            d.resolve(response);
        });

        return d.promise;
    };

    this.getAthleteDetails = function (id) {
        var d = $q.defer();
        $http.get('api/user/' + id).success(function (response) {
            d.resolve(response);
        });

        return d.promise;
    };

    this.getAccelerationData = function () {
        var d = $q.defer();
        $http.get('api/realtime/current').success(function (response) {
            d.resolve(response);
        });

        return d.promise;
    };

    this.getGpsData = function () {
        var d = $q.defer();
        $http.get('api/realtime/gps').success(function (response) {
            d.resolve(response);
        });

        return d.promise;
    };

    this.getCurrentData = function() {
        var d = $q.defer();
        $http.get('/api/realtime/current').success(function (response) {
            d.resolve(response);
        });

        return d.promise;
    }

});

app.service('AuthenticationService', function ($http, $q, localStorageService) {
    this.login = function () {
        var d = $q.defer();

        $http.get('api/user/authenticated')
            .success(function (user) {
                localStorageService.set('localStorageUser', user);

                d.resolve();
            })
            .error(function () {
                // TODO: handle error
                d.reject();
            });

        return d.promise;
    };

    this.logout = function () {
        var d = $q.defer();

        $http.get('j_spring_security_logout')
            .success(function () {
                localStorageService.remove('localStorageUser');

                d.resolve();
            })
            .error(function () {
                // TODO: handle error
                d.reject();
            });

        return d.promise;
    };
});

app.service('Base64Service', function () {
    var keyStr = "ABCDEFGHIJKLMNOP" +
        "QRSTUVWXYZabcdef" +
        "ghijklmnopqrstuv" +
        "wxyz0123456789+/" +
        "=";
    this.encode = function (input) {
        var output = "",
            chr1, chr2, chr3 = "",
            enc1, enc2, enc3, enc4 = "",
            i = 0;

        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }

            output = output +
            keyStr.charAt(enc1) +
            keyStr.charAt(enc2) +
            keyStr.charAt(enc3) +
            keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        }

        return output;
    };

    this.decode = function (input) {
        var output = "",
            chr1, chr2, chr3 = "",
            enc1, enc2, enc3, enc4 = "",
            i = 0;

        // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

        while (i < input.length) {
            enc1 = keyStr.indexOf(input.charAt(i++));
            enc2 = keyStr.indexOf(input.charAt(i++));
            enc3 = keyStr.indexOf(input.charAt(i++));
            enc4 = keyStr.indexOf(input.charAt(i++));

            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;

            output = output + String.fromCharCode(chr1);

            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }

            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        }
    };
});