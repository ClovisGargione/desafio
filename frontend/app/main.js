var _require = require;

requirejs.config({
    baseUrl: './',
    waitSeconds: 0,
    urlArgs: "bust=" + (new Date()).getTime(),
    paths: {
        'jquery': 'assets/libs/jquery/dist/jquery.min',
        'popper': 'assets/libs/popper.js/dist/umd/popper.min',
        'bootstrap': 'assets/libs/bootstrap/dist/js/bootstrap.bundle.min',
        'moment': 'assets/libs/moment/min/moment-with-locales.min',
        'angular': 'assets/libs/angular/angular',
        'angular-ui-router': 'assets/libs/angular-ui-router/release/angular-ui-router.min',
        'ocLazyLoad': 'assets/libs/oclazyload/dist/ocLazyLoad.min',
        'angular-animate':'assets/libs/angular-animate/angular-animate.min',
        'angular-aria': 'assets/libs/angular-aria/angular-aria.min',
        'angular-messages': 'assets/libs/angular-messages/angular-messages.min',
        'angular-cookies': 'assets/libs/angular-cookies/angular-cookies.min',
        'datepicker': 'assets/libs/angularjs-datepicker/dist/angular-datepicker.min',
        'toastr': 'assets/libs/angular-toastr/dist/angular-toastr.tpls.min',
        'angular-sanitize': 'assets/libs/angular-sanitize/angular-sanitize.min',
        'angular-pagination': 'assets/libs/angularUtils-pagination/dirPagination',
        'string-mask': 'assets/libs/string-mask/src/string-mask',
        'br-validations': 'assets/libs/br-validations/releases/br-validations',
        'angular-input-masks': 'assets/libs/angular-input-masks/angular-input-masks.br',
        'angular-locale': 'assets/libs/angular-locale/angular-locale_pt-br',
        'loading': 'directives/loading/module',
        'modulo-itens': 'modules/itens/module'
    },
    shim: {
        jquery: {
            exports: 'jquery',
        },
        'popper': {
            deps: ['jquery']
        },
        'bootstrap': {
            deps: ['jquery']
        },
        'moment':{
        	deps: ['jquery']
        },
        angular: {
            exports: 'angular'
        },
        'angular-ui-router': {
            deps: ['angular']
        },
        'ocLazyLoad': {
            deps: ['angular']
        },
        'angular-animate':{
            deps: ['angular']  
        },
        'angular-aria': {
        	deps: ['angular']  
        },
        'angular-messages': {
        	deps: ['angular']  
        },
        'angular-cookies' : {
        	deps: ['angular']
        },
        'datepicker':{
        	deps: ['angular']
        },
        'toastr':{
           deps: ['angular', 'angular-animate'] 
        },
        'angular-sanitize':{
            deps: ['angular']
        },
        'angular-pagination': {
            deps: ['angular']
        },
        'angular-input-masks': {
            deps: ['angular', 'string-mask', 'moment', 'br-validations'],
            init: function () {
                require = _require;
            }
        },
        'angular-locale':{
           deps: ['angular'] 
        },
        'modulo-itens': {
            exports: 'modulo-itens'
        }
    }
});

require(['app/app'], function (app) {
    app.init();
});