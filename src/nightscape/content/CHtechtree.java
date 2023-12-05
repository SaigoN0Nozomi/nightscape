package nightscape.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.Objectives;

import static mindustry.content.TechTree.*;
import static nightscape.content.NSsectors.*;

public class CHtechtree {
    public static void load(){
        // pattern node(, () -> {});

        NSplanets.Chorda.techTree = nodeRoot("Chorda", NSBother.coreSatellite, () -> {
            //distribution
            node(NSBdistribution.tConveyor, () -> {
                node(NSBdistribution.aConveyor);
                node(NSBdistribution.tJunction, () -> {
                    node(NSBdistribution.tRouter, () -> {
                        node(NSBdistribution.tSorter, () -> {
                            node(NSBdistribution.tSorterInv);
                        });
                        node(NSBdistribution.tGate, () -> {
                            node(NSBdistribution.tGateInv);
                        });
                    });
                    node(NSBdistribution.tBridge);
                });
            });

            //liquid distribution
            node(NSBdistribution.tConduit, () -> {
                node(NSBdistribution.tcRouter, () ->{
                    node(NSBdistribution.tcBridge);
                });
                node(NSBdistribution.tcJuniction);
            });

            //production
            node(NSBproduction.nutExtractor, () -> {
                node(NSBproduction.shockDrill, () -> {
                    node(NSBproduction.veloniumFurnace, Seq.with(new Objectives.OnSector(iceCrater)), () -> {
                        node(NSBproduction.strebyPress, Seq.with(new Objectives.SectorComplete(iceCrater)), () -> {});
                    });
                    node(NSBproduction.cliffCrusher, Seq.with(new Objectives.SectorComplete(iceCrater)), () -> {});
                    node(NSBpower.ozoneHeater,  Seq.with(new Objectives.OnSector(iceCrater)), () -> {
                        node(NSBpower.heatRedirector, () -> {});
                    });
                });
            });

            //turrets
            node(NSBturret.victim, () -> {
                node(NSBturret.flicker);
                node(NSBturret.magnetic);
            });

            //defense
            node(NSBother.tWall, () ->{
                node(NSBother.tWall_large);
                node(NSBother.mender);
                node(NSBother.rWall, () -> {
                    node(NSBother.rWall_large);
                });
            });

            //items
            nodeProduce(NSitems.tantalum, () -> {
                nodeProduce(NSitems.velonium, () -> {});
                nodeProduce(NSitems.naturit, () -> {
                    nodeProduce(Liquids.ozone, () -> {});
                    nodeProduce(Items.sand, () -> {
                        nodeProduce(Items.silicon, () -> {});
                    });
                });
                nodeProduce(NSitems.zirconium, () -> {
                    nodeProduce(NSitems.streby, () -> {});
                });
            });

            //sectors
            node(safeEdge, () -> {
                node(iceCrater, () -> {
                });
            });
        });
    }
}
/*
            //production
            node(NSBproduction.cliffCrusher
            node(NSBproduction.strebyPress
            node(NSBpower.ozoneHeater
            node(NSBproduction.veloniumFurnace
            node(NSBproduction.siliconFurnace
            node(NSBpower.SFGenerator
            node(NSBpower.node
            node(NSBproduction
            node(NSBother.mender
            node(NSBother.radar
            node(NSBpower.heatRedirector
            node(NSBpower.heatCore
            node(NSBother.rWall
            node(NSBother.rWall_large
            //turrets
            node(NSBturret.combustion
            node(NSBturret.punctual
            node(NSBturret.stelle
 */