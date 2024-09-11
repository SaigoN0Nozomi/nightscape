package nightscape.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.game.Objectives;
import nightscape.content.blocks.*;

import static mindustry.content.TechTree.*;
import static nightscape.content.NSplanets.Pryma;
import static nightscape.content.NSsectors.*;

public class CHtechtree {
    public static void load(){
        // pattern:      node(, () -> {});

        /*
            node(NSBdistribution.driver, Seq.with(new Objectives.Research(NSLiquids.ammonia)), () -> {});

            node(NSBproduction.strebyPress, Seq.with(new Objectives.OnSector(purplePlateau)), () -> {});
            node(NSBproduction.naturitSeparator, Seq.with(new Objectives.OnSector(passage)), () -> {});

            node(NSBproduction.eruptionDrill, Seq.with(new Objectives.Research(NSLiquids.ammonia)), () -> {});

            node(NSBpower.heatCore, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
            node(NSBother.luminaire);

            node(NSBturret.magnetic, () -> {
            node(NSBturret.hornet, Seq.with(new Objectives.SectorComplete(passage), new Objectives.Research(NSLiquids.ammonia)), () -> {});

            node(NSBother.umbrella, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
       */
        NSplanets.Chorda.techTree = Pryma.techTree = nodeRoot("Decim solar system", NSBother.coreSatellite, () -> {
            //distribution
            node(NSBdistribution.tConveyor, () -> {
                node(NSBdistribution.aConveyor);
                node(NSBdistribution.dConveyor);
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
                    node(NSBother.storage, Seq.with(new Objectives.SectorComplete(aquarry)), () -> {
                        node(NSBother.unloader, Seq.with(new Objectives.SectorComplete(aquarry)), () -> {});
                    });
                });
                node(NSBunits.payConv, Seq.with(new Objectives.SectorComplete(bcross)), () -> {
                    node(NSBunits.payRout, Seq.with(new Objectives.SectorComplete(bcross)), () -> {});
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
                    node(NSBproduction.seaDrill, Seq.with(new Objectives.OnSector(frozenCanyon)), () -> {
                        node(NSBproduction.seaBuffer, () -> {});
                    });
                    node(NSBproduction.ozoneCondenser, Seq.with(new Objectives.SectorComplete(bcross)), () -> {});
                });
                node(NSBproduction.veloniumFurnace, Seq.with(new Objectives.OnSector(wasteland)), () -> {
                    node(NSBpower.ozoneHeater, () -> {
                        node(NSBpower.heatRedirector, () -> {});
                    });
                    node(NSBproduction.ozoneIncinerator, Seq.with(new Objectives.Research(NSBpower.SFGenerator)), () -> {});
                    node(NSBproduction.siliconFurnace, Seq.with(new Objectives.Research(NSLiquids.fernum)), () -> {
                        node(NSBproduction.highFurn, Seq.with(new Objectives.OnSector(sang)), () -> {});
                    });
                });
                node(NSBpower.SFGenerator, () -> {
                    node(NSBpower.node, () -> {
                        node(NSBpower.powerStorage, () -> {});
                        node(NSBother.luminaire);
                    });
                    //node(NSBpower.solarPanel, Seq.with(new Objectives.SectorComplete()), () -> {});
                });
            });

            //defense
            node(NSBother.tWall, () ->{
                node(NSBother.tWall_large);
                node(NSBother.mender, () -> {});
                node(NSBother.gayWall, () -> {
                    node(NSBother.gayWall_large);
                });
                node(NSBother.armoredDoor, Seq.with(new Objectives.OnSector(bcross)), () -> {
                    node(NSBother.armoredDoorBig);
                    node(NSBunits.repair, Seq.with(new Objectives.OnSector(bcross)), () -> {});
                });
            });

            //turrets
            node(NSBturret.victim, () -> {
                node(NSBturret.flicker);
                node(NSBturret.adrenaline, () -> {
                    node(NSBturret.emission, () -> {
                        node(NSBturret.pulse);
                    });
                    node(NSBturret.combustion, Seq.with(new Objectives.SectorComplete(bcross)), () -> {});
                });
                node(NSBturret.stelle, () -> {});
            });

            //items
            nodeProduce(NSitems.tantalum, () -> {
                nodeProduce(NSitems.velonium, () -> {
                    nodeProduce(NSLiquids.fernum, () -> {
                        nodeProduce(Items.silicon, () -> {});
                    });
                });
                nodeProduce(NSitems.naturit, () -> {
                    nodeProduce(Liquids.ozone, () -> {
                        nodeProduce(NSLiquids.ammonia, () -> {});
                    });
                });
                nodeProduce(NSitems.zirconium, () -> {
                    nodeProduce(NSitems.dense, () -> {});
                });
            });
            //units
            node(NSBunits.baseConstructor, () -> {
                node(NSunits.point, () -> {});
                node(NSunits.procursus, () -> {});
                node(NSunits.gutta, Seq.with(new Objectives.OnSector(bcross)), () -> {});
                node(NSunits.ishi, Seq.with(new Objectives.SectorComplete(sang)), () -> {});
                node(NSBunits.supplementReconstructorOne, Seq.with(new Objectives.SectorComplete(bcross)), () -> {
                    node(NSunits.pluvia, () -> {});
                    node(NSunits.radius, () -> {});
                    node(NSBunits.supplementReconstructorTwo, () -> {
                        node(NSunits.yama, () -> {});
                        node(NSunits.vector, () -> {});
                    });
                });
            });

            //sectors
            node(safeEdge, () -> {
                node(wasteland, Seq.with(new Objectives.SectorComplete(safeEdge)), () -> {
                    node(frozenCanyon, Seq.with(new Objectives.SectorComplete(wasteland)), () -> {
                        node(bcross, Seq.with(new Objectives.SectorComplete(frozenCanyon), new Objectives.Research(NSunits.procursus)), () -> {
                            node(sang, Seq.with(new Objectives.SectorComplete(bcross)), () -> {});
                        });
                        node(aquarry, Seq.with(new Objectives.SectorComplete(frozenCanyon)), () -> {
                            node(mise, Seq.with(new Objectives.SectorComplete(aquarry)), () -> {});
                        });
                    });
                });
            });

            node(NSBother.coreStar, Seq.with(new Objectives.SectorComplete(aquarry)), () -> {});
        });
    }
}