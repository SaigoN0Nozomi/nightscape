package nightscape.content;

import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.type.Category;
import mindustry.type.UnitType;
import mindustry.world.Block;
import mindustry.world.blocks.legacy.LegacyMechPad;
import mindustry.world.blocks.units.Reconstructor;
import mindustry.world.blocks.units.UnitFactory;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBunits {
    public static Block
    baseConstructor, supplementReconstructor;

    public static void load(){
        baseConstructor = new UnitFactory("baseConstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 250, silicon, 180));
            plans = Seq.with(
                new UnitPlan(NSunits.point,10 * 60f, with(silicon, 25, NSitems.tantalum, 15)),
                new UnitPlan(NSunits.procursus,15 * 60f, with(silicon, 20, NSitems.zirconium, 35)),
                new UnitPlan(NSunits.gutta,7 * 60f, with(silicon, 22, NSitems.zirconium, 12)),
                new UnitPlan(NSunits.ishi,12 * 60f, with(silicon, 30, NSitems.tantalum, 20))
            );
            size = 3;
            consumePower(1.5f);
        }};

        supplementReconstructor = new Reconstructor("supplementReconstructor"){{
            requirements(Category.units, with(NSitems.tantalum, 650, NSitems.streby, 380, silicon, 140));

            size = 3;
            consumePower(5f);
            consumeItems(with(silicon, 60, NSitems.streby, 35));

            constructTime = 60f * 15f;

            upgrades.addAll(
                    new UnitType[]{NSunits.point, NSunits.vector},
                    new UnitType[]{NSunits.procursus, NSunits.radius},
                    new UnitType[]{NSunits.gutta, NSunits.pluvia},
                new UnitType[]{NSunits.ishi, NSunits.yama}
            );
        }};
    }
}
