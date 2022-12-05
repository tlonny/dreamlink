package dreamlink.player;

import dreamlink.player.kinematics.KinematicsStateModule;
import dreamlink.player.ray.RayCastingModule;

public interface IPlayerModuleProvider {

    public KinematicsStateModule getKinematicsStateModule();

    public RayCastingModule getRayCastingModule();

}
