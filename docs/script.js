// ===== CONFIGURATION =====
const GITHUB_USERNAME = 'artemon0';
const GITHUB_REPO = 'FULL-Java-Course-RUS';
const GITHUB_API = `https://api.github.com/repos/${GITHUB_USERNAME}/${GITHUB_REPO}`;

// ===== SMOOTH SCROLLING =====
function scrollToSection(id) {
    const element = document.getElementById(id);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth', block: 'start' });
    }
}

function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    });
});

// ===== PROGRESS BAR =====
window.addEventListener('scroll', () => {
    const windowHeight = document.documentElement.scrollHeight - document.documentElement.clientHeight;
    const scrolled = (window.pageYOffset / windowHeight) * 100;
    document.getElementById('progressBar').style.width = scrolled + '%';
});

// ===== NAVBAR SCROLL EFFECT =====
const navbar = document.getElementById('navbar');
window.addEventListener('scroll', () => {
    if (window.scrollY > 50) {
        navbar.classList.add('scrolled');
    } else {
        navbar.classList.remove('scrolled');
    }
});

// ===== ACTIVE NAVIGATION =====
const sections = document.querySelectorAll('section[id]');
const navLinks = document.querySelectorAll('.nav-link');

window.addEventListener('scroll', () => {
    let current = '';
    sections.forEach(section => {
        const sectionTop = section.offsetTop;
        if (pageYOffset >= sectionTop - 100) {
            current = section.getAttribute('id');
        }
    });

    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === `#${current}`) {
            link.classList.add('active');
        }
    });
});

// ===== MOBILE MENU =====
const mobileMenuToggle = document.getElementById('mobileMenuToggle');
const navMenu = document.getElementById('navMenu');

mobileMenuToggle.addEventListener('click', () => {
    mobileMenuToggle.classList.toggle('active');
    navMenu.classList.toggle('active');
});

// Close mobile menu when clicking on a link
navLinks.forEach(link => {
    link.addEventListener('click', () => {
        mobileMenuToggle.classList.remove('active');
        navMenu.classList.remove('active');
    });
});

// ===== BACK TO TOP BUTTON =====
const backToTop = document.getElementById('backToTop');
window.addEventListener('scroll', () => {
    if (window.scrollY > 300) {
        backToTop.classList.add('visible');
    } else {
        backToTop.classList.remove('visible');
    }
});

// ===== COUNTER ANIMATION =====
function animateCounter(element, target, duration = 2000) {
    let start = 0;
    const increment = target / (duration / 16);

    const timer = setInterval(() => {
        start += increment;
        if (start >= target) {
            element.textContent = target;
            clearInterval(timer);
        } else {
            element.textContent = Math.floor(start);
        }
    }, 16);
}

// Animate stats when visible
const statsObserver = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            const statNumbers = entry.target.querySelectorAll('.stat-number');
            statNumbers.forEach(stat => {
                const target = parseInt(stat.getAttribute('data-target'));
                animateCounter(stat, target);
            });
            statsObserver.unobserve(entry.target);
        }
    });
}, { threshold: 0.5 });

const heroStats = document.querySelector('.hero-stats');
if (heroStats) {
    statsObserver.observe(heroStats);
}

// ===== LEVEL TABS =====
const tabBtns = document.querySelectorAll('.tab-btn');
const levelPanels = document.querySelectorAll('.level-panel');

tabBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        const level = btn.getAttribute('data-level');

        tabBtns.forEach(b => b.classList.remove('active'));
        levelPanels.forEach(p => p.classList.remove('active'));

        btn.classList.add('active');
        document.getElementById(level).classList.add('active');
    });
});

// ===== GITHUB API - FETCH REPO STATS =====
async function fetchGitHubStats() {
    try {
        const response = await fetch(GITHUB_API);
        if (!response.ok) throw new Error('GitHub API error');

        const data = await response.json();

        // Update stats
        document.getElementById('githubStars').textContent = data.stargazers_count || '0';
        document.getElementById('githubForks').textContent = data.forks_count || '0';
        document.getElementById('githubWatchers').textContent = data.subscribers_count || '0';
        document.getElementById('githubIssues').textContent = data.open_issues_count || '0';

        // Update last update date
        const lastUpdate = new Date(data.updated_at);
        document.getElementById('lastUpdate').textContent = `Обновлено: ${lastUpdate.toLocaleDateString('ru-RU')}`;

    } catch (error) {
        console.log('GitHub stats not available:', error);
        // Set default values
        document.getElementById('githubStars').textContent = '⭐';
        document.getElementById('githubForks').textContent = '🔄';
        document.getElementById('githubWatchers').textContent = '👁️';
        document.getElementById('githubIssues').textContent = '📝';
    }
}

