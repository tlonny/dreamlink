package periwinkle.graphics;

import java.nio.IntBuffer;

import periwinkle.environment.Sky;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class Display {

    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    public static String TITLE = "Aberrance";

    public static Display DISPLAY = new Display();
    public static void init() {
        DISPLAY.setup();
    }

    private long windowID;
    private final boolean[] keys = new boolean[500];
    private final double[] xBuffer = new double[1];
    private final double[] yBuffer = new double[2];

    public float screenR;
    public float screenG;
    public float screenB;

    public void setKeyCallback(GLFWKeyCallbackI callback) {
        GLFW.glfwSetKeyCallback(this.windowID, callback);
    }

    public void setMouseButtonCallback(GLFWMouseButtonCallbackI callback) {
        GLFW.glfwSetMouseButtonCallback(this.windowID, callback);
    }

    public void setCharCallback(GLFWCharCallbackI callback) {
        GLFW.glfwSetCharCallback(this.windowID, callback);
    }

    public void setShouldClose() {
        GLFW.glfwSetWindowShouldClose( this.windowID, true );
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose( this.windowID);
    }

    public void setup() {
        GLFWErrorCallback.createPrint(System.err).set();
        GLFW.glfwInit();
        GLFW.glfwWindowHint( GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE );
        GLFW.glfwWindowHint( GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE );

        this.windowID = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, MemoryUtil.NULL, MemoryUtil.NULL );

        try ( MemoryStack stack = MemoryStack.stackPush() ) {
            IntBuffer width  = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            GLFW.glfwGetWindowSize( this.windowID, width, height);
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode( GLFW.glfwGetPrimaryMonitor() );
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
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        //GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        //GL11.glEnable(GL30.GL_FRAMEBUFFER_SRGB);

    }

    public float getMouseX() {
        return (float)this.xBuffer[0];
    }

    public float getMouseY() {
        return (float)this.yBuffer[0];
    }

    public float getDeltaMouseX() {
        return this.getMouseX() - WIDTH/2f;
    }

    public float getDeltaMouseY() {
        return this.getMouseY() - HEIGHT/2f;
    }

    public void refresh() {
        GLFW.glfwSwapBuffers(this.windowID);
        GLFW.glfwPollEvents();
        var colors = Sky.SKY.skyType.skyColor;
        GL15.glClearColor(colors.x, colors.y, colors.z, 1f);
        GL15.glClear( GL15.GL_COLOR_BUFFER_BIT | GL15.GL_DEPTH_BUFFER_BIT );

        GLFW.glfwGetCursorPos( this.windowID, this.xBuffer, this.yBuffer );
        GLFW.glfwSetCursorPos(this.windowID, WIDTH / 2f, HEIGHT / 2f);
    }


}
