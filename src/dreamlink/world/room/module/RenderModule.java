package dreamlink.world.room.module;

import org.joml.Vector3f;
import org.joml.Vector3fc;

import dreamlink.graphics.FrameBuffer;
import dreamlink.graphics.glconfig.DepthTestConfig;
import dreamlink.graphics.glconfig.PolygonOffsetConfig;
import dreamlink.graphics.glconfig.ShaderProgramConfig;
import dreamlink.graphics.glconfig.TextureConfig;
import dreamlink.graphics.glconfig.cullface.CullFace;
import dreamlink.graphics.glconfig.cullface.CullFaceConfig;
import dreamlink.graphics.program.AnimationFrameManager;
import dreamlink.graphics.program.ShaderProgramColor;
import dreamlink.graphics.program.TerrainShaderProgram;
import dreamlink.graphics.texture.EntityTexture;
import dreamlink.graphics.texture.TextureUnit;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.world.room.IRoomModuleProvider;
import dreamlink.world.room.module.color.ColorConfig;
import dreamlink.world.room.module.door.Door;
import dreamlink.world.room.module.door.DoorLinkData;
import dreamlink.world.room.module.door.PortalMesh;
import dreamlink.world.room.module.doortemplate.DoorTemplate;
import dreamlink.world.room.module.userblock.BillboardBlock;

public class RenderModule {
    
    private static final float deg180Rads = (float)Math.toRadians(180);
    private static final float doorOpenFactorLightModifier = 0.35f;
    private final IRoomModuleProvider provider;

    public RenderModule(IRoomModuleProvider provider) {
        this.provider = provider;
    }

    private final TextureConfig renderRoomTextureState = new TextureConfig(TextureUnit.room);
    private final TextureConfig renderEntityTextureState = new TextureConfig(TextureUnit.entity);
    private final TextureConfig renderPortalTextureState = new TextureConfig(TextureUnit.portal);
    private final DepthTestConfig renderDepthTestStateState = new DepthTestConfig();
    private final ShaderProgramConfig renderShaderProgramStateState = new ShaderProgramConfig();
    private final PolygonOffsetConfig renderPolygonOffsetState = new PolygonOffsetConfig();
    private final CullFaceConfig renderCullFaceConfig = new CullFaceConfig();

    private final DoorLinkData renderDoorLinkData = new DoorLinkData();
    private final Vector3f renderBaseLightColor = new Vector3f();
    private final Vector3f renderIncidentLight = new Vector3f();
    private final Vector3f renderShaderLight = new Vector3f();
    private final Vector3f renderTargetIncidentLight = new Vector3f();
    private final Vector3f renderDoorLight = new Vector3f();
    private final Vector3f renderPosition = new Vector3f();

