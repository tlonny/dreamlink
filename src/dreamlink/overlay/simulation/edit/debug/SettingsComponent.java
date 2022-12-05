package dreamlink.overlay.simulation.edit.debug;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.checkbox.CheckBoxComponent;
import dreamlink.overlay.component.checkbox.ICheckBoxComponentProvider;
import dreamlink.overlay.component.slider.ISliderComponentProvider;
import dreamlink.overlay.component.slider.SliderComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.World;

public class SettingsComponent extends WrapperComponent {

    private class InternalEnableLightComponentProvider implements ICheckBoxComponentProvider {

        @Override
        public boolean isCheckBoxDisabled() {
            return false;
        }

        @Override
        public void onChange(boolean isChecked) {
            World.instance.getRoom().setIsLightingEnabled(isChecked);
        }

        @Override
        public boolean getValue() {
            return World.instance.getRoom().getIsLightingEnabled();
        }

    }

    private class InternalEnableAmbientSoundComponentProvider implements ICheckBoxComponentProvider {

        @Override
        public boolean isCheckBoxDisabled() {
            return false;
        }

        @Override
        public void onChange(boolean isChecked) {
            World.instance.getRoom().setIsAmbientSoundEnabled(isChecked);
        }

        @Override
        public boolean getValue() {
            return World.instance.getRoom().getIsAmbientSoundEnabled();
        }

    }

    private class InternalLightAmountComponentProvider implements ISliderComponentProvider {

        @Override
        public float getSlideFactor() {
            return World.instance.getRoom().getBaseLightAmount();
        }

        @Override
        public void onChange(float slideFactor) {
            World.instance.getRoom().setBaseLightAmount(slideFactor);
        }

        @Override
        public boolean isSliderDisabled() {
            return false;
        }

    }

    private class InternalEnableFogComponentProvider implements ICheckBoxComponentProvider {

        @Override
        public boolean isCheckBoxDisabled() {
            return false;
        }

        @Override
        public void onChange(boolean isChecked) {
            World.instance.getRoom().setIsFogEnabled(isChecked);
        }

        @Override
        public boolean getValue() {
            return World.instance.getRoom().getIsFogEnabled();
        }

    }

    private class InternalEnableSkyBoxComponentProvider implements ICheckBoxComponentProvider {

        @Override
        public boolean isCheckBoxDisabled() {
            return false;
        }

        @Override
        public void onChange(boolean isChecked) {
            World.instance.getRoom().setIsSkyBoxEnabled(isChecked);
        }

        @Override
        public boolean getValue() {
            return World.instance.getRoom().getIsSkyBoxEnabled();
        }

    }

    private class InternalFogMinDistanceComponentProvider implements ISliderComponentProvider {

        @Override
        public float getSlideFactor() {
            return World.instance.getRoom().getFogMinDistance() / SettingsComponent.fogMaxRange;
        }

        @Override
        public void onChange(float slideFactor) {
            World.instance.getRoom().setFogMinDistance(slideFactor * SettingsComponent.fogMaxRange);
        }

        @Override
        public boolean isSliderDisabled() {
            return false;
        }

    }

    private class InternalFogMaxDistanceComponentProvider implements ISliderComponentProvider {

        @Override
        public float getSlideFactor() {
            return World.instance.getRoom().getFogMaxDistance() / SettingsComponent.fogMaxRange;
        }

        @Override
        public void onChange(float slideFactor) {
            World.instance.getRoom().setFogMaxDistance(slideFactor * SettingsComponent.fogMaxRange);
        }

        @Override
        public boolean isSliderDisabled() {
            return false;
        }

    }

    private class InternalVolumeComponentProvider implements ISliderComponentProvider {

        @Override
        public float getSlideFactor() {
            return World.instance.getRoom().getMasterGain();
        }

        @Override
        public void onChange(float slideFactor) {
            World.instance.getRoom().setMasterGain(slideFactor);
        }

        @Override
        public boolean isSliderDisabled() {
            return false;
        }

    }


    private final static int itemSpacing = 5;
    private final static String enableLightingText = "Enable Lighting";
    private final static String enableFogText = "Enable fog";
    private final static String enableSkyBox = "Enable skybox";
    private final static String enableAmbientSound = "Enable ambient sound";

    private final static String masterGainText = "Master gain:";
    private final static String baseLightingAmountText = "Base Lighting amount:";
    private final static String fogStartText = "Fog start distance:";
    private final static String fogRangeText = "Fog range distance:";
    private static final float fogMaxRange = 128;

    private final IComponent component;

    public SettingsComponent() {
        this.component = new PaddingComponent(
            new VerticalSpanComponent(
                Alignment.start,
                SettingsComponent.itemSpacing
            ).addComponent(
                new HorizontalSpanComponent(
                    Alignment.start,
                    SettingsComponent.itemSpacing
                ).addComponent(
                    new CheckBoxComponent(
                        new InternalEnableLightComponentProvider()
                    )
                ).addComponent(
                    new TextLineLabelComponent(
                        SettingsComponent.enableLightingText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
            ).addComponent(
                new HorizontalSpanComponent(
                    Alignment.start,
                    SettingsComponent.itemSpacing
                ).addComponent(
                    new CheckBoxComponent(
                        new InternalEnableSkyBoxComponentProvider()
                    )
                ).addComponent(
                    new TextLineLabelComponent(
                        SettingsComponent.enableSkyBox,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
            ).addComponent(
                new HorizontalSpanComponent(
                    Alignment.start,
                    SettingsComponent.itemSpacing
                ).addComponent(
                    new CheckBoxComponent(
                        new InternalEnableFogComponentProvider()
                    )
                ).addComponent(
                    new TextLineLabelComponent(
                        SettingsComponent.enableFogText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
            ).addComponent(
                new HorizontalSpanComponent(
                    Alignment.start,
                    SettingsComponent.itemSpacing
                ).addComponent(
                    new CheckBoxComponent(
                        new InternalEnableAmbientSoundComponentProvider()
                    )
                ).addComponent(
                    new TextLineLabelComponent(
                        SettingsComponent.enableAmbientSound,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
            ).addComponent(
                new TextLineLabelComponent(
                    SettingsComponent.masterGainText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                )
            ).addComponent(
                new SliderComponent(
                    new InternalVolumeComponentProvider()
                )
            ).addComponent(
                new TextLineLabelComponent(
                    SettingsComponent.baseLightingAmountText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                )
            ).addComponent(
                new SliderComponent(
                    new InternalLightAmountComponentProvider()
                )
            ).addComponent(
                new TextLineLabelComponent(
                    SettingsComponent.fogStartText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                )
            ).addComponent(
                new SliderComponent(
                    new InternalFogMinDistanceComponentProvider()
                )
            ).addComponent(
                new TextLineLabelComponent(
                    SettingsComponent.fogRangeText,
                    FontDecoration.normal,
                    Vector4fMaths.black
                )
            ).addComponent(
                new SliderComponent(
                    new InternalFogMaxDistanceComponentProvider()
                )
            ),
            SettingsComponent.itemSpacing
        ); 

    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }


    
}
