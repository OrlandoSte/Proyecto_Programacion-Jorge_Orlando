
async function loadData() {
    const response = await fetch('carreras.json');

    const data = await response.json();

    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];

    data.forEach(item => {
        const row = document.createElement('tr');
        
        const cod_Carrera = document.createElement('td');
        cod_Carrera.textContent = item.hora_comienzo;
        row.appendChild(cod_Carrera);

        const recorrido = document.createElement('td');
        recorrido.textContent = item.cod_recorrido;
        row.appendChild(recorrido);

        const sexoCarrera = document.createElement('td');
        sexoCarrera.textContent = item.dorsal;
        row.appendChild(sexoCarrera);
        
        const reglas = document.createElement('td');
        reglas.textContent = item.tiempo;
        row.appendChild(reglas);

        tableBody.appendChild(row);
    });
}



let currentPage = 1;
const rowsPerPage = 5;
let data = [];

function displayPage(page) {
    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];
    tableBody.innerHTML = ''; 

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const paginatedItems = data.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('tr');

        const cod_Carrera = document.createElement('td');
        cod_Carrera.textContent = item.codigo_carrera;
        row.appendChild(cod_Carrera);

        
        const recorrido = document.createElement('td');
        recorrido.textContent = item.cod_recorrido;
        row.appendChild(recorrido);

        const sexoCarrera = document.createElement('td');
        sexoCarrera.textContent = item.sexo;
        row.appendChild(sexoCarrera);

        const reglas = document.createElement('td');
        reglas.textContent = item.regla;
        row.appendChild(reglas);

        const horaCom = document.createElement('td');
        horaCom.textContent = item.hora_comienzo;
        row.appendChild(horaCom);

        tableBody.appendChild(row);
    });

    document.getElementById('prevBtn').disabled = page === 1;
    document.getElementById('nextBtn').disabled = end >= data.length;
}

async function loadDatas() {
    const response = await fetch('carreras.json');
    data = await response.json();
    displayPage(currentPage);
}

loadDatas();

function nextPage() {
    if (currentPage * rowsPerPage < data.length) {
        currentPage++;
        displayPage(currentPage);
    }
}

function prevPage() {
    if (currentPage > 1) {
        currentPage--;
        displayPage(currentPage);
    }
}

