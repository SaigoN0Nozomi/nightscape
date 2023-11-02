package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.type.StatusEffect;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class NSstatus {
    public static StatusEffect ozoneCorrosion, prepared, overCharged;

    public static void load(){
        ozoneCorrosion = new StatusEffect("OzoneCorrosion"){{
            speedMultiplier = 0.90f;
            damageMultiplier = 0.90f;
            effect = new Effect(30, e -> {
                randLenVectors(e.id, 1, 8f + e.fin() * 8f, (x, y) -> {
                    color(Color.valueOf("ffbdd4ff"), Color.valueOf("fc81dd22"), e.fin());
                    Fill.circle(e.x + x, e.y + y, e.fout() * 1.2f);
                });
            });
            damage = 0.5f / 60f;
            effectChance = 0.05f;
        }};

        overCharged = new StatusEffect("Overcharged"){{
            speedMultiplier = 0.7f;
            reloadMultiplier = 0.7f;
            damageMultiplier = 0.7f;
            damage = 10f/60f;
        }};
    }
}
