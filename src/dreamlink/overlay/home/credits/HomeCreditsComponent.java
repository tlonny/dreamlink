package dreamlink.overlay.home.credits;

import org.joml.Vector2i;

import dreamlink.graphics.sprite.template.ISpriteTemplate;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.EntityTextureSample;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.dialog.DialogBorderComponent;
import dreamlink.overlay.component.border.dialog.DialogState;
import dreamlink.overlay.component.border.dialog.StaticDialogBorderComponentProvider;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.select.IListSelectComponentProvider;
import dreamlink.overlay.component.list.select.ListSelectComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.CharacterData;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.view.ITextLineViewComponentProvider;
import dreamlink.overlay.component.text.line.view.TextLineViewComponent;
import dreamlink.overlay.component.window.WindowComponent;
import dreamlink.overlay.home.IHomeChildComponentProvider;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;

public class HomeCreditsComponent extends WrapperComponent {

    private class InternalCreditListProvider implements IListSelectComponentProvider {

        @Override
        public int getRowCount() {
            return CreditsRow.getSize();
        }

        @Override
        public int getSelectedRowIndex() {
            return HomeCreditsComponent.this.selectedRowIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            var creditRow = CreditsRow.get(index);
            data.text = creditRow.name;
            data.icon = creditRow.avatar;
        }

        @Override
        public void onChange(int index) {
            HomeCreditsComponent.this.selectedRowIndex = index;
        }

    }

    private class InternalNameComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData state) {
            if(characterIndex < HomeCreditsComponent.thanksPrefix.length()) {
                state.set(
                    HomeCreditsComponent.thanksPrefix.charAt(characterIndex),
                    FontDecoration.normal,
                    Vector4fMaths.black
                );
                return;
            }

            characterIndex -= HomeCreditsComponent.thanksPrefix.length();

            var selectedComponent = CreditsRow.get(HomeCreditsComponent.this.selectedRowIndex);
            state.set(
                selectedComponent.name.charAt(characterIndex),
                FontDecoration.underline,
                Vector4fMaths.blue
            );
        }

        @Override
        public int getCharacterOffset() {
            return 0;
        }

        @Override
        public int getCharacterSize() {
            if(!IntMaths.inRange(CreditsRow.getSize(), HomeCreditsComponent.this.selectedRowIndex)) {
                return HomeCreditsComponent.thanksPrefix.length();
            }

            return HomeCreditsComponent.thanksPrefix.length() + CreditsRow.get(HomeCreditsComponent.this.selectedRowIndex).name.length();
        }

    }

    private class InternalMessageComponentProvider implements ITextLineViewComponentProvider {

        @Override
        public void setCharacterState(int characterIndex, CharacterData state) {
            if(HomeCreditsComponent.this.selectedRowIndex == -1) {
                state.clear();
                return;
            }

            var selectedComponent = CreditsRow.get(HomeCreditsComponent.this.selectedRowIndex);
            if(characterIndex >= selectedComponent.message.length()) {
                state.clear();
                return;
            }

            state.set(
                selectedComponent.message.charAt(characterIndex),
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
            if(!IntMaths.inRange(CreditsRow.getSize(), HomeCreditsComponent.this.selectedRowIndex)) {
                return 0;
            }

            return CreditsRow.get(HomeCreditsComponent.this.selectedRowIndex).message.length();
        }

    }

    private static String windowTitle = "Dreamlink: Credits";
    private static String thanksPrefix = "Thanks to: ";
    private static Vector2i logoDimensions = new Vector2i(128, 128);
    private static Vector2i listDimensions = new Vector2i(180, 180);
    private static String backButtonText = "Back";
    private static int itemSpacing = 5;
    private static int itemPadding = 2;
    private static Vector2i buttonDimensions = new Vector2i(160, 24);
    private static Vector2i avatarDimensions = new Vector2i(36);

    private IComponent component;
    private IHomeChildComponentProvider provider;
    private int selectedRowIndex = 0;

    public HomeCreditsComponent(IHomeChildComponentProvider provider) {
        this.provider = provider;
        this.component = new WindowComponent(
            MenuTextureSample.iconFolder,
            HomeCreditsComponent.windowTitle,
            new PaddingComponent(
                new VerticalSpanComponent(
                    Alignment.start,
                    HomeCreditsComponent.itemSpacing
                ).addComponent(
                    new TextLineLabelComponent(
                        "A game by The Lonny Corporation", 
                        FontDecoration.normal, 
                        Vector4fMaths.black
                    )
                ).addComponent(
                    new HorizontalSpanComponent(
                        Alignment.center,
                        HomeCreditsComponent.itemSpacing
                    )
                        .addComponent(
                            new DialogBorderComponent(
                                new StaticDialogBorderComponentProvider(DialogState.focused) ,
                                new PaddingComponent(
                                    new IconComponent(
                                        new StaticIconComponentProvider(EntityTextureSample.logo),
                                        HomeCreditsComponent.logoDimensions
                                    ),
                                    HomeCreditsComponent.itemPadding
                                )
                            )
                        )
                        .addComponent(
                            new BoxComponent(
                                new ListSelectComponent(
                                    new InternalCreditListProvider()
                                ),
                                BoxDimension.min(HomeCreditsComponent.listDimensions.x),
                                BoxDimension.grow()
                            )
                        )
                ).addComponent(
                    new HorizontalSpanComponent(
                        Alignment.start,
                        HomeCreditsComponent.itemSpacing
                    ).addComponent(
                        new DialogBorderComponent(
                            new StaticDialogBorderComponentProvider(DialogState.focused),
                            new IconComponent(
                                this::getSelectedAvatar,
                                HomeCreditsComponent.avatarDimensions
                            )
                        )
                    ).addComponent(
                        new VerticalSpanComponent(
                            Alignment.start,
                            HomeCreditsComponent.itemSpacing
                        ).addComponent(
                            new TextLineViewComponent(
                                new InternalNameComponentProvider(),
                                Alignment.start
                            )
                        ).addComponent(
                            new TextLineViewComponent(
                                new InternalMessageComponentProvider(),
                                Alignment.start
                            )
                        )
                    )
                ).addComponent(
                    new BoxComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoRoot),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    HomeCreditsComponent.backButtonText,
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(HomeCreditsComponent.buttonDimensions.x),
                                BoxDimension.fixed(HomeCreditsComponent.buttonDimensions.y)
                            )
                        ),
                        BoxDimension.grow(),
                        BoxDimension.wrap()
                    )
                ),
                HomeCreditsComponent.itemSpacing
            )
        );
    }

    private ISpriteTemplate getSelectedAvatar() {
        if(!IntMaths.inRange(CreditsRow.getSize(), this.selectedRowIndex)) {
            return null;
        }

        return CreditsRow.get(this.selectedRowIndex).avatar;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    
}
