/**
 * Created by Serhat CAN on 04.01.2015.
 */

app.filter('FirstTwoLetters', function () {

    return function (input) {

        if (input != null && input != "") {
            var a = input.split(" ", 2);

            if (a.length == 1) {
                return (a[0].substring(0, 1)).toUpperCase();
            }

            return (a[0].substring(0, 1) + a[1].substring(0, 1)).toUpperCase();
        } else {
            return "";
        }

    }

});