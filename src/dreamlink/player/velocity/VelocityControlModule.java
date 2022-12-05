package dreamlink.player.velocity;

import dreamlink.window.Window;
import dreamlink.window.button.Button;

import org.joml.Vector3f;

import dreamlink.Entry;
import dreamlink.player.IPlayerModuleProvider;
import dreamlink.state.SimulationState;
import dreamlink.utility.maths.CubeFace;
import dreamlink.utility.maths.Vector3fMaths;

public class VelocityControlModule {

    private static final float fuzzEpsilon = 0.001f;

    private static final float maxWalkHorizontalSpeed = 6f * Entry.updateFactor;
    private static final float maxSprintHorizontalSpeed = 9f * Entry.updateFactor;
    private static final float maxCrouchHorizontalSpeed = 3.5f * Entry.updateFactor;
    private static final float groundAcceleration = 3f * Entry.updateFactor;
    private static final float airAcceleration = 2f * Entry.updateFactor;
    private static final float groundFriction = 1f * Entry.updateFactor;
    private static final float airFriction = 0.1f * Entry.updateFactor;
    private static final float jumpSpeed = 10f * Entry.updateFactor;
    private static final float gravity = 0.35f * Entry.updateFactor;
    private static final float maxFallSpeed = 8f * Entry.updateFactor;

    private final IPlayerModuleProvider provider;

    public VelocityControlModule(IPlayerModuleProvider provider) {
        this.provider = provider;
    }

    private void updateHorizontalVelocity() {
        var state = this.provider.getKinematicsStateModule();
        var velocity = state.velocity;
        var horizontalVelocity = new Vector3f(velocity.x, 0, velocity.z);

        // Get the current speed of the player.
        var speed = horizontalVelocity.length();

        // Apply friction only when on the ground.
        var isOnGround = state.isInContact(CubeFace.bottom);
        var friction = isOnGround
            ? VelocityControlModule.groundFriction
            : VelocityControlModule.airFriction;

        var reducedSpeed = Math.max(0, speed - friction);
        var fuzzedSpeed = speed + VelocityControlModule.fuzzEpsilon;
        horizontalVelocity.mul(reducedSpeed / fuzzedSpeed);

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
        }

        wishDirection.rotateY(state.rotation.y);
        Vector3fMaths.safeNormalize(wishDirection, VelocityControlModule.fuzzEpsilon);
        
        float maxSpeed;
        if(state.isCrouching) {
            maxSpeed = VelocityControlModule.maxCrouchHorizontalSpeed;
        } else if(Window.instance.isButtonDown(Button.keyLeftShift)) {
            maxSpeed = VelocityControlModule.maxSprintHorizontalSpeed;
        } else {
            maxSpeed = VelocityControlModule.maxWalkHorizontalSpeed;
        }
        
        // Calculate the horizontal velocity.
        var acceleration = isOnGround 
            ? VelocityControlModule.groundAcceleration 
            : VelocityControlModule.airAcceleration;

        if(acceleration + speed > maxSpeed) {
            acceleration = Math.max(maxSpeed - speed, 0f);
        }

        wishDirection.mul(acceleration);
        horizontalVelocity.add(wishDirection);

        velocity.x = horizontalVelocity.x;
        velocity.z = horizontalVelocity.z;
    }

    private void updateJump() {
        if(!Window.instance.isButtonDown(Button.keySpace)) {
            return;
        }

        var state = this.provider.getKinematicsStateModule();
        var isOnGround = state.isInContact(CubeFace.bottom);

        if(isOnGround) {
            state.velocity.y += VelocityControlModule.jumpSpeed;
            return;
        }
    }

    public void update() {
        if(SimulationState.instance.getNoClip()) {
            return;
        }

        this.updateJump();
        this.updateHorizontalVelocity();

        var state = this.provider.getKinematicsStateModule();
        state.velocity.y = Math.max(
            - VelocityControlModule.maxFallSpeed,
            state.velocity.y - VelocityControlModule.gravity
        );
        Vector3fMaths.fuzz(state.velocity, VelocityControlModule.fuzzEpsilon);
    }
    
}
