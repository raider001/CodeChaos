package game.gridgame.key_processors;

import game.gridgame.GridGameManager;
import game.gridgame.GridManager;
import game.objects.Player;
import game.objects.Spell;
import game.gridgame.timers.SpellMovementTimerTask;
import game.utils.enums.SpellType;

import java.util.Timer;

/**
 * Processes the key inputs for spells.
 */
public class SpellProcessor {

    private final Player player;
    private final GridManager gridManager;
    private SpellType currentSpellType;

    public SpellProcessor(Player player, GridManager gridManager) {
        this.player      = player;
        this.gridManager = gridManager;
        currentSpellType = SpellType.ENERGY;
    }

    /**
     * Creates the spell by kicking off a timer.
     */
    public void castSpell() {
        Spell spell = new Spell(currentSpellType, player.getLocation(), player.getDirection());
        Timer spellTimer = new Timer();
        spellTimer.schedule(new SpellMovementTimerTask(spell, gridManager), GridGameManager.SPELL_DELAY,
                            GridGameManager.SPELL_PERIOD);
    }

    /**
     * Changes the current spell being used.
     */
    public void switchSpell() {
        currentSpellType = currentSpellType == SpellType.ENERGY ? SpellType.FIRE :
                           SpellType.ENERGY;
    }
}
