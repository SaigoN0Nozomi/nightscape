package nightscape.content.blocks;

import arc.struct.Seq;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.payloads.PayloadRouter;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.RepairTower;
import mindustry.world.blocks.units.UnitFactory;
import nightscape.content.NSLiquids;
import nightscape.content.NSitems;
import nightscape.content.NSunits;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBunits {
    public static Block
    baseConstructor,
    //t2
    supplementReconstructorOne, supplementReconstructorTwo,
    //t3
    reinforcemingReconstructor, acceleratingReconstructor,
    //t4

    //enemy and other
    enemyFactory,
    //blocks
    payConv, payRout, repair;

    public static void load(){
        baseConstructor = new UnitFactory("baseConstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 150, silicon, 80));
            researchCost = with(NSitems.tantalum, 500, silicon, 120);
            plans = Seq.with(
                new UnitPlan(NSunits.point,10 * 60f, with(silicon, 15, NSitems.tantalum, 25)),
                new UnitPlan(NSunits.procursus,15 * 60f, with(silicon, 12, NSitems.zirconium, 22)),
                new UnitPlan(NSunits.gutta,7 * 60f, with(silicon, 8, NSitems.naturit, 12)),
                new UnitPlan(NSunits.ishi,22 * 60f, with(silicon, 18, NSitems.tantalum, 27))
            );
            size = 3;
            consumePower(1f);
        }};

        supplementReconstructorOne = new Reconstructor("supplementReconstructor"){{
            requirements(Category.units, with(NSitems.zirconium, 450, NSitems.velonium, 210, silicon, 150));
            researchCost = with(NSitems.zirconium, 1750, NSitems.velonium, 290, silicon, 210);

            size = 3;
            consumePower(2.5f);
            consumeItems(with(silicon, 65, NSitems.velonium, 35));

            constructTime = 60f * 15f;

            upgrades.addAll(
                new UnitType[]{NSunits.procursus, NSunits.radius},
                new UnitType[]{NSunits.gutta, NSunits.pluvia}
            );
        }};

        supplementReconstructorTwo = new Reconstructor("firmingReconstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 375, NSitems.dense, 110, silicon, 180));
            researchCost = with(NSitems.tantalum, 1350, NSitems.dense, 270, silicon, 390);

            size = 3;
            consumePower(2f);
            consumeItems(with(silicon, 65, NSitems.tantalum, 30, NSitems.dense, 12));

            constructTime = 60f * 15f;

            upgrades.addAll(
                    new UnitType[]{NSunits.point, NSunits.vector},
                    new UnitType[]{NSunits.ishi, NSunits.yama}
            );
        }};

        reinforcemingReconstructor = new Reconstructor("reinforcemingReconstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 760, NSitems.velonium, 345, NSitems.dense, 265, NSitems.streby, 160));

            size = 5;
            consumePower(5f);
            consumeItems(with(silicon, 155, NSitems.dense, 55, NSitems.streby, 75));
            consumeLiquid(NSLiquids.fernum, 1);
            constructTime = 60f * 35f;

            upgrades.addAll(
                    new UnitType[]{NSunits.vector, NSunits.planum},
                    new UnitType[]{NSunits.yama, NSunits.kometto}
            );
        }};

        acceleratingReconstructor = new Reconstructor("acceleratingReconstructor"){{
            requirements(Category.units, with(NSitems.zirconium, 930, NSitems.naturit, 495, NSitems.velonium, 375, NSitems.streby, 180));

            size = 5;
            consumePower(7f);
            consumeItems(with(silicon, 135, NSitems.velonium, 120, NSitems.streby, 65));
            consumeLiquid(NSLiquids.ammonia, 25/60f);
            constructTime = 60f * 30f;

            upgrades.addAll(
                    new UnitType[]{NSunits.pluvia, NSunits.diluvio},
                    new UnitType[]{NSunits.radius, NSunits.fluor}
            );
        }};

        enemyFactory = new UnitFactory("enemyFactory"){{
            requirements(Category.units, with(NSitems.tantalum, 120, silicon, 80, NSitems.cyanid, 50));
            plans = Seq.with(
                    new UnitPlan(NSunits.barier,12 * 60f, with(NSitems.cyanid, 8, NSitems.velonium, 8)),
                    new UnitPlan(NSunits.force,14 * 60f, with(NSitems.cyanid, 22, NSitems.tantalum, 14)),
                    new UnitPlan(NSunits.invader,8 * 60f, with(NSitems.cyanid, 15, NSitems.naturit, 10)),
                    new UnitPlan(NSunits.rush,10 * 60f, with(NSitems.cyanid, 25, NSitems.zirconium, 20))
            );
            size = 2;
            consumePower(40/60f);
        }};

        payConv = new PayloadConveyor("payConveyor"){{
            requirements(Category.units, with(NSitems.velonium, 10, NSitems.zirconium, 15));
            researchCost = with(NSitems.velonium, 340, NSitems.zirconium, 620);
            moveTime = 60f;
            canOverdrive = false;
            health = 300;
            underBullets = false;
        }};

        payRout = new PayloadRouter("payRout"){{
            requirements(Category.units, with(NSitems.velonium, 15, NSitems.zirconium, 35));
            researchCost = with(NSitems.velonium, 450, NSitems.zirconium, 980);
            moveTime = 60f;
            canOverdrive = false;
            health = 420;
            underBullets = false;
        }};

        repair = new RepairTower("repair"){{
            requirements(Category.units, with(NSitems.zirconium, 75, NSitems.velonium, 55, silicon, 40));
            consumePower(3);
            consumeLiquid(Liquids.ozone, 8/60f);

            squareSprite = false;
            size = 2;
            range = 40f;
            healAmount = 0.5f;

            circleSpeed = 180f;
            circleStroke = 5;
            squareRad = 2.5f;
            squareSpinScl = 0.7f;
        }};
    }
}
