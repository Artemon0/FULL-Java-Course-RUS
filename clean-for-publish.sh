#!/bin/bash
# Скрипт для очистки проекта перед публикацией на GitHub

echo "🧹 Очистка проекта для публикации..."

# Удаление скомпилированных файлов
echo ""
echo "📦 Удаление скомпилированных файлов..."
find . -name "*.class" -type f -delete
echo "✅ *.class файлы удалены"

# Удаление target папок
echo ""
echo "📦 Удаление target/ папок..."
find . -name "target" -type d -exec rm -rf {} + 2>/dev/null
echo "✅ target/ папки удалены"

# Удаление .idea папок
echo ""
echo "📦 Удаление .idea/ папок..."
find . -name ".idea" -type d -exec rm -rf {} + 2>/dev/null
echo "✅ .idea/ папки удалены"

# Удаление *.iml файлов
echo ""
echo "📦 Удаление *.iml файлов..."
find . -name "*.iml" -type f -delete
echo "✅ *.iml файлы удалены"

# Удаление .vscode папок
echo ""
echo "📦 Удаление .vscode/ папок..."
find . -name ".vscode" -type d -exec rm -rf {} + 2>/dev/null
echo "✅ .vscode/ папки удалены"

# Удаление временных файлов
echo ""
echo "📦 Удаление временных файлов..."
find . -name "*.swp" -o -name "*.swo" -o -name "*~" -type f -delete
echo "✅ Временные файлы удалены"

# Удаление .DS_Store
echo ""
echo "📦 Удаление .DS_Store файлов..."
find . -name ".DS_Store" -type f -delete
echo "✅ .DS_Store файлы удалены"

# Удаление Thumbs.db
echo ""
echo "📦 Удаление Thumbs.db файлов..."
find . -name "Thumbs.db" -type f -delete
echo "✅ Thumbs.db файлы удалены"

# Статистика
echo ""
echo "📊 Статистика проекта:"
JAVA_FILES=$(find . -name "*.java" -type f | wc -l)
MD_FILES=$(find . -name "*.md" -type f | wc -l)
TOTAL_FILES=$(find . -type f | wc -l)

echo "   Java файлов: $JAVA_FILES"
echo "   Markdown файлов: $MD_FILES"
echo "   Всего файлов: $TOTAL_FILES"

echo ""
echo "✅ Проект очищен и готов к публикации!"
echo ""
echo "📝 Следующие шаги:"
echo "   1. Проверьте PUBLISH-CHECKLIST.md"
echo "   2. Выполните: git init"
echo "   3. Выполните: git add ."
echo "   4. Выполните: git commit -m '🎉 Первый релиз v1.0.0'"
echo "   5. Следуйте инструкциям в GITHUB-SETUP.md"
