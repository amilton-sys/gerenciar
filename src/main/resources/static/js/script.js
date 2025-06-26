const toggle = document.getElementById('menu-toggle')
const menu = document.getElementById('side-menu')

toggle.addEventListener('click', () => {
  toggle.classList.toggle('active')
  menu.classList.toggle('active')
});

const openModal = document.getElementById('openModal')
const modal = document.getElementById('modal')
const closeModal = document.getElementById('closeModal')


openModal.addEventListener('click', () => {
  modal.classList.add('show')
})

closeModal.addEventListener('click', () => {
  modal.classList.remove('show')
})

window.addEventListener("click", (event) => {
  if (event.target === modal) {
    modal.classList.remove('show')
  }
});