// ===== GITHUB API - FETCH MODULES =====
async function fetchModules(level) {
    const modulesContainer = document.getElementById(`${level}Modules`);

    try {
        const response = await fetch(`${GITHUB_API}/contents/${level}`);
        if (!response.ok) throw new Error('Modules not found');

        const data = await response.json();
        const modules = data.filter(item => item.type === 'dir' && item.name.startsWith('module'));

        if (modules.length === 0) {
            modulesContainer.innerHTML = '<div class="loading">Модули скоро появятся...</div>';
            return;
        }

        modulesContainer.innerHTML = '';

        modules.forEach((module, index) => {
            const moduleNumber = (index + 1).toString().padStart(2, '0');
            const moduleName = module.name.replace(/module-\d+-/, '').replace(/-/g, ' ');

            const moduleCard = document.createElement('div');
            moduleCard.className = 'module-card';
            moduleCard.innerHTML = `
                <div class="module-number">${moduleNumber}</div>
                <div class="module-info">
                    <h4>${capitalizeWords(moduleName)}</h4>
                    <p>Нажмите чтобы открыть на GitHub</p>
                </div>
            `;
            moduleCard.onclick = () => window.open(module.html_url, '_blank');
            modulesContainer.appendChild(moduleCard);
        });

    } catch (error) {
        console.log(`Modules for ${level} not available:`, error);
        modulesContainer.innerHTML = createDefaultModules(level);
    }
}

// ===== CREATE DEFAULT MODULES =====
function createDefaultModules(level) {
    const modules = {
        beginner: [
            { num: '01', name: 'Основы Java', desc: 'Переменные, типы данных, операторы' },
            { num: '02', name: 'Синтаксис', desc: 'Условия, циклы, массивы' },
            { num: '03', name: 'ООП Часть 1', desc: 'Классы, объекты, инкапсуляция' },
            { num: '04', name: 'ООП Часть 2', desc: 'Наследование, полиморфизм' },
            { num: '🎮', name: 'Финальный проект', desc: 'Текстовая RPG игра', isProject: true }
        ],
        intermediate: [
            { num: '05', name: 'Коллекции', desc: 'List, Set, Map, Generics' },
            { num: '06', name: 'Stream API', desc: 'Lambda, функциональное программирование' },
            { num: '07', name: 'IO/NIO', desc: 'Работа с файлами' },
            { num: '08', name: 'Многопоточность', desc: 'Thread, синхронизация' },
            { num: '09', name: 'Сеть и JDBC', desc: 'Sockets, базы данных' },
            { num: '🎮', name: 'Финальные проекты', desc: 'Чат + База данных', isProject: true }
        ],
        advanced: [
            { num: '10', name: 'Maven/Gradle', desc: 'Системы сборки' },
            { num: '11', name: 'JUnit', desc: 'Тестирование' },
            { num: '12', name: 'Spring Basics', desc: 'DI, Spring Boot' },
            { num: '13', name: 'Spring Advanced', desc: 'MVC, Data, Security' },
            { num: '14', name: 'JavaFX', desc: 'GUI приложения' },
            { num: '15', name: 'Архитектура', desc: 'Паттерны проектирования' },
            { num: '16', name: 'Оптимизация', desc: 'Производительность' },
            { num: '🎮', name: 'MINECRAFT', desc: '3D игра с OpenGL', isProject: true }
        ]
    };

    return modules[level].map(m => `
        <div class="module-card">
            <div class="module-number">${m.num}</div>
            <div class="module-info">
                <h4>${m.name}</h4>
                <p>${m.desc}</p>
            </div>
        </div>
    `).join('');
}

// ===== HELPER FUNCTIONS =====
function capitalizeWords(str) {
    return str.split(' ').map(word =>
        word.charAt(0).toUpperCase() + word.slice(1)
    ).join(' ');
}

