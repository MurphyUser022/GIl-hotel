// Fonction pour ouvrir la modal
document.getElementById('ouvrir-modal').addEventListener('click', function() {
    document.getElementById('modal').style.display = 'block';
});

// Fonction pour fermer la modal en cliquant sur le bouton de fermeture (×)
document.getElementById('fermer-modal').addEventListener('click', function() {
    document.getElementById('modal').style.display = 'none';
});

// Fonction pour fermer la modal en cliquant en dehors de celle-ci
window.addEventListener('click', function(event) {
    const modal = document.getElementById('modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});





// Fonction pour ouvrir la modal
document.getElementById('ouvrir-modal2').addEventListener('click', function() {
    document.getElementById('modal2').style.display = 'block';
});

// Fonction pour fermer la modal en cliquant sur le bouton de fermeture (×)
document.getElementById('fermer-modal2').addEventListener('click', function() {
    document.getElementById('modal2').style.display = 'none';
});

// Fonction pour fermer la modal2 en cliquant en dehors de celle-ci
window.addEventListener('click', function(event) {
    const modal2 = document.getElementById('modal2');
    if (event.target === modal2) {
        modal2.style.display = 'none';
    }
});

