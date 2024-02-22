package nightscape.world.status;

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
        stats.add(Stat.armor, (int)(armorAdd));
    }
    public void update(Unit unit, float time){
        unit.armor = unit.type.armor;
        if(time > 1) {
            unit.armor = unit.armor + armorAdd;
        }
        super.update(unit, time);
    }
}
