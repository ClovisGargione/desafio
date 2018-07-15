define(['angular'], function (angular) {
    'use strict';

    function navbarController($state, $timeout) {
        var vm = this;
        vm.menuActive = 'menu-1';
        vm.isActive = isActive;
        vm.setActive = setActive;
        
        /**
        * Define um menu como ativo
        */
        function setActive(id){
        	vm.menuActive = id;
        }
        
        /**
        * Verifica se o menu está ativo
        */
        function isActive(id){
        	return vm.menuActive == id;
        }
        
        /**
        * Mostra ou esconde o menu lateral
        */
        $timeout(function(){
            $('[data-toggle=offcanvas]').click(function() {
                $('.row-offcanvas').toggleClass('active');
                $('span.collapse').toggleClass('in');
            });

            $('[data-toggle=offcanvas-in]').click(function() {
                $('.row-offcanvas').addClass('active');
                $('span.collapse').addClass('in');
            });
        }, 300);
    }
    navbarController.$inject = ['$state','$timeout'];
    return navbarController;

});