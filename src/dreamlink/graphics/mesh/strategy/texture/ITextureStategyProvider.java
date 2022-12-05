package dreamlink.graphics.mesh.strategy.texture;

public interface ITextureStategyProvider {

    public void addTextureOffsets(
        float x, 
        float y, 
        float strideX, 
        float strideY
    );

    public void addMetadata(int metadata);

}
