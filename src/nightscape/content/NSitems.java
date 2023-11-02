package nightscape.content;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.type.Item;

import static mindustry.content.Items.*;

public class NSitems {
    public static Item
            tantalum, naturit, velonium, electrum;

    public static final Seq<Item> ChItems = new Seq<>();

    public static void load() {
        tantalum = new Item("tantalum", Color.valueOf("7f9da9")){{
            cost = 1f;
            hardness = 2;
        }};

        naturit = new Item("naturit", Color.valueOf("eecd74")){{
            cost = 1f;
            flammability = 1f;
        }};

        velonium = new Item("velonium", Color.valueOf("abced3")){{
            cost = 2f;
        }};

        electrum = new Item("electrum", Color.valueOf("e6dd8b")){{
            cost = 2f;
            charge = 0.1f;
            hardness = 3;
        }};

        ChItems.addAll(
                tantalum, naturit, velonium, electrum
        );
    }
}
