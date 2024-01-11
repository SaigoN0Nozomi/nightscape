package nightscape.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class NSLiquids {
    public static Liquid
    ammonia;

    public static void load(){
        ammonia = new Liquid("ammonia"){{
            gas = false;
            color = gasColor = lightColor = barColor = Color.valueOf("deffd1");
            flammability = 0.05f;
            explosiveness = 0.45f;
            temperature = 0.2f;
            heatCapacity = 0.6f;
            coolant = true;
            viscosity = 0.3f;
        }};
    }
}
