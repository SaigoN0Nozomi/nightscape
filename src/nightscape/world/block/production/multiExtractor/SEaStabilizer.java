package nightscape.world.block.production.multiExtractor;

import arc.math.Mathf;
import mindustry.gen.Building;
import mindustry.world.Block;

public class SEaStabilizer extends Block{
    public float randPeriod = 60;
    public float breakChance = 0.001f;
    public SEaStabilizer(String name){
        super(name);
        update = true;
        solid = true;
        sync = true;
        drawArrow = false;
    }
    public class SEaBufferBuild extends Building {
        public float timer;
        @Override
        public void updateTile(){
            timer++;
            if(timer > randPeriod){
                if(Mathf.chanceDelta(breakChance)) {
                    damage(9999);
                }
                timer = 0;
            }
        }
    }
}
