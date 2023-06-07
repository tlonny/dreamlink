package doors.graphics;

public class CoreShader extends ShaderProgram {

    public static CoreShader CORE_SHADER = new CoreShader();

    public void setup() {
        super.setup("src/glsl/core");
    }

}
