var dependencies = ['shared/namespace', 
					'angular', 
					'shared/vendor', 
					'init/routesConfig',
					'init/constantsConfig',
					'modules/navbar/module'];

function modules(namespace){
	return ['ui.router','ngCookies', 'oc.lazyLoad', 'ngAnimate', '720kb.datepicker', 'toastr', 'ngSanitize', 'angularUtils.directives.dirPagination','ui.utils.masks', namespace + '.spinnerbar', namespace + '.navbar']
};

function angularConfig(namespace, angular){
	  'use strict';
    var app = angular.module(namespace, modules(namespace));


    app.config(routesConfigDependencies);
    app.constant('CONSTANT', (constant)());

    /**
	 * Inicia o app.
	 */
    app.init = function () {
        angular.element(document).ready(function () {
            angular.bootstrap(document, [namespace]);
        });
    };

    return app;
}

define(dependencies, angularConfig);