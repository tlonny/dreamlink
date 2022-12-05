package dreamlink.overlay.home.settings;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.overlay.eventspan.EventSpanHandler;
import dreamlink.overlay.eventspan.IEvent;
import dreamlink.overlay.eventspan.IEventSpan;
import dreamlink.overlay.eventspan.IEventSpanRegistry;
import dreamlink.overlay.eventspan.event.PressStartEvent;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.window.Window;
import dreamlink.window.button.Button;

public class UploadKeyInputComponent extends WrapperComponent implements IEventSpan {

    private class InternalTextLineViewComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            var numSegments = characterIndex / (UploadKeyInputComponent.numUploadKeySegments + 1);
            var segmentIndex = characterIndex % (UploadKeyInputComponent.numUploadKeySegments + 1);

            var value = UploadKeyInputComponent.this.provider.getValue();
            var adjustedIndex = numSegments * UploadKeyInputComponent.uploadKeySegmentLength + segmentIndex;

            if(adjustedIndex >= value.length()) {
                characterState.set(
                    ' ',
                    FontDecoration.normal,
                    Vector4fMaths.white,
                    Vector4fMaths.black
                );
                return;
            }

            if(segmentIndex == UploadKeyInputComponent.uploadKeySegmentLength) {
                characterState.set(
                    '-',
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterState.set(
                value.charAt(adjustedIndex),
                FontDecoration.normal,
                Vector4fMaths.black
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            var value = UploadKeyInputComponent.this.provider.getValue();
            var length = Math.min(value.length(), UploadKeyInputComponent.keyLength);
            var numSegments = length / UploadKeyInputComponent.uploadKeySegmentLength;
            var numDashes = Math.min(numSegments, UploadKeyInputComponent.numUploadKeySegments - 1);
            var remainder = length % UploadKeyInputComponent.uploadKeySegmentLength;
            var totalLength = numSegments * UploadKeyInputComponent.uploadKeySegmentLength + numDashes + remainder;

            var adjustedSystemTime = System.currentTimeMillis() - UploadKeyInputComponent.this.systemTimeOffset;
            var isHighlighted =  adjustedSystemTime % cursorBlinkPeriod < cursorBlinkPeriod * cursorBlinkFactor;
            var isSelected = UploadKeyInputComponent.this.eventHandler.isFocused()  && isHighlighted;
            return totalLength + (isSelected ? 1 : 0);
            
        }

    }
    
    private static final int padding = 2;
    private static final int cursorBlinkPeriod = 400;
    private static final float cursorBlinkFactor = 0.5f;
    private static final int uploadKeySegmentLength = 4;
    private static final int numUploadKeySegments = 4;
    public static final int keyLength = numUploadKeySegments * uploadKeySegmentLength;

    private long systemTimeOffset;
    private final IComponent component;
    private final ITextLineInputComponentProvider provider;
    private final EventSpanHandler eventHandler = new EventSpanHandler();
    private final TextLineViewComponent textLineViewComponent;

    public UploadKeyInputComponent(ITextLineInputComponentProvider provider) {
        this.provider = provider;
        this.textLineViewComponent = new TextLineViewComponent(
            new InternalTextLineViewComponentProvider(),
            Alignment.start
        );

        this.component = new DialogBorderComponent(
            this::getDialogState,
            new PaddingComponent(
                this.textLineViewComponent,
                padding
            )
        );
    }

    private DialogState getDialogState() {
        return DialogState.focused;
    }

    public int getVisibleCharacterCount() {
        return this.textLineViewComponent.getVisibleCharacterCount();
    }

    @Override
    public void onEvent(IEvent event) {
        this.eventHandler.onEvent(event);
        if(event instanceof PressStartEvent) {
            this.systemTimeOffset = System.currentTimeMillis();
        }
    }

    @Override
    public int getDragDistanceThreshold() {
        return Integer.MAX_VALUE;
    }

    private final StringBuilder updateBuilder = new StringBuilder();

    @Override
    public void registerEventSpans(IEventSpanRegistry registry) {
        super.registerEventSpans(registry);
        if(this.eventHandler.isFocused()) {
            var isChange = false;
            var value = this.provider.getValue();
            var maxLength = numUploadKeySegments * uploadKeySegmentLength;
            this.updateBuilder.setLength(0);
            if(value != null) {
                this.updateBuilder.append(value);
            }
            for(var character : Window.instance.getCharacters()) {
                if(this.updateBuilder.length() >= maxLength) {
                    break;
                }
                if(Character.isAlphabetic(character) || Character.isDigit(character)) {
                    isChange = true;
                    this.updateBuilder.append(Character.toUpperCase(character));
                    this.systemTimeOffset = System.currentTimeMillis();
                }
            }

            if(Window.instance.isButtonPressed(Button.keyBackspace)) {
                isChange = true;
                this.updateBuilder.setLength(Math.max(0,this.updateBuilder.length() - 1));
                this.systemTimeOffset = System.currentTimeMillis();
            }

            if(isChange) {
                this.provider.onChange(this.updateBuilder.toString());
            }
        }

        registry.register(this);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
