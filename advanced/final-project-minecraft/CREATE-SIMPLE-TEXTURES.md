# 🎨 Создание простых текстур программно

Если у вас нет графического редактора, можно создать простые текстуры программно.

## 📝 Java код для создания текстур

Создайте класс `TextureGenerator.java`:

```java
package com.minecraft.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureGenerator {

    public static void main(String[] args) {
        try {
            // Создаём папку для текстур
            File texturesDir = new File("src/main/resources/textures");
            texturesDir.mkdirs();

            // Генерируем текстуры
            generateGrassTexture();
            generateDirtTexture();
            generateStoneTexture();
            generateWoodTexture();
            generateLeavesTexture();
            generateSandTexture();
            generateWaterTexture();
            generateBedrockTexture();

            // Создаём атлас
            generateAtlas();

            System.out.println("✅ Текстуры созданы успешно!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateGrassTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Зелёный цвет травы
        g.setColor(new Color(124, 189, 107));
        g.fillRect(0, 0, 16, 16);

        // Добавляем немного шума
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            g.setColor(new Color(100, 170, 90));
            g.fillRect(x, y, 1, 1);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/grass.png"));
    }

    private static void generateDirtTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Коричневый цвет земли
        g.setColor(new Color(139, 111, 71));
        g.fillRect(0, 0, 16, 16);

        // Добавляем текстуру
        for (int i = 0; i < 30; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            g.setColor(new Color(120, 95, 60));
            g.fillRect(x, y, 1, 1);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/dirt.png"));
    }

    private static void generateStoneTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Серый цвет камня
        g.setColor(new Color(127, 127, 127));
        g.fillRect(0, 0, 16, 16);

        // Добавляем текстуру камня
        for (int i = 0; i < 40; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            int shade = 100 + (int) (Math.random() * 55);
            g.setColor(new Color(shade, shade, shade));
            g.fillRect(x, y, 1, 1);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/stone.png"));
    }

    private static void generateWoodTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Коричневый цвет дерева
        g.setColor(new Color(139, 90, 43));
        g.fillRect(0, 0, 16, 16);

        // Вертикальные линии (текстура дерева)
        g.setColor(new Color(120, 75, 35));
        for (int x = 0; x < 16; x += 2) {
            g.drawLine(x, 0, x, 16);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/wood.png"));
    }

    private static void generateLeavesTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // Тёмно-зелёный цвет листвы
        g.setColor(new Color(34, 139, 34));
        g.fillRect(0, 0, 16, 16);

        // Добавляем вариации
        for (int i = 0; i < 25; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            g.setColor(new Color(20, 120, 20));
            g.fillRect(x, y, 1, 1);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/leaves.png"));
    }

    private static void generateSandTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Жёлтый цвет песка
        g.setColor(new Color(237, 201, 175));
        g.fillRect(0, 0, 16, 16);

        // Добавляем зернистость
        for (int i = 0; i < 35; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            g.setColor(new Color(220, 185, 160));
            g.fillRect(x, y, 1, 1);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/sand.png"));
    }

    private static void generateWaterTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();

        // Синий цвет воды с прозрачностью
        g.setColor(new Color(30, 144, 255, 180));
        g.fillRect(0, 0, 16, 16);

        // Добавляем волны
        g.setColor(new Color(50, 160, 255, 200));
        for (int y = 0; y < 16; y += 4) {
            g.drawLine(0, y, 16, y);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/water.png"));
    }

    private static void generateBedrockTexture() throws IOException {
        BufferedImage img = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        // Тёмно-серый цвет bedrock
        g.setColor(new Color(50, 50, 50));
        g.fillRect(0, 0, 16, 16);

        // Добавляем тёмные пятна
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 16);
            int y = (int) (Math.random() * 16);
            g.setColor(new Color(30, 30, 30));
            g.fillRect(x, y, 2, 2);
        }

        g.dispose();
        ImageIO.write(img, "PNG", new File("src/main/resources/textures/bedrock.png"));
    }

    private static void generateAtlas() throws IOException {
        // Создаём атлас 64x32 (4x2 текстуры)
        BufferedImage atlas = new BufferedImage(64, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = atlas.createGraphics();

        // Загружаем и размещаем текстуры
        String[] textures = {"grass", "dirt", "stone", "wood", "leaves", "sand", "water", "bedrock"};
        
        for (int i = 0; i < textures.length; i++) {
            try {
                BufferedImage tex = ImageIO.read(new File("src/main/resources/textures/" + textures[i] + ".png"));
                int x = (i % 4) * 16;
                int y = (i / 4) * 16;
                g.drawImage(tex, x, y, null);
            } catch (IOException e) {
                System.err.println("Не удалось загрузить: " + textures[i]);
            }
        }

        g.dispose();
        ImageIO.write(atlas, "PNG", new File("src/main/resources/textures/atlas.png"));
        System.out.println("✅ Атлас создан: atlas.png (64x32)");
    }
}
```

## 🚀 Как использовать

1. **Создайте файл** `TextureGenerator.java` в пакете `com.minecraft.util`

2. **Запустите** метод `main`:
   ```
   Правой кнопкой → Run 'TextureGenerator.main()'
   ```

3. **Проверьте результат** в `src/main/resources/textures/`:
   - grass.png
   - dirt.png
   - stone.png
   - wood.png
   - leaves.png
   - sand.png
   - water.png
   - bedrock.png
   - atlas.png (все текстуры в одном файле)

## 🎨 Результат

Вы получите:
- 8 простых текстур 16×16
- 1 атлас 64×32 со всеми текстурами
- Готовые к использованию в игре

## 💡 Улучшения

Можете улучшить текстуры:
- Добавить больше деталей
- Использовать градиенты
- Добавить паттерны
- Создать анимированные текстуры

## 📝 Альтернатива

Если не хотите писать код, используйте онлайн инструменты:
- **Piskel** (piskelapp.com) - рисуйте pixel art
- **Photopea** (photopea.com) - редактируйте как в Photoshop

---

**Готово! Теперь у вас есть простые текстуры для игры! 🎨**

