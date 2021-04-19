function FormVM(pTipo) {
    var self = this;
    var thisValidator = undefined;

    self.tipo = ko.observable(pTipo);
    self.nome = ko.observable('');
    self.nome_place = ko.observable('');
    self.sobrenome = ko.observable('');
    self.sobrenome_place = ko.observable('');
    self.email = ko.observable('');
    self.email_place = ko.observable('');
    self.senha = ko.observable('');
    self.cod_uf = ko.observable(0);
    self.nome_uf = ko.observable('');
    self.cod_cidade = ko.observable('');
    self.nome_cidade = ko.observable('');
    self.assunto = ko.observable('');
    self.mensagem = ko.observable('');
    self.aguarde = ko.observable(false);
    self.mostrar = ko.observable(false);
    self.tipoPW = ko.observable('password');
    self.textoBotaoAdicional = ko.observable('');

    self.erro = ko.observable('');
    self.sucesso = ko.observable('');

    self.limpar = function() {
        self.nome('');
        self.sobrenome('');
        self.email('');
        self.senha('');
        self.cod_uf('');
        self.nome_uf('');
        self.cod_cidade('');
        self.nome_cidade('');
        self.assunto('');
        self.mensagem('');
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
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        
        if (self.tipo() === 'CADASTRO') {
            if (!self.nome()) return false;
            if (!self.sobrenome()) return false;
            if (!self.email()) return false;
            if (!self.senha()) return false;
            if (!self.cod_uf()) return false;
            if (!self.cod_cidade()) return false;
            if (!re.test(String(self.email()).toLowerCase())) return false;
            return true;
        } else if (self.tipo() === 'LOGIN') {
            if (!self.email()) return false;
            if (!self.senha()) return false;
            if (!re.test(String(self.email()).toLowerCase())) return false;
            return true;
        } else if (self.tipo() === 'CONTATO') {
            if (!self.nome()) return false;
            if (!self.email()) return false;
            if (!self.assunto()) return false;
            if (!self.mensagem()) return false;
            if (!re.test(String(self.email()).toLowerCase())) return false;
            return true;
        } else if (self.tipo() === 'CONTA') {
            if (!self.nome()) return false;
            if (!self.sobrenome()) return false;
            if (!self.email()) return false;
            if (!self.cod_uf()) return false;
            if (!self.cod_cidade()) return false;
            if (!re.test(String(self.email()).toLowerCase())) return false;
            return true;
        }
        return false;
    });

    self.togglePassword = function() {
        if (self.tipoPW() === 'password') {
            self.tipoPW('text');
        } else {
            self.tipoPW('password');
        }
    };

    self.titlePW = ko.computed(function() {
        if (self.tipoPW() === 'password') {
            return 'Mostrar senha';
        } else {
            return 'Esconder senha';
        }
    });

    self.getValidator = function() {
        return thisValidator;
    };

    self.setValidator = function(pValidator) {
        thisValidator = pValidator;
    };
}