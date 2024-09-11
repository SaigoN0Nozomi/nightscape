package nightscape.content.blocks;

import arc.graphics.Color;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.AutoDoor;
import mindustry.world.blocks.defense.ForceProjector;
import mindustry.world.blocks.defense.Radar;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.power.LightBlock;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.blocks.storage.StorageBlock;
import mindustry.world.blocks.storage.Unloader;
import mindustry.world.meta.BuildVisibility;
import nightscape.content.NSitems;
import nightscape.content.NSunits;
import nightscape.content.effects.blockFx;
import nightscape.world.block.BetterMend;
import nightscape.world.block.HealingWall;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBother {
    public static Block
            coreSatellite, coreStar,
            tWall, tWall_large, gayWall, gayWall_large, rWall, rWall_large,
            armoredDoor, armoredDoorBig,
            mender, radar, luminaire,
            storage, unloader, umbrella;

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

        gayWall = new Wall("gayWall") {{
            requirements(Category.defense, with(NSitems.velonium, 6, NSitems.dense, 4));
            health = 410;
            armor = 4;
            absorbLasers = true;
            size = 1;
            researchCost = ItemStack.with(NSitems.velonium, 280, NSitems.dense, 170);
        }};

        gayWall_large = new Wall("gayWall-large") {{
            requirements(Category.defense, with(NSitems.velonium, 24, NSitems.dense, 16));
            health = 410*4;
            armor = 4;
            absorbLasers = true;
            size = 2;
            researchCost = ItemStack.with(NSitems.velonium, 980, NSitems.dense, 520);
        }};

        rWall = new HealingWall("rWall") {{
            requirements(Category.defense, with(NSitems.streby, 6, NSitems.naturit, 2));
            health = 360;
            healPercent = 2f;
            researchCost = ItemStack.with(NSitems.streby, 170, NSitems.naturit, 50);
        }};

        rWall_large = new HealingWall("rWall-large") {{
            requirements(Category.defense, with(NSitems.streby, 24, NSitems.naturit, 8));
            health = 320 * 4;
            size = 2;
            healPercent = 1f;
            researchCost = ItemStack.with(NSitems.streby, 1120, NSitems.naturit, 680);
        }};

        armoredDoor = new AutoDoor("armoredDoor"){{
            requirements(Category.defense, with(NSitems.zirconium, 8, silicon, 6));
            health = 380;
            armor = 3;
        }};

        armoredDoorBig = new AutoDoor("armoredDoor-big"){{
            requirements(Category.defense, with(NSitems.zirconium, 32, silicon, 24));
            health = 380 * 4;
            size = 2;
            armor = 3;
        }};

        coreSatellite = new CoreBlock("Core_Satellite") {{
            requirements(Category.effect, with(NSitems.tantalum, 1500, NSitems.naturit, 750));
            isFirstTier = true;
            unitType = NSunits.observer;
            health = 950;
            itemCapacity = 3000;
            size = 3;
            squareSprite = false;
            armor = 3f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;
            unitCapModifier = 6;
        }};

        coreStar = new CoreBlock("Core_Star") {{
            requirements(Category.effect, with(NSitems.tantalum, 3500, NSitems.velonium, 2750, NSitems.dense, 800));
            researchCost = with(NSitems.tantalum, 7000, NSitems.velonium, 6550, NSitems.dense, 2800);
            isFirstTier = true;
            unitType = NSunits.suppressor;
            health = 1750;
            itemCapacity = 4500;
            size = 4;
            squareSprite = false;
            armor = 5f;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;
            unitCapModifier = 8;
        }};
        mender = new BetterMend("mender"){{
            requirements(Category.effect, with(NSitems.tantalum, 60, NSitems.velonium, 20));
            size = 2;
            reload = 120f;
            healAmount = 10f;
            healPercent = 0.2f;
            health = 170;
            phaseRangeBoost = 0f;
            maxHeat = 4f;
            heatRequirement = 1f;
            range = 40f;
            hRange = 15;
            hasPower = false;
        }};

        radar = new Radar("radar"){{
            requirements(Category.effect, with(NSitems.velonium, 60, silicon, 120));
            outlineColor = Color.valueOf("443645");
            fogRadius = 40;
            discoveryTime = 160 * 10f;

            size = 2;
            researchCost = with(NSitems.velonium, 210, silicon, 480);

            consumePower(4f);
        }};

        luminaire = new LightBlock("luminaire"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(NSitems.zirconium, 20, silicon, 12));
            brightness = 0.75f;
            radius = 100f;
            consumePower(0.1f);
        }};

        storage = new StorageBlock("storage"){{
            requirements(Category.effect, with(NSitems.tantalum, 210, NSitems.dense, 15));
            size = 2;
            squareSprite = false;
            itemCapacity = 400;
            health = 600;
        }};

        unloader = new Unloader("unloader"){{
            requirements(Category.effect, with(NSitems.tantalum, 70, NSitems.dense, 10));
            speed = 4;
        }};

        umbrella = new ForceProjector("umbrella"){{
            requirements(Category.effect, with(NSitems.tantalum, 340, NSitems.streby, 210, NSitems.velonium, 110));
            size = 4;
            hasItems = false;
            radius = 240f;
            shieldHealth = 200f;
            shieldBreakEffect = blockFx.shieldBrokeFx;
            cooldownNormal = 1f;
            cooldownBrokenBase = 0.1f;
            consumePower(5f / 6);
        }};
    }
}
