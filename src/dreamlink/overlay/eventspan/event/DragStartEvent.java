package dreamlink.overlay.eventspan.event;

import dreamlink.overlay.eventspan.IEvent;

public class DragStartEvent implements IEvent {

    public Object draggedPayload;

    public void reset() {
        this.draggedPayload = null;
    }

}
