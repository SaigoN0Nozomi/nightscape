package nightscape.entity.abilities;

import arc.scene.ui.layout.Table;
import arc.util.Strings;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.UnitSpawnAbility;
import mindustry.gen.Unit;
import mindustry.type.UnitType;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import nightscape.content.effects.baseunitFx;

public class DamageSpawnAbility extends UnitSpawnAbility {
    public float damage;
    public float range = 120;
    public float charge;
    public Effect fx = baseunitFx.drain;
    public DamageSpawnAbility(UnitType unit, float spawnTime, float spawnX, float spawnY, float damage){
        this.unit = unit;
        this.spawnTime = spawnTime;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.damage = damage;
    }
    public DamageSpawnAbility(){}

    public void addStats(Table t){
        t.add("[lightgray]" + Stat.damage.localized() + ": [white]" + Strings.autoFixed(damage, 2));
        t.row();
        t.add("[lightgray]" + Stat.range.localized() + ": [white]" + Strings.autoFixed(range / 8, 2) + " " + StatUnit.blocks.localized());
        t.row();
        t.add("[lightgray]" + Stat.buildTime.localized() + ": [white]" + Strings.autoFixed(spawnTime / 60f, 2) + " " + StatUnit.seconds.localized());
        t.row();
        t.add(unit.emoji() + " " + unit.localizedName);
    }
    @Override
    public void update(Unit unit) {
        var target = Units.closestEnemy(unit.team, unit.x, unit.y, range, u -> !u.dead());
        var targetBuild = Units.findEnemyTile(unit.team, unit.x, unit.y, range, t -> !t.dead);

        if(target != null && charge == 0){
            target.damage(damage);
            fx.at(target);
            charge += 20;
        }
        else if (targetBuild != null && charge == 0){
            targetBuild.damage(damage);
            fx.at(targetBuild);
            charge += 20;
        }
        if(charge > 0){
            super.update(unit);
            charge--;
        }
    }
}
