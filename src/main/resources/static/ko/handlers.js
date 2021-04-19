// Fonte: https://stackoverflow.com/questions/22706765/twitter-bootstrap-3-modal-with-knockout
ko.bindingHandlers.modal = {
    init: function (element, valueAccessor, allBindings, viewModel, bindingContext) {
        var optBackdrop = allBindings.get('backdrop') || true;
        var optKeyboard = allBindings.get('keyboard') || true;

        if (optKeyboard === 'false') {
            optKeyboard = false;
        }
        if (optKeyboard === 'true') {
            optKeyboard = true;
        }

        $(element).modal({
            backdrop: optBackdrop,
            keyboard: optKeyboard,
            show: false
        });

        var value = valueAccessor();
        if (ko.isObservable(value)) {
            $(element).on('hidden.bs.modal', function() {
               value(false);
            });
        }
    },
    update: function (element, valueAccessor) {
        var value = valueAccessor();
        if (ko.utils.unwrapObservable(value)) {
            $(element).modal('show');
        } else {
            $(element).modal('hide');
        }
    }
}
