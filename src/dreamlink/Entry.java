package dreamlink;

import org.lwjgl.opengl.GL42;

import dreamlink.audio.SoundMixer;
import dreamlink.config.RepositoryConfig;
import dreamlink.config.SoundMixerConfig;
import dreamlink.config.UserSettingsConfig;
import dreamlink.config.WindowConfig;
import dreamlink.config.WorkerPoolConfig;
import dreamlink.config.RoomCacheConfig;
import dreamlink.disk.LocalRoomState;
import dreamlink.disk.PackState;
import dreamlink.graphics.FrameBuffer;
import dreamlink.graphics.glconfig.DepthTestConfig;
import dreamlink.graphics.glconfig.FrameBufferConfig;
import dreamlink.graphics.glconfig.MeshConfig;
import dreamlink.graphics.glconfig.PolygonOffsetConfig;
import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.glconfig.blend.BlendModeConfig;
import dreamlink.graphics.glconfig.cullface.CullFaceConfig;
import dreamlink.graphics.program.ShaderProgramColor;
import dreamlink.graphics.program.SpriteShaderProgram;
import dreamlink.graphics.program.TerrainShaderProgram;
import dreamlink.graphics.sprite.SpriteHeight;
import dreamlink.graphics.texture.EntityTexture;
import dreamlink.graphics.texture.MenuTexture;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.overlay.PublishOverlay;
import dreamlink.overlay.ReticuleOverlay;
import dreamlink.overlay.LogoOverlay;
import dreamlink.overlay.RoomBannerOverlay;
import dreamlink.overlay.RoomLoadOverlay;
import dreamlink.overlay.home.HomeOverlay;
import dreamlink.overlay.simulation.SimulationExploreMenuOverlay;
import dreamlink.overlay.simulation.edit.SimulationEditMenuOverlay;
import dreamlink.overlay.simulation.quickbar.QuickBarOverlay;
import dreamlink.state.SimulationState;
import dreamlink.state.SynchronizedStateManager;
import dreamlink.state.scene.LogoScene;
import dreamlink.state.scene.SceneManager;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Orientation;
import dreamlink.window.Window;
import dreamlink.window.button.Button;
import dreamlink.worker.WorkerPool;
import dreamlink.world.RayCastCursorMesh;
import dreamlink.world.World;
import dreamlink.world.room.module.block.BlockLight;
import dreamlink.world.room.module.color.ColorConfig;
import dreamlink.world.room.module.door.DoorSegment;
import dreamlink.world.room.module.door.PortalMesh;
import dreamlink.world.room.module.terrain.TerrainLight;

public class Entry {

    public static final long simulationStep = 10;
    public static final float updateFactor = Entry.simulationStep / 1000f;

    public static Entry instance = new Entry();

    private long lastUpdateTime;

    public void setup() {
        RepositoryConfig.instance.load();
        SoundMixerConfig.instance.load();
        UserSettingsConfig.instance.load();
        WorkerPoolConfig.instance.load();
        RoomCacheConfig.instance.load();
        WindowConfig.instance.load();

        // Setup worker pool
        WorkerPool.instance.setup();

        // Perform enum indexing
        CubeFace.setup();
        Orientation.setup();
        TerrainLight.setup();
        TextureUnit.setup();
        SpriteHeight.setup();
        ShaderProgramColor.setup();
        BlockLight.setup();
        ColorConfig.setup();
        DoorSegment.setup();

        // Setup core window object
        Window.instance.setup();

        // Setup GL state defaults
        CullFaceConfig.setup();
        BlendModeConfig.setup();
        DepthTestConfig.setup();
        FrameBufferConfig.setup();
        MeshConfig.setup();
        PolygonOffsetConfig.setup();
        ShaderProgramConfig.setup();
        TextureConfig.setup();

        // Setup shader programs
        TerrainShaderProgram.instance.setup();
        SpriteShaderProgram.instance.setup();

        // Setup system textures and framebuffers
        MenuTexture.instance.setup();
        EntityTexture.instance.setup();
        FrameBuffer.portal.setup();

        // Setup shared/common meshes
        PortalMesh.instance.setup();
        RayCastCursorMesh.instance.setup();

        // Setup sound mixer
        SoundMixer.instance.setup();

        // Load local room data
        LocalRoomState.instance.setup();
        PackState.instance.setup();

        TextureConfig.setState(TextureUnit.entity, EntityTexture.instance);
        TextureConfig.setState(TextureUnit.portal, FrameBuffer.portal.getTexture());
        TextureConfig.setState(TextureUnit.menu, MenuTexture.instance);

        // Register synchronized state objects so they advance at the end of each loop
        SceneManager.instance.setup();
        SimulationState.instance.setup();

        // Setup overlays
        HomeOverlay.instance.setup();
        LogoOverlay.instance.setup();
        PublishOverlay.instance.setup();
        QuickBarOverlay.instance.setup();
        ReticuleOverlay.instance.setup();
        SimulationEditMenuOverlay.instance.setup();
        SimulationExploreMenuOverlay.instance.setup();
        RoomBannerOverlay.instance.setup();
        RoomLoadOverlay.instance.setup();

        SimulationEditMenuOverlay.instance.setup();
        SimulationExploreMenuOverlay.instance.setup();
    }

    private void update() {
        SynchronizedStateManager.instance.advanceState();
        Window.instance.handleUserInputs();

        SoundMixer.instance.clear();
        SceneManager.instance.getScene().update();
        SoundMixer.instance.update();

        if(Window.instance.isButtonDown(Button.keyLeftAlt) && Window.instance.isButtonPressed(Button.keyQ)) {
            Window.instance.setShouldClose();
        }
    }

    private void render() {
        GL42.glClear(GL42.GL_COLOR_BUFFER_BIT | GL42.GL_DEPTH_BUFFER_BIT);
        SceneManager.instance.getScene().render();
        Window.instance.swapBuffers();
    }

    public void destroy() {
        World.instance.clear(); 

        // Destroy overlays
        HomeOverlay.instance.destroy();
        LogoOverlay.instance.destroy();
        PublishOverlay.instance.destroy();
        QuickBarOverlay.instance.destroy();
        ReticuleOverlay.instance.destroy();
        SimulationEditMenuOverlay.instance.destroy();
        SimulationExploreMenuOverlay.instance.destroy();
        RoomBannerOverlay.instance.destroy();
        RoomLoadOverlay.instance.destroy();

        SoundMixer.instance.destroy();
        PortalMesh.instance.destroy();
        RayCastCursorMesh.instance.destroy();
        RoomBannerOverlay.instance.destroy();
        MenuTexture.instance.destroy();
        EntityTexture.instance.destroy();
        FrameBuffer.portal.destroy();
        TerrainShaderProgram.instance.destroy();
        SpriteShaderProgram.instance.destroy();
        Window.instance.destroy();
        WorkerPool.instance.destroy();
    }
    
    public void run() {
        while(!Window.instance.shouldClose()) {
            var updateStartTime = System.currentTimeMillis();
            if(this.lastUpdateTime == 0) {
                this.lastUpdateTime = updateStartTime;
            }

            while(this.lastUpdateTime < updateStartTime) {
                this.update();
                this.lastUpdateTime += Entry.simulationStep;
            }

            this.render();
        }
    }

    public static void main(String args[]) {
        LogoScene.instance.bind();

        try {
            Entry.instance.setup();
            Entry.instance.run();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            Entry.instance.destroy();
        }

    }

}
