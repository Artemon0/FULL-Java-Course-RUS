package com.minecraft.core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Класс окна игры
 */
public class Window {

    private final int width;
    private final int height;
    private final String title;

    private long windowHandle;
    private boolean resized;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.resized = false;
    }

    public void init() {
        // Инициализация GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Не удалось инициализировать GLFW");
        }

        // Настройка окна
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        // Создание окна
        windowHandle = glfwCreateWindow(width, height, title, NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("Не удалось создать окно GLFW");
        }

        // Callback для изменения размера
        glfwSetFramebufferSizeCallback(windowHandle, (window, w, h) -> {
            this.resized = true;
        });

        // Callback для клавиатуры
        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        // Центрирование окна
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                windowHandle,
                (vidMode.width() - width) / 2,
                (vidMode.height() - height) / 2
        );

        // Создание OpenGL контекста
        glfwMakeContextCurrent(windowHandle);

        // Включение V-Sync
        glfwSwapInterval(1);

        // Показать окно
        glfwShowWindow(windowHandle);

        // ЗАХВАТ КУРСОРА - это важно для FPS игры!
        glfwSetInputMode(windowHandle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        // Включить raw mouse motion если доступно (более точное управление)
        if (glfwRawMouseMotionSupported()) {
            glfwSetInputMode(windowHandle, GLFW_RAW_MOUSE_MOTION, GLFW_TRUE);
        }

        // Инициализация OpenGL
        GL.createCapabilities();

        // Настройка OpenGL
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        // Цвет очистки (небо)
        glClearColor(0.53f, 0.81f, 0.92f, 1.0f);

        System.out.println("Окно создано: " + width + "x" + height);
        System.out.println("OpenGL версия: " + glGetString(GL_VERSION));
        System.out.println("Курсор захвачен - используйте мышь для осмотра");
    }

    public void update() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(windowHandle);
    }

    public boolean isResized() {
        return resized;
    }

    public void setResized(boolean resized) {
        this.resized = resized;
    }

    public int getWidth() {
        int[] w = new int[1];
        int[] h = new int[1];
        glfwGetFramebufferSize(windowHandle, w, h);
        return w[0];
    }

    public int getHeight() {
        int[] w = new int[1];
        int[] h = new int[1];
        glfwGetFramebufferSize(windowHandle, w, h);
        return h[0];
    }

    public long getHandle() {
        return windowHandle;
    }

    public void cleanup() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
    }
}
