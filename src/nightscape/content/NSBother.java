package nightscape.content;

import arc.graphics.Color;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Radar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BuildVisibility;
import nightscape.world.BetterMend;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBother {
    public static Block
            coreSatellite,
            tWall, tWall_large,
            mender, radar;

    public static void load(){
        tWall = new Wall("tWall") {{
        requirements(Category.defense, with(NSitems.tantalum, 8));
        health = 420;
        researchCost = ItemStack.with(NSitems.tantalum, 80);
    }};

        tWall_large = new Wall("tWall-large") {{
            requirements(Category.defense, with(NSitems.tantalum, 32));
            health = 1680;
            size = 2;
            researchCost = ItemStack.with(NSitems.tantalum, 320);
        }};

        coreSatellite = new CoreBlock("Core_Satellite") {{
            requirements(Category.effect, with(NSitems.tantalum, 1500, NSitems.naturit, 750));
            isFirstTier = true;
            unitType = NSunits.observer;
            health = 1800;
            itemCapacity = 3000;
            size = 3;
            squareSprite = false;
            armor = 4f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;

            unitCapModifier = 15;
            researchCostMultiplier = 0.1f;
        }};

        mender = new BetterMend("mender"){{
            requirements(Category.effect, with(NSitems.tantalum, 60, NSitems.velonium, 50));
            size = 2;
            reload = 120f;
            healAmount = 10f;
            healPercent = 0.2f;
            health = 170;
            phaseRangeBoost = 0f;
            maxHeat = 4f;
            heatRequirement = 1f;
            range = 80f;
            hasPower = false;
        }};

        radar = new Radar("radar"){{
            requirements(Category.effect, BuildVisibility.fogOnly, with(NSitems.velonium, 150, silicon, 120));
            outlineColor = Color.valueOf("443645");
            fogRadius = 40;
            discoveryTime = 240 * 10f;
            size = 2;
            researchCost = with(NSitems.velonium, 600, silicon, 480);

            consumePower(4f);
        }};
    }
}
