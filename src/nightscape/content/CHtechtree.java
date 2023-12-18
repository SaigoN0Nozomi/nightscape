package nightscape.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.Objectives;
import mindustry.world.blocks.defense.ShieldWall;

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
                        node(NSBproduction.strebyPress, Seq.with(new Objectives.OnSector(purplePlateau)), () -> {});
                    });
                    node(NSBproduction.cliffCrusher, Seq.with(new Objectives.OnSector(shieldValley)), () -> {
                        node(NSBproduction.siliconFurnace, () -> {
                            node(NSBproduction.ozoneCondenser, () -> {});
                        });
                    });
                    node(NSBpower.ozoneHeater,  Seq.with(new Objectives.OnSector(iceCrater)), () -> {
                        node(NSBpower.heatRedirector, () -> {});
                        node(NSBpower.heatCore, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
                    });
                    node(NSBpower.SFGenerator, () -> {
                        node(NSBpower.node, () -> {});
                    });
                });
            });

            //turrets
            node(NSBturret.victim, () -> {
                node(NSBturret.flicker);
                node(NSBturret.adrenaline, () -> {
                    node(NSBturret.combustion, () -> {});
                    node(NSBturret.magnetic, () -> {
                    });
                });
                node(NSBturret.stelle, Seq.with(new Objectives.SectorComplete(iceCrater)), () -> {});
            });

            //defense
            node(NSBother.tWall, () ->{
                node(NSBother.tWall_large);
                node(NSBother.mender);
                node(NSBother.rWall, () -> {
                    node(NSBother.rWall_large);
                });
                node(NSBother.radar, () -> {});
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
                    node(shieldValley);
                    node(purplePlateau);
                });
            });

            node(NSBunits.baseConstructor, () -> {
                node(NSunits.point, () -> {});
                node(NSunits.procursus, Seq.with(new Objectives.SectorComplete(purplePlateau)),() -> {});
                node(NSunits.ishi, Seq.with(new Objectives.SectorComplete(shieldValley)), () -> {});
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