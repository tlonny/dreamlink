package dreamlink.world.room;

import dreamlink.world.IWorldModuleProvider;
import dreamlink.world.RoomCacheModule;
import dreamlink.world.room.module.PortalLightModule;
import dreamlink.world.room.module.SettingsModule;
import dreamlink.world.room.module.AmbienceModule;
import dreamlink.world.room.module.ChunkProcessorModule;
import dreamlink.world.room.module.EchoModule;
import dreamlink.world.room.module.IOModule;
import dreamlink.world.room.module.PaletteModule;
import dreamlink.world.room.module.SoundModule;
import dreamlink.world.room.module.TextureAtlasModule;
import dreamlink.world.room.module.block.BlockModule;
import dreamlink.world.room.module.color.ColorModule;
import dreamlink.world.room.module.door.DoorModule;
import dreamlink.world.room.module.doortemplate.DoorTemplateModule;
import dreamlink.world.room.module.lightpropagation.LightPropagationModule;
import dreamlink.world.room.module.lightresection.LightResectionModule;
import dreamlink.world.room.module.skybox.SkyBoxModule;
import dreamlink.world.room.module.stamp.StampModule;
import dreamlink.world.room.module.terrain.TerrainModule;
import dreamlink.world.room.module.texturesample.TextureSampleModule;
import dreamlink.world.room.module.userblock.UserBlockModule;

public interface IRoomModuleProvider extends IWorldModuleProvider {

    public RoomCacheModule getRoomCacheModule();

    public StampModule getStampModule();

    public SkyBoxModule getSkyBoxModule();

    public AmbienceModule getAmbienceModule();

    public SettingsModule getSettingsModule();

    public ChunkProcessorModule getChunkProcessorModule();

    public TextureAtlasModule getTextureAtlasModule();

    public TextureSampleModule getTextureSampleModule();

    public LightPropagationModule getLightPropagationModule();

    public LightResectionModule getLightResectionModule();

    public TerrainModule getTerrainModule();

    public DoorModule getDoorModule();

    public DoorTemplateModule getDoorTemplateModule();

    public SoundModule getSoundModule();

    public ColorModule getColorModule();

    public BlockModule getBlockModule();

    public UserBlockModule getUserBlockModule();

    public IOModule getIOModule();

    public PaletteModule getPaletteModule();

    public PortalLightModule getPortalLightModule();

    public EchoModule getEchoModule();

    public boolean isShadowCopy();

    public String getName();

}
