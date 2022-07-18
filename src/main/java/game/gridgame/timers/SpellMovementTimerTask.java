package game.gridgame.timers;

import game.gridgame.GridManager;
import game.objects.Spell;
import game.utils.Dimensions;
import game.utils.Location;
import game.utils.enums.LocationType;
import game.utils.enums.SpellType;

import java.util.TimerTask;

/**
 * Processes the spell movements on a timer.
 */
public class SpellMovementTimerTask extends TimerTask {

    private final GridManager gridManager;
    private final Spell spell;

    public SpellMovementTimerTask(Spell spell, GridManager gridManager) {
        this.spell       = spell;
        this.gridManager = gridManager;
    }

    @Override
    public void run() {
        int yMove = 0;
        int xMove = 0;

        switch (spell.getDirection()) {
            case UP -> yMove--;
            case DOWN -> yMove++;
            case RIGHT -> xMove++;
            case LEFT -> xMove--;
            default -> throw new IllegalStateException(
                    "Unexpected value: " + spell.getDirection());
        }

        int futX = spell.getLocation().getXLoc() + xMove;
        int futY = spell.getLocation().getYLoc() + yMove;
        Location newSpellLoc = new Location(futX, futY);

        if (futX < 0 || futY < 0 || futX > Dimensions.GAME_SIZE - 1 || futY > Dimensions.GAME_SIZE - 1) {
            gridManager.clearCell(spell.getLocation());
            this.cancel();
            return;
        }

        gridManager.updateGridWithLocType(spell.getLocation(), newSpellLoc, getLocationType(spell.getSpell()));
        spell.setLocation(newSpellLoc);
    }


    private LocationType getLocationType(SpellType spellType) {
        return spellType == SpellType.ENERGY ? LocationType.ENERGY : LocationType.FIRE;
    }
}
