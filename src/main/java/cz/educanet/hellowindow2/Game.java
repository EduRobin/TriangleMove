package cz.educanet.hellowindow2;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;
import java.nio.FloatBuffer;

public class Game {

    private static final float[] vertices = {
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };
    private static int vaoId;
    private static int vboId;


    public static void init(long window) {
        // setup shaders
        Shaders.initShaders();

        vaoId = GL33.glGenVertexArrays();
        vboId = GL33.glGenBuffers();

        GL33.glBindVertexArray(vaoId);
        {
            GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, vboId);
            {
                FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                        .put(vertices)
                        .flip();

                GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
                GL33.glVertexAttribPointer(0,3, GL33.GL_FLOAT, false, 0, 0);
                GL33.glEnableVertexAttribArray(0);

                MemoryUtil.memFree(fb);
            }
        }
    }

    public static void render(long window) {
        GL33.glUseProgram(Shaders.shaderId);
        GL33.glBindVertexArray(vaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertices.length);
    }

    public static void update(long window) {
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT) == GLFW.GLFW_PRESS) {
            vertices[0] += 0.001;
            vertices[3] += 0.001;
            vertices[6] += 0.001;
            vertices[9] += 0.001;
            vertices[12] += 0.001;
            vertices[15] += 0.001;


            FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                    .put(vertices)
                    .flip();

            GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);


        }


    }



}
