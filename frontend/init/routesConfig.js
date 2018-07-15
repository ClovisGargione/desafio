var namespace = 'cadastro-de-itens';

function routesConfig($stateProvider, $urlRouterProvider){
	
	 $stateProvider
     .state('itens', {
         abstract: true,
         url: '/itens',
         templateUrl: 'shared/views/index.html',
         resolve: {
             loadModule: ['$ocLazyLoad', '$q', function ($ocLazyLoad, $q) {
                 var deferred = $q.defer();
                 require(["modulo-itens"], function () {
                     $ocLazyLoad.inject(namespace + '.itens');
                     deferred.resolve();
                 });
                 return deferred.promise;
             }]
         }
     })
     .state('itens.lista', {
         url: '/lista',
         templateUrl: 'modules/itens/views/lista.html',
         controller: 'itensController',
         controllerAs: 'vm',
         resolve: {
             itens: ['itensService', function(itensService){
                 return itensService.listaDeItens();
             }]
         }
     })
     .state('itens.edit',{
         url: '/editar/:id',
         templateUrl: 'modules/itens/views/item.html',
         controller: 'itensEditController',
         controllerAs: 'vm',
         resolve: {
             item: ['$stateParams', 'itensService', function($stateParams, itensService){
                 return itensService.getItem($stateParams.id);
             }]
         }

     })
     .state('itens.novo',{
            url: '/novo',
            templateUrl: 'modules/itens/views/item.html',
            controller: 'itensNovoController',
            controllerAs: 'vm'
    });
	 $urlRouterProvider.otherwise('/itens/lista');
}
var routesConfigDependencies = ['$stateProvider', '$urlRouterProvider', routesConfig];
