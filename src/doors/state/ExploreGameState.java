package doors.state;

import org.lwjgl.opengl.GL42;

import doors.Camera;
import doors.graphics.rendertarget.PhysicalRenderTarget;
import doors.level.Terrain;
import doors.level.TerrainCache;
import doors.perspective.WorldPerspective;

public class ExploreGameState extends GameState {

    public static ExploreGameState EXPLORE_GAME_STATE = new ExploreGameState();

    public Terrain terrain;

    public void use(String level) {
        super.use();
        this.terrain = TerrainCache.TERRAIN_CACHE.getTerrain(level);
        this.terrain.setup();
    }

    @Override
    public void update() {
        Camera.CAMERA.update();

        PhysicalRenderTarget.PHYSICAL_RENDER_TARGET.useRenderTarget();
        GL42.glEnable(GL42.GL_DEPTH_TEST);
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);

        WorldPerspective.WORLD_PERSPECTIVE.alignToCamera();
        WorldPerspective.WORLD_PERSPECTIVE.apply();

        this.terrain.render();
    }

}
