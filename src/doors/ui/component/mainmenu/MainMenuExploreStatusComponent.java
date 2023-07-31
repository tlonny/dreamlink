package doors.ui.component.mainmenu;

import doors.graphics.spritebatch.SpriteBatch;
import doors.graphics.text.FontDecoration;
import doors.ui.component.IComponent;
import doors.ui.component.TextComponent;
import doors.ui.component.border.DialogBorderComponent;
import doors.ui.component.layout.PaddingComponent;
import doors.ui.component.layout.box.BoxComponent;
import doors.ui.component.layout.box.GrowDimension;
import doors.ui.component.layout.box.WrapDimension;
import doors.ui.root.UIRoot;
import doors.utility.vector.Vector2in;
import doors.utility.vector.Vector3fl;

public class MainMenuExploreStatusComponent implements IComponent {

    private static int PADDING = 2;

    private TextComponent statusComponent;
    private DialogBorderComponent borderComponent;

    public MainMenuExploreStatusComponent() {
        this.statusComponent = new TextComponent("", FontDecoration.NORMAL, Vector3fl.BLACK);
        
        var paddingComponent = new PaddingComponent(this.statusComponent, PADDING);

        var spaceComponent = new BoxComponent(
            paddingComponent,
            new GrowDimension(),
            new WrapDimension()
        );

        this.borderComponent = new DialogBorderComponent(spaceComponent);
    }

    public void setStatus(String text, boolean isError) {

    }

    @Override
    public Vector2in getDimensions() {
        return this.borderComponent.getDimensions();
    }

    @Override
    public Vector2in getPosition() {
        return this.borderComponent.getPosition();
    }

    @Override
    public void calculateDimensions() {
        this.borderComponent.calculateDimensions();
    }

    @Override
    public void adjustDimensions(Vector2in availableSpace) {
        this.borderComponent.adjustDimensions(availableSpace);
    }

    @Override
    public void calculatePosition(Vector2in origin) {
        this.borderComponent.calculatePosition(origin);
    }

    @Override
    public void update(UIRoot root) {
        this.borderComponent.update(root);
    }

    @Override
    public void writeComponentToSpriteBatch(SpriteBatch spriteBatch) {
        this.borderComponent.writeComponentToSpriteBatch(spriteBatch);
    }
    
}
