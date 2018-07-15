/**
 * Controller para lista de itens cadatrados
 */
define(['moment'], function(moment) {
	'use strict';

	var itensController = function(itensService, $state, $window, $scope, toastr, $timeout, utils, $filter, CONSTANT, itens) {
		var vm = this;
		vm.itens = itens == undefined ? [] : itens;
		vm.modal = {};
		vm.escondeLabelBotao = false;
		vm.confirmarRemover = confirmarRemover;
		vm.remover = remover;
		vm.formataData = formataData;
        vm.getNomePorChaveUnidadeDeMedida = getNomePorChaveUnidadeDeMedida;
        vm.formataQuantidade = formataQuantidade;
        vm.validarData = validarData;
        vm.dataAtual = CONSTANT.dataAtual;
        vm.bradcrumb = [{nome: "Itens", link: "#!/itens/lista"}, {nome: "Lista", link: ""}];
		utils.scrollToTop();
        
        /**
        * Valida se a data inicial não é maior que a data final;
        * return false caso a data inicial seja maior que a data final
        */
        function validarData(inicial, final){
            return utils.validarData(inicial, final);
        }
        
        /**
        * Formata quantidade para exibição na lista
        */
        function formataQuantidade(unidadeDeMedida, quantidade){
            switch(unidadeDeMedida){
                case CONSTANT.CHAVE_LITRO:
                    return $filter('currency')(quantidade, "", 3);
                case CONSTANT.CHAVE_QUILOGRAMA:
                    return $filter('currency')(quantidade, "", 3);
                case CONSTANT.CHAVE_UNIDADE:
                    return quantidade;
                default:
                    return quantidade;
            }
        }
        
        /**
         * Retorna o nome da unidade de medida pela chave
         */
        function getNomePorChaveUnidadeDeMedida(chave){
            return utils.getNomePorChave(chave);
        }
		
        /**
         * Mostra modal de confirmação para remover item.
         */
		function confirmarRemover(item){
			vm.modal = item;
			$('#modalConfirmacao').modal('show');
		}
		
        /**
        * Remove o item cadastro e mostra uma mensagem de sucesso caso sucesso ou uma mensagem de erro caso ocorra algum erro.
        *
        */
		function remover(item){
            try{
			 itensService.remover(item);
             toastr.success("Item " + item.nome +" removido com sucesso!", "Sucesso!",{
                    closeButton: true
                });
             $timeout(function(){$state.reload();}, 200);       
            }catch(erro){
                toastr.error("Não foi possível remover o item " + item.nome  +"!", "Ops..",{
                    closeButton: true
                });
            }
		}

        /**
        * Controla o estilo de apresentção do botão 'Novo item'
        */
		function controlaEstiloBotao(){
			angular.element(window).bind('scroll', function(){
			      if($window.scrollY == 0){
			    	 vm.escondeLabelBotao=false;
			    	  $scope.$apply();
			      }else{
			    	  vm.escondeLabelBotao=true;
			    	  $scope.$apply();
			      }
			    });
			
		}
		
        /**
        * Formata a data para exibição
        */
		function formataData(data){
			return moment(data).format("DD/MM/YYYY");
		}
		
		controlaEstiloBotao();
	};
	itensController.$inject = [ 'itensService', '$state', '$window', '$scope', 'toastr', '$timeout', 'utils', '$filter', 'CONSTANT', 'itens'];

	return itensController;

});