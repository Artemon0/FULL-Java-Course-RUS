// ===== JAVA COURSE WEBSITE - ULTIMATE SCRIPT =====
// Максимальная функциональность + красота + практичность

const CONFIG = {
    github: {
        username: 'artemon0',
        repo: 'FULL-Java-Course-RUS',
        api: 'https://api.github.com/repos/artemon0/FULL-Java-Course-RUS'
    },
    modules: {
        beginner: [
            { num: '01', name: 'Основы Java', desc: 'Переменные, типы данных, операторы', path: 'beginner/module-01-basics' },
            { num: '02', name: 'Синтаксис', desc: 'Условия, циклы, массивы', path: 'beginner/module-02-syntax' },
            { num: '03', name: 'ООП Часть 1', desc: 'Классы, объекты, инкапсуляция', path: 'beginner/module-03-oop-part1' },
            { num: '04', name: 'ООП Часть 2', desc: 'Наследование, полиморфизм', path: 'beginner/module-04-oop-part2' },
            { num: '🎮', name: 'Проект: Текстовая RPG', desc: 'Финальный проект уровня', path: 'beginner/project-text-rpg', isProject: true }
        ],
        intermediate: [
            { num: '05', name: 'Коллекции', desc: 'List, Set, Map, Generics', path: 'intermediate/module-05-collections' },
            { num: '06', name: 'Stream API', desc: 'Lambda, функциональное программирование', path: 'intermediate/module-06-streams' },
            { num: '07', name: 'IO/NIO', desc: 'Работа с файлами', path: 'intermediate/module-07-io' },
            { num: '08', name: 'Многопоточность', desc: 'Thread, синхронизация', path: 'intermediate/module-08-threads' },
            { num: '09', name: 'Сеть и JDBC', desc: 'Sockets, базы данных', path: 'intermediate/module-09-network' }
        ],
        advanced: [
            { num: '10', name: 'Maven/Gradle', desc: 'Системы сборки', path: 'advanced/module-10-build' },
            { num: '11', name: 'JUnit', desc: 'Тестирование', path: 'advanced/module-11-testing' },
            { num: '12', name: 'Spring Basics', desc: 'DI, Spring Boot', path: 'advanced/module-12-spring-basics' },
            { num: '13', name: 'Spring Advanced', desc: 'MVC, Data, Security', path: 'advanced/module-13-spring' },
            { num: '14', name: 'JavaFX', desc: 'GUI приложения', path: 'advanced/module-14-javafx' },
            { num: '15', name: 'Архитектура', desc: 'Паттерны проектирования', path: 'advanced/module-15-patterns' },
            { num: '16', name: 'Оптимизация', desc: 'Производительность', path: 'advanced/module-16-optimization' },
            { num: '🎮', name: 'MINECRAFT CLONE', desc: '3D игра с OpenGL', path: 'advanced/final-project-minecraft', isProject: true }
        ]
    }
};

