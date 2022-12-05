package dreamlink.player;

import org.joml.AABBf;
import org.joml.Vector3f;

import dreamlink.player.kinematics.KinematicsStateModule;
import dreamlink.player.ray.RayCastData;
import dreamlink.player.ray.RayCastingModule;
import dreamlink.player.velocity.NoClipVelocityControlModule;
import dreamlink.player.velocity.VelocityControlModule;

public class Player {

    private class InternalModuleProvider implements IPlayerModuleProvider {

        @Override
        public KinematicsStateModule getKinematicsStateModule() {
            return Player.this.kinematicsStateModule;
        }

        @Override
        public RayCastingModule getRayCastingModule() {
            return Player.this.rayCastingModule;
        }

    }

    public static Player instance = new Player();

    private final KinematicsStateModule kinematicsStateModule;
    private final RotationControlModule rotationControlModule;
    private final VelocityControlModule velocityControlModule;
    private final NoClipVelocityControlModule noClipVelocityControlModule;
    private final RayCastingModule rayCastingModule;
    private final PositionIntegrationModule positionIntegrationModule;
    private final BlockEraseModule blockEraseModule;
    private final BlockEyeDropModule blockEyeDropModule;
    private final BlockInteractModule blockInteractModule;
    private final StampApplyModule stampApplyModule;
    private final SpawnModule spawnModule;
    private final SoundReceiverModule soundReceiverModule;

    public Player() {
        var provider = new InternalModuleProvider();
        this.kinematicsStateModule = new KinematicsStateModule();
        this.rotationControlModule = new RotationControlModule(provider);
        this.velocityControlModule = new VelocityControlModule(provider);
        this.noClipVelocityControlModule = new NoClipVelocityControlModule(provider);
        this.positionIntegrationModule = new PositionIntegrationModule(provider);
        this.blockEraseModule = new BlockEraseModule(provider);
        this.blockInteractModule = new BlockInteractModule(provider);
        this.blockEyeDropModule = new BlockEyeDropModule(provider);
        this.stampApplyModule = new StampApplyModule(provider);
        this.rayCastingModule = new RayCastingModule(provider);
        this.soundReceiverModule = new SoundReceiverModule(provider);
        this.spawnModule = new SpawnModule(provider);
    }
    
    public void spawn(String doorName) {
        this.spawnModule.spawn(doorName);
    }

    public Vector3f getHeadPosition(Vector3f headPosition) {
        return this.kinematicsStateModule.getHeadPosition(headPosition);
    }

    public Vector3f getRotation() {
        return this.kinematicsStateModule.rotation;
    }

    public boolean canInteract() {
        return this.blockInteractModule.canInteract();
    }

    public AABBf getCollider(AABBf collider) {
        return this.kinematicsStateModule.getCollider(collider);
    }

    public RayCastData getRayCastData(RayCastData rayCastData) {
        return this.rayCastingModule.getRayCastData(rayCastData);
    }

    public void update() {
        this.rotationControlModule.update();
        this.velocityControlModule.update();
        this.noClipVelocityControlModule.update();
        this.positionIntegrationModule.update();
        this.kinematicsStateModule.update();
        this.rayCastingModule.update();
        this.blockEraseModule.update();
        this.blockInteractModule.update();
        this.blockEyeDropModule.update();
        this.stampApplyModule.update();
        this.soundReceiverModule.update();
    }
}
