function FormPortalVM() {
    var self = this;
    var thisValidator = undefined;

    // portal
    self.id = ko.observable(0);
    self.nome = ko.observable('');
    self.url = ko.observable('');
    self.cidades_id = ko.observable(0);

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
        self.url('');
        self.cidades_id(0);
        self.ufs_id(0);
        self.nome_cidade('');
        self.nome_uf('');
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
        if (!self.url()) return false;
        if (!self.cidades_id()) return false;
        if (!self.ufs_id()) return false;
        if (!self.nome_cidade()) return false;
        if (!self.nome_uf()) return false;
        return true;
    });

    self.getValidator = function() {
        return thisValidator;
    };

    self.setValidator = function(pValidator) {
        thisValidator = pValidator;
    };
}