// ===== INTERSECTION OBSERVER FOR ANIMATIONS =====
const observerOptions = {
    threshold: 0.1,
    rootMargin: '0px 0px -50px 0px'
};

const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
        if (entry.isIntersecting) {
            entry.target.style.opacity = '1';
            entry.target.style.transform = 'translateY(0)';
        }
    });
}, observerOptions);

// Observe feature cards
document.querySelectorAll('.feature-card').forEach(card => {
    card.style.opacity = '0';
    card.style.transform = 'translateY(30px)';
    card.style.transition = 'all 0.6s ease';
    observer.observe(card);
});

// ===== PARTICLES EFFECT =====
function createParticles() {
    const particlesContainer = document.querySelector('.hero-particles');
    if (!particlesContainer) return;

    for (let i = 0; i < 50; i++) {
        const particle = document.createElement('div');
        particle.style.cssText = `
            position: absolute;
            width: ${Math.random() * 4 + 1}px;
            height: ${Math.random() * 4 + 1}px;
            background: rgba(99, 102, 241, ${Math.random() * 0.5});
            border-radius: 50%;
            left: ${Math.random() * 100}%;
            top: ${Math.random() * 100}%;
            animation: float ${Math.random() * 10 + 5}s infinite ease-in-out;
            animation-delay: ${Math.random() * 5}s;
        `;
        particlesContainer.appendChild(particle);
    }
}

// Add float animation
const style = document.createElement('style');
style.textContent = `
    @keyframes float {
        0%, 100% { transform: translateY(0) translateX(0); }
        25% { transform: translateY(-20px) translateX(10px); }
        50% { transform: translateY(-40px) translateX(-10px); }
        75% { transform: translateY(-20px) translateX(10px); }
    }
`;
document.head.appendChild(style);

// ===== CONSOLE MESSAGE =====
console.log('%c☕ Java Course', 'font-size: 40px; font-weight: bold; background: linear-gradient(135deg, #6366f1, #8b5cf6, #ec4899); -webkit-background-clip: text; -webkit-text-fill-color: transparent;');
console.log('%cДобро пожаловать в курс Java!', 'font-size: 16px; color: #6366f1;');
console.log('%cGitHub: https://github.com/' + GITHUB_USERNAME + '/' + GITHUB_REPO, 'font-size: 12px; color: #8b5cf6;');

// ===== INITIALIZE ON PAGE LOAD =====
window.addEventListener('DOMContentLoaded', () => {
    // Fetch GitHub stats
    fetchGitHubStats();

    // Fetch modules for all levels
    fetchModules('beginner');
    fetchModules('intermediate');
    fetchModules('advanced');

    // Create particles
    createParticles();

    // Add smooth reve    const heroContent = document.querySelector('.hero-content');
    if (heroContent) {
        heroContent.style.opacity = '0';
        heroContent.stysform = 'translateY(30px)';
        setTimeout(() => {
            heroContent.style.transition = 'all 1s ease';
            heroContent.style.opacity = '1';
            heroContent.style.transform = 'translateY(0)';
        }, 100);
    }
});

// ===== EASTER EGG: KONAMI CODE =====
let konamiCode = [];
const konamiPattern = ['ArrowUp', 'ArrowUp', 'ArrowDown', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'ArrowLeft', 'ArrowRight', 'b', 'a'];

document.addEventListener('keydown', (e) => {
    konamiCode.push(e.key);
    konamiCode = konamiCode.slice(-10);

    if (konamiCode.join(',') === konamiPattern.join(',')) {
        document.body.style.animation = 'rainbow 2s infinite';
        alert('🎉 Вы нашли секретный код! Поздравляем!');
        setTimeout(() => {
            document.body.style.animation = '';
        }, 5000);
    }
});

const rainbowStyle = document.createElement('style');
rainbowStyle.textContent = `
    @keyframes rainbow {
        0% { filter: hue-rotate(0deg); }
        100% { filter: hue-rotate(360deg); }
    }
`;
document.head.appendChild(rainbowStyle);


// ===== ONLINE COMPILER =====
function openOnlineCompiler() {
    document.getElementById('compilerModal').classList.add('active');
    document.body.style.overflow = 'hidden';
}

function closeCompiler() {
    document.getElementById('compilerModal').classList.remove('active');
    document.body.style.overflow = 'auto';
}

