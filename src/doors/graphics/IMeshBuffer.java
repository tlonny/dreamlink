package doors.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public interface IMeshBuffer {

    void flip();

    void clear();

    int getIndexCount();

    FloatBuffer getPositionBuffer();

    FloatBuffer getNormalBuffer();

    FloatBuffer getTextureOffsetBuffer();

    IntBuffer getIndexBuffer();
}
