(function() {
    if ($('#adm-portais').length === 1) {
        'use strict';

        function AdmPortais(jQ) {
            var self = this,
                tblPortais;

            self.selectedIndex = ko.observable(-1);
            self.form = new FormPortalVM();
            self.uf_list = ko.observableArray([]);
            self.cidade_list = ko.observableArray([]);
            self.ufs = {};
            self.cidades = {};
            self.ativarDesativarTxt = ko.observable('Desativar');
            self.aguarde = ko.observable('');

            self.alterar = alterar;
            self.cancelar_form = cancelar_form;
            self.toggleAtivar = toggleAtivar;
            self.incluir = incluir;
            self.uf_changed = uf_changed;
            self.submitForm = submitForm;

            start();

            function start() {
                tblPortais = jQ('#tblPortais').DataTable({
                    ajax: {
                        url: '/adm-portais/get-portais',
                        dataType: 'json',
                        cache: false,
                        contentType: 'application/json; charset=utf8',
                        dataSrc: function(json){
                            return JSON.parse(JSON.stringify(json));
                        }
                    },
                    select: {
                        style: 'single',
                        info: false
                    },
                    sScrollX: true,
                    bScrollCollapse: true,
                    language: datatable_ptBRJson,
                    columns: [
                        { data: 'id', width: '10px' },
                        { data: 'nome', width: '100px' },
                        { data: 'cidades_id', width: '80px' },
                        { data: 'nome_cidade', width: '100px' },
                        { data: 'ufs_id', width: '60px' },
                        { data: 'nome_uf', width: '80px' },
                        { data: 'url', width: '400px' },
                        { data: 'data_desativacao', width: '120px' }
                    ]
                });
                tblPortais.on("select", function(e, dt, type, indexed) {
                    self.selectedIndex(indexed[0]);
                    self.ativarDesativarTxt('Desativar');
                    var row = tblPortais.rows({selected: true}).data().toArray()[0];
                    if (row.data_desativacao) {
                        self.ativarDesativarTxt('Ativar');
                    }
                });
                tblPortais.on("deselect", function(e, dt, type, indexed) {
                    self.selectedIndex(-1);
                    self.ativarDesativarTxt('Desativar');
                });

                self.form.setValidator(jQ('#form_portais').validate());

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
            }

            function alterar() {
                if (self.selectedIndex() < 0) {
                    bootbox.alert('Selecione um portal para alterar!');
                    return;
                }

                cancelar_form();

                var row = tblPortais.rows({selected: true}).data().toArray()[0];

                self.cidade_list([]);
                self.cidade_list.push({
                    valor: row.cidades_id,
                    texto: row.nome_cidade
                });

                self.form.operacao('Alterar portal');
                self.form.id(row.id);
                self.form.nome(row.nome);
                self.form.url(row.url);
                self.form.cidades_id(row.cidades_id);
                self.form.nome_cidade(row.nome_cidade);
                self.form.ufs_id(row.ufs_id);
                self.form.nome_uf(row.nome_uf);
                self.form.mostrar(true);
            }

            function cancelar_form() {
                self.form.limpar_mensagens();
                self.form.limpar();
                self.form.getValidator().resetForm();
                self.form.mostrar(false);
            }

            function toggleAtivar() {
                if (self.selectedIndex() < 0) {
                    bootbox.alert('Selecione um portal para desativar!');
                    return;
                }

                var row = tblPortais.rows({selected: true}).data().toArray()[0],
                    confirmacao = 'Confirma desativação do portal?';
                if (row.data_desativacao) {
                    confirmacao = 'Confirma ativação do portal?';
                }

                confirmar(confirmacao, function(respSim) {
                    if (respSim) {
                        self.aguarde('Calma ai...');

                        jQ.post('/adm-portais/toggle-ativar/' + row.id, okFn)
                            .fail((jqXHR, responseText, error) => {
                                self.aguarde('');
                            });

                        function okFn() {
                            tblPortais.ajax.reload();
                            self.ativarDesativarTxt('Desativar');
                            self.aguarde('');
                        }
                    }
                });
            }

            function incluir() {
                self.form.operacao('Incluir portal');
                self.form.mostrar(true);
            }

            function uf_changed() {
                self.form.cidades_id('');
                self.cidade_list([]);
    
                if (!self.form.ufs_id()) {
                    return;
                }
    
                self.form.nome_uf(self.ufs[self.form.ufs_id()]);
                
                var urlGet = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados/'+self.form.ufs_id()+'/municipios';
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

            function submitForm(formElement) {
                if (!jQ(formElement).valid()) {
                    return;
                }

                if (!self.form.nome_cidade()) {
                    self.form.nome_cidade(self.cidades[self.form.cidades_id()]);
                }

                if (!self.form.valido()) {
                    self.form.erro('Um ou mais campos são inválidos!');
                    return;
                }

                self.form.aguarde(true);
                self.form.limpar_mensagens();

                jQ.post('/adm-portais/salvar-portal', self.form.data(), okFn)
                    .fail((jqXHR, responseText, error) => {
                        self.form.erro('Falhou: ' + jqXHR.responseJSON);
                        self.form.aguarde(false);
                    });
                
                function okFn() {
                    self.form.aguarde(false);
                    self.form.sucesso('Portal salvo com sucesso!');
                    setTimeout(function() {
                        self.form.mostrar(false);
                        tblPortais.ajax.reload();
                    }, 500);
                }
            }
        }

        ko.applyBindings(new AdmPortais(jQuery), $('#adm-portais')[0]);
    }
})();