function clearCode() {
    document.getElementById('codeEditor').value = '';
}

function loadExample() {
    const examples = [
        `public class Main {
    public static void main(String[] args) {
        // Пример: Hello World
        System.out.println("Привет, Java!");
    }
}`,
        `public class Main {
    public static void main(String[] args) {
        // Пример: Переменные
        int age = 25;
        String name = "Студент";
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
    }
}`,
        `public class Main {
    public static void main(String[] args) {
        // Пример: Цикл
        for (int i = 1; i <= 5; i++) {
            System.out.println("Число: " + i);
        }
    }
}`
    ];

    const randomExample = examples[Math.floor(Math.random() * examples.length)];
    document.getElementById('codeEditor').value = randomExample;
}

async function runCode() {
    const code = document.getElementById('codeEditor').value;
    const output = document.getElementById('compilerOutput');

    output.textContent = '⏳ Компиляция и запуск...';

    try {
        // Используем JDoodle API
        const response = await fetch('https://api.jdoodle.com/v1/execute', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                clientId: '8c8042f6e1f2e0e5e5f5f5f5f5f5f5f5', // Публичный demo ID
                clientSecret: 'demo-secret-key', // Demo secret
                script: code,
                language: 'java',
                versionIndex: '4' // Java 18
            })
        });

        const result = await response.json();

        if (result.output) {
            output.textContent = result.output;
        } else if (result.error) {
            output.textContent = '❌ Ошибка:\n' + result.error;
        } else {
            output.textContent = '❌ Не удалось выполнить код. Попробуйте позже.';
        }
    } catch (error) {
        output.textContent = '❌ Ошибка подключения к компилятору.\n\nПопробуйте:\n1. Проверьте интернет соединение\n2. Используйте локальную IDE (IntelliJ IDEA)\n3. Скачайте курс и запускайте код локально';
    }
}

// Close modal on Escape key
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        closeCompiler();
        closeCodeViewer();
    }
});

// Close modal on background click
document.getElementById('compilerModal')?.addEventListener('click', (e) => {
    if (e.target.id === 'compilerModal') {
        closeCompiler();
    }
});

document.getElementById('codeViewerModal')?.addEventListener('click', (e) => {
    if (e.target.id === 'codeViewerModal') {
        closeCodeViewer();
    }
});

// ===== DOWNLOAD COURSE =====
function downloadCourse() {
    // Скачивание ZIP архива репозитория
    const downloadUrl = `https://github.com/${GITHUB_USERNAME}/${GITHUB_REPO}/archive/refs/heads/main.zip`;
    window.open(downloadUrl, '_blank');

    // Показать уведомление
    showNotification('📥 Скачивание началось! Проверьте папку загрузок.');
}

// ===== CODE VIEWER =====
let currentCodeFiles = [];
let currentCodeContent = '';

async function openCodeViewer() {
    document.getElementById('codeViewerModal').classList.add('active');
    document.body.style.overflow = 'hidden';

    // Загрузка файлов из beginner/module-01-basics
    await loadCodeFiles('beginner/module-01-basics');
}

function closeCodeViewer() {
    document.getElementById('codeViewerModal').classList.remove('active');
    document.body.style.overflow = 'auto';
}

