$(document).ready(function() {

    var lastClickTime = new Date().getTime();

    // Show the first desktop by default
    $('.desktop1').addClass('visible');

    // Listen for clicks on each desktop
    $('.screen').click(function() {

        // Get the next desktop to slide in
        var nextDesktop = $(this).next('.screen');
        if (!nextDesktop.length) {
            nextDesktop = $('.screen').first();
        }

        var currentTime = new Date().getTime();
        var timeSinceLastClick = currentTime - lastClickTime;
        if (timeSinceLastClick < 500) {
            return;
        }

        // Update the last click time
        lastClickTime = currentTime;

        // Slide the current desktop out of view
        $(this).animate({ top: '100vh' }, 500, function() {
            $(this).removeClass('visible');
        });

        // Slide the next desktop into view
        nextDesktop.css('top', '-100vh').addClass('visible').animate({ top: '0' }, 500);

    });
});