$(document).ready(function () {
    "use strict";
    var body = $("body");
    $(function () {
        $(".preloader").fadeOut();
        $('#side-menu').metisMenu();
    });

    /* ===== Theme Settings ===== */
    $(".open-close").on("click", function () {
        body.toggleClass("show-sidebar").toggleClass("hide-sidebar");
        $(".sidebar-head .open-close i").toggleClass("ti-menu");
    });

    /* ===========================================================
        Loads the correct sidebar on window load.
        collapses the sidebar on window resize.
        Sets the min-height of #page-wrapper to window size.
    =========================================================== */

    $(function () {
        var set = function () {
            var topOffset = 60,
                width = (window.innerWidth > 0) ? window.innerWidth : this.screen.width,
                height = ((window.innerHeight > 0) ? window.innerHeight : this.screen.height) - 1;
            if (width < 768) {
                $('div.navbar-collapse').addClass('collapse');
                topOffset = 100; /* 2-row-menu */
            } else {
                $('div.navbar-collapse').removeClass('collapse');
            }

            /* ===== This is for resizing window ===== */
            if (width < 1170) {
                body.addClass('content-wrapper');
                $(".sidebar-nav, .slimScrollDiv").css("overflow-x", "visible").parent().css("overflow", "visible");
            } else {
                body.removeClass('content-wrapper');
            }

            height = height - topOffset;
            if (height < 1) {
                height = 1;
            }
            if (height > topOffset) {
                $("#page-wrapper").css("min-height", (height) + "px");
            }
        },
        url = window.location,
        element = $('ul.nav a').filter(function () {
            return this.href === url || url.href.indexOf(this.href) === 0;
        }).addClass('active').parent().parent().addClass('in').parent();
        if (element.is('li')) {
            element.addClass('active');
        }
        $(window).ready(set);
        $(window).bind("resize", set);
    });

    /* ===== Sidebar ===== */
    $('.slimscrollsidebar').slimScroll({
        height: '100%',
        position: 'right',
        size: "6px",
        color: 'rgba(0,0,0,0.3)'
    });

    /* ===== Resize all elements ===== */
    body.trigger("resize");

    /* ===== Visited ul li ===== */
    $('.visited li a').on("click", function (e) {
        $('.visited li').removeClass('active');
        var $parent = $(this).parent();
        if (!$parent.hasClass('active')) {
            $parent.addClass('active');
        }
        e.preventDefault();
    });

    /* =================================================================
        Update 1.5
        this is for close icon when navigation open in mobile view
    ================================================================= */

    $(".navbar-toggle").on("click", function () {
        $(".navbar-toggle i").toggleClass("ti-menu").addClass("ti-close");
    });
 });

 var confirmar = function(text, callbackFn, confirmText, cancelText) {
    bootbox.confirm({
        message: text,
        buttons: {
            confirm: {
                label: confirmText || 'Confirmar',
                className: 'btn-primary pull-left'
            },
            cancel: {
                label: cancelText || 'Cancelar',
                className: 'btn-secondary'
            }
        },
        callback: callbackFn
    });
 };

var datatable_ptBRJson = {
    "sEmptyTable": "Nenhum registro encontrado",
    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
    "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
    "sInfoPostFix": "",
    "sInfoThousands": ".",
    "sLengthMenu": "_MENU_ resultados por página",
    "sLoadingRecords": "Carregando...",
    "sProcessing": "Processando...",
    "sZeroRecords": "Nenhum registro encontrado",
    "sSearch": "Pesquisar",
    "oPaginate": {
        "sNext": "Próximo",
        "sPrevious": "Anterior",
        "sFirst": "Primeiro",
        "sLast": "Último"
    },
    "oAria": {
        "sSortAscending": ": Ordenar colunas de forma ascendente",
        "sSortDescending": ": Ordenar colunas de forma descendente"
    }
};