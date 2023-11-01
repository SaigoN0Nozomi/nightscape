package nightscape.content;

import mindustry.world.meta.Attribute;

public class NSattribute {
    public static Attribute
    naturit, ozone;

    public static void load() {
        naturit = Attribute.add("naturit");
        ozone = Attribute.add("Ozone");
    }
}
