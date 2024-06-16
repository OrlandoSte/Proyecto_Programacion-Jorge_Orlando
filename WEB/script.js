document.getElementsByTagName("h1")[0].style.fontSize = "6vw";


async function cargaJson() {
			
    // Declaracion del url de la api
    let url = "C:\Users\orlo\Documents\GitHub\Proyecto_Programacion-Jorge_Orlando\APP\Programacion1";
    
    //Declaro una variable para la respuesta que me devuelva al conectarse a la API
    let response = await fetch(url);
    
    fetch(url)
        .then(response => {
        if (!response.ok) {
            throw new Error('Error en el fetch' + response.status);
        }
           return response.json();
        })
    }