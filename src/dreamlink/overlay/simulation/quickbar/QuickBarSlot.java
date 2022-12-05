package dreamlink.overlay.simulation.quickbar;

import dreamlink.window.button.Button;

public class QuickBarSlot {

    public static final QuickBarSlot slot1 = new QuickBarSlot(Button.key1);
    public static final QuickBarSlot slot2 = new QuickBarSlot(Button.key2);
    public static final QuickBarSlot slot3 = new QuickBarSlot(Button.key3);
    public static final QuickBarSlot slot4 = new QuickBarSlot(Button.key4);
    public static final QuickBarSlot slot5 = new QuickBarSlot(Button.key5);
    public static final QuickBarSlot slot6 = new QuickBarSlot(Button.key6);
    public static final QuickBarSlot slot7 = new QuickBarSlot(Button.key7);
    public static final QuickBarSlot slot8 = new QuickBarSlot(Button.key8);
    public static final QuickBarSlot slot9 = new QuickBarSlot(Button.key9);
    public static final QuickBarSlot slot0 = new QuickBarSlot(Button.key0);

    private static final QuickBarSlot[] slots = new QuickBarSlot[] {
        QuickBarSlot.slot1,
        QuickBarSlot.slot2,
        QuickBarSlot.slot3,
        QuickBarSlot.slot4,
        QuickBarSlot.slot5,
        QuickBarSlot.slot6,
        QuickBarSlot.slot7,
        QuickBarSlot.slot8,
        QuickBarSlot.slot9,
        QuickBarSlot.slot0,
    };

    public static int getSize() {
        return QuickBarSlot.slots.length;
    }

    public static QuickBarSlot get(int index) {
        return QuickBarSlot.slots[index];
    }

    public final Button button;

    public QuickBarSlot(Button button) {
        this.button = button;
    }
    
}
