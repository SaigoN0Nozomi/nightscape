package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
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
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.production.BurstDrill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.draw.*;
import nightscape.world.HeatCore;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class NSBproduction {
    public static Block
            shockDrill, nutExtractor,
            ozoneHeater, veloniumFurnace, heatRedirector,
            heatCore, node, SFGenerator, naturitSeparator, combustionMixer;
    public static void load(){


        shockDrill = new BurstDrill("shockDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 25, NSitems.naturit, 12));

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
            itemCapacity = 40;
            researchCostMultiplier = 0.5f;
            fogRadius = 4;
            drillMultipliers.put(NSitems.tantalum, 1.8f);

            arrowOffset = 0;
            arrows = 1;
            baseArrowColor = Color.valueOf("4c3d4e");

            consumeLiquid(Liquids.ozone, 1.5f / 60f);
        }};

        nutExtractor = new AttributeCrafter("naturit-extractor"){{
            requirements(Category.production, with(NSitems.tantalum, 40));

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

        heatRedirector = new HeatConductor("heatRedirector"){{
            requirements(Category.power, with(NSitems.tantalum, 45, NSitems.velonium, 25));

            researchCostMultiplier = 10f;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};

        ozoneHeater = new HeatProducer("ozoneHeater"){{
            requirements(Category.power, with(NSitems.tantalum, 35, NSitems.naturit, 20));

            researchCostMultiplier = 3f;

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            rotateDraw = false;
            size = 2;
            heatOutput = 1f;
            squareSprite = false;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;
            consumeLiquid(Liquids.ozone, 0.75f/60);
        }};

        heatCore = new HeatCore("heatCore"){{
            requirements(Category.power, with(NSitems.tantalum, 135, NSitems.velonium, 120, NSitems.electrum, 80));
            researchCostMultiplier = 3f;

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawHeatOutput(),
                    new DrawFlame(Color.valueOf("feb380"))
            );
            rotateDraw = false;
            size = 3;
            regionRotated1 = 1;
            squareSprite = false;
            ambientSound = Sounds.hum;
            consumePower(1f);

            perEnOutput = 2f;
            heatMin = 1;
            heatMax = 6;
            step = 0.5f;
        }};

        node = new BeamNode("node"){{
            requirements(Category.power, with(NSitems.tantalum, 15, NSitems.electrum, 6));
            consumesPower = outputsPower = true;
            health = 120;
            range = 6;
            fogRadius = 3;

            consumePowerBuffered(250f);
        }};

        SFGenerator = new ConsumeGenerator("solidFuelGenerator"){{
            requirements(Category.power, with(NSitems.tantalum, 30, NSitems.electrum, 25));
            powerProduction = 5f / 6f;
            itemDuration = 45f;
            size = 2;

            generateEffect = Fx.generatespark;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;

            consumeItem(NSitems.naturit);
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawFlame(Color.valueOf("e6dd8bs"))
            );
        }};

        naturitSeparator = new HeatCrafter("naturitSeparator"){{
            requirements(Category.crafting, with(NSitems.tantalum, 270, NSitems.naturit, 180, NSitems.electrum, 90));
            size = 3;
            heatRequirement = 3f;
            maxEfficiency = 2;
            regionRotated1 = 3;
            rotate = true;
            invertFlip = true;
            consumeItem(NSitems.naturit, 6);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawBlurSpin("-rotor", 16f){{
                        blurThresh = 0.01f;
                    }},
                    new DrawRegion(),
                    new DrawLiquidOutputs()
            );
            outputLiquids = LiquidStack.with(Liquids.oil, 8 / 60f, Liquids.water, 16 / 60f);
            liquidOutputDirections = new int[]{1, 3};
        }};

        combustionMixer = new GenericCrafter("combustionMixer"){{
            requirements(Category.crafting, with(NSitems.tantalum, 80, NSitems.electrum, 60, NSitems.velonium, 30));
            size = 2;
            consumePower(1.5f);
            consumeItem(NSitems.naturit, 2);
            consumeLiquid(Liquids.oil, 1.5f / 60);
            craftTime = 30f;
            outputItem = new ItemStack(NSitems.flammable, 3);
        }};
    }
}
