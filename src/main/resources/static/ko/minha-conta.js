(function() {
    if ($('#minha-conta').length === 1) {
        'use strict';

        function MinhaContaVM(jQ) {
            var self = this;

            self.nomePessoa = ko.observable('');

            iniciar();

            function iniciar() {
                jQ.getJSON('/app/user/get-logged-user', (response) => {
                    self.nomePessoa(response.first_name || '');
                });
            }
        }

        // recortar foto: https://github.com/fengyuanchen/jquery-cropper

        ko.applyBindings(new MinhaContaVM(jQuery));
    }
})();