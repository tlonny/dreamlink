package dreamlink.world.room;

import org.joml.Vector3fc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import dreamlink.graphics.texture.ITexture;
import dreamlink.world.IWorldModuleProvider;
import dreamlink.world.RoomCacheModule;
import dreamlink.world.RoomStateModule;
import dreamlink.world.room.module.PortalLightModule;
import dreamlink.world.room.module.AmbienceModule;
import dreamlink.world.room.module.ChunkProcessorModule;
import dreamlink.world.room.module.EchoModule;
import dreamlink.world.room.module.IOModule;
import dreamlink.world.room.module.LoadModule;
import dreamlink.world.room.module.PaletteModule;
import dreamlink.world.room.module.RenderModule;
import dreamlink.world.room.module.SaveModule;
import dreamlink.world.room.module.SettingsModule;
import dreamlink.world.room.module.SoundEmissionModule;
import dreamlink.world.room.module.SoundModule;
import dreamlink.world.room.module.TextureAtlasModule;
import dreamlink.world.room.module.block.BlockModule;
import dreamlink.world.room.module.block.IBlock;
import dreamlink.world.room.module.color.ColorModule;
import dreamlink.world.room.module.door.Door;
import dreamlink.world.room.module.door.DoorModule;
import dreamlink.world.room.module.doortemplate.DoorTemplate;
import dreamlink.world.room.module.doortemplate.DoorTemplateModule;
import dreamlink.world.room.module.lightpropagation.LightPropagationModule;
import dreamlink.world.room.module.lightresection.LightResectionModule;
import dreamlink.world.room.module.skybox.SkyBoxModule;
import dreamlink.world.room.module.stamp.IStamp;
import dreamlink.world.room.module.stamp.StampModule;
import dreamlink.world.room.module.terrain.TerrainBlockData;
import dreamlink.world.room.module.terrain.TerrainModule;
import dreamlink.world.room.module.texturesample.TextureSampleModule;
import dreamlink.world.room.module.userblock.UserBlockModule;

public class Room {

    private class InternalRoomModuleProvider implements IRoomModuleProvider {

        @Override
        public String getName() {
            return Room.this.name;
        }

        @Override
        public boolean isShadowCopy() {
            return Room.this.isShadowCopy;
        }

        @Override
        public IOModule getIOModule() {
            return Room.this.IOModule;
        }

        @Override
        public TextureAtlasModule getTextureAtlasModule() {
            return Room.this.textureAtlasModule;
        }

        @Override
        public TextureSampleModule getTextureSampleModule() {
            return Room.this.textureSampleModule;
        }

        @Override
        public BlockModule getBlockModule() {
            return Room.this.blockModule;
        }

        @Override
        public SoundModule getSoundModule() {
            return Room.this.soundModule;
        }

        @Override
        public ColorModule getColorModule() {
            return Room.this.colorModule;
        }

        @Override
        public TerrainModule getTerrainModule() {
            return Room.this.terrainModule;
        }

        @Override
        public DoorModule getDoorModule() {
            return Room.this.doorModule;
        }

        @Override
        public PaletteModule getPaletteModule() {
            return Room.this.paletteModule;
        }

        @Override
        public DoorTemplateModule getDoorTemplateModule() {
            return Room.this.doorTemplateModule;
        }

        @Override
        public PortalLightModule getPortalLightModule() {
            return Room.this.portalLightModule;
        }

        @Override
        public UserBlockModule getUserBlockModule() {
            return Room.this.userBlockModule;
        }

        @Override
        public RoomStateModule getRoomStateModule() {
            return Room.this.worldModuleProvider.getRoomStateModule();
        }

        @Override
        public RoomCacheModule getRoomCacheModule() {
            return Room.this.worldModuleProvider.getRoomCacheModule();
        }

        @Override
        public LightPropagationModule getLightPropagationModule() {
            return Room.this.lightPropagationModule;
        }

        @Override
        public LightResectionModule getLightResectionModule() {
            return Room.this.lightResectionModule;
        }

        @Override
        public EchoModule getEchoModule() {
            return Room.this.echoModule;
        }

        @Override
        public ChunkProcessorModule getChunkProcessorModule() {
            return Room.this.chunkProcessorModule;
        }

        @Override
        public SettingsModule getSettingsModule() {
            return Room.this.settingsModule;
        }

        @Override
        public StampModule getStampModule() {
            return Room.this.stampModule;
        }

