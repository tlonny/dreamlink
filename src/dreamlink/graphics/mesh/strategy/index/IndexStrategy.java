package dreamlink.graphics.mesh.strategy.index;

public class IndexStrategy {

    private static final int[] quadIndices = new int[] { 
        0, 1, 3, 3, 1, 2 
    };

    private final IMeshIndexQuadStrategyProvider provider;

    public IndexStrategy(IMeshIndexQuadStrategyProvider provider) {
        this.provider = provider;
    }

    public void addIndices() {
        var numQuads = this.provider.getIndexCount() / 6;
        var numVertices = numQuads * 4;
        for(var index : quadIndices) {
            this.provider.addIndex(numVertices + index);
        }
    }
    
}
