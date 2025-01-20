package dreamlink.overlay.home.edit;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.joml.Vector2i;
import org.joml.Vector2ic;

import dreamlink.disk.LocalRoomState;
import dreamlink.disk.PackState;
import dreamlink.graphics.text.FontDecoration;
import dreamlink.graphics.texture.sample.MenuTextureSample;
import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.box.BoxComponent;
import dreamlink.overlay.component.box.BoxDimension;
import dreamlink.overlay.component.button.ButtonComponent;
import dreamlink.overlay.component.button.IButtonComponentProvider;
import dreamlink.overlay.component.button.StaticButtonComponentProvider;
import dreamlink.overlay.component.list.ListRowData;
import dreamlink.overlay.component.list.select.IListSelectComponentProvider;
import dreamlink.overlay.component.list.select.ListSelectDropDownComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;
import dreamlink.overlay.component.text.line.TextLineLabelComponent;
import dreamlink.overlay.component.text.line.input.ITextLineInputComponentProvider;
import dreamlink.overlay.component.text.line.input.TextLineInputComponent;
import dreamlink.utility.file.FileFns;
import dreamlink.utility.maths.IntMaths;
import dreamlink.utility.maths.Vector4fMaths;

public class HomeEditRoomCreateComponent extends WrapperComponent{

    private class InternalButtonComponentProvider implements IButtonComponentProvider {

