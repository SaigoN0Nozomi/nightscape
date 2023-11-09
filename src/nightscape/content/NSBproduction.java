package nightscape.content;

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
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.draw.*;
import mindustry.world.meta.Attribute;
import nightscape.world.HeatCore;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Items.*;
import static mindustry.type.ItemStack.with;

public class NSBproduction {
    public static Block
    shockDrill, nutExtractor, veloniumFurnace, naturitSeparator, combustionMixer, cliffCrusher, siliconFurnace;
    public static void load(){


        shockDrill = new BurstDrill("shockDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 25, NSitems.naturit, 12));
            researchCost = ItemStack.with(NSitems.tantalum, 100, NSitems.naturit, 50);

            squareSprite = false;
            size = 2;
            tier = 3;
            itemCapacity = 20;

            drillTime = 60f * 9f;
            drillEffect = new MultiEffect(
                    Fx.mineImpact,
                    Fx.drillSteam,
                    Fx.mineImpactWave.wrap(Liquids.ozone.color, 45f)
            );
            shake = 4f;
            researchCostMultiplier = 0.5f;
            fogRadius = 4;
            drillMultipliers.put(NSitems.tantalum, 1.8f);

            arrowOffset = 0;
            arrows = 1;
            baseArrowColor = Color.valueOf("4c3d4e");

            consumeLiquid(Liquids.ozone, 1.5f / 60f);
        }};

        nutExtractor = new AttributeCrafter("naturit-extractor"){{
            requirements(Category.production, with(NSitems.tantalum, 20));

            researchCost = ItemStack.with(NSitems.tantalum, 40);
            outputItem = new ItemStack(NSitems.naturit, 1);
            outputLiquid = new LiquidStack(Liquids.ozone, 3f / 60f);
            craftTime = 60;
            size = 2;

            hasItems = true;
            hasLiquids = true;
            liquidCapacity = 20f;

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
            boostScale = 0.25f;
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

            outputItem = new ItemStack(NSitems.velonium, 2);
            craftTime = 180f;
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

            consumeItems(with(NSitems.tantalum, 5));
            heatRequirement = 2f;
        }};

        naturitSeparator = new HeatCrafter("naturitSeparator"){{
            requirements(Category.crafting, with(NSitems.tantalum, 270, NSitems.naturit, 180, NSitems.electrum, 90));
            size = 3;
            heatRequirement = 3f;
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
                    new DrawLiquidOutputs()
            );
            outputLiquids = LiquidStack.with(Liquids.ozone, 4 / 60f, Liquids.water, 3 / 60f);
            liquidOutputDirections = new int[]{1, 3};
        }};

        combustionMixer = new GenericCrafter("combustionMixer"){{
            requirements(Category.crafting, with(NSitems.tantalum, 80, NSitems.electrum, 60, NSitems.velonium, 30));
            size = 2;
            consumePower(1.5f);
            consumeItems(ItemStack.with(NSitems.naturit, 2, coal, 3));
            craftTime = 30f;
            outputItem = new ItemStack(pyratite, 3);
        }};

        cliffCrusher = new WallCrafter("cliffCrusher"){{
            requirements(Category.production, with(NSitems.naturit, 90, NSitems.velonium, 60));

            consumeLiquid(Liquids.ozone, 9 / 60f);
            drillTime = 60f;
            size = 3;
            rotateSpeed = 6;
            attribute = Attribute.sand;
            output = Items.sand;
            researchCost = with(NSitems.naturit, 500, NSitems.velonium, 220);
            ambientSound = Sounds.drill;
            ambientSoundVolume = 0.15f;
        }};

        siliconFurnace = new HeatCrafter("siliconFurnace"){{
            requirements(Category.crafting, with(NSitems.tantalum, 270, NSitems.velonium, 120));
            size = 3;
            heatRequirement = 6f;
            maxEfficiency = 2;
            outputItem = new ItemStack(silicon, 2);
            consumeItems(with(sand, 3, NSitems.naturit, 2));
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawArcSmelt(),
                    new DrawRegion()
            );
            researchCost = with(NSitems.tantalum, 1550, NSitems.velonium, 920);
        }};
    }
}
