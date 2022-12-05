package dreamlink.player;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.utility.maths.Vector3iMaths;
import dreamlink.world.World;
import dreamlink.world.room.module.door.DoorLinkData;
import dreamlink.world.room.module.door.DoorSegment;

public class SoundReceiverModule {

    private static final int maxSoundLevel = 0xF;
    private final static long echoInterval = 150;
    private final IPlayerModuleProvider provider;

    public SoundReceiverModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }
    
    private long lastEchoTime;
    private final Vector3f updateHeadPosition = new Vector3f();
    private final Vector3i updateBlockPosition = new Vector3i();
    private final DoorLinkData updateLinkData = new DoorLinkData(); 

    public void update() {
        var doEcho = false;
        if(System.currentTimeMillis() - this.lastEchoTime > echoInterval) {
            this.lastEchoTime = System.currentTimeMillis();
            doEcho = true;
        }

        var room = World.instance.getRoom();

        var headPosition = this.provider
            .getKinematicsStateModule()
            .getHeadPosition(this.updateHeadPosition);
        var headBlockPosition = Vector3iMaths.castFrom(this.updateBlockPosition, headPosition);

        if(doEcho) {
            room.queueEchoSource(headBlockPosition, SoundReceiverModule.maxSoundLevel);
            room.runEcho();
        }

        room.emitSounds(1f);

        var openDoor = World.instance.getOpenDoor();

        if(openDoor != null) {
            var doorBlockID = openDoor.block.getBlockID();
            var doorGain = room.getMaxGainForBlockID(doorBlockID);
            var linkData = World.instance.getOpenDoor().resolveLink(this.updateLinkData);

            if(doEcho) {
                for(var ix = 0; ix < DoorSegment.getSize(); ix += 1) {
                    var segment = DoorSegment.get(ix);
                    var doorBlockPosition = linkData.targetDoor.getBlockPosition(segment, this.updateBlockPosition);
                    linkData.targetRoom.queueEchoSource(
                        doorBlockPosition,
                        SoundReceiverModule.maxSoundLevel - 1
                    );
                }
                linkData.targetRoom.runEcho();
            }

            linkData.targetRoom.emitSounds(doorGain * World.instance.getDoorOpenFactor());
        }


        
    }
    
}
