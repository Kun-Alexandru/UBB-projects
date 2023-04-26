$(document).ready(function () {

    var lastClickTime = new Date().getTime();
    var lastScrollTop = 0;

    $('.desktop1').addClass('visible');




    $('.screen').bind('mousewheel', function(event) {
        if (event.originalEvent.wheelDelta >= 0) {
            console.log('Scroll up');
            var nextDesktop = $(this).next('.screen');
            if (!nextDesktop.length) {
                nextDesktop = $('.screen').first();
            }


            var currentTime = new Date().getTime();
            var timeSinceLastClick = currentTime - lastClickTime;
            if (timeSinceLastClick < 500) {
                return;
            }
    
            lastClickTime = currentTime;

            $(this).animate({ top: '100vh' }, 500, function () {
                $(this).removeClass('visible');
            });

            nextDesktop.css('top', '-100vh').addClass('visible').animate({ top: '0' }, 500);
        }
        else {
            console.log('Scroll down');
            var nextDesktop = $(this).prev('.screen');
            if (!nextDesktop.length) {
                nextDesktop = $('.screen').last();
            }

            var currentTime = new Date().getTime();
            var timeSinceLastClick = currentTime - lastClickTime;
            if (timeSinceLastClick < 500) {
                return;
            }
    
            lastClickTime = currentTime;

            $(this).animate({ top: '-100vh' }, 500, function () {
                $(this).removeClass('visible');
            });

            nextDesktop.css('top', '100vh').addClass('visible').animate({ top: '0' }, 500);
        }
    });
});