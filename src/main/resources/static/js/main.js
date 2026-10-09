/**
 * PhoneVerdict - Interactive Client-Side Logic
 */

// Compare Storage Key
const COMPARE_KEY = 'phoneverdict_compare_ids';

// Get selected compare phones from localStorage
function getCompareList() {
    try {
        const stored = localStorage.getItem(COMPARE_KEY);
        return stored ? JSON.parse(stored) : [];
    } catch (e) {
        return [];
    }
}

// Save compare list to localStorage
function saveCompareList(list) {
    try {
        localStorage.setItem(COMPARE_KEY, JSON.stringify(list));
    } catch (e) {
        console.error(e);
    }
}

// Add a phone to comparison tray
function addToCompare(id, name, brand, imageUrl) {
    let list = getCompareList();
    const existing = list.find(item => item.id === id);

    if (existing) {
        showToast(`"${brand} ${name}" is already in comparison!`);
        return;
    }

    if (list.length >= 3) {
        showToast('You can compare a maximum of 3 phones at once.', 'warning');
        return;
    }

    list.push({ id, name, brand, imageUrl });
    saveCompareList(list);
    updateCompareTray();
    showToast(`Added "${brand} ${name}" to comparison!`, 'success');
}

// Remove from comparison
function removeFromCompare(id) {
    let list = getCompareList();
    list = list.filter(item => item.id !== id);
    saveCompareList(list);
    updateCompareTray();
}

// Clear comparison
function clearCompare() {
    saveCompareList([]);
    updateCompareTray();
}

// Go to comparison results
function navigateToCompare() {
    const list = getCompareList();
    if (list.length < 2) {
        showToast('Please select at least 2 smartphones to compare.', 'warning');
        return;
    }
    const ids = list.map(item => item.id).join(',');
    window.location.href = `/compare/result?ids=${ids}`;
}

// Render Compare Tray UI
function updateCompareTray() {
    let tray = document.getElementById('compare-tray');
    const list = getCompareList();

    if (!tray) {
        tray = document.createElement('div');
        tray.id = 'compare-tray';
        tray.className = 'compare-tray shadow-lg';
        document.body.appendChild(tray);
    }

    if (list.length === 0) {
        tray.classList.remove('active');
        return;
    }

    tray.classList.add('active');
    tray.innerHTML = `
        <div class="container d-flex flex-wrap align-items-center justify-content-between gap-3">
            <div class="d-flex align-items-center gap-2">
                <span class="badge bg-primary rounded-pill px-3 py-2 fw-bold">Compare (${list.length}/3)</span>
                <div class="d-flex flex-wrap gap-2">
                    ${list.map(p => `
                        <span class="compare-item">
                            ${p.brand} ${p.name}
                            <i class="bi bi-x-circle-fill remove-compare" onclick="removeFromCompare(${p.id})"></i>
                        </span>
                    `).join('')}
                </div>
            </div>
            <div class="d-flex align-items-center gap-2">
                <button class="btn btn-sm btn-outline-light rounded-pill px-3" onclick="clearCompare()">Clear</button>
                <button class="btn btn-sm btn-primary-custom rounded-pill px-4" onclick="navigateToCompare()" ${list.length < 2 ? 'disabled' : ''}>
                    Compare Now <i class="bi bi-arrow-right ms-1"></i>
                </button>
            </div>
        </div>
    `;
}

// Lightweight Toast notification
function showToast(message, type = 'info') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        container.style.position = 'fixed';
        container.style.bottom = '80px';
        container.style.right = '20px';
        container.style.zIndex = '1090';
        document.body.appendChild(container);
    }

    const toast = document.createElement('div');
    const bgClass = type === 'warning' ? 'bg-warning text-dark' : type === 'success' ? 'bg-success text-white' : 'bg-dark text-white';
    toast.className = `p-3 mb-2 rounded shadow-lg d-flex align-items-center justify-content-between gap-3 ${bgClass}`;
    toast.style.minWidth = '260px';
    toast.innerHTML = `
        <div class="d-flex align-items-center gap-2">
            <i class="bi ${type === 'success' ? 'bi-check-circle-fill' : type === 'warning' ? 'bi-exclamation-triangle-fill' : 'bi-info-circle-fill'}"></i>
            <span>${message}</span>
        </div>
    `;

    container.appendChild(toast);
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transition = 'opacity 0.4s ease';
        setTimeout(() => toast.remove(), 400);
    }, 2800);
}

// Document Ready Initialization
document.addEventListener('DOMContentLoaded', () => {
    updateCompareTray();

    // Auto dismiss alerts after 5 seconds
    const alerts = document.querySelectorAll('.alert-dismissible');
    alerts.forEach(alert => {
        setTimeout(() => {
            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);
            if (bsAlert) bsAlert.close();
        }, 5000);
    });
});
