package nightscape.world.status;

import arc.util.Strings;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;

public class ArmorStatus extends StatusEffect {
    public float armorAdd;
    public ArmorStatus(String name){
        super(name);
    }

    public void setStats(){
        super.setStats();
        stats.add(Stat.armor, Strings.autoFixed(armorAdd, 0));
    }
    public void update(Unit unit, float time){
        unit.armor = unit.type.armor;
        if(time > 1) {
            unit.armor = unit.armor + armorAdd;
        }
        super.update(unit, time);
    }
}
