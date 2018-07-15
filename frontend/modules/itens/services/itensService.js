define(['angular'], function(angular) {
    'use strict';

    var itemService = function($http, CONSTANT) {
            
        /**
        * Retorna a lista de itens cadastrados.
        */
    	 this.listaDeItens = function() {
             return angular.fromJson(localStorage.itens);
         };
         
        /**
        * Retorna um item pelo id
        */
         this.getItem = function(id) {
             var itens = angular.fromJson(localStorage.itens);
             for(var index=0; index < itens.length; index++){
                 if(itens[index].id == id){
                    return itens[index];
                  }
             }
             return null;
         };
         
        /**
        * Atualiza um item já cadastrado
        */
         this.atualizar = function(item){
             var itens = angular.fromJson(localStorage.itens);
             for(var index=0; index < itens.length; index++){
                 if(itens[index].id == item.id){
                    itens.splice(index, 1);
                    itens.push(item); 
                    return localStorage.itens = angular.toJson(itens); 
                  }
             }
         }
         
         /**
         * Remove um item cadastrado
         */
         this.remover = function(item){
        	var itens = angular.fromJson(localStorage.itens);
             for(var index=0; index < itens.length; index++){
                 if(itens[index].id == item.id){
                    itens.splice(index, 1);
                    return localStorage.itens = angular.toJson(itens);
                  }
             }
         }
         
         /**
         * Adiciona um novo item
         */
         this.adicionar = function(item){
             var itens = angular.fromJson(localStorage.itens);
             itens = itens == undefined ? [] : itens;
             item.id = itens.length + 1;
             itens.push(item);
             return localStorage.itens = angular.toJson(itens);
         }
    	
    };

    itemService.$inject = ['$http', 'CONSTANT'];

    return itemService;
});