package periwinkle.io;

import org.joml.Vector2i;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class Display {

    private static String TITLE = "Periwinkle";

    public long windowID;
    public Vector2i dimensions = new Vector2i(2560, 1440);

    public boolean isFocused() {
        return GLFW.glfwGetWindowAttrib(this.windowID, GLFW.GLFW_FOCUSED) == 1;
    }

    public void setShouldClose() {
        GLFW.glfwSetWindowShouldClose(this.windowID, true);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.windowID);
    }

    public void setup() {
        GLFWErrorCallback.createPrint(System.err).set();
        GLFW.glfwInit();

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        this.windowID = GLFW.glfwCreateWindow(this.dimensions.x, this.dimensions.y, TITLE, MemoryUtil.NULL, MemoryUtil.NULL);

        try (var stack = MemoryStack.stackPush() ) {
            var width  = stack.mallocInt(1);
            var height = stack.mallocInt(1);
            GLFW.glfwGetWindowSize( this.windowID, width, height);
            var vidMode = GLFW.glfwGetVideoMode( GLFW.glfwGetPrimaryMonitor() );
            if(vidMode == null)
                throw new RuntimeException("Unable to construct display");
            GLFW.glfwSetWindowPos(
                this.windowID,
                (vidMode.width() - width.get(0) )/2,
                (vidMode.height() - height.get(0) )/2
            );
        }

        GLFW.glfwMakeContextCurrent(this.windowID);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(this.windowID);
        GLFW.glfwSetInputMode(this.windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
        GL.createCapabilities();

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void refresh() {
        GLFW.glfwSwapBuffers(this.windowID);
        GLFW.glfwPollEvents();
        GL15.glClearColor(0f, 0f, 0f, 1f);
        GL15.glClear( GL15.GL_COLOR_BUFFER_BIT | GL15.GL_DEPTH_BUFFER_BIT );
    }


}
