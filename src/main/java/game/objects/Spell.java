package game.objects;

import game.utils.Location;
import game.utils.enums.Direction;
import game.utils.enums.SpellType;

/**
 * Contains spell details.
 */
public class Spell extends MovingObject {

    private final SpellType spellType;

    public Spell(SpellType spellType, Location location, Direction direction) {
        super(location, direction);
        this.spellType = spellType;
    }

    public SpellType getSpell() {
        return spellType;
    }
}
