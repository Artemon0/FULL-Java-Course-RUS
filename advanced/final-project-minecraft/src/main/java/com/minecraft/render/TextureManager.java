package com.minecraft.render;

import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер текстур для загрузки и управления отдельными текстурами (БЕЗ атласа
 * - каждая текстура в отдельном файле)
 */
public class TextureManager {

    private Map<String, Texture> textures = new HashMap<>();

    public TextureManager() {
        System.out.println("Загрузка текстур...");

        // Загружаем все текстуры блоков
        loadTexture("grass", "textures/grass.png");
        loadTexture("dirt", "textures/dirt.png");
        loadTexture("stone", "textures/stone.png");
        loadTexture("wood", "textures/wood.png");
        loadTexture("leaves", "textures/leaves.png");
        loadTexture("sand", "textures/sand.png");
        loadTexture("water", "textures/water.png");
        loadTexture("bedrock", "textures/bedrock.png");

        if (textures.isEmpty()) {
            System.err.println("⚠️ Ни одна текстура не загружена!");
            System.err.println("   Положите PNG файлы в: src/main/resources/textures/");
        } else {
            System.out.println("✅ Загружено текстур: " + textures.size());
            System.out.println("   Доступные: " + String.join(", ", textures.keySet()));
        }
    }

    /**
     * Загрузить текстуру из файла
     */
    private void loadTexture(String name, String path) {
        try {
            Texture texture = new Texture(path);
            textures.put(name, texture);
        } catch (Exception e) {
            System.err.println("⚠️ Не удалось загрузить текстуру: " + name);
            System.err.println("   Путь: " + path);
            System.err.println("   Ошибка: " + e.getMessage());

            // Создаём заглушку (белая текстура)
            // textures.put(name, createDefaultTexture());
        }
    }

    /**
     * Получить текстуру по имени
     */
    public Texture getTexture(String name) {
        Texture texture = textures.get(name);
        if (texture == null) {
            System.err.println("⚠️ Текстура не найдена: " + name);
            // Возвращаем первую доступную текстуру как fallback
            if (!textures.isEmpty()) {
                return textures.values().iterator().next();
            }
            return null;
        }
        return texture;
    }

    /**
     * Привязать текстуру по имени
     */
    public void bindTexture(String name) {
        Texture texture = getTexture(name);
        if (texture != null) {
            texture.bind();
        }
    }

    /**
     * Отвязать текущую текстуру
     */
    public void unbind() {
        Texture.unbind();
    }

    /**
     * Проверить существование текстуры
     */
    public boolean hasTexture(String name) {
        return textures.containsKey(name);
    }

    /**
     * Получить количество загруженных текстур
     */
    public int getTextureCount() {
        return textures.size();
    }

    /**
     * Очистить все текстуры
     */
    public void cleanup() {
        System.out.println("Очистка текстур...");
        for (Texture texture : textures.values()) {
            texture.cleanup();
        }
        textures.clear();
    }
}
