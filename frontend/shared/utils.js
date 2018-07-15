/**
* Centralização de funções úteis utilizadas na aplicação
*/
define(['angular', 'moment'], function(angular, moment) {
    'use strict';
    
    function utils(CONSTANT){
        var util = {};
        util.getNomePorChave = getNomePorChave;
        util.criarListaDeUnidadeDeMedida = criarListaDeUnidadeDeMedida;
        util.defineDecimal = defineDecimal;
        util.validarData = validarData;
        util.scrollToTop = scrollToTop;
        
        /**
        * Hashmap para unidade de medida
        */
        var UNIDADE_DE_MEDIDA = {};
        UNIDADE_DE_MEDIDA[CONSTANT.CHAVE_LITRO] = CONSTANT.LITRO;
        UNIDADE_DE_MEDIDA[CONSTANT.CHAVE_QUILOGRAMA] = CONSTANT.QUILOGRAMA;
        UNIDADE_DE_MEDIDA[CONSTANT.CHAVE_UNIDADE] = CONSTANT.UNIDADE;
        
        /**
        * Retorna o o nome da unidade de medida pela chave
        */
        function getNomePorChave(chave){
            return UNIDADE_DE_MEDIDA[chave];
        }
        
        /**
        * Cria lista para hashmap de unidade de medida
        */
        function criarListaDeUnidadeDeMedida(){
            var lista = [];
            for(var key in UNIDADE_DE_MEDIDA){
               lista.push({id: key, tipo: UNIDADE_DE_MEDIDA[key]}); 
            }
            return lista;
        }
        
        /**
        * Define a quantidade de casas decimais
        */
        function defineDecimal(valor){
            if(valor == CONSTANT.CHAVE_UNIDADE){
               return 0;
            }else{
                return 3;
            }
        }
        
        /**
        * Valida se a data inicial é menor que a data final
        * Retorna false caso a data inicial seja maior que a final
        */
        function validarData(inicial, final){
            if(final == undefined || final == null){
               true;
            }
            return moment(inicial, "DD/MM/YYYY").valueOf() > moment(final, "DD/MM/YYYY").valueOf() ? false : true;
        }
        
        /**
        * Vai para o inicío da página
        */
        function scrollToTop(){
			$( 'html, body' ).animate( { scrollTop: 0 }, 500);
		}
        
        return util;
    }
    
     utils.$inject = ['CONSTANT'];
    return utils;
});