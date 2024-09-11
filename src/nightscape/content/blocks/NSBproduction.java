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
import mindustry.world.meta.BuildVisibility;
import nightscape.content.NSLiquids;
import nightscape.content.NSattribute;
import nightscape.content.NSitems;
import nightscape.content.effects.blockFx;
import nightscape.world.block.production.AirCollector;
import nightscape.world.block.production.AttributeCollector;
import nightscape.world.block.production.NSDrill;
import nightscape.world.block.production.multiExtractor.SEaBuffer;
import nightscape.world.block.production.multiExtractor.SEaDrill;
import nightscape.world.block.production.multiExtractor.SEaStabilizer;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Items.*;
import static mindustry.type.ItemStack.with;

public class NSBproduction {
    public static Block
    shockDrill, nutExtractor, veloniumFurnace, naturitSeparator,
    seaDrill, seaBuffer, seaBufferEnemy, seaStabilizer,
    siliconFurnace, ozoneCondenser, highFurn,
    eruptionDrill, ozoneIncinerator, strebyPress,
    //Pryma region
    natAirCollector, thermalExtractor, slagDrill, ozoneEl;
    public static void load(){


        shockDrill = new BurstDrill("shockDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 18, NSitems.naturit, 12));
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
            requirements(Category.production, with(NSitems.tantalum, 80, NSitems.naturit, 55, NSitems.streby, 40));
            researchCost = ItemStack.with(NSitems.tantalum, 3260, NSitems.naturit, 2350, NSitems.streby, 1920);
            size = 3;
            health = 360;

            itemCapacity = 30;
            rotateSpeed = 10;
            consumeLiquid(NSLiquids.ammonia, 0.25f);
            drillTime = 100;
            tier = 4;
            heatReq = 3;
            liquidBoostIntensity = 1;
            hardnessDrillMultiplier = 0;
        }};

        slagDrill = new BeamDrill("slagDrill"){{
            requirements(Category.production, with(NSitems.tantalum, 44, NSitems.dense, 24));
            size = 2;
            health = 190;
            range = 3;
            tier = 4;
            hasPower = true;
            consumePower(1);
        }};

        seaDrill = new SEaDrill("e"){{
            requirements(Category.production, with(NSitems.tantalum, 460, NSitems.zirconium, 380, NSitems.velonium, 310));
            researchCost = with(NSitems.tantalum, 750, NSitems.zirconium, 510, NSitems.velonium, 420);
            health = 1020;
            size = 3;
            lightRadius = 0;
            attribute = NSattribute.fundum;
            baseEfficiency = 0;
            effPerBuff = 0.07f;
            drawer = new DrawMulti(
                new DrawRegion("-bottom"),
                new DrawLiquidTile(NSLiquids.fernum),
                new DrawRegion("-spin"){{
                    spinSprite = true;
                    rotateSpeed = 3;
                }},
                new DrawDefault(),
                new DrawRegion("-rotator"){{
                    spinSprite = true;
                    rotateSpeed = 6;
                }},
                new DrawRegion("-top")
            );

            outputLiquid = new LiquidStack(NSLiquids.fernum, 2/6f);
            consumeLiquid(Liquids.ozone, 12/60f);
        }};
        seaBuffer = new SEaBuffer("t"){{
            requirements(Category.production, with(NSitems.tantalum, 20, NSitems.naturit, 10));
            researchCost = with(NSitems.tantalum, 400, NSitems.naturit, 250);
            size = 1;
            breakChance = 0.001f;
            randPeriod = 60f;
            health = 120;
        }};

