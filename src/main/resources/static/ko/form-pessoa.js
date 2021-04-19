function FormPessoaVM() {
    var self = this;
    var thisValidator = undefined;

    // pessoa
    self.id = ko.observable(0);
    self.nome = ko.observable('');
    self.sobrenome = ko.observable('');
    self.email = ko.observable('');
    self.senha = ko.observable('');
    self.cidades_id = ko.observable(0);
    self.dia_vencimento = ko.observable(0);
    self.is_admin = ko.observable('');

    // cidade
    self.ufs_id = ko.observable(0);
    self.nome_cidade = ko.observable('');

    // uf
    self.nome_uf = ko.observable('');

    self.aguarde = ko.observable(false);
    self.mostrar = ko.observable(false);
    self.operacao = ko.observable('');
    self.erro = ko.observable('');
    self.sucesso = ko.observable('');

    self.limpar = function() {
        self.id(0);
        self.nome('');
        self.sobrenome('');
        self.email('');
        self.senha('');
        self.cidades_id(0);
        self.ufs_id(0);
        self.nome_cidade('');
        self.nome_uf('');
        self.dia_vencimento(0);
        self.is_admin('');
    };

    self.mostrar_modal = function() {
        self.limpar();
        self.limpar_mensagens();
        self.aguarde(false);
        self.mostrar(true);
    };

    self.limpar_mensagens = function() {
        self.erro('');
        self.sucesso('');
    };

    self.data = function() {
        return JSON.parse(ko.toJSON(self));
    };

    self.valido = ko.computed(function() {
        if (!self.nome()) return false;
        if (!self.sobrenome()) return false;
        if (!self.email()) return false;
        if (!self.cidades_id()) return false;
        if (!self.nome_cidade()) return false;
        if (!self.ufs_id()) return false;
        if (!self.nome_uf()) return false;
        if (!self.dia_vencimento()) return false;
        return true;
    });

    self.getValidator = function() {
        return thisValidator;
    };

    self.setValidator = function(pValidator) {
        thisValidator = pValidator;
    };
}