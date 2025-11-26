# 🎨 Использование отдельных текстур (БЕЗ атласа)

## 📁 Структура файлов

Положите каждую текстуру отдельным файлом:

```
src/main/resources/textures/
├── grass.png       ← Трава
├── dirt.png        ← Земля
├── stone.png       ← Камень
├── wood.png        ← Дерево
├── leaves.png      ← Листва
├── sand.png        ← Песок
└── water.png       ← Вода
```

## ✅ Преимущества

- **Проще добавлять** - просто кидаете новый PNG файл
- **Не нужен атлас** - не надо объединять текстуры
- **Легче редактировать** - меняете один файл
- **Любые размеры** - можно 16×16, 32×32, 64×64

## 💻 Код для загрузки

### Вариант 1: Загрузка всех текстур

```java
package com.minecraft.render;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private Map<String, Texture> textures = new HashMap<>();
    
    public TextureManager() {
        // Загружаем все текстуры
        loadTexture("grass", "textures/grass.png");
        loadTexture("dirt", "textures/dirt.png");
        loadTexture("stone", "textures/stone.png");
        loadTexture("wood", "textures/wood.png");
        loadTexture("leaves", "textures/leaves.png");
        loadTexture("sand", "textures/sand.png");
        loadTexture("water", "textures/water.png");
        
        System.out.println("✅ Загружено текстур: " + textures.size());
    }
    
    private void loadTexture(String name, String path) {
        try {
            textures.put(name, new Texture(path));
        } catch (Exception e) {
            System.err.println("⚠️ Не удалось загрузить: " + name);
        }
    }
    
    public Texture getTexture(String name) {
        return textures.get(name);
    }
    
    public void bindTexture(String name) {
        Texture tex = textures.get(name);
        if (tex != null) {
            tex.bind();
        }
    }
    
    public void cleanup() {
        for (Texture tex : textures.values()) {
            tex.cleanup();
        }
    }
}
```

### Вариант 2: Простой способ

```java
// В Renderer.java
private Texture grassTexture;
private Texture dirtTexture;
private Texture stoneTexture;

public Renderer(Window window, Camera camera) {
    this.window = window;
    this.camera = camera;
    
    // Загружаем текстуры
    grassTexture = new Texture("textures/grass.png");
    dirtTexture = new Texture("textures/dirt.png");
    stoneTexture = new Texture("textures/stone.png");
    
    initShaders();
    initCube();
}
```

## 🎮 Использование в рендеринге

### С TextureManager (рекомендуется):

```java
public class Renderer {
    private TextureManager textureManager;
    
    public Renderer(Window window, Camera camera) {
        this.window = window;
        this.camera = camera;
        
        // Создаём менеджер текстур
        textureManager = new TextureManager();
        
        initShaders();
        initCube();
    }
    
    public void render(World world) {
        glUseProgram(shaderProgram);
        
        // Рендерим блоки по типам
        renderBlockType(world, BlockType.GRASS);
        renderBlockType(world, BlockType.DIRT);
        renderBlockType(world, BlockType.STONE);
    }
    
    private void renderBlockType(World world, BlockType type) {
        // Привязываем нужную текстуру
        switch (type) {
            case GRASS -> textureManager.bindTexture("grass");
            case DIRT -> textureManager.bindTexture("dirt");
            case STONE -> textureManager.bindTexture("stone");
            case WOOD -> textureManager.bindTexture("wood");
        }
        
        // Рендерим все блоки этого типа
        // ... код рендеринга ...
    }
}
```

### Простой способ:

```java
public void render(World world) {
    glUseProgram(shaderProgram);
    
    // Рендерим траву
    grassTexture.bind();
    // ... рендеринг кубов травы ...
    
    // Рендерим землю
    dirtTexture.bind();
    // ... рендеринг кубов земли ...
    
    // Рендерим камень
    stoneTexture.bind();
    // ... рендеринг кубов камня ...
}
```

## 📝 UV координаты для отдельных текстур

Для отдельных текстур UV координаты всегда одинаковые:

```java
// UV координаты для полной текстуры (0.0 - 1.0)
float[] uvs = {
    0.0f, 0.0f,  // Левый нижний
    1.0f, 0.0f,  // Правый нижний
    1.0f, 1.0f,  // Правый верхний
    0.0f, 1.0f   // Левый верхний
};
```

Для каждой грани куба:

```java
// Формат: позиция (3) + цвет (3) + UV (2)
float[] vertices = {
    // Передняя грань
    -0.5f, -0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 0.0f,
     0.5f, -0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  1.0f, 0.0f,
     0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  1.0f, 1.0f,
    -0.5f,  0.5f,  0.5f,  1.0f, 1.0f, 1.0f,  0.0f, 1.0f,
    
    // ... остальные грани ...
};
```

## 🚀 Быстрый старт

### 1. Создайте TextureManager.java

Скопируйте код выше в:
```
src/main/java/com/minecraft/render/TextureManager.java
```

### 2. Положите текстуры

Положите ваши PNG файлы в:
```
src/main/resources/textures/
```

Назовите их:
- grass.png
- dirt.png
- stone.png
- и т.д.

### 3. Используйте в Renderer

```java
private TextureManager textureManager;

public Renderer(Window window, Camera camera) {
    textureManager = new TextureManager();
    // ...
}
```

### 4. Запустите игру

Текстуры загрузятся автоматически!

## 📊 Сравнение с атласом

| Характеристика | Отдельные текстуры | Атлас |
|---------------|-------------------|-------|
| Простота добавления | ✅ Очень просто | ⚠️ Нужно пересобирать |
| Производительность | ⚠️ Медленнее | ✅ Быстрее |
| Редактирование | ✅ Легко | ⚠️ Сложнее |
| UV координаты | ✅ Всегда 0-1 | ⚠️ Нужно считать |
| Для обучения | ✅ Отлично | ⚠️ Сложнее |

## 💡 Рекомендация

**Для начала используйте отдельные текстуры!**

Когда будет много блоков (50+) и нужна оптимизация - переходите на атлас.

## 🎨 Размеры текстур

Все текстуры должны быть одного размера:
- **16×16** - классический Minecraft стиль ✅
- **32×32** - HD текстуры ✅
- **64×64** - очень детальные ✅

Можно использовать разные размеры, но лучше одинаковые.

## 🐛 Решение проблем

### Текстура не загружается
```
⚠️ Не удалось загрузить: grass
```

**Решение:**
1. Проверьте что файл `grass.png` существует
2. Проверьте путь: `src/main/resources/textures/grass.png`
3. Проверьте расширение (должно быть `.png`)

### Текстура размытая
**Решение:** В `Texture.java` используется `GL_NEAREST` - это правильно для pixel art

### Текстура перевёрнута
**Решение:** Добавьте в начало `Texture.java`:
```java
STBImage.stbi_set_flip_vertically_on_load(true);
```

---

**Готово! Теперь вы можете использовать отдельные текстуры без атласа! 🎨**

Просто кидайте PNG файлы в папку `textures/` и загружайте их по имени!
