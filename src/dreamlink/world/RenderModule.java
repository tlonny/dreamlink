package dreamlink.world;

import org.joml.AABBf;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL42;

import dreamlink.graphics.FrameBuffer;
import dreamlink.graphics.glconfig.DepthTestConfig;
import dreamlink.graphics.glconfig.FrameBufferConfig;
import dreamlink.graphics.glconfig.PolygonOffsetConfig;
import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.program.TerrainShaderProgram;
import dreamlink.graphics.texture.EntityTexture;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.player.Player;
import dreamlink.player.ray.RayCastData;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.AABBfMaths;
import dreamlink.utility.maths.PortalTransformer;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.module.door.DoorLinkData;

public class RenderModule {

    private final IWorldModuleProvider provider;

    public RenderModule(IWorldModuleProvider provider) {
        this.provider = provider;
    }

    private final FrameBufferConfig renderFrameBufferState = new FrameBufferConfig();
    private final DoorLinkData renderLinkData = new DoorLinkData();
    private final PortalTransformer renderPortalTransformer = new PortalTransformer();
    private final Vector3f renderHeadPosition = new Vector3f();
    private final Vector3f renderDoorPlanePosition = new Vector3f();
    private final Vector3f renderTargetDoorPlanePosition = new Vector3f();
    private final Vector3f renderPosition = new Vector3f();
    private final Vector3f renderRotation = new Vector3f();
    private final Vector3f renderClipPosition = new Vector3f();
    private final Vector3f renderClipNormal = new Vector3f();
    private final Vector3f renderBlockPosition = new Vector3f();
    private final AABBf renderPortalBounds = new AABBf();
    private final RayCastData renderRayCastData = new RayCastData();

    private final TextureConfig renderEntityTextureState = new TextureConfig(TextureUnit.entity);
    private final DepthTestConfig renderDepthTestStateState = new DepthTestConfig();
    private final ShaderProgramConfig renderShaderProgramStateState = new ShaderProgramConfig();
    private final PolygonOffsetConfig renderPolygonOffsetState = new PolygonOffsetConfig();

    public void render() {
        var roomState = this.provider.getRoomStateModule();
        var headPosition = Player.instance.getHeadPosition(this.renderHeadPosition);
        var openDoor = roomState.getOpenDoor();

        try (var frameBufferState = this.renderFrameBufferState.checkpoint()) {
            frameBufferState.setState(FrameBuffer.portal);
            GL42.glClear(GL42.GL_DEPTH_BUFFER_BIT | GL42.GL_COLOR_BUFFER_BIT);

            if(openDoor != null) {
                var linkData = openDoor.resolveLink(this.renderLinkData);
                var transformer = this.renderPortalTransformer.set(
                    openDoor.getPlanePosition(this.renderDoorPlanePosition),
                    openDoor.getOrientation().yaw,
                    linkData.targetDoor.getPlanePosition(this.renderTargetDoorPlanePosition),
                    linkData.targetDoor.getOrientation().yaw
                );

                this.renderPosition.set(headPosition);
                this.renderRotation.set(Player.instance.getRotation());

                var transformedPosition = transformer.getTransformedPosition(this.renderPosition);
                var transformedRotation = transformer.getTransformedRotation(this.renderRotation);
                var clipPosition = linkData.targetDoor.getPlanePosition(this.renderClipPosition);
                var clipNormal = this.renderClipNormal.set(linkData.targetDoor.getOrientation().cubeFace.normal);

                var collider = openDoor.getCollider(this.renderPortalBounds);
                var projection = AABBfMaths.project(collider, collider, openDoor.getOrientation().cubeFace);
                var distanceToPortal = AABBfMaths.distance(projection, headPosition);

                linkData.targetRoom.render(
                    transformedPosition, 
                    transformedRotation,
                    clipPosition,
                    clipNormal,
                    linkData.targetDoor,
                    !roomState.isDoorPull(),
                    roomState.getDoorOpenFactor(),
                    distanceToPortal

                );
            }
        }

        roomState.getRoom().render(
            headPosition, 
            Player.instance.getRotation(),
            Vector3fMaths.zero,
            Vector3fMaths.zero,
            openDoor,
            roomState.isDoorPull(),
            roomState.getDoorOpenFactor(),
            0f
        );

        if(!SimulationState.instance.getAllowEdit()) {
            return;
        }

        var raycastData = Player.instance.getRayCastData(this.renderRayCastData);
        try(
            var entityTextureState = this.renderEntityTextureState.checkpoint();
            var depthTestStateState = this.renderDepthTestStateState.checkpoint();
            var shaderProgramStateState = this.renderShaderProgramStateState.checkpoint();
            var polygonOffsetState = this.renderPolygonOffsetState.checkpoint();
        ) {
            entityTextureState.setState(EntityTexture.instance);
            depthTestStateState.setState(true);
            shaderProgramStateState.setState(TerrainShaderProgram.instance);
            polygonOffsetState.setState(true);
            var blockPosition = this.renderBlockPosition.set(raycastData.blockPosition);
            TerrainShaderProgram.instance.setModel(blockPosition, 0f);
            TerrainShaderProgram.instance.setCamera(headPosition, Player.instance.getRotation());
            RayCastCursorMesh.instance.render();
        }

    }
    
}
