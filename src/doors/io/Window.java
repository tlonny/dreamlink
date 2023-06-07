package doors.io;

import org.joml.Vector2i;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

public class Window {

    private static String TITLE = "Doors";

    public static Window WINDOW = new Window();

    public long windowID;
    public Vector2i dimensions;

    public Window() {
        this.dimensions = new Vector2i();
    }

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
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
        var primaryMonitorID = GLFW.glfwGetPrimaryMonitor();
        var vidMode = GLFW.glfwGetVideoMode(primaryMonitorID);
        this.dimensions.set(vidMode.width(), vidMode.height());
        this.windowID = GLFW.glfwCreateWindow(this.dimensions.x, this.dimensions.y, TITLE, primaryMonitorID, MemoryUtil.NULL);

        GLFW.glfwMakeContextCurrent(this.windowID);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(this.windowID);
        GLFW.glfwSetInputMode(this.windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);

        GL.createCapabilities();
        GL42.glClearColor(0f, 0f, 0f, 0f);
        GL42.glEnable(GL42.GL_CULL_FACE);
        GL42.glEnable(GL42.GL_BLEND);
        GL42.glBlendFunc(GL42.GL_SRC_ALPHA, GL42.GL_ONE_MINUS_SRC_ALPHA);
        GL42.glPixelStorei(GL42.GL_UNPACK_ALIGNMENT, 1);
    }

    public void refresh() {
        GLFW.glfwSwapBuffers(this.windowID);
        GLFW.glfwPollEvents();
    }

    public void bind() {
        GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, 0);
        GL42.glViewport(0, 0, this.dimensions.x, this.dimensions.y);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT );
    }


}
