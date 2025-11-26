package com.minecraft.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBImage;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Класс для загрузки и управления текстурами
 */
public class Texture {

    private int id;
    private int width;
    private int height;

    public Texture(String path) {
        // Буферы для размеров
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        // Загрузка изображения через STB
        // Путь относительно корня проекта
        String fullPath = "src/main/resources/" + path;

        // Проверяем существование файла
        File file = new File(fullPath);
        if (!file.exists()) {
            throw new RuntimeException("Текстура не найдена: " + fullPath);
        }

        ByteBuffer image = STBImage.stbi_load(fullPath, w, h, channels, 4);

        if (image == null) {
            String error = STBImage.stbi_failure_reason();
            throw new RuntimeException("Не удалось загрузить текстуру: " + path + "\nОшибка: " + error);
        }

        width = w.get(0);
        height = h.get(0);

        // Создание OpenGL текстуры
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);

        // Загрузка данных в OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height,
                0, GL_RGBA, GL_UNSIGNED_BYTE, image);

        // Настройки фильтрации (NEAREST для pixel art стиля)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Настройки повтора текстуры
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        // Освобождение памяти
        STBImage.stbi_image_free(image);

        System.out.println("✅ Текстура загружена: " + path);
        System.out.println("   Размер: " + width + "×" + height + " пикселей");
        System.out.println("   OpenGL ID: " + id);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public static void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void cleanup() {
        glDeleteTextures(id);
    }
}
