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

                self.aguarde(true);

                const userAccountJson = {
                    firstName: self.nameChange() || self.userAccount.firstName(),
                    lastName: self.lastNameChange() || self.userAccount.lastName(),
                    email: self.emailChange() || self.userAccount.email(),
                    password: self.passwdChange() || ''
                };

                jQ.ajax({
                    type: 'POST',
                    url: '/app/minha-conta/update-user-data',
                    data: JSON.stringify(userAccountJson),
                    contentType: 'application/json',
                    dataType: 'json',
                    sucess: function() {
                        console.log('it works!');
                        //self.nomePessoa(response.firstName || '');
                        //ko.mapping.fromJS(response, self.userAccount);
                        self.aguarde(false);
                    },
                    error: function() {
                        console.log('it not works!');
                        self.aguarde(false);
                    }
                });
            };

            self.cancelarMeusDados = function() {
                self.erro('');
                self.nameChange(undefined);
                self.lastNameChange(undefined);
                self.emailChange(undefined);
                self.passwdChange(undefined);
                self.passwdChangeTwo(undefined);
            };

            self.submitConta = function() {
                //
            };
        }

        // recortar foto: https://github.com/fengyuanchen/jquery-cropper

        ko.applyBindings(new MinhaContaVM(jQuery));
    }
})();