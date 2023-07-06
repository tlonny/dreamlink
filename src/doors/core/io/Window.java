package doors.core.io;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL42;
import org.lwjgl.system.MemoryUtil;

import doors.core.utility.vector.IVector2fl;
import doors.core.utility.vector.Vector2in;
import doors.core.utility.vector.Vector2fl;

public class Window {

    private static String TITLE = "Doors";

    public static Window WINDOW = new Window();

    public Vector2in dimensions;
    public long windowID;
    
    private Vector2fl cursorPosition;

    private double[] cursorXPositionBuffer;
    private double[] cursorYPositionBuffer;

    public Window() {
        this.dimensions = new Vector2in();
        this.cursorPosition = new Vector2fl();
        this.cursorXPositionBuffer = new double[1];
        this.cursorYPositionBuffer = new double[1];
    }

    public boolean isFocused() {
        return GLFW.glfwGetWindowAttrib(this.windowID, GLFW.GLFW_FOCUSED) == 1;
    }

    public void setShouldClose() {
        GLFW.glfwSetWindowShouldClose(this.windowID, true);
    }

    public void setCursorVisibility(boolean visibility) {
        GLFW.glfwSetInputMode(this.windowID, GLFW.GLFW_CURSOR, visibility ? GLFW.GLFW_CURSOR_NORMAL : GLFW.GLFW_CURSOR_HIDDEN);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.windowID);
    }

    public void setKeyCallback(GLFWKeyCallbackI callback) {
        GLFW.glfwSetKeyCallback(this.windowID, callback);
    }

    public void setCharCallback(GLFWCharCallbackI callback) {
        GLFW.glfwSetCharCallback(this.windowID, callback);
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallbackI callback) {
        GLFW.glfwSetMouseButtonCallback(this.windowID, callback);
    }

    public IVector2fl getCursorPosition() {
        return this.cursorPosition;
    }

    public void setCursorPosition(IVector2fl cursorPosition) {
        this.cursorPosition.set(cursorPosition);
        GLFW.glfwSetCursorPos(
            this.windowID,
            cursorPosition.getFloatX() * this.dimensions.x,
            cursorPosition.getFloatY() * this.dimensions.y
        );
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

        GL.createCapabilities();
        GL42.glClearColor(0f, 0f, 0f, 1f);
        GL42.glEnable(GL42.GL_BLEND);
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glBlendFunc(GL42.GL_SRC_ALPHA, GL42.GL_ONE_MINUS_SRC_ALPHA);
        GL42.glPixelStorei(GL42.GL_UNPACK_ALIGNMENT, 1);
        GLFW.glfwSetInputMode(this.windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
    }

    public void update() {
        GLFW.glfwSwapBuffers(this.windowID);
        GLFW.glfwPollEvents();
        GLFW.glfwGetCursorPos(this.windowID, this.cursorXPositionBuffer, this.cursorYPositionBuffer);
        this.cursorPosition.set(
            (float)this.cursorXPositionBuffer[0],
            (float)this.cursorYPositionBuffer[0]
        ).div(this.dimensions);
    }

    public void bind() {
        GL42.glBindFramebuffer(GL42.GL_FRAMEBUFFER, 0);
        GL42.glViewport(0, 0, this.dimensions.x, this.dimensions.y);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT );
    }


}