    public void render(
        Vector3fc cameraPosition, 
        Vector3fc cameraRotation,
        Vector3fc clipPosition,
        Vector3fc clipNormal,
        Door openDoor,
        boolean isDoorPull,
        float doorOpenFactor,
        float fogBias
    ) {
        var textureAtlasModule = this.provider.getTextureAtlasModule();
        var skyBoxModule = this.provider.getSkyBoxModule();
        var colorModule = this.provider.getColorModule();
        var settingsModule = this.provider.getSettingsModule();
        var terrainModule = this.provider.getTerrainModule();
        var doorModule = this.provider.getDoorModule();
        var currentAnimationFrame = AnimationFrameManager.instance.getAnimationFrame();
        
        try(
            var roomTextureState = this.renderRoomTextureState.checkpoint();
            var entityTextureState = this.renderEntityTextureState.checkpoint();
            var portalTextureState = this.renderPortalTextureState.checkpoint();
            var depthTestStateState = this.renderDepthTestStateState.checkpoint();
            var shaderProgramStateState = this.renderShaderProgramStateState.checkpoint();
            var polygonOffsetState = this.renderPolygonOffsetState.checkpoint();
        ) {
            shaderProgramStateState.setState(TerrainShaderProgram.instance);
            entityTextureState.setState(EntityTexture.instance);
            portalTextureState.setState(FrameBuffer.portal.getTexture());
            depthTestStateState.setState(true);
            roomTextureState.setState(textureAtlasModule.getTexture());

            TerrainShaderProgram.instance.setClip(clipPosition, clipNormal);
            TerrainShaderProgram.instance.setCamera(cameraPosition, cameraRotation);
            TerrainShaderProgram.instance.setAnimationFrame(currentAnimationFrame);
            TerrainShaderProgram.instance.setShowHiddenBlocks(SimulationState.instance.getShowHiddenBlocks());
            TerrainShaderProgram.instance.setFog(
                settingsModule.fogStartDistance + fogBias,
                settingsModule.fogRangeDistance
            );

            if(settingsModule.isSkyBoxEnabled) {
                TerrainShaderProgram.instance.setEnableFog(false);
                TerrainShaderProgram.instance.setModel(cameraPosition, 0f);
                TerrainShaderProgram.instance.setEnableLighting(false);
                try(var cullFaceConfig = this.renderCullFaceConfig.checkpoint()) {
                    cullFaceConfig.setState(CullFace.front);
                    skyBoxModule.getSkyBox().render();
                }
            }


            if(openDoor == null) {
                TerrainShaderProgram.instance.setColor(ShaderProgramColor.portalLight, Vector3fMaths.zero);
            } else {
                var lightFactor = (float)Math.pow(doorOpenFactor, RenderModule.doorOpenFactorLightModifier);
                var linkData = openDoor.resolveLink(this.renderDoorLinkData);
                var targetIncidentLight = linkData.targetDoor.getIncidentLight(this.renderIncidentLight).mul(lightFactor);
                TerrainShaderProgram.instance.setColor(ShaderProgramColor.portalLight, targetIncidentLight);
            }

            var baseLightColor = this.renderBaseLightColor.set(settingsModule.baseLight);
            TerrainShaderProgram.instance.setColor(ShaderProgramColor.baseLight, baseLightColor);
            for(var ix = 0; ix < ColorConfig.getSize(); ix += 1) {
                var colorConfig = ColorConfig.get(ix);
                var lightColor = colorModule.getColor(this.renderShaderLight, colorConfig);
                TerrainShaderProgram.instance.setColor(
                    colorConfig.color,
                    lightColor
                );
            }

            TerrainShaderProgram.instance.setEnableLighting(settingsModule.isLightingEnabled);
            TerrainShaderProgram.instance.setEnableFog(settingsModule.isFogEnabled);
            TerrainShaderProgram.instance.setModel(Vector3fMaths.zero, 0.0f);
            BillboardBlock.setTransformers(deg180Rads + cameraRotation.y());
            for(var ix = 0; ix < terrainModule.getSize(); ix += 1) {
                var chunk = terrainModule.getChunkByIndex(ix);
                chunk.render();
            }

            polygonOffsetState.setState(true);

            for(var ix = 0; ix < doorModule.getSize(); ix += 1) {
                var door = doorModule.getDoorByIndex(ix);
                if(!door.isPlaced()) {
                    continue;
                }

                var incidentLight = door.getIncidentLight(this.renderIncidentLight);
                var position = door.getPlanePosition(this.renderPosition);
                TerrainShaderProgram.instance.setModel(position, door.getOrientation().yaw);

                if(door != openDoor) {
                    roomTextureState.setState(textureAtlasModule.getTexture());
                    TerrainShaderProgram.instance.setColor(ShaderProgramColor.baseLight, incidentLight);
                    DoorTemplate.setTransformers(0f);
                    door.template.render();
                    continue;
                }


                try(var cullFaceConfig = this.renderCullFaceConfig.checkpoint()) {
                    cullFaceConfig.setState(CullFace.front);
                    PortalMesh.instance.render();
                }

                if(!isDoorPull) {
                    continue;
                }

                var linkData = openDoor.resolveLink(this.renderDoorLinkData);
                var targetIncidentLight = linkData.targetDoor.getIncidentLight(this.renderTargetIncidentLight);

                var outerDoorLight = this.renderDoorLight.set(targetIncidentLight).mul(doorOpenFactor).max(incidentLight);
                roomTextureState.setState(textureAtlasModule.getTexture());
                TerrainShaderProgram.instance.setColor(ShaderProgramColor.baseLight, outerDoorLight);
                DoorTemplate.setTransformers(doorOpenFactor);
                door.template.render();

                var innerDoorLight = this.renderDoorLight.set(incidentLight).mul(doorOpenFactor).max(targetIncidentLight);
                roomTextureState.setState(linkData.targetRoom.getTexture());
                TerrainShaderProgram.instance.setModel(position, door.getOrientation().getOpposite().yaw);
                TerrainShaderProgram.instance.setColor(ShaderProgramColor.baseLight, innerDoorLight);
                DoorTemplate.setTransformers(-doorOpenFactor);
                linkData.targetDoor.template.render();
            }
        }
    }
    
}
