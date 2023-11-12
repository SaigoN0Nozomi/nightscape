package nightscape.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.Objectives;

import static mindustry.content.TechTree.*;
import static nightscape.content.NSsectors.*;

public class CHtechtree {
    public static void load(){
        NSplanets.Chorda.techTree = nodeRoot("Chorda", NSBother.coreSatellite, () -> {
            //distribution
            node(NSBdistribution.tConveyor, () -> {
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
            //liquid
            node(NSBdistribution.tConduit, () -> {
                node(NSBdistribution.tcRouter, () ->{
                    node(NSBdistribution.tcBridge);
                });
                node(NSBdistribution.tcJuniction);
            });
            //production
            node(NSBproduction.nutExtractor, () -> {
                node(NSBproduction.shockDrill, () -> {
                    node(NSBproduction.cliffCrusher);
                });
                node(NSBpower.ozoneHeater, Seq.with(new Objectives.OnSector(frozenCanyon)), () -> {
                    node(NSBproduction.veloniumFurnace, () -> {
                        node(NSBproduction.siliconFurnace, Seq.with(new Objectives.OnSector(deepGap)), () -> {});
                    });
                    node(NSBpower.SFGenerator, () -> {
                        node(NSBpower.node);
                        node(NSBproduction.ozoneCondenser);
                    });
                    node(NSBother.mender, Seq.with(new Objectives.SectorComplete(frozenCanyon)), () -> {
                        node(NSBother.radar);
                    });
                    node(NSBpower.heatRedirector, () -> {
                        node(NSBpower.heatCore);
                    });
                });
            });
            //defence
            node(NSBother.tWall, () -> {
                node(NSBother.tWall_large);
            });
            //turrets
            node(NSBturret.victim, () -> {
                node(NSBturret.flicker, Seq.with(new Objectives.SectorComplete(safeEdge)), () -> {
                    node(NSBturret.combustion);
                });
                node(NSBturret.punctual);
                node(NSBturret.stelle);
            });
            //items
            nodeProduce(NSitems.naturit, () -> {
                nodeProduce(Liquids.ozone, () -> {
                    nodeProduce(Items.sand, () -> {
                        nodeProduce(Items.silicon, () -> {});
                    });
                });
                nodeProduce(NSitems.tantalum, () -> {
                    nodeProduce(NSitems.velonium, () -> {});
                });
            });
            //sectors
            node(safeEdge, () -> {
                node(frozenCanyon, Seq.with(new Objectives.Research(NSBturret.flicker)), () -> {
                    node(deepGap);
                });
            });
        });
    }
}
