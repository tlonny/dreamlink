package dreamlink.overlay.component.tab;

import dreamlink.overlay.component.Alignment;
import dreamlink.overlay.component.IComponent;
import dreamlink.overlay.component.PaddingComponent;
import dreamlink.overlay.component.WrapperComponent;
import dreamlink.overlay.component.border.tab.TabBodyBorderComponent;
import dreamlink.overlay.component.choice.ChoiceComponent;
import dreamlink.overlay.component.span.HorizontalSpanComponent;
import dreamlink.overlay.component.span.VerticalSpanComponent;

public class TabViewComponent extends WrapperComponent {

    private class InternalTabComponentProvider implements ITabComponentProvider {

        private final int tabID;

        public InternalTabComponentProvider(int tabID) {
            this.tabID = tabID;
        }

        @Override
        public boolean isTabSelected() {
            return this.tabID == TabViewComponent.this.selectedTabID;
        }
        
        @Override
        public void onTabSelect() {
            TabViewComponent.this.selectedTabID = this.tabID;
        }

    }

    private static final int padding = 4;

    private final HorizontalSpanComponent tabGroupComponent;
    private final ChoiceComponent<Integer> viewComponent;
    private final IComponent component;

    private int selectedTabID = 0;
    private int tabIDGenerator = 0;

    public TabViewComponent() {
        this.tabGroupComponent = new HorizontalSpanComponent(Alignment.start, 0);
        this.viewComponent = new ChoiceComponent<>(this::getSelectedTabID);
        this.component = new VerticalSpanComponent(Alignment.start, 0)
            .addComponent(this.tabGroupComponent)
            .addComponent(
                new TabBodyBorderComponent(
                    new PaddingComponent(
                        this.viewComponent,
                        TabViewComponent.padding
                    )
                )
            );
    }

    public TabViewComponent addTab(IComponent tabContent, IComponent content) {
        this.viewComponent.addComponent(this.tabIDGenerator, content);

        this.tabGroupComponent.addComponent(
            new TabComponent(
                new InternalTabComponentProvider(this.tabIDGenerator),
                tabContent
            )
        );

        this.tabIDGenerator += 1;
        return this;
    }

    private int getSelectedTabID() {
        return this.selectedTabID;
    }

    @Override
    public IComponent getComponent() {
        return this.component;
    }
    
}
