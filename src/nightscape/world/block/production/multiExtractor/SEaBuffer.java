package nightscape.world.block.production.multiExtractor;

import arc.math.Mathf;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.Build;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.production.HeatCrafter;

import java.util.Random;

public class SEaBuffer extends Block {
    public float randPeriod = 60;
    public float breakChance = 0.001f;
    public SEaBuffer(String name){
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
