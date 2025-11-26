package com.minecraft.core;

import com.minecraft.world.World;
import com.minecraft.entity.Player;
import com.minecraft.entity.Camera;
import com.minecraft.render.Renderer;
import static org.lwjgl.opengl.GL11.*;

/**
 * Главный игровой цикл
 */
public class GameLoop {

    private final Window window;
    private World world;
    private Player player;
    private Camera camera;
    private Renderer renderer;

    private static final int TARGET_FPS = 60;
    private static final int TARGET_UPS = 30;

    public GameLoop(Window window) {
        this.window = window;
    }

    public void start() {
        init();
        loop();
    }

    private void init() {
        System.out.println("Инициализация игры...");

        // Создание мира
        world = new World(12345L); // seed
        System.out.println("Мир создан");

        // Создание игрока
        player = new Player(0, 80, 0);
        System.out.println("Игрок создан");

        // Создание камеры
        camera = new Camera(player);
        System.out.println("Камера создана");

        // Создание рендерера
        renderer = new Renderer(window, camera);
        System.out.println("Рендерер создан");

        // Генерация начальных чанков
        world.generateInitialChunks(player.getX(), player.getZ());
        System.out.println("Начальные чанки сгенерированы");

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  Игра запущена!                       ║");
        System.out.println("║  WASD - движение                      ║");
        System.out.println("║  Space - прыжок                       ║");
        System.out.println("║  ESC - выход                          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    private void loop() {
        double lastTime = getTime();
        double deltaTime = 0;
        double accumulator = 0;

        int frames = 0;
        int updates = 0;
        double lastFpsTime = getTime();

        while (!window.shouldClose()) {
            double currentTime = getTime();
            deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            accumulator += deltaTime;

            // Обновление (фиксированный шаг)
            while (accumulator >= 1.0 / TARGET_UPS) {
                update(1.0f / TARGET_UPS);
                accumulator -= 1.0 / TARGET_UPS;
                updates++;
            }

            // Рендеринг
            render();
            frames++;

            // Обновление окна
            window.update();

            // FPS счётчик
            if (currentTime - lastFpsTime >= 1.0) {
                System.out.printf("FPS: %d | UPS: %d | Чанков: %d | Позиция: (%.1f, %.1f, %.1f) | Yaw: %.1f | Pitch: %.1f%n",
                        frames, updates, world.getLoadedChunksCount(),
                        player.getX(), player.getY(), player.getZ(),
                        player.getYaw(), player.getPitch());
                frames = 0;
                updates = 0;
                lastFpsTime = currentTime;
            }
        }
    }

    private void update(float delta) {
        // Обновление игрока
        player.update(delta, world, window);

        // Обновление камеры
        camera.update();

        // Обновление мира (загрузка/выгрузка чанков)
        world.update(player.getX(), player.getZ());
    }

    private void render() {
        // Очистка буферов
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        // Обновление viewport при изменении размера
        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            camera.updateProjection(window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        // Рендеринг мира
        renderer.render(world);
    }

    private double getTime() {
        return System.nanoTime() / 1_000_000_000.0;
    }
}
