package nightscape.world.block.turrets;

import mindustry.world.blocks.ControlBlock;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class AdrenalineTurret extends ItemTurret {
    public float baseReload, reloadPerHp;
    public AdrenalineTurret(String name){
        super(name);
    }
    public class AdrenalineTurretBuild extends ItemTurretBuild implements ControlBlock {
        @Override
        public void updateTile(){
            reload = baseReload + reloadPerHp * health;
            super.updateTile();
        }
    }
}
