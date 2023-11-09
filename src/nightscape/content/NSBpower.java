package nightscape.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.heat.HeatConductor;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.BeamNode;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.consumers.ConsumeItemFlammable;
import mindustry.world.draw.*;
import nightscape.world.HeatCore;

import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBpower {
    public static Block
    ozoneHeater, heatRedirector, heatCore,
    node, SFGenerator;
    public static void load(){
        heatRedirector = new HeatConductor("heatRedirector"){{
            requirements(Category.power, with(NSitems.tantalum, 45, NSitems.velonium, 25));

            researchCost = ItemStack.with(NSitems.tantalum, 900, NSitems.velonium, 500);

            researchCostMultiplier = 1f;
            size = 2;
            drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput(), new DrawHeatInput("-heat"));
            regionRotated1 = 1;
        }};

        ozoneHeater = new HeatProducer("ozoneHeater"){{
            requirements(Category.power, with(NSitems.tantalum, 35, NSitems.naturit, 20));

            researchCost = ItemStack.with(NSitems.tantalum, 600, NSitems.naturit, 400);

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
            requirements(Category.power, with(NSitems.tantalum, 135, NSitems.velonium, 120, silicon, 80));
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
            researchCost = ItemStack.with(NSitems.tantalum, 1850, NSitems.velonium, 1150, silicon, 750);

            perEnOutput = 1.5f;
            heatMin = 1;
            heatMax = 6;
            step = 0.5f;
        }};

        node = new BeamNode("node"){{
            requirements(Category.power, with(NSitems.tantalum, 15, silicon, 6));
            consumesPower = outputsPower = true;
            health = 120;
            range = 6;
            fogRadius = 3;
            researchCost = ItemStack.with(NSitems.tantalum, 225, silicon, 150);

            consumePowerBuffered(250f);
        }};

        SFGenerator = new ConsumeGenerator("solidFuelGenerator"){{
            requirements(Category.power, with(NSitems.tantalum, 30, silicon, 25));
            powerProduction = 5f / 6f;
            itemDuration = 45f;
            size = 2;
            researchCost = ItemStack.with(NSitems.tantalum, 650, silicon, 550);

            generateEffect = Fx.generatespark;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.06f;

            consume(new ConsumeItemFlammable());
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawFlame(Color.valueOf("e6dd8bs"))
            );
        }};

    }
}
