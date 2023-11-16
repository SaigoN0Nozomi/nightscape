package nightscape.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.liquid.LiquidBridge;
import mindustry.world.blocks.liquid.LiquidJunction;
import mindustry.world.blocks.liquid.LiquidRouter;

import static mindustry.type.ItemStack.with;

public class NSBdistribution {
    public static Block
            tRouter, tJunction, tBridge, tConveyor, tSorter, tSorterInv, tGate, tGateInv,
            tcRouter, tcBridge, tConduit, tcJuniction;

    public static void load(){
        tConveyor = new Conveyor("tConveyor"){{
            requirements(Category.distribution, with(NSitems.tantalum, 2));
            health = 20;
            speed = 0.05f;
            displayedSpeed = 6f;
            underBullets = false;
            researchCost = ItemStack.with(NSitems.tantalum, 5);
        }};

        tJunction = new Junction("tJunction"){{
            requirements(Category.distribution, with(NSitems.tantalum, 6));
            health = 30;
            ((Conveyor)tConveyor).junctionReplacement = this;
            researchCost = ItemStack.with(NSitems.tantalum, 15);
        }};

        tRouter = new Router("tRouter"){{
            requirements(Category.distribution, with(NSitems.tantalum, 6));
            health = 35;
            researchCost = ItemStack.with(NSitems.tantalum, 15);
        }};

        tSorter = new Sorter("tSorter"){{
            requirements(Category.distribution, with(NSitems.tantalum, 9));
            health = 45;
            researchCost = ItemStack.with(NSitems.tantalum, 30);
        }};

        tSorterInv = new Sorter("tSorterInv"){{
            requirements(Category.distribution, with(NSitems.tantalum, 9));
            health = 45;
            invert = true;
            researchCost = ItemStack.with(NSitems.tantalum, 30);
        }};

        tGate = new OverflowGate("tGate"){{
            requirements(Category.distribution, with(NSitems.tantalum, 7));
            health = 40;
            researchCost = ItemStack.with(NSitems.tantalum, 25);
        }};

        tGateInv = new OverflowGate("tGateInv"){{
            requirements(Category.distribution, with(NSitems.tantalum, 7));
            health = 40;
            invert = true;
            researchCost = ItemStack.with(NSitems.tantalum, 25);
        }};

        tBridge = new ItemBridge("tBridge"){{
            requirements(Category.distribution, with(NSitems.tantalum, 12, NSitems.zirconium, 6));
            health = 90;
            range = 3;
            squareSprite = false;
            hasPower = false;
            itemCapacity = 5;
            ((Conveyor)tConveyor).bridgeReplacement = this;
            researchCost = ItemStack.with(NSitems.tantalum, 45, NSitems.zirconium, 30);
        }};

        tConduit = new Conduit("tConduit"){{
            requirements(Category.liquid, with(NSitems.tantalum, 2, NSitems.naturit, 1));
            liquidCapacity = 10f;
            liquidPressure = 1.025f;
            health = 30;
            underBullets = false;
            researchCost = ItemStack.with(NSitems.tantalum, 10, NSitems.naturit, 5);
        }};

        tcJuniction = new LiquidJunction("tcJuniction"){{
            requirements(Category.liquid, with(NSitems.tantalum, 6, NSitems.naturit, 4));
            health = 30;
            researchCost = ItemStack.with(NSitems.tantalum, 15, NSitems.naturit, 10);
            ((Conduit)tConduit).junctionReplacement = this;
        }};

        tcRouter = new LiquidRouter("tcRouter"){{
            requirements(Category.liquid, with(NSitems.tantalum, 6, NSitems.naturit, 3));
            health = 60;
            researchCost = ItemStack.with(NSitems.tantalum, 20, NSitems.naturit, 15);
        }};

        tcBridge = new LiquidBridge("tcBridge"){{
            requirements(Category.liquid, with(NSitems.tantalum, 15, NSitems.naturit, 10, NSitems.zirconium, 6));
            health = 75;
            range = 3;
            squareSprite = false;
            hasPower = false;
            ((Conduit)tConduit).bridgeReplacement = this;
            researchCost = ItemStack.with(NSitems.tantalum, 50, NSitems.naturit, 30, NSitems.zirconium, 25);
        }};
    }
}
