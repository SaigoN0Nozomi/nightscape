package nightscape.world.types;

import arc.net.dns.SRVRecord;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

public class AccWeapon extends Weapon{
    public float accelerate = 0;
    public float minReload = 30;
    public float maxReload = 60;
    public AccWeapon(String name){
        super(name);
    }

    @Override
    public void update(Unit unit, WeaponMount mount){
        super.update(unit, mount);

        if(unit.isShooting){
            if(this.reload > minReload){
                this.reload = this.reload - accelerate;
            }
        }
        else {
            this.reload = maxReload;
        }
    }
}
