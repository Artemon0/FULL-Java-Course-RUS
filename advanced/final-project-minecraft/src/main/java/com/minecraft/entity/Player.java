package com.minecraft.entity;

import com.minecraft.world.World;
import com.minecraft.core.Window;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Игрок
 */
public class Player {

    private float x, y, z;
    private float velocityY;
    private boolean onGround;

    // Углы поворота камеры
    private float yaw = 0;   // Поворот влево-вправо
    private float pitch = 0; // Поворот вверх-вниз

    private double lastMouseX = 0;
    private double lastMouseY = 0;
    private boolean firstMouse = true;

    private static final float MOUSE_SENSITIVITY = 0.1f;

    private static final float MOVE_SPEED = 4.3f;
    private static final float JUMP_FORCE = 8.0f;
    private static final float GRAVITY = -20.0f;

    public Player(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.velocityY = 0;
        this.onGround = false;
    }

    public void update(float delta, World world, Window window) {
        // Обработка мыши
        updateMouseInput(window);

        // Применение гравитации
        velocityY += GRAVITY * delta;
        y += velocityY * delta;

        // Простая проверка земли
        if (y < 65) {
            y = 65;
            velocityY = 0;
            onGround = true;
        } else {
            onGround = false;
        }

        // Управление (упрощённое, без Input класса)
        long windowHandle = window.getHandle();
        float moveX = 0;
        float moveZ = 0;

        if (glfwGetKey(windowHandle, GLFW_KEY_W) == GLFW_PRESS) {
            moveZ -= 1;
        }
        if (glfwGetKey(windowHandle, GLFW_KEY_S) == GLFW_PRESS) {
            moveZ += 1;
        }
        if (glfwGetKey(windowHandle, GLFW_KEY_A) == GLFW_PRESS) {
            moveX -= 1;
        }
        if (glfwGetKey(windowHandle, GLFW_KEY_D) == GLFW_PRESS) {
            moveX += 1;
        }

        // Нормализация и применение поворота к движению
        float length = (float) Math.sqrt(moveX * moveX + moveZ * moveZ);
        if (length > 0) {
            moveX /= length;
            moveZ /= length;

            // Применяем поворот камеры к направлению движения
            float yawRad = (float) Math.toRadians(yaw);
            float cos = (float) Math.cos(yawRad);
            float sin = (float) Math.sin(yawRad);

            float newMoveX = moveX * cos - moveZ * sin;
            float newMoveZ = moveX * sin + moveZ * cos;

            x += newMoveX * MOVE_SPEED * delta;
            z += newMoveZ * MOVE_SPEED * delta;
        }

        // Прыжок
        if (glfwGetKey(windowHandle, GLFW_KEY_SPACE) == GLFW_PRESS && onGround) {
            velocityY = JUMP_FORCE;
        }
    }

    private void updateMouseInput(Window window) {
        long windowHandle = window.getHandle();

        double[] xpos = new double[1];
        double[] ypos = new double[1];
        glfwGetCursorPos(windowHandle, xpos, ypos);

        if (firstMouse) {
            lastMouseX = xpos[0];
            lastMouseY = ypos[0];
            firstMouse = false;
        }

        double deltaX = xpos[0] - lastMouseX;
        double deltaY = ypos[0] - lastMouseY; // Без инверсии

        lastMouseX = xpos[0];
        lastMouseY = ypos[0];

        yaw += (float) deltaX * MOUSE_SENSITIVITY;
        pitch += (float) deltaY * MOUSE_SENSITIVITY;

        // Ограничение pitch
        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Vector3f getPosition() {
        return new Vector3f(x, y, z);
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
