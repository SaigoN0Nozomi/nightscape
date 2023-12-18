package nightscape.world.types.abilities;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.scene.ui.layout.Table;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Time;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import static mindustry.Vars.tilesize;

public class DamageFieldAbility extends Ability {
    public float reload = 60, radius = 20, damage = 10;
    public float rotateSpeed = 1, sectors = 5, secRad = 0.14f;
    public Effect useEffect;
    public Color radColor;
    public StatusEffect effect;
    public float effectDuration = reload;
    protected float timer;

    public void addStats(Table t) {
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage * reload / 60, 2) + " " + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + Stat.shootRange.localized() + ": [white]" + Strings.autoFixed(radius / tilesize, 2) + " " + StatUnit.blocks.localized());
        t.row();
        t.add(effect.emoji() + " " + effect.localizedName);
    }

    @Override
    public void update(Unit unit) {
        if ((timer += Time.delta) >= reload && unit.isShooting) {
            Damage.damage(unit.team, unit.x, unit.y, radius, damage);
            timer = 0f;
            useEffect.at(unit.x, unit.y);
            if (effect != null) {
                Units.nearbyEnemies(unit.team, unit.x, unit.y, radius, other -> {
                    other.apply(effect, effectDuration);
                });
            }
        }
    }

    @Override
    public void draw(Unit unit) {
        Draw.z(Layer.effect);
        Draw.color(radColor);

        if (unit.isShooting) {
            for (int i = 0; i < sectors; i++) {
                float rot = i * 360f / sectors - Time.time * rotateSpeed;
                Lines.arc(unit.x, unit.y, radius, secRad, rot);
            }
        }
    }
}
