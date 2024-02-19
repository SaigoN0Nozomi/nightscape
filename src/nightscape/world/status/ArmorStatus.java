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
        stats.add(Stat.armor, armorAdd);
    }
    public void update(Unit unit, float time){
        unit.armor = armorAdd;
        super.update(unit, time);
    }
}
