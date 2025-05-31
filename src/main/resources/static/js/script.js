const toggle = document.getElementById('menu-toggle');
const menu = document.getElementById('side-menu');

toggle.addEventListener('click', () => {
  toggle.classList.toggle('active');
  menu.classList.toggle('active');
});

