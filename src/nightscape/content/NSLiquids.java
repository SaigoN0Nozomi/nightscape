package nightscape.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

public class NSLiquids {
    public static Liquid
    ammonia, fernum;

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
        fernum = new Liquid("fernum"){{
            gas = false;
            color = gasColor = lightColor = barColor = Color.valueOf("ab938d");
            flammability = 0f;
            explosiveness = 0f;
            temperature = 0.7f;
            heatCapacity = 0.1f;
            coolant = false;
            viscosity = 0.6f;
        }};
    }
}