        @Override
        public SkyBoxModule getSkyBoxModule() {
            return Room.this.skyBoxModule;
        }

        @Override
        public AmbienceModule getAmbienceModule() {
            return Room.this.ambienceModule;
        }

    }

    private final IWorldModuleProvider worldModuleProvider;

    private final BlockModule blockModule;
    private final AmbienceModule ambienceModule;
    private final EchoModule echoModule;
    private final DoorModule doorModule;
    private final DoorTemplateModule doorTemplateModule;
    private final ColorModule colorModule;
    private final IOModule IOModule;
    private final PaletteModule paletteModule;
    private final PortalLightModule portalLightModule;
    private final RenderModule renderModule;
    private final SoundModule soundModule;
    private final TerrainModule terrainModule;
    private final TextureAtlasModule textureAtlasModule;
    private final SoundEmissionModule soundEmissionModule;
    private final TextureSampleModule textureSampleModule;
    private final UserBlockModule userBlockModule;
    private final LightPropagationModule lightPropagationModule;
    private final ChunkProcessorModule chunkProcessorModule;
    private final LightResectionModule lightResectionModule;
    private final LoadModule loadModule;
    private final SaveModule saveModule;
    private final SettingsModule settingsModule;
    private final StampModule stampModule;
    private final SkyBoxModule skyBoxModule;

    public final String name;
    public final boolean isShadowCopy;
    private boolean isFinalized;

    public Room(
        IWorldModuleProvider worldModuleProvider,
        String roomKey, 
        boolean isShadowCopy
    ) {
        this.worldModuleProvider = worldModuleProvider;
        this.name = roomKey;
        this.isShadowCopy = isShadowCopy;

        var provider = new InternalRoomModuleProvider();
        this.IOModule = new IOModule(provider);
        this.blockModule = new BlockModule(provider);
        this.doorModule = new DoorModule(provider);
        this.doorTemplateModule = new DoorTemplateModule(provider);
        this.echoModule = new EchoModule(provider);
        this.colorModule = new ColorModule(provider);
        this.paletteModule = new PaletteModule();
        this.stampModule = new StampModule();
        this.portalLightModule = new PortalLightModule(provider);
        this.chunkProcessorModule = new ChunkProcessorModule(provider);
        this.renderModule = new RenderModule(provider);
        this.soundModule = new SoundModule(provider);
        this.soundEmissionModule = new SoundEmissionModule(provider);
        this.terrainModule = new TerrainModule(provider);
        this.lightPropagationModule = new LightPropagationModule(provider);
        this.lightResectionModule = new LightResectionModule(provider);
        this.textureAtlasModule = new TextureAtlasModule(provider);
        this.textureSampleModule = new TextureSampleModule(provider);
        this.userBlockModule = new UserBlockModule(provider);
        this.ambienceModule = new AmbienceModule(provider);
        this.loadModule = new LoadModule(provider);
        this.saveModule = new SaveModule(provider);
        this.skyBoxModule = new SkyBoxModule(provider);
        this.settingsModule = new SettingsModule(provider);
    }

    public void queueEchoSource(Vector3ic position, int soundLevel) {
        this.echoModule.queueEchoSource(position, soundLevel);
    }

    public void runEcho() {
        this.echoModule.runEcho();
    }

    public void emitSounds(float gainModulator) {
        this.soundEmissionModule.emitSounds(gainModulator);
    }

    public float getMaxGainForBlockID(int blockID) {
        return this.echoModule.getMaxGainForBlockID(blockID);
    }

    public void load() {
        this.loadModule.load();
    }

    public void save() {
        this.saveModule.save();
    }

    public String getLoadError() {
        return this.loadModule.getError();
    }

    public boolean isSaving() {
        return this.saveModule.isSaving();
    }

    public boolean isFinalized() {
        return this.isFinalized;
    }

    public Vector3i getDimensions(Vector3i target) {
        return this.terrainModule.getDimensions(target);
    }

    public Door createDoor(DoorTemplate doorTemplate, String name) {
        return this.doorModule.createDoor(doorTemplate, name);
    }
    
    public void removeDoor(Door door) {
        this.doorModule.removeDoor(door);
    }

    public ITexture getTexture() {
        return this.textureAtlasModule.getTexture();
    }

    public int getDoorTemplateSize() {
        return this.doorTemplateModule.getSize();
    }

    public DoorTemplate getDoorTemplateByIndex(int index) {
        return this.doorTemplateModule.getDoorTemplateByIndex(index);
    }

