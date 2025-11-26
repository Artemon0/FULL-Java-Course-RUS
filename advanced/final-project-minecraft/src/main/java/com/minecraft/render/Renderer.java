package com.minecraft.render;

import com.minecraft.core.Window;
import com.minecraft.entity.Camera;
import com.minecraft.world.World;
import com.minecraft.world.Chunk;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Рендерер
 */
public class Renderer {

    private final Window window;
    private final Camera camera;

    private int shaderProgram;
    private int vao;
    private int vbo;

    public Renderer(Window window, Camera camera) {
        this.window = window;
        this.camera = camera;
        initShaders();
        initTestCube();
    }

    private void initShaders() {
        // Простой вершинный шейдер
        String vertexShaderSource = """
            #version 330 core
            layout (location = 0) in vec3 aPos;
            layout (location = 1) in vec3 aColor;
            
            out vec3 vertexColor;
            
            uniform mat4 projection;
            uniform mat4 view;
            uniform mat4 model;
            
            void main() {
                gl_Position = projection * view * model * vec4(aPos, 1.0);
                vertexColor = aColor;
            }
            """;

        // Простой фрагментный шейдер
        String fragmentShaderSource = """
            #version 330 core
            in vec3 vertexColor;
            out vec4 FragColor;
            
            void main() {
                FragColor = vec4(vertexColor, 1.0);
            }
            """;

        // Компиляция вершинного шейдера
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexShaderSource);
        glCompileShader(vertexShader);
        checkShaderCompilation(vertexShader, "VERTEX");

        // Компиляция фрагментного шейдера
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentShaderSource);
        glCompileShader(fragmentShader);
        checkShaderCompilation(fragmentShader, "FRAGMENT");

        // Линковка программы
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);
        checkProgramLinking(shaderProgram);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        System.out.println("Шейдеры скомпилированы успешно");
    }

    private void initTestCube() {
        // Простой куб с цветами (позиция + цвет)
        float[] vertices = {
            // Позиции          // Цвета
            -0.5f, -0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            0.5f, -0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            0.5f, 0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            0.5f, 0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            -0.5f, 0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            -0.5f, -0.5f, -0.5f, 0.8f, 0.5f, 0.2f,
            -0.5f, -0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            0.5f, -0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            -0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            -0.5f, -0.5f, 0.5f, 0.2f, 0.8f, 0.2f,
            -0.5f, 0.5f, 0.5f, 0.2f, 0.2f, 0.8f,
            -0.5f, 0.5f, -0.5f, 0.2f, 0.2f, 0.8f,
            -0.5f, -0.5f, -0.5f, 0.2f, 0.2f, 0.8f,
            -0.5f, -0.5f, -0.5f, 0.2f, 0.2f, 0.8f,
            -0.5f, -0.5f, 0.5f, 0.2f, 0.2f, 0.8f,
            -0.5f, 0.5f, 0.5f, 0.2f, 0.2f, 0.8f,
            0.5f, 0.5f, 0.5f, 0.8f, 0.8f, 0.2f,
            0.5f, 0.5f, -0.5f, 0.8f, 0.8f, 0.2f,
            0.5f, -0.5f, -0.5f, 0.8f, 0.8f, 0.2f,
            0.5f, -0.5f, -0.5f, 0.8f, 0.8f, 0.2f,
            0.5f, -0.5f, 0.5f, 0.8f, 0.8f, 0.2f,
            0.5f, 0.5f, 0.5f, 0.8f, 0.8f, 0.2f,
            -0.5f, -0.5f, -0.5f, 0.8f, 0.2f, 0.8f,
            0.5f, -0.5f, -0.5f, 0.8f, 0.2f, 0.8f,
            0.5f, -0.5f, 0.5f, 0.8f, 0.2f, 0.8f,
            0.5f, -0.5f, 0.5f, 0.8f, 0.2f, 0.8f,
            -0.5f, -0.5f, 0.5f, 0.8f, 0.2f, 0.8f,
            -0.5f, -0.5f, -0.5f, 0.8f, 0.2f, 0.8f,
            -0.5f, 0.5f, -0.5f, 0.2f, 0.8f, 0.8f,
            0.5f, 0.5f, -0.5f, 0.2f, 0.8f, 0.8f,
            0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.8f,
            0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.8f,
            -0.5f, 0.5f, 0.5f, 0.2f, 0.8f, 0.8f,
            -0.5f, 0.5f, -0.5f, 0.2f, 0.8f, 0.8f
        };

        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        // Позиция
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // Цвет
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);

        System.out.println("Тестовый куб создан");
    }

    public void render(World world) {
        glUseProgram(shaderProgram);

        // Получаем матрицы из камеры
        Matrix4f projection = camera.getProjectionMatrix();
        Matrix4f view = camera.getViewMatrix();

        // Рисуем несколько кубов для теста на разных позициях
        // Игрок стоит на y=65, глаза на y=66.6
        // Куб прямо перед игроком (на уровне глаз)
        Matrix4f model1 = new Matrix4f().identity().translate(0, 66, -5);
        setUniformMatrix4f("projection", projection);
        setUniformMatrix4f("view", view);
        setUniformMatrix4f("model", model1);
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        // Куб справа
        Matrix4f model2 = new Matrix4f().identity().translate(3, 66, -5);
        setUniformMatrix4f("model", model2);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        // Куб слева
        Matrix4f model3 = new Matrix4f().identity().translate(-3, 66, -5);
        setUniformMatrix4f("model", model3);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        // Куб сверху
        Matrix4f model4 = new Matrix4f().identity().translate(0, 69, -5);
        setUniformMatrix4f("model", model4);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        // Куб на земле
        Matrix4f model5 = new Matrix4f().identity().translate(0, 65, -5);
        setUniformMatrix4f("model", model5);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        glBindVertexArray(0);
    }

    private void setUniformMatrix4f(String name, Matrix4f matrix) {
        int location = glGetUniformLocation(shaderProgram, name);
        float[] buffer = new float[16];
        matrix.get(buffer);
        glUniformMatrix4fv(location, false, buffer);
    }

    private void checkShaderCompilation(int shader, String type) {
        int success = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            String log = glGetShaderInfoLog(shader);
            System.err.println("Ошибка компиляции " + type + " шейдера:\n" + log);
        }
    }

    private void checkProgramLinking(int program) {
        int success = glGetProgrami(program, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            String log = glGetProgramInfoLog(program);
            System.err.println("Ошибка линковки программы:\n" + log);
        }
    }
}
