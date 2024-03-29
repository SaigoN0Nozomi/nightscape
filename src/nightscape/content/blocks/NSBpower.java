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
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.SolarGenerator;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.draw.*;
import nightscape.content.NSitems;
import nightscape.world.block.production.HeatCore;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBpower {
    public static Block
    ozoneHeater, heatRedirector, heatCore,
    node, SFGenerator, solarPanel;
    public static void load(){
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

            EnPerHeat = 70f / 60f;
            heatMin = 1;
            heatMax = 6;
            step = 0.5f;
        }};

        node = new BeamNode("node"){{
            requirements(Category.power, with(NSitems.tantalum, 9, silicon, 6));
            consumesPower = outputsPower = true;
            health = 120;
            range = 6;
            fogRadius = 3;
            researchCost = ItemStack.with(NSitems.tantalum, 225, silicon, 150);

            consumePowerBuffered(250f);
        }};

        SFGenerator = new ConsumeGenerator("solidFuelGenerator"){{
            requirements(Category.power, with(NSitems.tantalum, 30, NSitems.zirconium, 30, silicon, 15));
            powerProduction = 5f / 6f;
            itemDuration = 75f;
            size = 2;
            researchCost = ItemStack.with(NSitems.tantalum, 650, NSitems.zirconium, 580, silicon, 350);

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
           requirements(Category.power, with(NSitems.velonium, 20, silicon, 15));
           size = 2;
           powerProduction = 20/60f;
        }};
    }
}