    public int getDoorSize() {
        return this.doorModule.getSize();
    }

    public Door getDoorByIndex(int index) {
        return this.doorModule.getDoorByIndex(index);
    }

    public Door getDoorByName(String name) {
        return this.doorModule.getDoorByName(name);
    }

    public TerrainBlockData getBlockData(Vector3i position, TerrainBlockData target) {
        return this.terrainModule.getBlockData(position, target);
    }

    public IBlock getBlockByID(int blockID) {
        return this.blockModule.getBlockByID(blockID);
    }

    public int getStampSize() {
        return this.stampModule.getSize();
    }

    public IStamp getStampByIndex(int index) {
        return this.stampModule.getStampByIndex(index);
    }

    public IStamp getPaletteStamp(int slotIndex) {
        return this.paletteModule.getStamp(slotIndex);
    }

    public void setPaletteStamp(int slotIndex, IStamp stamp) {
        this.paletteModule.setStamp(slotIndex, stamp);
    }

    public void setSelectedPaletteStampSlotIndex(int index) {
        this.paletteModule.setSelectedStampSlotIndex(index);
    }

    public IStamp getSelectedPaletteStamp() {
        return this.paletteModule.getSelectedStamp();
    }

    public int getSelectedPaletteStampSlotIndex() {
        return this.paletteModule.getSelectedStampSlotIndex();
    }
    
    public boolean getIsLightingEnabled() {
        return this.settingsModule.isLightingEnabled;
    }

    public void setIsLightingEnabled(boolean isLightingEnabled) {
        this.settingsModule.isLightingEnabled = isLightingEnabled;
    }

    public boolean getIsFogEnabled() {
        return this.settingsModule.isFogEnabled;
    }

    public boolean getIsSkyBoxEnabled() {
        return this.settingsModule.isSkyBoxEnabled;
    }

    public void setIsSkyBoxEnabled(boolean isSkyBoxEnabled) {
        this.settingsModule.isSkyBoxEnabled = isSkyBoxEnabled;
    }

    public float getFogMinDistance() {
        return this.settingsModule.fogStartDistance;
    }

    public void setFogMinDistance(float fogMinDistance) {
        this.settingsModule.fogStartDistance = fogMinDistance;
    }

    public float getFogMaxDistance() {
        return this.settingsModule.fogRangeDistance;
    }

    public void setFogMaxDistance(float fogMaxDistance) {
        this.settingsModule.fogRangeDistance = fogMaxDistance;
    }

    public boolean getIsAmbientSoundEnabled() {
        return this.settingsModule.isAmbientSoundEnabled;
    }

    public void setIsAmbientSoundEnabled(boolean isAmbientSoundEnabled) {
        this.settingsModule.isAmbientSoundEnabled = isAmbientSoundEnabled;
    }

    public void setIsFogEnabled(boolean isFogEnabled) {
        this.settingsModule.isFogEnabled = isFogEnabled;
    }

    public float getBaseLightAmount() {
        return this.settingsModule.baseLight;
    }

    public void setBaseLightAmount(float baseLightAmount) {
        this.settingsModule.baseLight = baseLightAmount;
    }

    public float getMasterGain() {
        return this.settingsModule.masterGain;
    }

    public void setMasterGain(float masterGain) {
        this.settingsModule.masterGain = masterGain;
    }

    public boolean doWork() {
        if(this.loadModule.doWork()) {
            return true;
        }

        if(this.saveModule.doWork()) {
            return true;
        }

        if(!this.loadModule.isLoaded()) {
            return false;
        }

        if(this.textureAtlasModule.doWork()) {
            return true;
        }

        if(this.soundModule.doWork()) {
            return true;
        }

        if(this.skyBoxModule.doWork()) {
            return true;
        }

        if(this.terrainModule.doWork()) {
            return true;
        }

        if(this.doorTemplateModule.doWork()) {
            return true;
        }

        if(this.chunkProcessorModule.doWork()) {
            return true;
        }
        
        this.isFinalized = true;
        return false;
    }

    public void destroy() {
        this.soundModule.destroy();
        this.textureAtlasModule.destroy();
        this.terrainModule.destroy();
        this.doorTemplateModule.destroy();
        this.skyBoxModule.destroy();
    }

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
        this.renderModule.render(
            cameraPosition, 
            cameraRotation,
            clipPosition,
            clipNormal,
            openDoor,
            isDoorPull,
            doorOpenFactor,
            fogBias
        );
    }
}