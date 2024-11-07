package nightscape.content.blocks;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.*;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.draw.*;
import nightscape.content.NSLiquids;
import nightscape.content.NSitems;
import nightscape.content.effects.blockFx;
import nightscape.world.block.power.HeatGenerator;
import nightscape.world.block.production.HeatCore;

import static mindustry.content.Items.blastCompound;
import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBpower {
    public static Block
            //heat
    ozoneHeater, heatRedirector, heatCore, slagHeater, blastGen,
            //power
    node, bigNode, SFGenerator, solarPanel, powerStorage, heatGenerator;
    public static void load(){
        //heat region
        heatRedirector = new HeatConductor("heatRedirector"){{
            requirements(Category.power, with(NSitems.tantalum, 35, NSitems.velonium, 15));

            researchCost = ItemStack.with(NSitems.tantalum, 900, NSitems.velonium, 300);

            researchCostMultiplier = 1f;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};

        ozoneHeater = new HeatProducer("ozoneHeater"){{
            requirements(Category.power, with(NSitems.tantalum, 35, NSitems.zirconium, 30, NSitems.naturit, 20));

            researchCost = ItemStack.with(NSitems.tantalum, 600, NSitems.zirconium, 480, NSitems.naturit, 400);

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            rotateDraw = false;
            size = 2;
            heatOutput = 1f;
            squareSprite = false;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;
            consumeLiquid(Liquids.ozone, 1.5f/60);
        }};

        heatCore = new HeatCore("heatCore"){{
            requirements(Category.power, with(NSitems.tantalum, 135, NSitems.velonium, 50, silicon, 80));

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
            researchCost = ItemStack.with(NSitems.tantalum, 1850, NSitems.velonium, 550, silicon, 750);

            EnPerHeat = 55f / 60f;
            heatMin = 1;
            heatMax = 6;
            step = 0.5f;
        }};
        /*
        slagHeater = new HeatProducer("slagHeater"){{
            requirements(Category.power, with(NSitems.vanadium, 25, NSitems.tantalum, 35));

            researchCostMultiplier = 4f;

            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
            size = 2;
            itemCapacity = 0;
            health = 120;
            liquidCapacity = 40f;
            rotateDraw = false;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;
            consumeLiquid(Liquids.slag, 12f / 60f);
            heatOutput = 3f;

            researchCost = with(NSitems.vanadium, 425, NSitems.tantalum, 225);
        }};
         */

        //power region
        node = new BeamNode("node"){{
            requirements(Category.power, with(NSitems.tantalum, 9, silicon, 6));
            consumesPower = outputsPower = true;
            health = 120;
            range = 6;
            fogRadius = 3;
            researchCost = ItemStack.with(NSitems.tantalum, 36, silicon, 12);

            consumePowerBuffered(250f);
        }};

        bigNode = new PowerNode("bigNode"){{
            requirements(Category.power, with(NSitems.dense, 24, silicon, 18));
            consumesPower = outputsPower = true;
            health = 360;
            maxRange = 24;
            maxNodes = 3;
            size = 2;
            fogRadius = 3;

            consumePowerBuffered(1000f);
        }};

        powerStorage = new Battery("powerStorage"){{
            requirements(Category.power, with(NSitems.tantalum, 70, silicon, 55));
            consumesPower = outputsPower = true;
            health = 320;
            size = 2;

            consumePowerBuffered(6000f);
        }};

        SFGenerator = new ConsumeGenerator("solidFuelGenerator"){{
            requirements(Category.power, with(NSitems.tantalum, 30, NSitems.zirconium, 30, silicon, 15));
            powerProduction = 5f / 6f;
            itemDuration = 150f;
            health = 120;
            size = 2;
            researchCost = ItemStack.with(NSitems.tantalum, 350, NSitems.zirconium, 280, silicon, 30);

            generateEffect = Fx.generatespark;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;

            consumeItem(NSitems.naturit);
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawFlame(Color.valueOf("e6dd8bs"))
            );
        }};

        solarPanel = new SolarGenerator("solarPanel"){{
            requirements(Category.power, with(NSitems.velonium, 20, silicon, 45));
            health = 130;
            size = 2;
            powerProduction = 20/60f;
        }};

        blastGen = new ConsumeGenerator("blastGen"){{
            requirements(Category.power, with(NSitems.tantalum, 50, silicon, 25, NSitems.dense, 5));
            size = 2;
            health = 210;
            powerProduction = 110/60f;
            itemDuration = 180f;
            consumeItem(blastCompound);
            explosionRadius = 5;
            explosionDamage = 90;

            consumePowerBuffered(1200f);
        }};

        heatGenerator = new HeatGenerator("heatGenerator"){{
            requirements(Category.power, with(NSitems.tantalum, 170, NSitems.velonium, 80, silicon, 75, NSitems.dense, 35));
            size = 3;
            health = 380;
            squareSprite = false;

            effectChance = 0.1f;
            generateEffect = blockFx.amSmoke;
            minEff = 0.1f;
            heatReq = 5;
            powerProduction = 380/60f;
            consumeLiquid(NSLiquids.ammonia, 13/60f);

            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawLiquidTile(NSLiquids.ammonia, 1.5f),
                    new DrawRegion("-top")
            );
        }};
    }
}
