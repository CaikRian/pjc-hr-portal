const API = 'http://localhost:8080/api';

async function fetchCandidates() {
  const r = await fetch(`${API}/candidates`);
  return r.json();
}

function drawTable(list) {
  const tbody = document.getElementById('tableBody');
  if (!list.length) {
    tbody.innerHTML = `<tr><td colspan="5" class="text-muted">Nenhum candidato</td></tr>`;
    return;
  }
  tbody.innerHTML = list.map(c => `
    <tr data-name="${c.name.toLowerCase()}">
      <td>${c.name}</td>
      <td>${c.email ?? ''}</td>
      <td>${c.role ?? ''}</td>
      <td>${c.seniority ?? ''}</td>
      <td>
        <button class="btn btn-sm btn-outline-danger" onclick="removeCandidate(${c.id})">Excluir</button>
      </td>
    </tr>`).join('');
}

async function removeCandidate(id){
  if(!confirm('Confirmar exclusão?')) return;
  await fetch(`${API}/candidates/${id}`, { method:'DELETE' });
  load();
}

async function load(){
  const data = await fetchCandidates();
  drawTable(data);
}
load();

// form
const form = document.getElementById('candidateForm');
form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const fd = new FormData(form);
  const payload = Object.fromEntries(fd.entries());
  const r = await fetch(`${API}/candidates`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  if(r.ok){
    document.getElementById('formMsg').textContent = 'Salvo!';
    form.reset();
    load();
    setTimeout(()=> document.getElementById('formMsg').textContent = '', 1500);
  } else {
    alert('Erro ao salvar');
  }
});

// filter (vanilla)
const filterInput = document.getElementById('filterInput');
filterInput.addEventListener('input', () => {
  const term = filterInput.value.toLowerCase();
  document.querySelectorAll('#tableBody tr').forEach(tr => {
    tr.style.display = tr.getAttribute('data-name').includes(term) ? '' : 'none';
  });
});
