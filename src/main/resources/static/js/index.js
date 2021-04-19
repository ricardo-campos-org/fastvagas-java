$(document).ready(function(){
    //wow.js init
	var wow = new WOW({
        animateClass: 'animated',
        mobile: false,
        offset: 100
    });
	wow.init();
    
    // Evento ao clicar no link 'contato' no header: rola até o form no rodapé
    $('#contato-header').click(function(e) {
        e.preventDefault();
        $([document.documentElement, document.body]).animate({
            scrollTop: $("#contato").offset().top
        }, 1000);
    });
    
    // Evento ao clicar no link 'contato' no footer: rola até o form no rodapé
    $('#contato-footer').click(function(e) {
        e.preventDefault();
        $([document.documentElement, document.body]).animate({
            scrollTop: $("#contato").offset().top
        }, 1000);
    });
    
    // Evento ao clicar no link 'inicio' no footer: rola até o topo
    $('#to-top').click(function(e) {
        e.preventDefault();
        $([document.documentElement, document.body]).animate({
            scrollTop: $(document.body).offset().top
        }, 1000);
    });
});
