(function() {
    if ($('#adm-cron').length === 1) {
        'use strict';

        function AdmCron(jQ) {
            var self = this,
                tblCron;

            self.selectedIndex = ko.observable(-1);
            self.form = new FormCronVM();
            self.ativarDesativarTxt = ko.observable('Desativar');
            self.aguarde = ko.observable('');

            self.alterar = alterar;
            self.cancelar_form = cancelar_form;
            self.toggleAtivar = toggleAtivar;
            self.incluir = incluir;
            self.submitForm = submitForm;

            start();

            function start() {
                tblCron = jQ('#tblCron').DataTable({
                    ajax: {
                        url: '/adm-cron/get-cron',
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
                        { data: 'minutos', width: '50px' },
                        { data: 'horas', width: '50px' },
                        { data: 'dias', width: '50px' },
                        { data: 'meses', width: '50px' },
                        { data: 'dia_da_semana', width: '100px' },
                        { data: 'caminho_script', width: '400px' },
                        { data: 'data_desativacao', width: '120px' }
                    ]
                });
                tblCron.on("select", function(e, dt, type, indexed) {
                    self.selectedIndex(indexed[0]);
                    self.ativarDesativarTxt('Desativar');
                    var row = tblCron.rows({selected: true}).data().toArray()[0];
                    if (row.data_desativacao) {
                        self.ativarDesativarTxt('Ativar');
                    }
                });
                tblCron.on("deselect", function(e, dt, type, indexed) {
                    self.selectedIndex(-1);
                    self.ativarDesativarTxt('Desativar');
                });

                self.form.setValidator(jQ('#form_cron').validate());
            }

            function alterar() {
                if (self.selectedIndex() < 0) {
                    bootbox.alert('Selecione uma tarefa cron para alterar!');
                    return;
                }

                cancelar_form();

                var row = tblCron.rows({selected: true}).data().toArray()[0];

                self.form.operacao('Alterar tarefa cron');
                self.form.id(row.id);
                self.form.nome(row.nome);
                self.form.minutos(row.minutos);
                self.form.horas(row.horas);
                self.form.dias(row.dias);
                self.form.meses(row.meses);
                self.form.dia_da_semana(row.dia_da_semana);
                self.form.caminho_script(row.caminho_script);
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
                    bootbox.alert('Selecione uma tarefa cron para desativar!');
                    return;
                }

                var row = tblCron.rows({selected: true}).data().toArray()[0],
                    confirmacao = 'Confirma desativação da tarefa cron?';
                if (row.data_desativacao) {
                    confirmacao = 'Confirma ativação da tarefa cron?';
                }

                confirmar(confirmacao, function(respSim) {
                    if (respSim) {
                        self.aguarde('Calma ai...');

                        jQ.post('/adm-cron/toggle-ativar/' + row.id, okFn)
                            .fail((jqXHR, responseText, error) => {
                                self.aguarde('');
                            });

                        function okFn() {
                            tblCron.ajax.reload();
                            self.ativarDesativarTxt('Desativar');
                            self.aguarde('');
                        }
                    }
                });
            }

            function incluir() {
                self.form.operacao('Incluir tarefa cron');
                self.form.mostrar_modal();
            }

            function submitForm(formElement) {
                if (!jQ(formElement).valid()) {
                    return;
                }

                if (!self.form.valido()) {
                    self.form.erro('Um ou mais campos são inválidos!');
                    return;
                }

                self.form.aguarde(true);
                self.form.limpar_mensagens();

                jQ.post('/adm-cron/salvar-cron', self.form.data(), okFn)
                    .fail((jqXHR, responseText, error) => {
                        self.form.erro('Falhou: ' + jqXHR.responseJSON);
                        self.form.aguarde(false);
                    });
                
                function okFn() {
                    self.form.aguarde(false);
                    self.form.sucesso('Tarefa cron salva com sucesso!');
                    setTimeout(function() {
                        self.form.mostrar(false);
                        tblCron.ajax.reload();
                    }, 500);
                }
            }
        }

        ko.applyBindings(new AdmCron(jQuery), $('#adm-cron')[0]);
    }
})();