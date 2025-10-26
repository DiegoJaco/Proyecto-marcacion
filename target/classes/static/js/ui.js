
// Helpers de UI básicos
export function $(sel, root=document){ return root.querySelector(sel); }
export function $all(sel, root=document){ return Array.from(root.querySelectorAll(sel)); }

export function toast(msg, type='info'){
  const el = document.createElement('div');
  el.className = `fixed bottom-4 right-4 px-4 py-2 rounded shadow text-white ${type==='error'?'bg-red-600': type==='success'?'bg-green-600':'bg-gray-800'}`;
  el.textContent = msg;
  document.body.appendChild(el);
  setTimeout(()=> el.remove(), 3000);
}

export function setLoading(btn, loading=true) {
  if(!btn) return;
  btn.disabled = loading;
  btn.dataset.originalText = btn.dataset.originalText || btn.textContent;
  btn.textContent = loading ? 'Procesando...' : btn.dataset.originalText;
}

export function renderTable(container, columns, rows){
  container.innerHTML = '';
  const table = document.createElement('table');
  table.className = 'min-w-full border border-gray-200 rounded overflow-hidden';
  table.innerHTML = `
    <thead class="bg-gray-50">
      <tr>${columns.map(c => `<th class="text-left px-3 py-2 border-b">${c.header}</th>`).join('')}</tr>
    </thead>
    <tbody></tbody>`;
  const tbody = table.querySelector('tbody');
  rows.forEach(r => {
    const tr = document.createElement('tr');
    tr.className = 'hover:bg-gray-50';
    tr.innerHTML = columns.map(c => `<td class="px-3 py-2 border-b">${c.cell ? c.cell(r) : (r[c.accessor] ?? '')}</td>`).join('');
    tbody.appendChild(tr);
  });
  container.appendChild(table);
}
