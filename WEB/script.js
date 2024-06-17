
async function loadData() {
    const response = await fetch('evento.json');

    const data = await response.json();

    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];

    data.forEach(item => {
        const row = document.createElement('tr');
        
        const cod_Carrera = document.createElement('td');
        cod_Carrera.textContent = item.codigo_carrera;
        row.appendChild(cod_Carrera);

        const nombreCor = document.createElement('td');
        const apellidos = item.apellidos;
        nombreCor.textContent = item.nombre+" "+apellidos;
        row.appendChild(nombreCor);

        const num_dortsal = document.createElement('td');
        num_dortsal.textContent = item.dorsal;
        row.appendChild(num_dortsal);
        
        const tiempoCor = document.createElement('td');
        tiempoCor.textContent = item.tiempo;
        row.appendChild(tiempoCor);

        tableBody.appendChild(row);
    });
}

window.onload = loadData;

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

        
        const nombreCor = document.createElement('td');
        const apellidos = item.apellidos;
        nombreCor.textContent = item.nombre+" "+apellidos;
        row.appendChild(nombreCor);

        const num_dortsal = document.createElement('td');
        num_dortsal.textContent = item.dorsal;
        row.appendChild(num_dortsal);

        const tiempoCor = document.createElement('td');
        tiempoCor.textContent = item.tiempo;
        row.appendChild(tiempoCor);

        tableBody.appendChild(row);
    });

    document.getElementById('prevBtn').disabled = page === 1;
    document.getElementById('nextBtn').disabled = end >= data.length;
}

async function loadData() {
    const response = await fetch('evento.json');
    data = await response.json();
    displayPage(currentPage);
}

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

document.getElementsByTagName("h1")[0].style.fontSize = "6vw";