        @Override
        public void onButtonClick() {
            var roomName = HomeEditRoomCreateComponent.this.name;
            var packName = PackState.instance.getPack(HomeEditRoomCreateComponent.this.selectedPackIndex);
            var roomPath = LocalRoomState.instance.addRoom(roomName).file.toPath();
            FileFns.copyDirectory(packName.packPath, roomPath.resolve("pack").toFile());
            var terrainFile = roomPath.resolve("terrain.dat").toFile();

            try(
                var fileOutputStream = new FileOutputStream(terrainFile);
                var bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                var dataOutputStream = new DataOutputStream(fileOutputStream);
            ) {
                var parsedWidth = Integer.parseInt(HomeEditRoomCreateComponent.chunkDimensions[HomeEditRoomCreateComponent.this.selectedWidthIndex]);
                var parsedHeight = Integer.parseInt(HomeEditRoomCreateComponent.chunkDimensions[HomeEditRoomCreateComponent.this.selectedHeightIndex]);
                var parsedDepth = Integer.parseInt(HomeEditRoomCreateComponent.chunkDimensions[HomeEditRoomCreateComponent.this.selectedDepthIndex]);
                dataOutputStream.writeInt(parsedWidth);
                dataOutputStream.writeInt(parsedHeight);
                dataOutputStream.writeInt(parsedDepth);
                dataOutputStream.writeInt(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            HomeEditRoomCreateComponent.this.provider.gotoList();
        }

        @Override
        public boolean isButtonDisabled() {
            return !HomeEditRoomCreateComponent.this.canCreate;
        }

    }

    private class InternalRoomNameTextInputComponentProvider implements ITextLineInputComponentProvider {

        @Override
        public String getValue() {
            return HomeEditRoomCreateComponent.this.name;
        }

        @Override
        public void onChange(String value) {
            HomeEditRoomCreateComponent.this.name = value;
            HomeEditRoomCreateComponent.this.updateCanCreate();
        }

    }

    private class InternalPackProvider implements IListSelectComponentProvider {

        @Override
        public int getSelectedRowIndex() {
            return HomeEditRoomCreateComponent.this.selectedPackIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            var pack = PackState.instance.getPack(index);
            data.icon = MenuTextureSample.iconResourcePack;
            data.text = pack.packName;
        }

        @Override
        public void onChange(int index) {
            HomeEditRoomCreateComponent.this.selectedPackIndex = index;
            HomeEditRoomCreateComponent.this.updateCanCreate();
        }

        @Override
        public int getRowCount() {
            return PackState.instance.getSize();
        }

    }

    private class InternalChunkDepthProvider implements IListSelectComponentProvider {

        @Override
        public int getSelectedRowIndex() {
            return HomeEditRoomCreateComponent.this.selectedDepthIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            data.icon = MenuTextureSample.iconFile;
            data.text = HomeEditRoomCreateComponent.chunkDimensions[index];
        }

        @Override
        public void onChange(int index) {
            HomeEditRoomCreateComponent.this.selectedDepthIndex = index;
            HomeEditRoomCreateComponent.this.updateCanCreate();
        }

        @Override
        public int getRowCount() {
            return HomeEditRoomCreateComponent.chunkDimensions.length;
        }

    }

    private class InternalChunkHeightProvider implements IListSelectComponentProvider {

        @Override
        public int getSelectedRowIndex() {
            return HomeEditRoomCreateComponent.this.selectedHeightIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            data.icon = MenuTextureSample.iconFile;
            data.text = HomeEditRoomCreateComponent.chunkDimensions[index];
        }

        @Override
        public void onChange(int index) {
            HomeEditRoomCreateComponent.this.selectedHeightIndex = index;
            HomeEditRoomCreateComponent.this.updateCanCreate();
        }

        @Override
        public int getRowCount() {
            return HomeEditRoomCreateComponent.chunkDimensions.length;
        }

    }

    private class InternalChunkWidthProvider implements IListSelectComponentProvider {

        @Override
        public int getSelectedRowIndex() {
            return HomeEditRoomCreateComponent.this.selectedWidthIndex;
        }

        @Override
        public void setRowData(int index, ListRowData data) {
            data.icon = MenuTextureSample.iconFile;
            data.text = HomeEditRoomCreateComponent.chunkDimensions[index];
        }

        @Override
        public void onChange(int index) {
            HomeEditRoomCreateComponent.this.selectedWidthIndex = index;
            HomeEditRoomCreateComponent.this.updateCanCreate();
        }

        @Override
        public int getRowCount() {
            return HomeEditRoomCreateComponent.chunkDimensions.length;
        }

    }

    private static final int itemSpacing = 5;
    private static final int dropDownHeight = 90;
    private static final Vector2ic buttonDimensions = new Vector2i(160, 24);

    private static final String[] chunkDimensions = new String[] {
        "1", "2", "3", "4", "5", "6", "7", "8"
    };

    private final IComponent component;
    private final IHomeEditComponentProvider provider;
    private int selectedWidthIndex = 0;
    private int selectedHeightIndex = 0;
    private int selectedDepthIndex = 0;
    private int selectedPackIndex;
    private String name;
    private boolean canCreate;

    public void clear() {
        this.name = "";
        this.canCreate = false;
        this.selectedWidthIndex = 0;
        this.selectedHeightIndex = 0;
        this.selectedDepthIndex = 0;
        this.selectedPackIndex = 0;
    }

    public HomeEditRoomCreateComponent(IHomeEditComponentProvider provider) {
        this.provider = provider;
        this.component = new VerticalSpanComponent(
            Alignment.start,
            HomeEditRoomCreateComponent.itemSpacing
        )
            .addComponent(
                new TextLineLabelComponent(
                    "Name:",
                    FontDecoration.underline,
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new TextLineInputComponent(
                    new InternalRoomNameTextInputComponentProvider()
                )
            )
            .addComponent(
                new BoxComponent(
                    new HorizontalSpanComponent(
                        Alignment.start,
                        HomeEditRoomCreateComponent.itemSpacing
                    )
                        .addComponent(
                            new VerticalSpanComponent(
                                Alignment.start,
                                HomeEditRoomCreateComponent.itemSpacing
                            )
                                .addComponent(
                                    new TextLineLabelComponent(
                                        "Width:",
                                        FontDecoration.underline,
                                        Vector4fMaths.black
                                    )
                                )
                                .addComponent(
                                    new ListSelectDropDownComponent(
                                        new InternalChunkWidthProvider(),
                                        HomeEditRoomCreateComponent.dropDownHeight
                                    )
                                )
                        )
                        .addComponent(
                            new VerticalSpanComponent(
                                Alignment.start,
                                HomeEditRoomCreateComponent.itemSpacing
                            )
                                .addComponent(
                                    new TextLineLabelComponent(
                                        "Height:",
                                        FontDecoration.underline,
                                        Vector4fMaths.black
                                    )
                                )
                                .addComponent(
                                    new ListSelectDropDownComponent(
                                        new InternalChunkHeightProvider(),
                                        HomeEditRoomCreateComponent.dropDownHeight
                                    )
                                )
                        )
                        .addComponent(
                            new VerticalSpanComponent(
                                Alignment.start,
                                HomeEditRoomCreateComponent.itemSpacing
                            )
                                .addComponent(
                                    new TextLineLabelComponent(
                                        "Depth:",
                                        FontDecoration.underline,
                                        Vector4fMaths.black
                                    )
                                )
                                .addComponent(
                                    new ListSelectDropDownComponent(
                                        new InternalChunkDepthProvider(),
                                        HomeEditRoomCreateComponent.dropDownHeight
                                    )
                                )
                        ),
                    BoxDimension.grow(Alignment.center),
                    BoxDimension.wrap()
                )
            )
            .addComponent(
                new TextLineLabelComponent(
                    "Pack:",
                    FontDecoration.underline,
                    Vector4fMaths.black
                )
            )
            .addComponent(
                new ListSelectDropDownComponent(
                    new InternalPackProvider(),
                    HomeEditRoomCreateComponent.dropDownHeight
                )
            )
            .addComponent(
                new HorizontalSpanComponent(
                    Alignment.center, 
                    HomeEditRoomCreateComponent.itemSpacing
                )
                    .addComponent(
                        new ButtonComponent(
                            new InternalButtonComponentProvider(),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    "Create",
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(HomeEditRoomCreateComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeEditRoomCreateComponent.buttonDimensions.y())
                            )
                        )
                    )
                    .addComponent(
                        new ButtonComponent(
                            new StaticButtonComponentProvider(this.provider::gotoList),
                            new BoxComponent(
                                new TextLineLabelComponent(
                                    "Back",
                                    FontDecoration.normal,
                                    Vector4fMaths.black
                                ),
                                BoxDimension.fixed(HomeEditRoomCreateComponent.buttonDimensions.x()),
                                BoxDimension.fixed(HomeEditRoomCreateComponent.buttonDimensions.y())
                            )
                        )
                    )
            );
    }

    private void updateCanCreate() {
        this.canCreate = true
            && IntMaths.inRange(HomeEditRoomCreateComponent.chunkDimensions.length, HomeEditRoomCreateComponent.this.selectedWidthIndex)
            && IntMaths.inRange(HomeEditRoomCreateComponent.chunkDimensions.length, HomeEditRoomCreateComponent.this.selectedHeightIndex)
            && IntMaths.inRange(HomeEditRoomCreateComponent.chunkDimensions.length, HomeEditRoomCreateComponent.this.selectedDepthIndex)
            && IntMaths.inRange(PackState.instance.getSize(), this.selectedPackIndex)
            && this.name.length() > 0
            && !LocalRoomState.instance.hasRoom(this.name);
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }

    
}
