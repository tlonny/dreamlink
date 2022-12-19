package periwinkle.graphics;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.*;

import periwinkle.Game;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    private static Vector3f COLOR_WHITE = new Vector3f(1f, 1f, 1f);
    private static int POSITION_LOCATION = 0;
    private static int NORMAL_LOCATION = 1;
    private static int LIGHT_LOCATION = 2;
    private static int COLOR_LOCATION = 3;
    private static int TEXTURE_LOCATION = 4;

    private int vertexArrayID;
    private int indexVertexBufferID;
    private int positionVertexBufferID;
    private int normalVertexBufferID;
    private int lightVertexBufferID;
    private int colorVertexBufferID;
    private int textureVertexBufferID;
    private int numIndices;

    private final Atlas atlas;


    public Mesh(Atlas atlas) {
        this.atlas = atlas;
    }

    public void setup() {
        this.vertexArrayID = GL30.glGenVertexArrays();
        this.bindVAO();
        this.indexVertexBufferID = GL30.glGenBuffers();
        this.positionVertexBufferID = GL30.glGenBuffers();
        this.normalVertexBufferID = GL30.glGenBuffers();
        this.lightVertexBufferID = GL30.glGenBuffers();
        this.colorVertexBufferID = GL30.glGenBuffers();
        this.textureVertexBufferID = GL30.glGenBuffers();
    }

    private void bindVAO() {
        GL30.glBindVertexArray(this.vertexArrayID);
    }

    private void pushIndexData(IntBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.indexVertexBufferID);
        GL30.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
    }

    private void pushFloatData(int location, int size, int vertexBufferID, FloatBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        GL30.glVertexAttribPointer(location, size, GL11.GL_FLOAT, false, 0, 0);
        GL30.glEnableVertexAttribArray(location);
    }

    private void pushIntData(int location, int size, int vertexBufferID, IntBuffer buffer) {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
        GL30.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_DYNAMIC_DRAW);
        GL30.glVertexAttribIPointer(location, size, GL11.GL_INT, 0, 0);
        GL30.glEnableVertexAttribArray(location);
    }

    public void loadFromBuffer(MeshBuffer buffer) {
        this.bindVAO();
        this.pushIndexData(buffer.indexBuffer);
        this.pushFloatData(POSITION_LOCATION, 3, this.positionVertexBufferID, buffer.positionBuffer);
        this.pushFloatData(NORMAL_LOCATION, 3, this.normalVertexBufferID, buffer.normalBuffer);
        this.pushIntData(LIGHT_LOCATION, 1, this.lightVertexBufferID, buffer.lightBuffer);
        this.pushIntData(COLOR_LOCATION, 1, this.colorVertexBufferID, buffer.colorBuffer);
        this.pushFloatData(TEXTURE_LOCATION, 2, this.textureVertexBufferID, buffer.textureOffsetBuffer);
        this.numIndices = buffer.indexCount;
    }

    public void tearDown() {
        GL30.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glDeleteBuffers(this.indexVertexBufferID);
        GL30.glDeleteBuffers(this.positionVertexBufferID);
        GL30.glDeleteBuffers(this.lightVertexBufferID);
        GL30.glDeleteBuffers(this.colorVertexBufferID);
        GL30.glDeleteBuffers(this.textureVertexBufferID);
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(this.vertexArrayID);
    }

    private Matrix4f modelMatrix = new Matrix4f();
    public void render(Vector3f position, Vector3f globalColor, float globalLightDirectionFactor) {
        if(this.numIndices == 0)
            return;
        this.bindVAO();
        this.atlas.bind();
        this.modelMatrix.identity().translate(position);
        Game.SHADER.setModelMatrix(this.modelMatrix);
        Game.SHADER.setGlobalColor(globalColor);
        Game.SHADER.setGlobalLightDirectionFactor(globalLightDirectionFactor);
        GL30.glDrawElements(GL11.GL_TRIANGLES, this.numIndices, GL11.GL_UNSIGNED_INT, 0);
    }

    public void render(Vector3f position) {
        this.render(position, COLOR_WHITE, 0f);
    }

}
