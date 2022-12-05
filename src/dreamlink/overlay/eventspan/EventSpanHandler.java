package dreamlink.overlay.eventspan;

import dreamlink.overlay.eventspan.event.DragEndEvent;
import dreamlink.overlay.eventspan.event.DragStartEvent;
import dreamlink.overlay.eventspan.event.FocusEndEvent;
import dreamlink.overlay.eventspan.event.FocusStartEvent;
import dreamlink.overlay.eventspan.event.HoverEndEvent;
import dreamlink.overlay.eventspan.event.HoverStartEvent;
import dreamlink.overlay.eventspan.event.PressEndEvent;
import dreamlink.overlay.eventspan.event.PressStartEvent;

public class EventSpanHandler {

    private boolean isHovered;
    private boolean isFocused;
    private boolean isPressed;
    private boolean isDragged;

    public boolean isHovered() {
        return this.isHovered;
    }

    public boolean isFocused() {
        return this.isFocused;
    }

    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isDragged() {
        return this.isDragged;
    }

    public void onEvent(IEvent event) {
        if(event instanceof HoverStartEvent) {
            this.isHovered = true;
        } else if(event instanceof HoverEndEvent) {
            this.isHovered = false;
        } else if(event instanceof PressStartEvent) {
            this.isPressed = true;
        } else if(event instanceof PressEndEvent) {
            this.isPressed = false;
        } else if(event instanceof DragStartEvent) {
            this.isDragged = true;
        } else if(event instanceof DragEndEvent) {
            this.isDragged = false;
        } else if(event instanceof FocusStartEvent) {
            this.isFocused = true;
        } else if(event instanceof FocusEndEvent) {
            this.isFocused = false;
        }
    }
    
}
