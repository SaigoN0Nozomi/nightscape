package nightscape.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Planets;
import mindustry.type.Item;
import mindustry.type.Planet;

import static mindustry.content.Items.*;
import static mindustry.content.Planets.*;

public class NSitems {
    public static Item
    tantalum, naturit, velonium, zirconium, streby, dense, cyanid, vanadium;

    public static Seq<Item> chordaItems = new Seq<>();

    public static void load() {
        tantalum = new Item("tantalum", Color.valueOf("7f9da9")){{
            cost = 1f;
            hardness = 2;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        naturit = new Item("naturit", Color.valueOf("eecd74")){{
            cost = 1f;
            flammability = 0.4f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        zirconium = new Item("zirconium", Color.valueOf("9091a8")){{
            cost = 1f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
            hardness = 2;
        }};

        velonium = new Item("velonium", Color.valueOf("abced3")){{
            cost = 2f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        streby = new Item("streby", Color.valueOf("bbd3c1")){{
            cost = 2f;
            charge = 1.3f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        dense = new Item("dense", Color.valueOf("ba8cc8")){{
            cost = 3f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        cyanid = new Item("cyanid", Color.valueOf("8deebb")){{
            cost = 2f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        vanadium = new Item("vanadium", Color.valueOf("f8f7c7")){{
            cost = 2f;
            hiddenOnPlanets = new Planet[]{serpulo, erekir};
        }};

        chordaItems.addAll(tantalum, naturit, velonium, silicon, dense, zirconium, streby, vanadium, cyanid);
    }
}
