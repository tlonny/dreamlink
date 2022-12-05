package dreamlink.overlay.home.edit;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.config.UserSettingsConfig;
import dreamlink.disk.LocalRoomState;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.PublishOverlay;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.EmptyComponent;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.checkbox.CheckBoxComponent;
import dreamlink.overlay.component.checkbox.ICheckBoxComponentProvider;
import dreamlink.overlay.component.icon.IconComponent;
import dreamlink.overlay.component.icon.StaticIconComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.select.IListSelectComponentProvider;
import dreamlink.overlay.component.list.select.ListSelectComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.state.SimulationState;
import dreamlink.state.scene.LoadingScene;
import dreamlink.state.scene.PublishScene;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;
import dreamlink.world.World;

public class HomeEditRoomListComponent extends WrapperComponent {

    private class EditTerrainButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            SimulationState.instance.setAllowCrouch(false);
            SimulationState.instance.setAllowEdit(true);
            SimulationState.instance.setAllowOpenDoors(false);
            SimulationState.instance.setShowHiddenBlocks(true);
            SimulationState.instance.setNoClip(true);

            var localRoom = LocalRoomState.instance.getRoomByIndex(HomeEditRoomListComponent.this.selectedIndex);
            World.instance.setRoom(localRoom.roomName);
            LoadingScene.instance.bind();
        }

        @Override
        public boolean isButtonDisabled() {
            return !IntMaths.inRange(LocalRoomState.instance.getSize(), HomeEditRoomListComponent.this.selectedIndex);
        }

    }

    private class InternalLockDeleteCheckboxComponentProvider implements ICheckBoxComponentProvider {

        @Override
        public boolean isCheckBoxDisabled() {
            return false;
        }

        @Override
        public boolean getValue() {
            return HomeEditRoomListComponent.this.allowDeletes;
        }

        @Override
        public void onChange(boolean isChecked) {
            HomeEditRoomListComponent.this.allowDeletes = isChecked;
        }

    }

    private class InternalDeleteButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var localRoom = LocalRoomState.instance.getRoomByIndex(HomeEditRoomListComponent.this.selectedIndex);
            LocalRoomState.instance.removeRoom(localRoom.roomName);
        }

        @Override
        public boolean isButtonDisabled() {
            return false
                || !HomeEditRoomListComponent.this.allowDeletes
                || !IntMaths.inRange(LocalRoomState.instance.getSize(), HomeEditRoomListComponent.this.selectedIndex);
        }

    }

    private class InternalRenameButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var localRoom = LocalRoomState.instance.getRoomByIndex(HomeEditRoomListComponent.this.selectedIndex);
            HomeEditRoomListComponent.this.provider.gotoEdit(localRoom);
        }

        @Override
        public boolean isButtonDisabled() {
            return !IntMaths.inRange(LocalRoomState.instance.getSize(), HomeEditRoomListComponent.this.selectedIndex);
        }

    }

    private class InternalPublishButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var localRoom = LocalRoomState.instance.getRoomByIndex(HomeEditRoomListComponent.this.selectedIndex);
            PublishOverlay.instance.setRoom(localRoom);
            PublishScene.instance.bind();
        }

        @Override
        public boolean isButtonDisabled() {
            return false
                || !IntMaths.inRange(LocalRoomState.instance.getSize(), HomeEditRoomListComponent.this.selectedIndex)
                || !HomeEditRoomListComponent.this.isValidSelection
                || UserSettingsConfig.instance.uploadKey.length() == 0;
        }

    }

    private class InternalRoomSelectProvider implements IListSelectComponentProvider {

        @Override
        public int getRowCount() {
            return LocalRoomState.instance.getSize();
        }

        @Override
        public int getSelectedRowIndex() {
            return HomeEditRoomListComponent.this.selectedIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            data.text = LocalRoomState.instance.getRoomByIndex(index).roomName;
            data.icon = MenuTextureSample.iconFileStack;
        }

        @Override
        public void onChange(int index) {
            HomeEditRoomListComponent.this.selectedIndex = index;
            HomeEditRoomListComponent.this.updateIsValidSelection();
        }
    }

    private static final String createButtonText = "Create Room";
    private static final String editButtonText = "Edit";
    private static final String editMetadataButtonText = "Edit Metadata";
    private static final String deleteAllowText = "Allow Delete: ";
    private static final String publishButtonText = "Publish";
    private static final String deleteButtonText = "Delete";
    private static final String instructionsText = "Select Room to Edit:";
    private static final Vector2ic buttonDimensions = new Vector2i(120, 24);
    private static final Vector2ic iconDimensions = new Vector2i(16, 16);

    private static final int itemSpacing = 5;

    private IHomeEditComponentProvider provider;
    private final IComponent component;
    private int selectedIndex = -1;
    private boolean allowDeletes;
    private boolean isValidSelection;

    public HomeEditRoomListComponent(IHomeEditComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            HomeEditRoomListComponent.itemSpacing
        ).addComponent(
            new HorizontalSpanComponent(
                Alignment.center, 
                HomeEditRoomListComponent.itemSpacing
            )
                .addComponent(
                    new TextLineLabelComponent(
                        HomeEditRoomListComponent.instructionsText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
                .addComponent(
                    new BoxComponent(
                        new EmptyComponent(),
                        BoxDimension.grow(),
                        BoxDimension.wrap()
                    )
                )
                .addComponent(
                    new TextLineLabelComponent(
                        HomeEditRoomListComponent.deleteAllowText,
                        FontDecoration.normal,
                        Vector4fMaths.black
                    )
                )
                .addComponent(
                    new CheckBoxComponent(
                        new InternalLockDeleteCheckboxComponentProvider()
                    )
                )
        ).addComponent(
            new ListSelectComponent(new InternalRoomSelectProvider())
        ).addComponent(
            new HorizontalSpanComponent(
                Alignment.center, 
                HomeEditRoomListComponent.itemSpacing
            )
                .addComponent(
                    new ButtonComponent(
                        new StaticButtonComponentProvider(this.provider::gotoCreate),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeEditRoomListComponent.createButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.y())
                        )
                    )
                )
                .addComponent(
                    new ButtonComponent(
                        new EditTerrainButtonComponentProvider(),
                        new BoxComponent(
                            new HorizontalSpanComponent(
                                Alignment.center, 
                                HomeEditRoomListComponent.itemSpacing
                            )
                                .addComponent(
                                    new IconComponent(
                                        new StaticIconComponentProvider(MenuTextureSample.iconGlobe),
                                        HomeEditRoomListComponent.iconDimensions
                                    )
                                )
                                .addComponent(
                                    new TextLineLabelComponent(
                                        HomeEditRoomListComponent.editButtonText,
                                        FontDecoration.normal,
                                        Vector4fMaths.black
                                    )
                                ),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.y())
                        )
                    )
                )
                .addComponent(
                    new ButtonComponent(
                        new InternalRenameButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeEditRoomListComponent.editMetadataButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.y())
                        )
                    )
                )
                .addComponent(
                    new ButtonComponent(
                        new InternalPublishButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeEditRoomListComponent.publishButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.black
                            ),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.y())
                        )
                    )
                )
                .addComponent(
                    new ButtonComponent(
                        new InternalDeleteButtonComponentProvider(),
                        new BoxComponent(
                            new TextLineLabelComponent(
                                HomeEditRoomListComponent.deleteButtonText,
                                FontDecoration.normal,
                                Vector4fMaths.red
                            ),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.x()),
                            BoxDimension.fixed(HomeEditRoomListComponent.buttonDimensions.y())
                        )
                    )
                )
        );
    }

    public void updateIsValidSelection() {
        this.isValidSelection = IntMaths.inRange(LocalRoomState.instance.getSize(), this.selectedIndex);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
}
