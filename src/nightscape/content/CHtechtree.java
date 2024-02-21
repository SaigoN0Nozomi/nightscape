package nightscape.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.Objectives;
import nightscape.content.blocks.*;

import static mindustry.content.TechTree.*;
import static nightscape.content.NSsectors.*;

public class CHtechtree {
    public static void load(){
        // pattern node(, () -> {});

        NSplanets.Chorda.techTree = nodeRoot("Chorda", NSBother.coreSatellite, () -> {
            node(NSBother.coreSystem, Seq.with(new Objectives.SectorComplete(passage)), () -> {});
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
                    node(NSBdistribution.driver, Seq.with(new Objectives.Research(NSLiquids.ammonia)), () -> {});
                });
                node(NSBother.storage, Seq.with(new Objectives.OnSector(badelaire)),() -> {
                    node(NSBother.unloader, Seq.with(new Objectives.OnSector(badelaire)),() -> {});
                });
            });

            //liquid distribution
            node(NSBdistribution.tConduit, () -> {
                node(NSBdistribution.tcRouter, () ->{
                    node(NSBdistribution.tcBridge);
                });
                node(NSBdistribution.tcJuniction);
                node(NSBdistribution.liquidContainer);
            });

            //production
            node(NSBproduction.nutExtractor, () -> {
                node(NSBproduction.shockDrill, () -> {
                    node(NSBproduction.veloniumFurnace, Seq.with(new Objectives.OnSector(iceCrater)), () -> {
                        node(NSBproduction.strebyPress, Seq.with(new Objectives.OnSector(purplePlateau)), () -> {});
                    });
                    node(NSBproduction.ozoneIncinerator, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
                    node(NSBproduction.cliffCrusher, Seq.with(new Objectives.OnSector(shieldValley)), () -> {
                        node(NSBproduction.siliconFurnace, () -> {
                            node(NSBproduction.ozoneCondenser, () -> {});
                            node(NSBproduction.naturitSeparator, Seq.with(new Objectives.OnSector(passage)), () -> {});
                        });
                        node(NSBproduction.eruptionDrill, Seq.with(new Objectives.Research(NSLiquids.ammonia)), () -> {});
                    });
                    node(NSBpower.ozoneHeater,  Seq.with(new Objectives.OnSector(iceCrater)), () -> {
                        node(NSBpower.heatRedirector, () -> {});
                        node(NSBpower.heatCore, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
                    });
                    node(NSBpower.SFGenerator, () -> {
                        node(NSBpower.node, () -> {});
                        node(NSBother.luminaire);
                        node(NSBpower.solarPanel, Seq.with(new Objectives.OnSector(badelaire)),() -> {});
                    });
                });
            });

            //turrets
            node(NSBturret.victim, () -> {
                node(NSBturret.flicker);
                node(NSBturret.adrenaline, () -> {
                    node(NSBturret.combustion, () -> {});
                    node(NSBturret.magnetic, () -> {
                        node(NSBturret.hornet, Seq.with(new Objectives.SectorComplete(passage), new Objectives.Research(NSLiquids.ammonia)), () -> {});
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
                    node(NSBother.armoredDoor, Seq.with(new Objectives.SectorComplete(frozenFault)), () -> {});
                });
                node(NSBother.radar, Seq.with(new Objectives.SectorComplete(frozenFault)), () -> {});
            });

            //items
            nodeProduce(NSitems.tantalum, () -> {
                nodeProduce(NSitems.velonium, () -> {});
                nodeProduce(NSitems.naturit, () -> {
                    nodeProduce(Liquids.ozone, () -> {
                        nodeProduce(NSLiquids.ammonia, () -> {});
                    });
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
                node(iceCrater, Seq.with(new Objectives.SectorComplete(safeEdge)), () -> {
                    node(shieldValley, Seq.with(new Objectives.SectorComplete(iceCrater)), () -> {
                        node(frozenFault, Seq.with(new Objectives.SectorComplete(shieldValley), new Objectives.Research(NSitems.streby), new Objectives.Research(NSBunits.baseConstructor) ), () -> {});
                    });
                    node(purplePlateau, Seq.with(new Objectives.SectorComplete(iceCrater)), () -> {
                        node(passage, Seq.with(new Objectives.SectorComplete(purplePlateau), new Objectives.SectorComplete(shieldValley)), () -> {
                            node(badelaire, Seq.with(new Objectives.SectorComplete(passage), new Objectives.SectorComplete(frozenFault), new Objectives.Research(NSBother.coreSystem)), () -> {});
                        });
                    });
                });
            });

            node(NSBunits.baseConstructor, () -> {
                node(NSunits.point, () -> {});
                node(NSunits.procursus, Seq.with(new Objectives.SectorComplete(purplePlateau)),() -> {});
                node(NSunits.ishi, Seq.with(new Objectives.SectorComplete(shieldValley)), () -> {});
                node(NSunits.gutta, Seq.with(new Objectives.OnSector(badelaire)), () -> {});
                node(NSBunits.supplementReconstructor, Seq.with(new Objectives.OnSector(frozenFault)), () -> {
                    node(NSunits.vector, Seq.with(new Objectives.OnSector(frozenFault)), () -> {});
                    node(NSunits.radius, Seq.with(new Objectives.OnSector(frozenFault)), () -> {});
                    node(NSunits.yama, Seq.with(new Objectives.OnSector(badelaire)), () -> {});
                    node(NSunits.pluvia, Seq.with(new Objectives.OnSector(badelaire)), () -> {});
                });
                node(NSBunits.payConv, Seq.with(new Objectives.SectorComplete(frozenFault)), () -> {
                    node(NSBunits.payRout, () -> {});
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