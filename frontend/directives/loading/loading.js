define(['jquery'], function(jquery) {

    var spinnerBar = function($rootScope, $http) {
        return {
            link: function(scope, element, attrs) {
                // esconde o spinner por padrão
                element.addClass('hide'); //

                // monstra o spinner quando inicia uma mudança de rota
                $rootScope.$on('$stateChangeStart', function() {
                    element.removeClass('hide');
                });

                // esconde o spinner quando a mudança de rota finaliza com sucesso
                $rootScope.$on('$stateChangeSuccess', function() {
                    element.addClass('hide');
                    $('body').removeClass('page-on-load');
                });

                // Esconde o spinner se não encontrar a rota
                $rootScope.$on('$stateNotFound', function() {
                    element.addClass('hide');
                });

                // Esconde o spinner se ocorrer algum erro
                $rootScope.$on('$stateChangeError', function() {
                    element.addClass('hide');
                });

                // verifica se existe requests pendentes
                scope.isLoading = function() {
                    return $http.pendingRequests.length > 0;
                };
                // Listener para mostrar o spinner
                scope.$watch(scope.isLoading, function(v) {
                    if (v) {
                        element.removeClass('hide');
                    } else {
                        element.addClass('hide');
                    }
                });

            }
        };
    };

    spinnerBar.$inject = ['$rootScope', '$http'];
    return spinnerBar;
});