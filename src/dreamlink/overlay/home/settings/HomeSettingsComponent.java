package dreamlink.overlay.home.settings;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.config.UserSettingsConfig;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.slider.ISliderComponentProvider;
import dreamlink.overlay.component.slider.SliderComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.home.IHomeChildComponentProvider;
import dreamlink.utility.maths.Vector4fMaths;

public class HomeSettingsComponent extends WrapperComponent {

    private class InternalUploadKeyTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return HomeSettingsComponent.this.uploadKey;
        }

        @Override
        public void onChange(String value) {
            HomeSettingsComponent.this.uploadKey = value;
        }

    }

    private class InternalSaveButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            UserSettingsConfig.instance.uploadKey = HomeSettingsComponent.this.uploadKey;
            UserSettingsConfig.instance.mouseSensitivity = HomeSettingsComponent.this.mouseSensitivity;
            UserSettingsConfig.instance.save();
        }

        @Override
        public boolean isButtonDisabled() {
            var length = HomeSettingsComponent.this.uploadKey.length();
            return length > 0 && length < UploadKeyInputComponent.keyLength;
        }

    }

    private class InternalMouseSensitivityProvider implements ISliderComponentProvider {

        @Override
        public float getSlideFactor() {
            return HomeSettingsComponent.this.mouseSensitivity;
        }

        @Override
        public void onChange(float slideFactor) {
            HomeSettingsComponent.this.mouseSensitivity = slideFactor;
        }

        @Override
        public boolean isSliderDisabled() {
            return false;
        }

    }

    private final static int spacing = 5;
    private final static String title = "Dreamlink: Settings";

    private static final String saveButtonText = "Save";
    private static final String backButtonText = "Back";
    private final static String uploadKeyText = "Upload Key:";
    private final static String mouseSensitivtyText = "Mouse Sensitivity: ";
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);

    private final IComponent component;
    private final IHomeChildComponentProvider provider;
    private String uploadKey;

    private float mouseSensitivity;

    public HomeSettingsComponent(IHomeChildComponentProvider provider) {
        this.provider = provider;
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            HomeSettingsComponent.title,
            new PaddingComponent(
                new VerticalSpanComponent(
                    Alignment.start,
                    HomeSettingsComponent.spacing
                )
                    .addComponent(
                        new TextLineLabelComponent(
                            HomeSettingsComponent.mouseSensitivtyText,
                            FontDecoration.normal,
                            Vector4fMaths.black
                        )
                    )
                    .addComponent(
                        new SliderComponent(
                            new InternalMouseSensitivityProvider()
                        )
                    )
                    .addComponent(
                        new TextLineLabelComponent(
                            HomeSettingsComponent.uploadKeyText,
                            FontDecoration.normal,
                            Vector4fMaths.black
                        )
                    )
                    .addComponent(
                        new UploadKeyInputComponent(
                            new InternalUploadKeyTextInputComponentProvider()
                        )
                    ).addComponent(
                        new HorizontalSpanComponent(
                            Alignment.center, 
                            HomeSettingsComponent.spacing
                        )
                            .addComponent(
                                new ButtonComponent(
                                    new InternalSaveButtonComponentProvider(),
                                    new BoxComponent(
                                        new TextLineLabelComponent(
                                            HomeSettingsComponent.saveButtonText,
                                            FontDecoration.normal,
                                            Vector4fMaths.black
                                        ),
                                        BoxDimension.fixed(HomeSettingsComponent.buttonDimensions.x()),
                                        BoxDimension.fixed(HomeSettingsComponent.buttonDimensions.y())
                                    )
                                )
                            ).addComponent(
                                new ButtonComponent(
                                    new StaticButtonComponentProvider(this.provider::gotoRoot),
                                    new BoxComponent(
                                        new TextLineLabelComponent(
                                            HomeSettingsComponent.backButtonText,
                                            FontDecoration.normal,
                                            Vector4fMaths.black
                                        ),
                                        BoxDimension.fixed(HomeSettingsComponent.buttonDimensions.x()),
                                        BoxDimension.fixed(HomeSettingsComponent.buttonDimensions.y())
                                    )
                                )
                            )
                ),
                HomeSettingsComponent.spacing
            )
        );
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    public void clear() {
        this.uploadKey = UserSettingsConfig.instance.uploadKey;
        this.mouseSensitivity = UserSettingsConfig.instance.mouseSensitivity;
    }
    
}
