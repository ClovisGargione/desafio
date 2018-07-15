define(['moment'], function(moment) {
	'use strict';

	var itensEditController = function(itensService, $state, $scope, CONSTANT, utils, item) {
        var iconeSalvar = CONSTANT.iconeSalvar;
    	var iconeCarregando = CONSTANT.iconeCarregando;
        var vm = this;
        vm.item = item;
        vm.voltar = voltar;
        vm.salvar = salvar;
        vm.defineDecimal = utils.defineDecimal;
        vm.validarData = validarData;
        vm.dataAtual = CONSTANT.dataAtual;
        vm.icone = iconeSalvar;
        vm.mensagemDeSucesso = "Item <b>%s</b> atualizado com sucesso!";
        vm.mensagemDeErro = "";
        vm.sucesso = false;
        vm.bradcrumb = [{nome: "Itens", link: "#!/itens/lista"}, {nome: "Editar", link: "#!/itens/editar/"+vm.item.id}, {nome: vm.item.nome, link: ""}];
        vm.validarDataDeFabricacao = validarDataDeFabricacao;
        vm.validarDataDeValidade = validarDataDeValidade;
        utils.scrollToTop();
        
        /**
        * Valida se a data inicial não é maior que a data final;
        * return false caso a data inicial seja maior que a data final
        */
        function validarData(inicial, final){
            return utils.validarData(inicial, final);
        }
        
        /**
        * Retorna para a lista de itens.
        */
        function voltar(){
            utils.scrollToTop();
            $state.go('itens.lista');
        }
        
        /**
        * Atualiza um novo item e monstra uma mensagem de feedback.
        */
        function salvar(){
            vm.icone = iconeCarregando;
            try{
                itensService.atualizar(vm.item); 
                vm.sucesso = true;
                vm.mensagemDeSucesso = vm.mensagemDeSucesso.replace("%s", vm.item.nome);
            }catch(error){
                vm.mensagemDeErro = "Ops. Não foi possível atualizar o item <b>%s</b>";
                vm.mensagemDeErro = vm.mensagemDeErro.replace("%s", vm.item.nome);
            }
            vm.icone = iconeSalvar;
            utils.scrollToTop();
        }
        
         /**
        * Faz as validações da data de fabricação
        */
        function validarDataDeFabricacao(itemForm){
            return itemForm.inputItemDataFabricacao.$dirty && itemForm.inputItemDataFabricacao.$invalid || itemForm.inputItemDataFabricacao.$dirty && !vm.validarData(vm.item.dataDeFabricacao, vm.item.dataDeValidade) || itemForm.inputItemDataFabricacao.$dirty && !vm.validarData(vm.item.dataDeFabricacao, vm.dataAtual) ? true : false;
        }
        
         /**
        * Faz as validações da data de validade
        */
        function validarDataDeValidade(itemForm){
            return itemForm.inputItemDataValidade.$dirty && itemForm.inputItemDataValidade.$invalid || itemForm.inputItemDataValidade.$dirty && !vm.validarData(vm.dataAtual, vm.item.dataDeValidade) ? true : false;
        }
				
	};
	itensEditController.$inject = [ 'itensService', '$state', '$scope', 'CONSTANT', 'utils', 'item'];

	return itensEditController;

});