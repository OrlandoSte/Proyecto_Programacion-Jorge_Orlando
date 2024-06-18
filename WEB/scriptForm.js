
document.addEventListener('DOMContentLoaded', () => {
    const emailInput = document.getElementById('email');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const submitButton = document.querySelector('button[type="submit"]');
    const emailError = document.getElementById('emailError');
    const usernameError = document.getElementById('usernameError');
    const passwordError = document.getElementById('passwordError');

    const validaremail = (email) => {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailPattern.test(email);
    };

    const validarnombre = (username) => {
        const usernamePattern = /^[a-zA-Z0-9]+$/;
        return usernamePattern.test(username);
    };

    const validarpassword = (password) => {
        return password.length >= 8 && password.length <= 16;
    };

    const validateForm = () => {
        const emailcorrecto = validaremail(emailInput.value);
        const usernamecorrecto = validarnombre(usernameInput.value);
        const passwordcorrecto = validarpassword(passwordInput.value);
        
        emailError.textContent = emailcorrecto ? '' : 'Por favor, ingrese un email correcto.';
        usernameError.textContent = usernamecorrecto ? '' : 'El nombre de usuario no debe tener caracteres especiales.';
        passwordError.textContent = passwordcorrecto ? '' : 'La contrasena debe tener entre 8 y 16 caracteres.';

        submitButton.disabled = !(emailcorrecto && usernamecorrecto && passwordcorrecto);
    };

    emailInput.addEventListener('keyup', validateForm);
    usernameInput.addEventListener('keyup', validateForm);
    passwordInput.addEventListener('keyup', validateForm);

    submitButton.addEventListener('click', (event) => {
        event.preventDefault();
        if (!submitButton.disabled) {
            window.location.href = 'index.html';
        }
    });
});