// ===== UTILITY FUNCTIONS =====
const Utils = {
    // Плавная прокрутка к элементу
    scrollTo(id) {
        const element = document.getElementById(id);
        if (element) {
            element.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    },

    // Анимация счетчика
    animateCounter(element, target, duration = 2000) {
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
    },

    // Показать уведомление
    notify(message, type = 'info') {
        const colors = {
            info: 'linear-gradient(135deg, #6366f1, #8b5cf6)',
            success: 'linear-gradient(135deg, #10b981, #059669)',
            error: 'linear-gradient(135deg, #ef4444, #dc2626)',
            warning: 'linear-gradient(135deg, #f59e0b, #d97706)'
        };

        const notification = document.createElement('div');
        notification.style.cssText = `
            position: fixed;
            top: 100px;
            right: 20px;
            background: ${colors[type]};
            color: white;
            padding: 1rem 1.5rem;
            border-radius: 12px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.3);
            z-index: 10001;
            animation: slideIn 0.3s ease;
            max-width: 350px;
            font-weight: 500;
        `;
        notification.textContent = message;
        document.body.appendChild(notification);

        setTimeout(() => {
            notification.style.animation = 'slideOut 0.3s ease';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    },

    // Копировать в буфер обмена
    async copyToClipboard(text) {
        try {
            await navigator.clipboard.writeText(text);
            this.notify('✅ Скопировано в буфер обмена!', 'success');
            return true;
        } catch (err) {
            this.notify('❌ Ошибка копирования', 'error');
            return false;
        }
    },

    // Открыть URL в новой вкладке
    openURL(url) {
        window.open(url, '_blank');
    },

    // Создать элемент из HTML
    createElement(html) {
        const template = document.createElement('template');
        template.innerHTML = html.trim();
        return template.content.firstChild;
    }
};

// ===== NAVIGATION =====
const Navigation = {
    init() {
        this.setupScrollEffects();
        this.setupMobileMenu();
        this.setupActiveLinks();
        this.setupBackToTop();
    },

    setupScrollEffects() {
        const navbar = document.getElementById('navbar');
        const progressBar = document.getElementById('progressBar');

        window.addEventListener('scroll', () => {
            // Navbar shadow
            if (window.scrollY > 50) {
                navbar?.classList.add('scrolled');
            } else {
                navbar?.classList.remove('scrolled');
            }

            // Progress bar
            const windowHeight = document.documentElement.scrollHeight - document.documentElement.clientHeight;
            const scrolled = (window.pageYOffset / windowHeight) * 100;
            if (progressBar) progressBar.style.width = scrolled + '%';
        });
    },

    setupMobileMenu() {
        const toggle = document.getElementById('mobileMenuToggle');
        const menu = document.getElementById('navMenu');
        const links = document.querySelectorAll('.nav-link');

        toggle?.addEventListener('click', () => {
            toggle.classList.toggle('active');
            menu?.classList.toggle('active');
        });

        links.forEach(link => {
            link.addEventListener('click', () => {
                toggle?.classList.remove('active');
                menu?.classList.remove('active');
            });
        });
    },

    setupActiveLinks() {
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
    },

    setupBackToTop() {
        const button = document.getElementById('backToTop');
        if (!button) return;

        window.addEventListener('scroll', () => {
            if (window.scrollY > 300) {
                button.classList.add('visible');
            } else {
                button.classList.remove('visible');
            }
        });
    }
};

// ===== GITHUB INTEGRATION =====
const GitHub = {
    async fetchStats() {
        try {
            const response = await fetch(CONFIG.github.api);
            if (!response.ok) throw new Error('API error');

            const data = await response.json();

            document.getElementById('githubStars').textContent = data.stargazers_count || '0';
            document.getElementById('githubForks').textContent = data.forks_count || '0';
            document.getElementById('githubWatchers').textContent = data.subscribers_count || '0';
            document.getElementById('githubIssues').textContent = data.open_issues_count || '0';

            const lastUpdate = new Date(data.updated_at);
            document.getElementById('lastUpdate').textContent = `Обновлено: ${lastUpdate.toLocaleDateString('ru-RU')}`;
        } catch (error) {
            console.log('GitHub stats unavailable:', error);
            // Fallback values
            document.getElementById('githubStars').textContent = '⭐';
            document.getElementById('githubForks').textContent = '🔄';
            document.getElementById('githubWatchers').textContent = '👁️';
            document.getElementById('githubIssues').textContent = '📝';
        }
    },

    downloadCourse() {
        const url = `https://github.com/${CONFIG.github.username}/${CONFIG.github.repo}/archive/refs/heads/main.zip`;
        Utils.openURL(url);
        Utils.notify('📥 Скачивание началось! Проверьте папку загрузок.', 'success');
    },

    openRepo() {
        Utils.openURL(`https://github.com/${CONFIG.github.username}/${CONFIG.github.repo}`);
    },

    openModule(path) {
        Utils.openURL(`https://github.com/${CONFIG.github.username}/${CONFIG.github.repo}/tree/main/${path}`);
    }
};

// ===== MODULES SYSTEM =====
const Modules = {
    init() {
        this.setupTabs();
        this.renderModules('beginner');
        this.renderModules('intermediate');
        this.renderModules('advanced');
    },

    setupTabs() {
        const tabs = document.querySelectorAll('.tab-btn');
        const panels = document.querySelectorAll('.level-panel');

        tabs.forEach(tab => {
            tab.addEventListener('click', () => {
                const level = tab.getAttribute('data-level');

                tabs.forEach(t => t.classList.remove('active'));
                panels.forEach(p => p.classList.remove('active'));

                tab.classList.add('active');
                document.getElementById(level)?.classList.add('active');
            });
        });
    },

    renderModules(level) {
        const container = document.getElementById(`${level}Modules`);
        if (!container) return;

        const modules = CONFIG.modules[level];
        container.innerHTML = modules.map(module => `
            <div class="module-card ${module.isProject ? 'project-card' : ''}" 
                 onclick="GitHub.openModule('${module.path}')">
                <div class="module-number">${module.num}</div>
                <div class="module-info">
                    <h4>${module.name}</h4>
                    <p>${module.desc}</p>
                </div>
                <div class="module-arrow">→</div>
            </div>
        `).join('');
    }
};

// ===== ANIMATIONS =====
const Animations = {
    init() {
        this.setupCounters();
        this.setupParticles();
        this.setupRevealEffects();
    },

    setupCounters() {
        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    const stats = entry.target.querySelectorAll('.stat-number');
                    stats.forEach(stat => {
                        const target = parseInt(stat.getAttribute('data-target'));
                        Utils.animateCounter(stat, target);
                    });
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.5 });

        const heroStats = document.querySelector('.hero-stats');
        if (heroStats) observer.observe(heroStats);
    },

    setupParticles() {
        const container = document.querySelector('.hero-particles');
        if (!container) return;

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
            container.appendChild(particle);
        }
    },

    setupRevealEffects() {
        const heroContent = document.querySelector('.hero-content');
        if (heroContent) {
            heroContent.style.opacity = '0';
            heroContent.style.transform = 'translateY(30px)';
            setTimeout(() => {
                heroContent.style.transition = 'all 1s ease';
                heroContent.style.opacity = '1';
                heroContent.style.transform = 'translateY(0)';
            }, 100);
        }
    }
};

