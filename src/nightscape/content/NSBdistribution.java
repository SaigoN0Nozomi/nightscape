package nightscape.content;

import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.Conveyor;
import mindustry.world.blocks.distribution.ItemBridge;
import mindustry.world.blocks.distribution.Junction;
import mindustry.world.blocks.distribution.Router;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidRouter;

import static mindustry.type.ItemStack.with;

public class NSBdistribution {
    public static Block
            tConveyor, tRouter, tJunction, tBridge,
            tConduit, tcRouter, tcBridge;

    public static void load(){
        tRouter = new Router("tRouter"){{
            requirements(Category.distribution, with(NSitems.tantalum, 6));
            health = 35;
        }};

        tJunction = new Junction("tJunction"){{
            requirements(Category.distribution, with(NSitems.tantalum, 6));
            health = 30;
        }};

        tBridge = new ItemBridge("tBridge"){{
            requirements(Category.distribution, with(NSitems.tantalum, 12));
            health = 90;
            range = 3;
            squareSprite = false;
        }};

        tConveyor = new Conveyor("tConveyor"){{
            requirements(Category.distribution, with(NSitems.tantalum, 2));
            health = 20;
            speed = 0.05f;
            displayedSpeed = 6f;
            underBullets = false;
            bridgeReplacement = tBridge;
            junctionReplacement = tJunction;
        }};

        tcRouter = new LiquidRouter("tcRouter"){{
            requirements(Category.liquid, with(NSitems.tantalum, 6, NSitems.naturit, 3));
            health = 60;
        }};

        tcBridge = new LiquidBridge("tcBridge"){{
            requirements(Category.liquid, with(NSitems.tantalum, 10, NSitems.naturit, 6));
            health = 75;
            range = 3;
            squareSprite = false;
        }};

        tConduit = new Conduit("tConduit"){{
            requirements(Category.liquid, with(NSitems.tantalum, 2, NSitems.naturit, 1));
            liquidCapacity = 10f;
            liquidPressure = 1.025f;
            health = 50;
            underBullets = false;
            bridgeReplacement = tcBridge;
        }};
    }
}
