define(
        ['shared/namespace', 'angular', 'modules/itens/services/itensService',
            'modules/itens/controller/itensController', 'modules/itens/controller/itensEditController',
            'modules/itens/controller/itensNovoController', 'shared/utils'
        ],
        function (namespace, angular, itensService, itensController, itensEditController, itensNovoController, utils) {
            'use strict';

            angular
                    .module(
                            namespace + '.itens', ['ui.router',
                                'oc.lazyLoad'
                            ])
                    .controller('itensController', itensController)
                    .controller('itensEditController', itensEditController)
                    .controller('itensNovoController', itensNovoController)
                    .service('itensService', itensService)
                    .factory('utils', utils);
        });