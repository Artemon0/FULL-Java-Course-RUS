package com.minecraft.entity;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Камера
 */
public class Camera {

    private final Player player;

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;

    private float fov = (float) Math.toRadians(70.0f);
    private float nearPlane = 0.1f;
    private float farPlane = 1000.0f;

    public Camera(Player player) {
        this.player = player;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        updateProjection(1280, 720); // Начальный размер
    }

    public void update() {
        // Обновляем view матрицу на основе позиции и поворота игрока
        viewMatrix.identity();

        // ВАЖНО: Сначала поворот, потом трансляция (в обратном порядке для view матрицы)
        viewMatrix.rotateX((float) Math.toRadians(player.getPitch()));
        viewMatrix.rotateY((float) Math.toRadians(player.getYaw()));

        // Позиция камеры (инвертированная позиция игрока + высота глаз)
        Vector3f playerPos = player.getPosition();
        viewMatrix.translate(-playerPos.x, -(playerPos.y + 1.6f), -playerPos.z); // +1.6f это высота глаз
    }

    public void updateProjection(int width, int height) {
        float aspectRatio = (float) width / (float) height;
        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspectRatio, nearPlane, farPlane);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }
}
