package org.apache.harmony.awt.datatransfer;

import java.awt.Point;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;


public class DragSourceEventProxy implements Runnable {
    public static final int DRAG_ACTION_CHANGED = 3;
    public static final int DRAG_DROP_END = 6;
    public static final int DRAG_ENTER = 1;
    public static final int DRAG_EXIT = 5;
    public static final int DRAG_MOUSE_MOVED = 4;
    public static final int DRAG_OVER = 2;
    private final DragSourceContext context;
    private final int modifiers;
    private final boolean success;
    private final int targetActions;
    private final int type;
    private final int userAction;
    private final int x;
    private final int y;

    public DragSourceEventProxy(DragSourceContext dragSourceContext, int i2, int i3, int i4, Point point, int i5) {
        this.context = dragSourceContext;
        this.type = i2;
        this.userAction = i3;
        this.targetActions = i4;
        this.x = point.x;
        this.y = point.y;
        this.modifiers = i5;
        this.success = false;
    }

    public DragSourceEventProxy(DragSourceContext dragSourceContext, int i2, int i3, boolean z, Point point, int i4) {
        this.context = dragSourceContext;
        this.type = i2;
        this.userAction = i3;
        this.targetActions = i3;
        this.x = point.x;
        this.y = point.y;
        this.modifiers = i4;
        this.success = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        switch (this.type) {
            case 1:
                this.context.dragEnter(newDragSourceDragEvent());
                return;
            case 2:
                this.context.dragOver(newDragSourceDragEvent());
                return;
            case 3:
                this.context.dropActionChanged(newDragSourceDragEvent());
                return;
            case 4:
                this.context.dragMouseMoved(newDragSourceDragEvent());
                return;
            case 5:
                this.context.dragExit(new DragSourceEvent(this.context, this.x, this.y));
                return;
            case 6:
                this.context.dragExit(new DragSourceDropEvent(this.context, this.userAction, this.success, this.x, this.y));
                return;
            default:
        }
    }

    private DragSourceDragEvent newDragSourceDragEvent() {
        return new DragSourceDragEvent(this.context, this.userAction, this.targetActions, this.modifiers, this.x, this.y);
    }
}
