app
	.config([
		'$routeProvider',
		'$httpProvider',
		'localStorageServiceProvider',
		function($routeProvider, $httpProvider,
				 localStorageServiceProvider) {

			// ======= local storage configuration ========

			localStorageServiceProvider.prefix = 'example';

			// ======= router configuration =============

			$routeProvider
				.when(
				'/main',
				{
					templateUrl : 'resources/html/partials/view/main.html'
				})
				.when(
				'/athletes',
				{
					controller : 'AthleteController',
					templateUrl : 'resources/html/partials/view/athletes.html'

				})
				.when(
				'/acceleration',
				{
					controller : 'CustomerController',
					templateUrl : 'resources/html/partials/view/acceleration.html'

				})
				.when(
				'/athlete/create',
				{
					controller : 'AthleteController',
					templateUrl : 'resources/html/partials/view/athlete_create.html'
				})
				.when(
				'/customer/details/:id',
				{
					controller : 'CustomerController',
					templateUrl : 'resources/html/partials/view/customer_details.html'
				})
				.when(
				'/login',
				{
					templateUrl : 'resources/html/partials/view/login.html'
				}).otherwise({
					redirectTo : "/main"
				});

			// ======== http configuration ===============

			// configure $http to view a login whenever a 401
			// unauthorized response arrives
			$httpProvider.responseInterceptors
				.push(function($rootScope, $q, $location) {
					return function(promise) {
						return promise
							.then(
							// success -> don't
							// intercept
							function(response) {
								return response;
							},
							// error -> if 401 save the
							// request and broadcast an
							// event
							function(response) {
								if (response.status === 401) {
									var deferred = $q
										.defer(), req = {
										config : response.config,
										deferred : deferred
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
										config:
											response.config,
										deferred:
											deferred };

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
		} ]);