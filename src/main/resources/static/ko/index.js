(function(){
    'use strict';

    function IndexVM(jQ) {
        var self = this;

        self.form_cadastro = new FormVM('CADASTRO');
        self.form_login = new FormVM('LOGIN');
        self.form_contato = new FormVM('CONTATO');
        self.uf_list = ko.observableArray([]);
        self.cidade_list = ko.observableArray([]);
        self.ufs = {};
        self.cidades = {};
        
        self.cancelar_form_cadastro = cancelar_form_cadastro;
        self.cancelar_form_login = cancelar_form_login;
        self.email_cadastro_changed = email_cadastro_changed;
        self.esqueceu_a_senha = esqueceu_a_senha;
        self.limpar_form_contato = limpar_form_contato;
        self.solicitarPortal = solicitarPortal;
        self.submit_cadastro = submit_cadastro;
        self.submit_contato = submit_contato;
        self.submit_login = submit_login;
        self.uf_cadastro_changed = uf_cadastro_changed;

        iniciar();

        function cancelar_form_cadastro() {
            self.form_cadastro.limpar_mensagens();
            self.form_cadastro.limpar();
            self.form_cadastro.getValidator().resetForm();
            self.form_cadastro.mostrar(false);
        }

        function cancelar_form_login() {
            self.form_login.limpar_mensagens();
            self.form_login.limpar();
            self.form_login.getValidator().resetForm();
            self.form_login.mostrar(false);
        }

        function email_cadastro_changed() {
            if (!self.form_cadastro.email()) {
                return;
            }

            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!re.test(String(self.email()).toLowerCase())) {
                return;
            }

            self.form_cadastro.limpar_mensagens();

            jQ.post('/verificar-email', {email: self.form_cadastro.email()}, okFn)
                .fail((jqXHR, responseText, error) => {
                    self.form_cadastro.erro('E-mail já cadastrado!');
                });
            
            function okFn() {
                self.form_cadastro.limpar_mensagens();
            }
        }

        function esqueceu_a_senha() {
            self.form_login.aguarde(true);
            self.form_login.limpar_mensagens();

            jQ.post('/esqueceu-a-senha', self.form_login.data(), okFn);
            
            function okFn() {
                self.form_login.aguarde(false);
                self.form_login.textoBotaoAdicional('');
                self.form_login.sucesso('Você receberá um link para resetar a senha, se o e-mail estiver cadastrado!');
            }
        }

        function limpar_form_contato() {
            self.form_contato.limpar_mensagens();
            self.form_contato.limpar();
            self.form_contato.getValidator().resetForm();
        }

        function solicitarPortal() {
            self.form_cadastro.aguarde(true);

            var portalData = {
                cod_cidade: self.form_cadastro.cod_cidade(),
                nome_cidade: self.form_cadastro.nome_cidade(),
                cod_uf: self.form_cadastro.cod_uf(),
                nome_uf: self.form_cadastro.nome_uf(),
                nome_pessoa: self.form_cadastro.nome(),
                email_pessoa: self.form_cadastro.email(),
            };

            jQ.post('/solicitar-portal', portalData, okFn);

            function okFn(response) {
                self.form_cadastro.limpar_mensagens();
                self.form_cadastro.textoBotaoAdicional('');
                if (response === 'OK') {
                    self.form_cadastro.sucesso('Solicitação recebida! Obrigado. Você receberá um e-mail de aviso em breve.');
                } else {
                    self.form_cadastro.sucesso('Obrigado. Em breve este portal será atendido');
                }
                self.form_cadastro.aguarde(false);
            }
        }

        function submit_cadastro(formElement) {
            if (!jQ(formElement).valid()) {
                return;
            }

            if (!self.form_cadastro.valido()) {
                self.form_cadastro.erro('Preencha corretamente todos os campos!');
                return;
            }

            self.form_cadastro.aguarde(true);
            self.form_cadastro.limpar_mensagens();
            self.form_cadastro.nome_cidade(self.cidades[self.form_cadastro.cod_cidade()]);
            
            jQ.post('/cadastrar', self.form_cadastro.data(), okFn)
                .fail((jqXHR, responseText, error) => {
                    self.form_cadastro.erro(jqXHR.responseJSON);
                    self.form_cadastro.aguarde(false);

                    if (jqXHR.responseJSON.includes('Região ainda não atendida')) {
                        self.form_cadastro.textoBotaoAdicional('Clique AQUI para solicitar atendimento. É free! &#x1F60A');
                    }
                });
            
            function okFn() {
                self.form_cadastro.sucesso('Cadastro realizado com sucesso!');
                self.form_cadastro.aguarde(false);
            }
        }

        function submit_contato(formElement) {
            if (!jQ(formElement).valid()) {
                return;
            }

            if (!self.form_contato.valido()) {
                self.form_contato.erro('Um ou mais campos são inválidos!');
                return;
            }

            self.form_contato.aguarde(true);
            self.form_contato.limpar_mensagens();
            
            jQ.post('/contato', self.form_contato.data(), okFn)
                .fail((jqXHR, responseText, error) => {
                    self.form_contato.erro('Erro ao enviar mensagem. Tente novamente mais tarde.');
                    self.form_contato.aguarde(false);
                });
            
            function okFn() {
                self.form_contato.aguarde(false);
                self.form_contato.sucesso('Sucesso! Mensagem enviada.');
            }
        }

        function submit_login(formElement) {
            if (!jQ(formElement).valid()) {
                return;
            }

            if (!self.form_login.valido()) {
                self.form_login.erro('Um ou mais campos são inválidos!');
                return;
            }

            self.form_login.aguarde(true);
            self.form_login.limpar_mensagens();
            
            jQ.post('/login', self.form_login.data(), okFn)
                .fail((jqXHR, responseText, error) => {
                    self.form_login.erro('Usuário ou senha inválidos!');
                    self.form_login.aguarde(false);
                    self.form_login.textoBotaoAdicional('Esqueceu a senha? Clique AQUI para resetá-la!');
                });
            
            function okFn() {
                self.form_login.aguarde(false);
                self.form_login.textoBotaoAdicional('');
                self.form_login.sucesso('Sucesso! Redirecionando..');
                if (typeof(Storage) !== "undefined") {
                    var k = 'vagas-por-email-home';
                    if (localStorage.getItem(k)) {
                        localStorage.removeItem(k);
                    }
                }

                setTimeout(function() {
                    window.location.href = '/home';
                }, 1000);
            }
        }

        function uf_cadastro_changed() {
            self.form_cadastro.cod_cidade('');
            self.cidade_list([]);

            if (!self.form_cadastro.cod_uf()) {
                return;
            }

            self.form_cadastro.nome_uf(self.ufs[self.form_cadastro.cod_uf()]);
            
            var urlGet = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados/'+self.form_cadastro.cod_uf()+'/municipios';
            jQ.getJSON(urlGet, okFn);
            
            function okFn(json) {
                self.cidades = {};

                self.cidade_list.push({valor: '', texto: ''});
                for (var i in json) {
                    self.cidades[json[i].id] = json[i].nome;

                    self.cidade_list.push({
                        valor: json[i].id,
                        texto: json[i].nome
                    });
                }
            }
        }

        function iniciar() {
            jQ.getJSON('/api/ufs', function(response) {
                self.ufs = {};
                self.ufs[''] = '';
                self.uf_list.push({valor: '', texto: ''});

                for (let i in response) {
                    self.ufs[response[i].id] = response[i].nome;
                    
                    self.uf_list.push({
                        valor: response[i].id,
                        texto: response[i].nome
                    });
                }
            });

            self.form_contato.setValidator(jQ('#form_contato').validate());
            self.form_login.setValidator(jQ('#form_login').validate());
            self.form_cadastro.setValidator(jQ('#form_cadastro').validate());

            // facebook login
            //FB.getLoginStatus(function(response) {
            //    console.log(response);
            //});
        }
    }

    ko.applyBindings(new IndexVM(jQuery));
})();
