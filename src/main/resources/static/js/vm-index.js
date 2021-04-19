function main() {
    'use strict';

    // OK
    function setFieldError(id, mensagem) {
        document.getElementById(`div-${id}`).className += ' has-error';

        const span = document.getElementById(`span-${id}`);
        span.innerHTML = mensagem;
        span.style.display = 'block';
    }

    // OK
    function cityValidation() {
        return new Promise((resolve, reject) => {
            const select = document.getElementById('conta_cidade');
            const codigo = select.options[select.selectedIndex].value;
            const selectSigla = document.getElementById('conta_estado');
            const sigla = selectSigla.options[selectSigla.selectedIndex].value;

            if (codigo === undefined || codigo === '') {
                setFieldError('conta_cidade', 'Cidade é obrigatória');
                reject(new Error(''));
                return;
            }

            App.getJson(`/guest/validate-state-city/${sigla}/${codigo}`)
                .then((response) => {
                    if (response === false) {
                        setFieldError(
                            'conta_cidade',
                            'Cidade inválida ou não atendida!'
                        );

                        reject(new Error('Cidade inválida ou não atendida!'));
                        return;
                    }

                    resolve();
                }).catch((error) => {
                    reject(new Error(error));
                });
        });
    }

    // OK
    function emailValidation() {
        return new Promise((resolve, reject) => {
            const email = document.getElementById('conta_email').value;

            if (email === undefined || email === '') {
                setFieldError('conta_email', 'E-mail é obrigatório');
                reject(new Error('E-mail é obrigatório'));
                return;
            }

            App.getJson(`/guest/email-available/${email}`).then((response) => {
                if (response === false) {
                    setFieldError('conta_email', 'E-mail já cadastrado');
                    reject(new Error('E-mail já cadastrado'));
                    return;
                }

                resolve();
            }).catch((error) => {
                reject(new Error(error));
            });
        });
    }

    // OK
    function stateValidation() {
        return new Promise((resolve, reject) => {
            const select = document.getElementById('conta_estado');
            const sigla = select.options[select.selectedIndex].value;

            if (sigla === undefined || sigla === '') {
                setFieldError('conta_estado', 'Estado é obrigatório');
                reject(new Error('Estado é obrigatório'));
                return;
            }

            App.getJson(`/guest/find-all-cities-by-state/${sigla}`)
                .then((responseArray) => {
                    if (responseArray.length === 0) {
                        setFieldError('conta_estado', 'Estado não atendido!');
                        reject(new Error('Estado não atendido'));
                        return;
                    }

                    resolve();
                }).catch((error) => {
                    reject(new Error(error));
                });
        });
    }

    // OK
    function accountFormResult(className, message) {
        const result = document.getElementById('conta_result');

        result.className = `alert ${className}`;
        result.innerHTML = message;
        result.style.display = 'block';
    }

    // OK
    function accountFormReset(isHideModal) {
        const spans = document.querySelectorAll('#conta_form .help-block');
        const divs = document.querySelectorAll('#conta_form .form-group');

        spans.forEach((node) => {
            node.style.display = 'none';
        });

        divs.forEach((node) => {
            node.className = 'form-group';
        });

        document.getElementById('conta_wait').style.display = 'none';
        document.getElementById('conta_buttons').style.display = 'block';
        document.getElementById('conta_result').style.display = 'none';

        if (isHideModal) {
            $('#conta_modal').modal('hide');
        }
    }

    // OK
    function accountFormValidation() {
        return new Promise((resolve, reject) => {
            accountFormReset(false);

            let formInvalid = false;
            const form = document.getElementById('conta_form');

            // Nome
            if (form.conta_name.value === '') {
                setFieldError('conta_name', 'Nome é obrigatório');
                formInvalid = true;
            } else if (form.conta_name.value.length < 3) {
                setFieldError('conta_name', 'Nome deve ter 3 letras ou mais');
                formInvalid = true;
            } else if (form.conta_name.value.length > 20) {
                setFieldError('conta_name', 'Nome deve ter 10 letras ou menos');
                formInvalid = true;
            }

            // Sobrenome
            if (form.conta_lastname.value === '') {
                setFieldError('conta_lastname', 'Sobrenome é obrigatório');
                formInvalid = true;
            } else if (form.conta_lastname.value.length < 3) {
                setFieldError(
                    'conta_lastname',
                    'Sobrenome deve 3 letras ou mais'
                );
                formInvalid = true;
            } else if (form.conta_lastname.value.length > 30) {
                setFieldError(
                    'conta_lastname',
                    'Sobrenome deve 30 letras ou menos'
                );
                formInvalid = true;
            }

            // E-mail
            if (form.conta_email.value === '') {
                setFieldError('conta_email', 'E-mail é obrigatório');
                formInvalid = true;
            } else if (form.conta_email.value.length > 100) {
                setFieldError(
                    'conta_email',
                    'E-mail deve ter no máximo 100 letras'
                );
                formInvalid = true;
            }

            // Senha A
            if (form.conta_senhaA.value === '') {
                setFieldError('conta_senhaA', 'Senha é obrigatória');
                formInvalid = true;
            } else if (form.conta_senhaA.value.length < 6) {
                setFieldError(
                    'conta_senhaA',
                    'Senha deve ter pelo menos 6 letras'
                );
                formInvalid = true;
            } else if (form.conta_senhaA.value.length > 15) {
                setFieldError(
                    'conta_senhaA',
                    'Senha deve ter no máximo 15 letras'
                );
                formInvalid = true;
            }

            // Senha B
            if (form.conta_senhaB.value === '') {
                setFieldError('conta_senhaB', 'Senha é obrigatória');
                formInvalid = true;
            } else if (form.conta_senhaB.value.length < 6) {
                setFieldError(
                    'conta_senhaB',
                    'Senha deve ter pelo menos 6 letras'
                );
                formInvalid = true;
            } else if (form.conta_senhaB.value.length > 15) {
                setFieldError(
                    'conta_senhaB',
                    'Segunda senha deve ter no máximo 15 letras'
                );
                formInvalid = true;
            } else if (form.conta_senhaA.value !== form.conta_senhaB.value) {
                setFieldError('conta_senhaA', 'Senhas devem ser iguais');
                setFieldError('conta_senhaB', 'Senhas devem ser iguais');
                formInvalid = true;
            }

            if (formInvalid) {
                reject(new Error('Campos não preenchidos corretamente!'));
            } else {
                resolve();
            }
        });
    }

    // OK
    function accountFormSubmit() {
        Promise.all([
            accountFormValidation(),
            stateValidation(),
            cityValidation(),
            emailValidation(),
        ]).then(() => {
            const inputSelector = '#conta_form .form-control';
            const inputs = document.querySelectorAll(inputSelector);
            const form = document.getElementById('conta_form');
            const selCity = document.getElementById('conta_cidade');
            const jsonBody = {};

            inputs.forEach((node) => {
                node.disabled = 'disabled';
            });

            document.getElementById('conta_wait').style.display = 'block';
            document.getElementById('conta_buttons').style.display = 'none';

            jsonBody.first_name = form.conta_name.value;
            jsonBody.last_name = form.conta_lastname.value;
            jsonBody.email = form.conta_email.value;
            jsonBody.password = form.conta_senhaA.value;
            jsonBody.city_id = selCity.options[selCity.selectedIndex].value;

            App.postJson('/guest/create-user', jsonBody).then((response) => {
                document
                    .getElementById('conta_wait')
                    .style
                    .display = 'none';

                accountFormResult(
                    'alert-success',
                    'Cadastro realizado com sucesso!'
                );
            }).catch((error) => {
                document
                    .getElementById('conta_buttons')
                    .style
                    .display = 'block';

                document
                    .getElementById('conta_wait')
                    .style
                    .display = 'none';

                accountFormResult('alert-danger', error.message);
            });
        }).catch((error) => {
            accountFormResult('alert-danger', error.message);
        });
    }

    function contactFormReset() {
        const spans = document.querySelectorAll('#contact_form .help-block');
        const divs = document.querySelectorAll('#contact_form .form-group');

        spans.forEach((node) => {
            node.style.display = 'none';
        });

        divs.forEach((node) => {
            node.className = 'form-group';
        });

        document.getElementById('contact_wait').style.display = 'none';
        document.getElementById('contact_buttons').style.display = 'block';
        document.getElementById('contact_result').style.display = 'none';
    }

    // OK
    function contactFormValidation() {
        return new Promise((resolve, reject) => {
            contactFormReset();

            const form = document.getElementById('contact_form');
            let formInvalid = false;

            // Nome
            if (form.contact_name.value === '') {
                formInvalid = true;
                setFieldError('contact_name', 'Nome é obrigatório');
            } else if (form.contact_name.value.length < 3) {
                formInvalid = true;
                setFieldError(
                    'contact_name',
                    'Nome deve ter pelo menos 3 letras.'
                );
            }

            // E-mail
            if (form.contact_email.value === '') {
                formInvalid = true;
                setFieldError('contact_email', 'E-mail é obrigatório');
            } else if (form.contact_email.value.length > 100) {
                formInvalid = true;
                setFieldError(
                    'contact_email',
                    'E-mail deve ter no máximo 100 letras.'
                );
            }

            // Assunto
            if (form.contact_subject.value === '') {
                formInvalid = true;
                setFieldError('contact_subject', 'Assunto é obrigatório');
            } else if (form.contact_subject.value.length > 20) {
                formInvalid = true;
                setFieldError(
                    'contact_subject',
                    'Assunto deve ter no máximo 20 letras.'
                );
            }

            // Mensagem
            if (form.contact_message.value === '') {
                formInvalid = true;
                setFieldError('contact_message', 'Mensagem é obrigatório');
            }

            if (formInvalid) {
                reject(new Error('Um ou mais campos estão inválidos!'));
                return;
            }

            resolve();
        });
    }

    // OK
    function contactFormResult(className, message) {
        const result = document.getElementById('contact_result');

        result.className = `alert ${className}`;
        result.innerHTML = message;
        result.style.display = 'block';

        document
            .getElementById('contact_wait')
            .style
            .display = 'none';

        if (className === 'alert-danger') {
            const inputSelector = '#contact_form .form-control';
            const inputs = document.querySelectorAll(inputSelector);
            inputs.forEach((node) => {
                node.disabled = '';
            });

            document
                .getElementById('contact_buttons')
                .style
                .display = 'block';
        }
    }

    // OK
    function contactFormSubmit() {
        contactFormValidation().then(() => {
            const inputSelector = '#contact_form .form-control';
            const inputs = document.querySelectorAll(inputSelector);
            const form = document.getElementById('contact_form');
            const jsonBody = {};

            inputs.forEach((node) => {
                node.disabled = 'disabled';
            });

            document.getElementById('contact_wait').style.display = 'block';
            document.getElementById('contact_buttons').style.display = 'none';

            jsonBody.name = form.contact_name.value;
            jsonBody.email = form.contact_email.value;
            jsonBody.subject = form.contact_subject.value;
            jsonBody.message = form.contact_message.value;

            fetch('/guest/contact', {
                method: 'POST',
                headers: new Headers(
                    {'Content-Type': 'application/json'},
                    {'Accept': 'application/json'}
                ),
                cache: 'default',
                body: JSON.stringify(jsonBody),
            }).then((response) => {
                return response.json()
            }).then((responseJson) => {
                if (responseJson.status) {
                    contactFormResult('alert-danger', responseJson.message);
                } else {
                    contactFormResult(
                        'alert-success',
                        'E-mail enviado com sucesso!'
                    );
                }
            }).catch((error) => {
                contactFormResult('alert-danger', error.message);
            });
        }).catch((error) => {
            contactFormResult('alert-danger', error.message);
        });
    }

    // OK
    function getCities() {
        const select = document.getElementById('conta_estado');
        const sigla = select.options[select.selectedIndex].value;
        const selectCity = document.getElementById('conta_cidade');
        const empty = document.createElement('option');

        selectCity.options.length = [];

        empty.appendChild(document.createTextNode('Selecione'));
        empty.value = '';
        selectCity.appendChild(empty);

        if (sigla === '') {
            return;
        }

        App.getJson(`/guest/find-all-cities-by-state/${sigla}`)
            .then((responseArray) => {
                if (responseArray.length > 0) {
                    for (const i in responseArray) {
                        if (responseArray.hasOwnProperty(i)) {
                            const city = responseArray[i];
                            const opt = document.createElement('option');

                            opt.appendChild(document.createTextNode(
                                city.name
                            ));
                            opt.value = city.city_id;
                            selectCity.appendChild(opt);
                        }
                    }
                }
            }).catch((error) => {
                accountFormResult('alert-danger', error.message);
            });
    }

    function events() {
        const linksCadastro = document.getElementsByClassName('cadastre-se');

        function accountModalClick(e) {
            e.preventDefault();

            $('#conta_modal').modal({
                backdrop: 'static',
                keyboard: false,
            });

            return false;
        }

        for (let i = 0; i < linksCadastro.length; i += 1) {
            linksCadastro[i].addEventListener('click', accountModalClick);
        }

        // mostrar esconder senha A
        document.getElementById('btn-senhaA').addEventListener('click', (e) => {
            e.preventDefault();
            const inputA = document.getElementById('conta_senhaA');

            if (inputA.type === 'password') {
                inputA.type = 'text';

                document
                    .getElementById('btn-senhaA')
                    .innerHTML = '<i class="fa fa-eye"></i>';
            } else {
                inputA.type = 'password';

                document
                    .getElementById('btn-senhaA')
                    .innerHTML = '<i class="fa fa-eye-slash"></i>';
            }

            return false;
        });

        // mostrar esconder senha B
        document.getElementById('btn-senhaB').addEventListener('click', (e) => {
            e.preventDefault();
            const inputB = document.getElementById('conta_senhaB');

            if (inputB.type === 'password') {
                inputB.type = 'text';

                document
                    .getElementById('btn-senhaB')
                    .innerHTML = '<i class="fa fa-eye"></i>';
            } else {
                inputB.type = 'password';

                document
                    .getElementById('btn-senhaB')
                    .innerHTML = '<i class="fa fa-eye-slash"></i>';
            }

            return false;
        });

        // submit form criar conta
        const selA = 'conta_form';
        document.getElementById(selA).addEventListener('submit', (e) => {
            e.preventDefault();
            accountFormSubmit();
            return false;
        });

        // limpar form criar conta
        const selB = '#conta_form input[type=button]';
        document.querySelector(selB).addEventListener('click', (e) => {
            e.preventDefault();
            accountFormReset(true);
        });

        // submit form contato
        const selC = 'contact_form';
        document.getElementById(selC).addEventListener('submit', (e) => {
            e.preventDefault();
            contactFormSubmit();
            return false;
        });

        // limpar form contato
        const selD = '#contact_form button[type=button]';
        document.querySelector(selD).addEventListener('click', (e) => {
            e.preventDefault();
            contactFormReset();
        });

        // buscar cidades ao alterar select de estados no cadastro
        const selE = 'conta_estado';
        document.getElementById(selE).addEventListener('change', (e) => {
            e.preventDefault();
            getCities();
        });
    }

    events();
}

main();