// ===== EXTERNAL TOOLS =====
const Tools = {
    openCompiler() {
        Utils.openURL('https://www.jdoodle.com/online-java-compiler');
        Utils.notify('🌐 Открываем JDoodle компилятор...', 'info');
    },

    openProgramiz() {
        Utils.openURL('https://www.programiz.com/java-programming/online-compiler/');
    },

    openOnlineGDB() {
        Utils.openURL('https://www.onlinegdb.com/online_java_compiler');
    },

    openCodespaces() {
        Utils.openURL(`https://github.com/${CONFIG.github.username}/${CONFIG.github.repo}/codespaces`);
    }
};

// ===== KEYBOARD SHORTCUTS =====
const Shortcuts = {
    init() {
        document.addEventListener('keydown', (e) => {
            // Ctrl/Cmd + K - поиск
            if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
                e.preventDefault();
                Utils.scrollTo('modules');
            }

            // Ctrl/Cmd + D - скачать
            if ((e.ctrlKey || e.metaKey) && e.key === 'd') {
                e.preventDefault();
                GitHub.downloadCourse();
            }

            // Ctrl/Cmd + / - компилятор
            if ((e.ctrlKey || e.metaKey) && e.key === '/') {
                e.preventDefault();
                Tools.openCompiler();
            }

            // Escape - закрыть меню
            if (e.key === 'Escape') {
                const toggle = document.getElementById('mobileMenuToggle');
                const menu = document.getElementById('navMenu');
                toggle?.classList.remove('active');
                menu?.classList.remove('active');
            }
        });
    }
};

