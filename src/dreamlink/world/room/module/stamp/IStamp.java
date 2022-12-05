package dreamlink.world.room.module.stamp;

import org.joml.Vector3f;
import org.joml.Vector3i;

import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.utility.maths.Orientation;

public interface IStamp {

    public String getStampName();

    public String getStampType();

    public ISpriteTemplate getStampSprite();

    public void onStampApply(
        Vector3i position, 
        Vector3f rayTerminationPosition,
        Orientation orientation
    );

}
