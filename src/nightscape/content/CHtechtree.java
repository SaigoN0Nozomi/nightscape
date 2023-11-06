package nightscape.content;

import mindustry.content.Items;
import mindustry.content.Liquids;

import static mindustry.content.TechTree.*;
public class CHtechtree {
    public static void load(){
        NSplanets.Chorda.techTree = nodeRoot("Chorda", NSBother.coreSatellite, () -> {
            //distribution
            node(NSBdistribution.tConveyor, () -> {
                node(NSBdistribution.tJunction, () -> {
                    node(NSBdistribution.tRouter);
                    node(NSBdistribution.tBridge);
                });
            });
            //liquid
            node(NSBdistribution.tConduit, () -> {
                node(NSBdistribution.tcRouter, () ->{
                    node(NSBdistribution.tcBridge);
                });
            });
            //production
            node(NSBproduction.nutExtractor, () -> {
                node(NSBproduction.shockDrill);
                node(NSBproduction.veloniumFurnace);
            });
            //defence
            node(NSBother.tWall, () -> {
                node(NSBother.tWall_large);
            });
            //turrets
            node(NSBturret.victim);
            //items
            nodeProduce(NSitems.tantalum, () -> {
                nodeProduce(NSitems.naturit, () -> {
                    nodeProduce(Items.sand, () -> {});
                });
                nodeProduce(Liquids.ozone, () -> {
                    nodeProduce(Items.silicon, () -> {});
                });
            });
        });
    }
}
