package nightscape.content.blocks;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import nightscape.content.NSLiquids;
import nightscape.content.NSattribute;
import nightscape.content.NSitems;
import nightscape.world.block.production.AttributeCollector;
import nightscape.world.block.production.NSDrill;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Items.*;
import static mindustry.type.ItemStack.with;

public class NSBproduction {
    public static Block
    shockDrill, nutExtractor, veloniumFurnace, naturitSeparator,
    cliffCrusher, siliconFurnace, ozoneCondenser,
    strebyPress, eruptionDrill;
    public static void load(){


        shockDrill = new BurstDrill("shockDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 25, NSitems.naturit, 12));
            researchCost = ItemStack.with(NSitems.tantalum, 100, NSitems.naturit, 50);

            squareSprite = false;
            size = 2;
            tier = 3;
            itemCapacity = 20;

            drillTime = 60f * 6f;
            drillEffect = new MultiEffect(
                    Fx.mineImpact,
                    Fx.drillSteam,
                    Fx.mineImpactWave.wrap(Liquids.ozone.color, 45f)
            );
            shake = 4f;
            fogRadius = 4;
            drillMultipliers.put(NSitems.tantalum, 1.5f);

            arrowOffset = 0;
            arrows = 1;
            baseArrowColor = Color.valueOf("4c3d4e");

            consumeLiquid(Liquids.ozone, 1.5f / 60f);
        }};

        eruptionDrill = new NSDrill("eruptionDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 120, NSitems.naturit, 75, NSitems.streby, 60));
            researchCost = ItemStack.with(NSitems.tantalum, 3260, NSitems.naturit, 2350, NSitems.streby, 1920);
            size = 3;
            health = 360;

            itemCapacity = 30;
            rotateSpeed = 10;
            consumeLiquid(NSLiquids.ammonia, 0.25f);
            drillTime = 120;
            tier = 4;
            heatReq = 3;
            liquidBoostIntensity = 1;
            hardnessDrillMultiplier = 0;
        }};

        nutExtractor = new AttributeCollector("naturit-extractor"){{
            requirements(Category.production, with(NSitems.tantalum, 20));

            researchCost = ItemStack.with(NSitems.tantalum, 40);
            outputItem = new ItemStack(NSitems.naturit, 1);
            outputLiquid = new LiquidStack(Liquids.ozone, 4.5f / 60f);
            craftTime = 60;
            size = 2;

            hasItems = true;
            hasLiquids = true;
            liquidCapacity = 20f;
            radius = 1;
            radColor = NSitems.naturit.color;

            craftEffect = Fx.none;
            attribute = NSattribute.naturit;

            legacyReadWarmup = true;
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawLiquidTile(Liquids.ozone),
                    new DrawRegion("-cultivator"){{
                        spinSprite = true;
                        rotateSpeed = 10f;
                    }},
                    new DrawRegion("-top")
            );
            maxBoost = 2f;
            boostScale = 0.2f;
            baseEfficiency = 0;
        }};

        veloniumFurnace = new HeatCrafter("veloniumFurnace"){{
            requirements(Category.crafting, with(NSitems.tantalum, 55));

            craftEffect = new Effect(60, e -> {
                randLenVectors(e.id, 8, 4f + e.fin() * 5f, (x, y) -> {
                    color(Color.orange, e.color, e.fin());
                    Fill.square(e.x + x, e.y + y, 0.1f + e.fout() * 3f, 45);
                });
            });

            outputItem = new ItemStack(NSitems.velonium, 1);
            craftTime = 60f;
            size = 2;
            squareSprite = false;
            hasPower = false;
            itemCapacity = 10;
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawFlame(Color.valueOf("ffef99")),
                    new DrawHeatInput("-heat")
            );
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(NSitems.tantalum, 2));
            heatRequirement = 2f;
        }};

        naturitSeparator = new HeatCrafter("naturitSeparator"){{
            requirements(Category.crafting, with(NSitems.tantalum, 270, NSitems.naturit, 180, NSitems.streby, 90));
            size = 3;
            heatRequirement = 6f;
            maxEfficiency = 2;
            regionRotated1 = 3;
            rotate = true;
            invertFlip = true;
            consumeItem(NSitems.naturit, 1);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawBlurSpin("-rotor", 16f){{
                        blurThresh = 0.01f;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs(),
                    new DrawHeatInput()
            );
            outputLiquids = LiquidStack.with(Liquids.ozone, 8 / 60f, NSLiquids.ammonia, 12 / 60f);
            liquidOutputDirections = new int[]{1, 3};
        }};

        cliffCrusher = new WallCrafter("cliffCrusher"){{
            requirements(Category.production, with(NSitems.naturit, 90, NSitems.velonium, 60, NSitems.zirconium, 30));

            consumeLiquid(Liquids.ozone, 9 / 60f);
            drillTime = 60f;
            size = 3;
            rotateSpeed = 6;
            attribute = Attribute.sand;
            output = Items.sand;
            researchCost = with(NSitems.naturit, 500, NSitems.velonium, 220, NSitems.zirconium, 190);
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.15f;
        }};

        siliconFurnace = new HeatCrafter("siliconFurnace"){{
            requirements(Category.crafting, with(NSitems.tantalum, 270, NSitems.velonium, 120, NSitems.zirconium, 90));
            size = 3;
            heatRequirement = 4f;
            maxEfficiency = 3;
            outputItem = new ItemStack(silicon, 2);
            consumeItems(with(sand, 3, NSitems.naturit, 2));
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawArcSmelt(),
                    new DrawRegion()
            );
            researchCost = with(NSitems.tantalum, 1550, NSitems.velonium, 920, NSitems.zirconium, 700);
        }};

        ozoneCondenser = new AttributeCrafter("ozoneCondenser"){{
            requirements(Category.production, with(NSitems.tantalum, 120, NSitems.zirconium, 120, silicon, 80));
            researchCost = with(NSitems.tantalum, 1420, NSitems.zirconium, 1340, silicon, 720);
            attribute = NSattribute.ozone;
            minEfficiency = 9f - 0.0001f;
            baseEfficiency = 0f;
            displayEfficiency = false;
            squareSprite = false;
            craftEffect = Fx.turbinegenerate;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.ozone, 1.5f),
                    new DrawRegion("-rotator"){{
                        spinSprite = true;
                        rotateSpeed = 18f;
                    }},
                    new DrawDefault()
            );
            craftTime = 60f;
            size = 3;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            hasLiquids = true;
            boostScale = 1f / 9f;
            outputLiquid = new LiquidStack(Liquids.ozone, 2 / 6f);
            consumePower(2f);
            liquidCapacity = 60f;
        }};

        strebyPress = new GenericCrafter("strebyPress"){{
            requirements(Category.crafting, with(NSitems.zirconium, 60, NSitems.velonium, 45));
            researchCost = with(NSitems.zirconium, 850, NSitems.velonium, 625);
            liquidCapacity = 60f;
            craftTime = 60f;
            consumeLiquid(Liquids.ozone, 3 / 60f);
            consumeItems(with(NSitems.zirconium, 2));
            outputItem = new ItemStack(NSitems.streby, 1);
            size = 2;
            health = 320;
            hasLiquids = true;
            hasPower = true;
            craftEffect = Fx.none;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(Liquids.ozone, 1.5f),
                    new DrawPistons(){{
                        sinMag = 2.5f;
                    }},
                    new DrawDefault()
            );
        }};
    }
}
