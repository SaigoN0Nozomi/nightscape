package nightscape.content.blocks;

import arc.struct.Seq;
import mindustry.type.Category;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.payloads.PayloadRouter;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;
import nightscape.content.NSitems;
import nightscape.content.NSunits;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBunits {
    public static Block
    baseConstructor, supplementReconstructor,
    payConv, payRout;

    public static void load(){
        baseConstructor = new UnitFactory("baseConstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 250, silicon, 180));
            plans = Seq.with(
                new UnitPlan(NSunits.point,10 * 60f, with(silicon, 15, NSitems.tantalum, 15)),
                new UnitPlan(NSunits.procursus,15 * 60f, with(silicon, 10, NSitems.zirconium, 35)),
                new UnitPlan(NSunits.gutta,7 * 60f, with(silicon, 12, NSitems.zirconium, 12)),
                new UnitPlan(NSunits.ishi,12 * 60f, with(silicon, 18, NSitems.tantalum, 20))
            );
            size = 3;
            consumePower(1.5f);
        }};

        supplementReconstructor = new Reconstructor("supplementReconstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 650, NSitems.streby, 380, silicon, 140));
            researchCost = with(NSitems.tantalum, 6000, NSitems.streby, 3800, silicon, 2650);

            size = 3;
            consumePower(5f);
            consumeItems(with(silicon, 30, NSitems.streby, 45));

            constructTime = 60f * 15f;

            upgrades.addAll(
                    new UnitType[]{NSunits.point, NSunits.vector},
                    new UnitType[]{NSunits.procursus, NSunits.radius},
                    new UnitType[]{NSunits.gutta, NSunits.pluvia},
                new UnitType[]{NSunits.ishi, NSunits.yama}
            );
        }};

        payConv = new PayloadConveyor("payConv"){{
            requirements(Category.units, with(NSitems.velonium, 20, NSitems.zirconium, 15));
            researchCost = with(NSitems.velonium, 960, NSitems.zirconium, 620);
            moveTime = 60f;
            canOverdrive = false;
            health = 300;
            underBullets = false;
        }};

        payRout = new PayloadRouter("payRout"){{
            requirements(Category.units, with(NSitems.velonium, 50, NSitems.zirconium, 35));
            researchCost = with(NSitems.velonium, 1780, NSitems.zirconium, 980);
            moveTime = 60f;
            canOverdrive = false;
            health = 420;
            underBullets = false;
        }};
    }
}
