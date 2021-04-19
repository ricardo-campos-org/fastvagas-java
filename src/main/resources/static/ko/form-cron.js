function FormCronVM() {
    var self = this;
    var thisValidator = undefined;

    // cron
    self.id = ko.observable(0);
    self.nome = ko.observable('');
    self.minutos = ko.observable('');
    self.horas = ko.observable('');
    self.dias = ko.observable('');
    self.meses = ko.observable('');
    self.dia_da_semana = ko.observable('');
    self.caminho_script = ko.observable('');

    self.aguarde = ko.observable(false);
    self.mostrar = ko.observable(false);
    self.operacao = ko.observable('');
    self.erro = ko.observable('');
    self.sucesso = ko.observable('');

    self.limpar = function() {
        self.id(0);
        self.nome('');
        self.minutos('');
        self.horas('');
        self.dias('');
        self.meses('');
        self.dia_da_semana('');
        self.caminho_script('');
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
        if (!self.minutos()) return false;
        if (!self.horas()) return false;
        if (!self.dias()) return false;
        if (!self.meses()) return false;
        if (!self.dia_da_semana()) return false;
        if (!self.caminho_script()) return false;
        return true;
    });

    self.getValidator = function() {
        return thisValidator;
    };

    self.setValidator = function(pValidator) {
        thisValidator = pValidator;
    };
}