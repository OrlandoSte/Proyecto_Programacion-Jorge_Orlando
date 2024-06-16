
async function loadData() {
    const response = await fetch('evento.json');

    const data = await response.json();


    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];

    data.forEach(item => {
        const row = document.createElement('tr');
        
        const cellCod_carrera = document.createElement('td');
        cellCod_carrera.textContent = item.codigo_carrera;
        row.appendChild(cellCod_carrera);

        const celldni = document.createElement('td');
        const apellidos = item.apellidos;
        celldni.textContent = item.nombre+" "+apellidos;
        row.appendChild(celldni);

        const celldorsal = document.createElement('td');
        celldorsal.textContent = item.dorsal;
        row.appendChild(celldorsal);
        
        const celltiempo = document.createElement('td');
        celltiempo.textContent = item.tiempo;
        row.appendChild(celltiempo);

        tableBody.appendChild(row);
    });
}

window.onload = loadData;

let currentPage = 1;
const rowsPerPage = 5;
let data = [];


async function loadData() {
    const response = await fetch('evento.json');
    data = await response.json();
    displayPage(currentPage);
}

function displayPage(page) {
    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];
    tableBody.innerHTML = ''; // Limpiar la tabla

    const start = (page - 1) * rowsPerPage;
    const end = start + rowsPerPage;
    const paginatedItems = data.slice(start, end);

    paginatedItems.forEach(item => {
        const row = document.createElement('tr');

        const cellCod_carrera = document.createElement('td');
        cellCod_carrera.textContent = item.codigo_carrera;
        row.appendChild(cellCod_carrera);

        
        const celldni = document.createElement('td');
        const apellidos = item.apellidos;
        celldni.textContent = item.nombre+" "+apellidos;
        row.appendChild(celldni);

        const celldorsal = document.createElement('td');
        celldorsal.textContent = item.dorsal;
        row.appendChild(celldorsal);

        const celltiempo = document.createElement('td');
        celltiempo.textContent = item.tiempo;
        row.appendChild(celltiempo);

        tableBody.appendChild(row);
    });

    document.getElementById('prevBtn').disabled = page === 1;
    document.getElementById('nextBtn').disabled = end >= data.length;
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