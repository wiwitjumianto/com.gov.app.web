$(function() {
    Common.initExternalLinks();
    Interface.initialize();
});
var Interface = function() {
    var _this;
    return {
        initialize: function() {
            _this = this;
            this.initNav();
			this.initJscroll();
			this.initSticky();
        },
        initNav: function() {
            $('#nav a, a.nav').click(function() {
                var $this = $(this),
                    iTop = $($this.attr('href')).offset().top;
                $('html, body').animate({scrollTop: iTop}, 500);
                return false;
            });
        },
		initJscroll: function() {
			$('[data-ui="jscroll-default"]').jscroll({
			    debug: true
			});
            $('[data-ui="jscroll-example2"]').jscroll({
                autoTrigger: false,
                debug: true
            });
            $('[data-ui="jscroll-example3"]').jscroll({
                autoTriggerUntil: 3,
                debug: true
            });
		},
		initSticky: function() {
		    $('[data-ui="sticky"]').sticky({
		        topSpacing: 10,
		        bottomSpacing: 50
		    });
		}
    };
}();