// ===== EASTER EGGS =====
const EasterEggs = {
    init() {
        this.konamiCode();
        this.consoleArt();
    },

    konamiCode() {
        let sequence = [];
        const pattern = ['ArrowUp', 'ArrowUp', 'ArrowDown', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'ArrowLeft', 'ArrowRight', 'b', 'a'];

        document.addEventListener('keydown', (e) => {
            sequence.push(e.key);
            sequence = sequence.slice(-10);

            if (sequence.join(',') === pattern.join(',')) {
                document.body.style.animation = 'rainbow 2s infinite';
                Utils.notify('🎉 Konami Code активирован! Вы нашли секрет!', 'success');
                setTimeout(() => {
                    document.body.style.animation = '';
                }, 5000);
            }
        });
    },

    consoleArt() {
        console.log('%c☕ Java Course', 'font-size: 40px; font-weight: bold; background: linear-gradient(135deg, #6366f1, #8b5cf6, #ec4899); -webkit-background-clip: text; -webkit-text-fill-color: transparent;');
        console.log('%cДобро пожаловать в курс Java!', 'font-size: 16px; color: #6366f1;');
        console.log('%cGitHub: https://github.com/' + CONFIG.github.username + '/' + CONFIG.github.repo, 'font-size: 12px; color: #8b5cf6;');
        console.log('%c\nГорячие клавиши:', 'font-size: 14px; font-weight: bold; color: #6366f1;');
        console.log('%cCtrl+K - Перейти к модулям', 'font-size: 12px; color: #8b5cf6;');
        console.log('%cCtrl+D - Скачать курс', 'font-size: 12px; color: #8b5cf6;');
        console.log('%cCtrl+/ - Открыть компилятор', 'font-size: 12px; color: #8b5cf6;');
    }
};

// ===== GLOBAL FUNCTIONS (для onclick в HTML) =====
function scrollToSection(id) {
    Utils.scrollTo(id);
}

function scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

function downloadCourse() {
    GitHub.downloadCourse();
}

// ===== STYLES INJECTION =====
const injectStyles = () => {
    const style = document.createElement('style');
    style.textContent = `
        @keyframes float {
            0%, 100% { transform: translateY(0) translateX(0); }
            25% { transform: translateY(-20px) translateX(10px); }
            50% { transform: translateY(-40px) translateX(-10px); }
            75% { transform: translateY(-20px) translateX(10px); }
        }

        @keyframes slideIn {
            from { transform: translateX(400px); opacity: 0; }
            to { transform: translateX(0); opacity: 1; }
        }

        @keyframes slideOut {
            from { transform: translateX(0); opacity: 1; }
            to { transform: translateX(400px); opacity: 0; }
        }

        @keyframes rainbow {
            0% { filter: hue-rotate(0deg); }
            100% { filter: hue-rotate(360deg); }
        }

        .module-card {
            position: relative;
            overflow: hidden;
        }

        .module-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: -100%;
            width: 100%;
            height: 100%;
            background: linear-gradient(90deg, transparent, rgba(99, 102, 241, 0.1), transparent);
            transition: left 0.5s;
        }

        .module-card:hover::before {
            left: 100%;
        }

        .module-arrow {
            font-size: 1.5rem;
            opacity: 0;
            transform: translateX(-10px);
            transition: all 0.3s;
        }

        .module-card:hover .module-arrow {
            opacity: 1;
            transform: translateX(0);
        }

        .project-card {
            background: linear-gradient(135deg, rgba(99, 102, 241, 0.1), rgba(139, 92, 246, 0.1));
            border: 2px solid rgba(99, 102, 241, 0.3);
        }

        .project-card:hover {
            border-color: rgba(99, 102, 241, 0.6);
            box-shadow: 0 20px 60px rgba(99, 102, 241, 0.2);
        }
    `;
    document.head.appendChild(style);
};

// ===== INITIALIZATION =====
document.addEventListener('DOMContentLoaded', () => {
    console.log('🚀 Initializing Java Course Website...');

    // Inject styles
    injectStyles();

    // Initialize all modules
    Navigation.init();
    GitHub.fetchStats();
    Modules.init();
    Animations.init();
    Shortcuts.init();
    EasterEggs.init();

    console.log('✅ Website initialized successfully!');
});

// ===== EXPORT FOR DEBUGGING =====
window.JavaCourse = {
    Utils,
    Navigation,
    GitHub,
    Modules,
    Animations,
    Tools,
    Shortcuts,
    CONFIG
};
