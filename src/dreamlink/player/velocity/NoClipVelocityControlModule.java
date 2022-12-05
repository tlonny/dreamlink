package dreamlink.player.velocity;

import org.joml.Vector3f;

import dreamlink.Entry;
import dreamlink.player.IPlayerModuleProvider;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Vector3fMaths;
import dreamlink.window.Window;
import dreamlink.window.button.Button;

public class NoClipVelocityControlModule {

    private static final float fuzzEpsilon = 0.001f;

    private static final float maxSpeed = 5f * Entry.updateFactor;
    private static final float maxCreepSpeed = 5f * Entry.updateFactor;
    private static final float maxSprintSpeed = 14f * Entry.updateFactor;
    private static final float acceleration = 10f * Entry.updateFactor;
    private static final float friction = 1f * Entry.updateFactor;
    private final IPlayerModuleProvider provider;

    public NoClipVelocityControlModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    public void update() {
        if(!SimulationState.instance.getNoClip()) {
            return;
        }

        var state = this.provider.getKinematicsStateModule();

        // Get the current speed of the player.
        var speed = state.velocity.length();

        var reducedSpeed = Math.max(speed - NoClipVelocityControlModule.friction, 0f);
        var fuzzedSpeed = Math.max(speed, NoClipVelocityControlModule.fuzzEpsilon);
        state.velocity.mul(reducedSpeed / fuzzedSpeed);

        // Calculate the direction the player wants to move in.
        var wishDirection = new Vector3f();
        if(!Window.instance.isButtonDown(Button.keyLeftAlt)) {
            if(Window.instance.isButtonDown(Button.keyW)) {
                Vector3fMaths.add(wishDirection, CubeFace.front.normal);
            }
            if(Window.instance.isButtonDown(Button.keyS)) {
                Vector3fMaths.add(wishDirection, CubeFace.back.normal);
            }
            if(Window.instance.isButtonDown(Button.keyD)) {
                Vector3fMaths.add(wishDirection, CubeFace.right.normal);
            }
            if(Window.instance.isButtonDown(Button.keyA)) {
                Vector3fMaths.add(wishDirection, CubeFace.left.normal);
            }
            if(Window.instance.isButtonDown(Button.keySpace)) {
                Vector3fMaths.add(wishDirection, CubeFace.top.normal);
            }
        }

        wishDirection.rotateX(state.rotation.x);
        wishDirection.rotateY(state.rotation.y);

        Vector3fMaths.safeNormalize(wishDirection, NoClipVelocityControlModule.fuzzEpsilon);

        float maxSpeed;
        if(state.isCrouching) {
            maxSpeed = NoClipVelocityControlModule.maxCreepSpeed;
        } else if(Window.instance.isButtonDown(Button.keyLeftShift)) {
            maxSpeed = NoClipVelocityControlModule.maxSprintSpeed;
        } else {
            maxSpeed = NoClipVelocityControlModule.maxSpeed;
        }

        var acceleration = NoClipVelocityControlModule.acceleration;
        if(acceleration + speed > maxSpeed) {
            acceleration = Math.max(maxSpeed - speed, 0f);
        }

        wishDirection.mul(acceleration);
        state.velocity.add(wishDirection);
        Vector3fMaths.fuzz(state.velocity, NoClipVelocityControlModule.fuzzEpsilon);
    }
    
}
