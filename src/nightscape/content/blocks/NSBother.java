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
            coreSatellite, coreSystem,
            tWall, tWall_large, rWall, rWall_large,
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
            healPercent = 0.8f;
            researchCost = ItemStack.with(NSitems.streby, 1120, NSitems.naturit, 680);
        }};

        armoredDoor = new AutoDoor("armoredDoor"){{
            requirements(Category.defense, with(NSitems.zirconium, 8, silicon, 6));
            health = 400;
            armor = 5;
        }};

        armoredDoorBig = new AutoDoor("armoredDoor-big"){{
            requirements(Category.defense, with(NSitems.zirconium, 8, silicon, 6));
            health = 1600;
            size = 2;
            armor = 5;
        }};

        coreSatellite = new CoreBlock("Core_Satellite") {{
            requirements(Category.effect, with(NSitems.tantalum, 1500, NSitems.naturit, 750));
            isFirstTier = true;
            unitType = NSunits.observer;
            health = 1200;
            itemCapacity = 3000;
            size = 3;
            squareSprite = false;
            armor = 4f;
            alwaysUnlocked = true;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;
            unitCapModifier = 6;
        }};

        coreSystem = new CoreBlock("Core_System") {{
            requirements(Category.effect, with(NSitems.tantalum, 2400, NSitems.streby, 1200, silicon, 800));
            researchCost = with(NSitems.tantalum, 8000, NSitems.streby, 6400, silicon, 5200);
            unitType = NSunits.suppressor;
            health = 1900;
            itemCapacity = 5500;
            size = 4;
            squareSprite = false;
            armor = 6f;
            incinerateNonBuildable = true;

            buildCostMultiplier = 0.5f;
            unitCapModifier = 10;
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
            requirements(Category.effect, with(NSitems.velonium, 150, silicon, 120));
            outlineColor = Color.valueOf("443645");
            fogRadius = 40;
            discoveryTime = 160 * 10f;

            size = 2;
            researchCost = with(NSitems.velonium, 600, silicon, 480);

            consumePower(4f);
        }};

        luminaire = new LightBlock("luminaire"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(NSitems.zirconium, 20, silicon, 12));
            brightness = 0.75f;
            radius = 100f;
            consumePower(0.1f);
        }};

        storage = new StorageBlock("storage"){{
            requirements(Category.effect, with(NSitems.tantalum, 350, NSitems.streby, 195));
            size = 2;
            squareSprite = false;
            itemCapacity = 400;
            health = 600;
        }};

        unloader = new Unloader("unloader"){{
            requirements(Category.effect, with(NSitems.tantalum, 70, NSitems.streby, 40));
            speed = 4;
        }};

        umbrella = new ForceProjector("umbrella"){{
            requirements(Category.effect, with(NSitems.tantalum, 540, NSitems.velonium, 350, NSitems.streby, 180));
            size = 4;
            hasItems = false;
            radius = 240f;
            shieldHealth = 100f;
            shieldBreakEffect = blockFx.shieldBrokeFx;
            cooldownNormal = 2f;
            cooldownBrokenBase = 0.3f;
            consumePower(5f / 6);
        }};
    }
}
