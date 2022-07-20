package game.gridgame.key_processors;

import java.util.Objects;

public record KeyAction(KeyActionEvent event, int ch) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyAction keyAction = (KeyAction) o;
        return ch == keyAction.ch && event == keyAction.event;
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, ch);
    }
}
