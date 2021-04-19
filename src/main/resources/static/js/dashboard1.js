$(document).ready(function () {
    "use strict";
    if ($('#home').length) {
        if (typeof(Storage) !== "undefined") {
            var k = 'vagas-por-email-home';
            if (!localStorage.getItem(k)) {
                localStorage.setItem(k, 'OK');
                $.toast({
                    heading: 'Bem vindo ao Vagas por E-mail',
                    text: 'Use the predefined ones, or specify a custom position object.',
                    position: 'top-right',
                    loaderBg: '#fff',
                    icon: 'success',
                    hideAfter: 7000,
                    stack: 6
                });
            }
        }

        // counter
        $(".counter").counterUp({
            delay: 100,
            time: 1200
        });

        var sparklineLogin = function () {
            $('#sparklinedash').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
                type: 'bar',
                height: '30',
                barWidth: '4',
                resize: true,
                barSpacing: '5',
                barColor: '#7ace4c'
            });
            $('#sparklinedash2').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
                type: 'bar',
                height: '30',
                barWidth: '4',
                resize: true,
                barSpacing: '5',
                barColor: '#7460ee'
            });
            $('#sparklinedash3').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
                type: 'bar',
                height: '30',
                barWidth: '4',
                resize: true,
                barSpacing: '5',
                barColor: '#11a0f8'
            });
            $('#sparklinedash4').sparkline([0, 5, 6, 10, 9, 12, 4, 9], {
                type: 'bar',
                height: '30',
                barWidth: '4',
                resize: true,
                barSpacing: '5',
                barColor: '#f33155'
            });
        }

        var sparkResize;
        $(window).on("resize", function (e) {
            clearTimeout(sparkResize);
            sparkResize = setTimeout(sparklineLogin, 500);
        });

        sparklineLogin();
    }
});
