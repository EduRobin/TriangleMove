package cz.educanet.hellowindow2;


import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

public class Main {

    public static void main(String[] args) throws Exception {

        GLFW.glfwInit();

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3); //openGL version 3.3
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);

        long window = GLFW.glfwCreateWindow(800, 600, "My first window", 0, 0);

        if (window == 0) {
            GLFW.glfwTerminate();
            throw new Exception("Dej si radši párek :-)");
        }
        GLFW.glfwMakeContextCurrent(window);


        GL.createCapabilities();
        GL33.glViewport(0, 0, 800, 600);


        Game.init(window);
        while (!GLFW.glfwWindowShouldClose(window)) { // Main loop of the program


            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }


            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
            GL33.glClearColor(1, 0, 1, 100000);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);


            Game.render(window);
            Game.update(window);

        }

        GLFW.glfwTerminate();
    }


}
