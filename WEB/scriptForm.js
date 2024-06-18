document.getElementById('loginForm').addEventListener('submit', function(event) {
    // Evitar el envío del formulario
    event.preventDefault();

    // Obtener valores de los campos
    var email = document.getElementById('email').value;
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // Validar que los campos no estén vacíos
    if (!email || !username || !password) {
        alert('Por favor, completa todos los campos.');
        return;
    }

    // Validar formato del correo electrónico
    var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert('Por favor, ingresa un correo electrónico válido.');
        return;
    }

    // Si todas las validaciones pasan, enviar el formulario
    this.submit();
});