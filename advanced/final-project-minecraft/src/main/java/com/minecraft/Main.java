package com.minecraft;

import com.minecraft.core.Window;
import com.minecraft.core.GameLoop;

/**
 * Главный класс игры Minecraft Clone
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                            ║");
        System.out.println("║              MINECRAFT CLONE - 3D VOXEL GAME               ║");
        System.out.println("║              Финальный проект курса Java                   ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Инициализация...");

        try {
            // Создание окна
            Window window = new Window(1280, 720, "Minecraft Clone");
            window.init();

            // Создание игрового цикла
            GameLoop gameLoop = new GameLoop(window);

            // Запуск игры
            gameLoop.start();

            // Очистка ресурсов
            window.cleanup();

        } catch (Exception e) {
            System.err.println("Ошибка при запуске игры:");
            e.printStackTrace();
        }
    }
}
