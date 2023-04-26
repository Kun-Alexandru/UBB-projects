const first_button = document.getElementById('bt1');
const second_button = document.getElementById('bt2');

first_button.addEventListener('click', function onClick(event) {
    const background_images = ["Cool-Art-PC-Wallpapers-5.jpg","faVQir.jpg", "Cool-PC-Wallpapers-Free-Download.jpg", "pU4O2X.jpg", "wallpaper2you_29162.jpg"];
    var current_bg = document.body.style.backgroundImage;
    var current_index = background_images.indexOf(current_bg.slice(5, -2));

    if (current_index == 4) {
        document.body.style.backgroundImage = `url("${background_images[0]}")`;
        //document.body.style.animationName = 'slideDown';
        //first_button.style.backgroundColor = getRandomColor();
    } else {
        document.body.style.backgroundImage = `url("${background_images[current_index+1]}")`;
        //document.body.style.animationName = 'slideDown';
        //first_button.style.backgroundColor = getRandomColor();
    }

    setTimeout(function() {
        document.body.style.animationName = '';
    }, 500);
});

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

second_button.addEventListener('click', function onClick(event) {
    const decoration = ["underline red","wavy overline lime","line-through","solid underline purple 4px","wavy underline magenta 3x"]
    const links = document.getElementsByTagName("a");
    const sameColor = getRandomColor();
    const sameRandomDec = Math.floor(Math.random() * 5);
    for (var i = 0; i < links.length; i++) {
        links[i].style.color = getRandomColor();
        links[i].style.textDecoration = decoration[Math.floor(Math.random() * 5)];
        //links[i].style.color = sameColor;
        //links[i].style.textDecoration = decoration[sameRandomDec];
    }
});