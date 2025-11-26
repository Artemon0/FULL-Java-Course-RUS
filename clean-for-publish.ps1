# Скрипт для очистки проекта перед публикацией на GitHub

Write-Host "🧹 Очистка проекта для публикации..." -ForegroundColor Cyan

# Удаление скомпилированных файлов
Write-Host "`n📦 Удаление скомпилированных файлов..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include *.class -Recurse -Force | Remove-Item -Force
Write-Host "✅ *.class файлы удалены" -ForegroundColor Green

# Удаление target папок
Write-Host "`n📦 Удаление target/ папок..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include target -Recurse -Directory -Force | Remove-Item -Recurse -Force
Write-Host "✅ target/ папки удалены" -ForegroundColor Green

# Удаление .idea папок (IntelliJ IDEA)
Write-Host "`n📦 Удаление .idea/ папок..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include .idea -Recurse -Directory -Force | Remove-Item -Recurse -Force
Write-Host "✅ .idea/ папки удалены" -ForegroundColor Green

# Удаление *.iml файлов
Write-Host "`n📦 Удаление *.iml файлов..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include *.iml -Recurse -Force | Remove-Item -Force
Write-Host "✅ *.iml файлы удалены" -ForegroundColor Green

# Удаление .vscode папок
Write-Host "`n📦 Удаление .vscode/ папок..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include .vscode -Recurse -Directory -Force | Remove-Item -Recurse -Force
Write-Host "✅ .vscode/ папки удалены" -ForegroundColor Green

# Удаление временных файлов
Write-Host "`n📦 Удаление временных файлов..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include *.swp,*.swo,*~ -Recurse -Force | Remove-Item -Force
Write-Host "✅ Временные файлы удалены" -ForegroundColor Green

# Удаление .DS_Store (macOS)
Write-Host "`n📦 Удаление .DS_Store файлов..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include .DS_Store -Recurse -Force | Remove-Item -Force
Write-Host "✅ .DS_Store файлы удалены" -ForegroundColor Green

# Удаление Thumbs.db (Windows)
Write-Host "`n📦 Удаление Thumbs.db файлов..." -ForegroundColor Yellow
Get-ChildItem -Path . -Include Thumbs.db -Recurse -Force | Remove-Item -Force
Write-Host "✅ Thumbs.db файлы удалены" -ForegroundColor Green

# Статистика
Write-Host "`n📊 Статистика проекта:" -ForegroundColor Cyan
$javaFiles = (Get-ChildItem -Path . -Include *.java -Recurse -File).Count
$mdFiles = (Get-ChildItem -Path . -Include *.md -Recurse -File).Count
$totalFiles = (Get-ChildItem -Path . -Recurse -File).Count

Write-Host "   Java файлов: $javaFiles" -ForegroundColor White
Write-Host "   Markdown файлов: $mdFiles" -ForegroundColor White
Write-Host "   Всего файлов: $totalFiles" -ForegroundColor White

Write-Host "`n✅ Проект очищен и готов к публикации!" -ForegroundColor Green
Write-Host "`n📝 Следующие шаги:" -ForegroundColor Cyan
Write-Host "   1. Проверьте PUBLISH-CHECKLIST.md" -ForegroundColor White
Write-Host "   2. Выполните: git init" -ForegroundColor White
Write-Host "   3. Выполните: git add ." -ForegroundColor White
Write-Host "   4. Выполните: git commit -m '🎉 Первый релиз v1.0.0'" -ForegroundColor White
Write-Host "   5. Следуйте инструкциям в GITHUB-SETUP.md" -ForegroundColor White
