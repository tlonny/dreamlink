package dreamlink.overlay.component.text.line.input;

import dreamlink.graphics.text.FontDecoration;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.text.CharacterData;
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

public class TextLineInputComponent extends WrapperComponent implements IEventSpan {

    private class InternalTextLineViewComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData characterState) {
            var value = TextLineInputComponent.this.provider.getValue();
            var inputLength = value.length();

            if(characterIndex < inputLength) {
                characterState.set(
                    value.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterState.set(
                ' ',
                FontDecoration.normal,
                Vector4fMaths.white,
                Vector4fMaths.black
            );
        }

        @Override
        public int getCharacterOffset() {
            if(!TextLineInputComponent.this.eventHandler.isFocused()) {
                return 0;
            }

            var visibleCharacters = TextLineInputComponent.this.textLineViewComponent.getVisibleCharacterCount() - 1;
            var inputLength = TextLineInputComponent.this.provider.getValue().length();
            return Math.max(inputLength - visibleCharacters, 0);
        }

        @Override
        public int getCharacterSize() {
            var adjustedSystemTime = System.currentTimeMillis() - TextLineInputComponent.this.systemTimeOffset;
            var isHighlighted =  adjustedSystemTime % cursorBlinkPeriod < cursorBlinkPeriod * cursorBlinkFactor;
            var isSelected = TextLineInputComponent.this.eventHandler.isFocused()  && isHighlighted;
            return TextLineInputComponent.this.provider.getValue().length() + (isSelected ? 1 : 0);
        }

    }
    
    private static final int padding = 2;
    private static final int cursorBlinkPeriod = 400;
    private static final float cursorBlinkFactor = 0.5f;

    private long systemTimeOffset;
    private final IComponent component;
    private final ITextLineInputComponentProvider provider;
    private final EventSpanHandler eventHandler = new EventSpanHandler();
    private final TextLineViewComponent textLineViewComponent;

    public TextLineInputComponent(ITextLineInputComponentProvider provider) {
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
            this.updateBuilder.setLength(0);
            if(value != null) {
                this.updateBuilder.append(value);
            }
            for(var character : Window.instance.getCharacters()) {
                isChange = true;
                this.updateBuilder.append(character);
                this.systemTimeOffset = System.currentTimeMillis();
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