        seaBufferEnemy = new SEaBuffer("unt"){{
            requirements(Category.production, BuildVisibility.editorOnly, with(NSitems.tantalum, 40, NSitems.naturit, 25, NSitems.cyanid, 15));
            size = 1;
            breakChance = 0;
            randPeriod = 600f;
            health = 120;
        }};
        /*
        seaStabilizer = new SEaStabilizer("s"){{
            requirements(Category.production, with(NSitems.tantalum, 1));
            size = 1;
            breakChance = 0.0005f;
            randPeriod = 60f;
            health = 180;
            consumeLiquid(NSLiquids.ammonia, 1/60f);
        }};
         */
        nutExtractor = new AttributeCollector("naturit-extractor"){{
            requirements(Category.production, with(NSitems.tantalum, 20));

            researchCost = ItemStack.with(NSitems.tantalum, 40);
            outputItem = new ItemStack(NSitems.naturit, 1);
            outputLiquid = new LiquidStack(Liquids.ozone, 9f / 60f);
            craftTime = 120;
            size = 2;

            hasItems = true;
            hasLiquids = true;
            liquidCapacity = 90f;
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
            researchCost = ItemStack.with(NSitems.tantalum, 650);

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
            requirements(Category.crafting, with(NSitems.tantalum, 180, NSitems.naturit, 120, NSitems.streby, 60));
            size = 3;
            heatRequirement = 6f;
            maxEfficiency = 2;
            regionRotated1 = 3;
            rotate = true;
            squareSprite = false;
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

        siliconFurnace = new HeatCrafter("siliconFurnace"){{
            requirements(Category.crafting, with(NSitems.tantalum, 210, NSitems.velonium, 100, NSitems.zirconium, 55));
            size = 3;
            heatRequirement = 3f;
            maxEfficiency = 3;
            squareSprite = false;
            outputItem = new ItemStack(silicon, 2);
            consumeItems(with(NSitems.naturit, 3));
            consumeLiquid(NSLiquids.fernum, 8/60f);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawArcSmelt(),
                    new DrawRegion()
            );
            researchCost = with(NSitems.tantalum, 550, NSitems.velonium, 220, NSitems.zirconium, 160);
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
            consumePower(8f/ 6);
            liquidCapacity = 60f;
        }};

        highFurn = new GenericCrafter("highFurn"){{
            requirements(Category.crafting, with(NSitems.tantalum, 120, NSitems.naturit, 75, silicon, 30));
            researchCost = with(NSitems.tantalum, 1230, NSitems.naturit, 1050, silicon, 650);
            size = 3;
            squareSprite = false;
            craftTime = 120;
            updateEffect = blockFx.furnSmoke;
            updateEffectChance = 0.2f;
            craftEffect = blockFx.furnCraft;
            outputItem = new ItemStack(NSitems.dense, 1);
            consumePower(25/6f);
            consumeItems(with(NSitems.velonium, 2, NSitems.zirconium, 3));
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawFlame(Color.valueOf("a4c9dd")),
                    new DrawRegion()
            );
        }};

        strebyPress = new GenericCrafter("strebyPress"){{
            requirements(Category.crafting, with(NSitems.zirconium, 60, NSitems.velonium, 25));
            researchCost = with(NSitems.zirconium, 850, NSitems.velonium, 625);
            liquidCapacity = 60f;
            craftTime = 120f;
            consumeLiquids(LiquidStack.with(Liquids.ozone, 1.5 / 60f, NSLiquids.fernum, 2 / 60f));
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

        ozoneIncinerator = new Incinerator("inc"){{
            requirements(Category.crafting, with(NSitems.tantalum, 35, NSitems.velonium, 15));
            researchCost = with(NSitems.tantalum, 650, NSitems.velonium, 375);
            size = 1;
            consumePower(20 / 60f);
        }};

        /*natAirCollector = new AirCollector("natAirCollector"){{
            requirements(Category.production, with(NSitems.tantalum, 50, NSitems.vanadium, 35));

            researchCost = with(NSitems.tantalum, 120, NSitems.vanadium, 80);
            outputItem = new ItemStack(NSitems.naturit, 1);
            craftTime = 90;
            size = 2;
            health = 380;
            craftEffect = blockFx.natAitCraft;

            hasItems = true;
            hasLiquids = false;
            hasPower = false;
            radius = 2;
            radColor = NSitems.naturit.color;

            legacyReadWarmup = true;
            maxBoost = 2f;
            effPerAir = 0.05f;
            baseEfficiency = 0.20f;
        }};

        thermalExtractor = new Pump("thermalExtractor"){{
            requirements(Category.liquid, with(NSitems.zirconium, 16, NSitems.naturit, 10, NSitems.vanadium, 8));
            researchCost = with(NSitems.zirconium, 70, NSitems.naturit, 65 ,NSitems.vanadium, 40);
            health = 80;
            hasPower = false;
            squareSprite = false;
            pumpAmount = 4f / 60f;
            drawer = new DrawMulti(
                    new DrawRegion("-bot"),
                    new DrawLiquidRegion(),
                    new DrawDefault()
            );
        }};

        ozoneEl = new HeatCrafter("ozoneEl"){{
            requirements(Category.crafting, with(NSitems.vanadium, 60, NSitems.velonium, 25));
            hasPower = false;
            heatRequirement = 3;
            size = 2;
            health = 160;
            maxEfficiency = 2;
            squareSprite = false;
            outputLiquid = new LiquidStack(Liquids.ozone, 4/60f);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawParticles(){{
                        color = Liquids.ozone.color;
                        particles = 10;
                        particleSize = 1.4f;
                    }},
                    new DrawDefault(),
                    new DrawHeatInput()
            );
        }};
         */
    }
}
