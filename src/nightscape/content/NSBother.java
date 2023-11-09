package nightscape.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.storage.CoreBlock;
import nightscape.world.NonPercentMend;

import static mindustry.type.ItemStack.with;

public class NSBother {
    public static Block
            coreSatellite,
            tWall, tWall_large,
            mender;

    public static void load(){
        tWall = new Wall("tWall") {{
        requirements(Category.defense, with(NSitems.tantalum, 8));
        health = 610;
        researchCost = ItemStack.with(NSitems.tantalum, 80);
    }};

        tWall_large = new Wall("tWall-large") {{
            requirements(Category.defense, with(NSitems.tantalum, 32));
            health = 2640;
            size = 2;
            researchCost = ItemStack.with(NSitems.tantalum, 320);
        }};

        coreSatellite = new CoreBlock("Core_Satellite") {{
            requirements(Category.effect, with(NSitems.tantalum, 1500, NSitems.naturit, 750));
            isFirstTier = true;
            unitType = NSunits.observer;
            health = 3000;
            itemCapacity = 3000;
            size = 3;
            squareSprite = false;
            armor = 10f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;

            unitCapModifier = 15;
            researchCostMultiplier = 0.1f;
        }};

        mender = new NonPercentMend("mender"){{
            requirements(Category.effect, with(NSitems.tantalum, 60, NSitems.velonium, 50));
            size = 2;
            reload = 120f;
            healAmount = 10f;
            health = 170;
            phaseRangeBoost = 0f;
            maxHeat = 6f;
            heatRequirement = 1.5f;
            range = 80f;
            hasPower = false;
        }};
    }
}
