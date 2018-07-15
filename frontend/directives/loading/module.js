define(['shared/namespace',
    'angular',
    'directives/loading/loading'],
    function(namespace, angular, loading) {
        'use strict';
        angular.module(
            namespace + '.spinnerbar',
            ['ui.router',
            'ngSanitize'])
            .directive('ngLoading', loading);
    });