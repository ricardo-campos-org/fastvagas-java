(function() {
    if ($('#minha-conta').length === 1) {
        'use strict';

        function MinhaContaVM(jQ) {
            var self = this;

            self.nomePessoa = ko.observable('');
            self.nameChange = ko.observable();
            self.lastNameChange = ko.observable();
            self.emailChange = ko.observable();
            self.passwdChange = ko.observable();
            self.passwdChangeTwo = ko.observable();
            self.userAccount = ko.mapping.fromJS(UserAccount.getData());
            self.aguarde = ko.observable(false);
            self.erro = ko.observable('');
            self.sucesso = ko.observable('');

            iniciar();

            function iniciar() {
                jQ.getJSON('/app/base/current-user', (response) => {
                    self.nomePessoa(response.firstName || '');
                    ko.mapping.fromJS(response, self.userAccount);
                });
            }

            self.submitMeusDados = function() {
                self.erro('');

                console.log('self.nameChange():', self.nameChange());

                let somethingChanged = self.nameChange()
                    || self.lastNameChange()
                    || self.emailChange()
                    || self.passwdChange()
                    || self.passwdChangeTwo();

                if (!somethingChanged) {
                    return;
                }

                if (self.passwdChange() || self.passwdChangeTwo()) {
                    if (self.passwdChange() !== self.passwdChangeTwo()) {
                        self.erro('As senhas devem ser iguais!');
                        return;
                    }
                }
            };

            self.submitConta = function() {
                //
            };
        }

        // recortar foto: https://github.com/fengyuanchen/jquery-cropper

        ko.applyBindings(new MinhaContaVM(jQuery));
    }
})();