async function loadCodeFiles(path) {
    const filesList = document.getElementById('codeFilesList');
    filesList.innerHTML = '<div class="loading">Загрузка файлов...</div>';

    try {
        const response = await fetch(`${GITHUB_API}/contents/${path}`);
        if (!response.ok) throw new Error('Files not found');

        const data = await response.json();
        currentCodeFiles = data.filter(item =>
            item.type === 'file' && item.name.endsWith('.java')
        );

        if (currentCodeFiles.length === 0) {
            filesList.innerHTML = '<div class="loading">Java файлы не найдены</div>';
            return;
        }

        filesList.innerHTML = '';

        // Добавляем модули для выбора
        const modules = [
            { path: 'beginner/module-01-basics', name: '📚 Module 01 - Basics' },
            { path: 'beginner/module-02-syntax', name: '📚 Module 02 - Syntax' },
            { path: 'beginner/module-03-oop-part1', name: '📚 Module 03 - OOP Part 1' },
            { path: 'beginner/module-04-oop-part2', name: '📚 Module 04 - OOP Part 2' },
            { path: 'intermediate/module-05-collections', name: '🚀 Module 05 - Collections' },
            { path: 'intermediate/module-06-streams', name: '🚀 Module 06 - Streams' },
            { path: 'advanced/final-project-minecraft/src/main/java/com/minecraft', name: '⚡ Minecraft Project' }
        ];

        // Добавляем селектор модулей
        const moduleSelector = document.createElement('select');
        moduleSelector.className = 'code-file-item';
        moduleSelector.style.width = '100%';
        moduleSelector.style.marginBottom = '1rem';
        moduleSelector.innerHTML = modules.map(m =>
            `<option value="${m.path}" ${m.path === path ? 'selected' : ''}>${m.name}</option>`
        ).join('');
        moduleSelector.onchange = (e) => loadCodeFiles(e.target.value);
        filesList.appendChild(moduleSelector);

        // Добавляем файлы
        currentCodeFiles.forEach((file, index) => {
            const fileItem = document.createElement('div');
            fileItem.className = 'code-file-item';
            if (index === 0) fileItem.classList.add('active');
            fileItem.innerHTML = `☕ ${file.name}`;
            fileItem.onclick = () => loadCodeContent(file, fileItem);
            filesList.appendChild(fileItem);
        });

        // Загружаем первый файл
        if (currentCodeFiles.length > 0) {
            loadCodeContent(currentCodeFiles[0], filesList.querySelector('.code-file-item:nth-child(2)'));
        }

    } catch (error) {
        console.log('Error loading files:', error);
        filesList.innerHTML = '<div class="loading">Ошибка загрузки файлов</div>';
    }
}

async function loadCodeContent(file, element) {
    // Убираем active у всех
    document.querySelectorAll('.code-file-item').forEach(item => {
        if (item.tagName !== 'SELECT') {
            item.classList.remove('active');
        }
    });
    element.classList.add('active');

    document.getElementById('currentFileName').textContent = file.name;
    document.getElementById('codeContent').innerHTML = '<div class="loading">Загрузка кода...</div>';

    try {
        const response = await fetch(file.download_url);
        if (!response.ok) throw new Error('Code not found');

        const code = await response.text();
        currentCodeContent = code;

        // Отображаем код с нумерацией строк
        const lines = code.split('\n');
        const formattedCode = lines.map(line =>
            `<span class="code-line">${escapeHtml(line)}</span>`
        ).join('\n');

        document.getElementById('codeContent').innerHTML = formattedCode;

    } catch (error) {
        console.log('Error loading code:', error);
        document.getElementById('codeContent').textContent = 'Ошибка загрузки кода';
    }
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function copyCurrentCode() {
    if (!currentCodeContent) {
        showNotification('❌ Нет кода для копирования');
        return;
    }

    navigator.clipboard.writeText(currentCodeContent).then(() => {
        showNotification('✅ Код скопирован в буфер обмена!');
    }).catch(() => {
        showNotification('❌ Ошибка копирования');
    });
}

function runCurrentCode() {
    if (!currentCodeContent) {
        showNotification('❌ Нет кода для запуска');
        return;
    }

    // Закрываем просмотр кода и открываем компилятор
    closeCodeViewer();
    openOnlineCompiler();

    // Вставляем код в компилятор
    setTimeout(() => {
        document.getElementById('codeEditor').value = currentCodeContent;
        showNotification('✅ Код загружен в компилятор!');
    }, 300);
}

// ===== NOTIFICATIONS =====
function showNotification(message) {
    const notification = document.createElement('div');
    notification.style.cssText = `
        position: fixed;
        top: 100px;
        right: 20px;
        background: linear-gradient(135deg, #6366f1, #8b5cf6);
        color: white;
        padding: 1rem 1.5rem;
        border-radius: 12px;
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
        z-index: 10001;
        animation: slideIn 0.3s ease;
        max-width: 300px;
    `;
    notification.textContent = message;
    document.body.appendChild(notification);

    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Add notification animations
const notificationStyle = document.createElement('style');
notificationStyle.textContent = `
    @keyframes slideIn {
        from {
            transform: translateX(400px);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOut {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(400px);
            opacity: 0;
        }
    }
`;
document.head.appendChild(notificationStyle);
