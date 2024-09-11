package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;
import mindustry.type.UnitType;
import nightscape.content.NSitems;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class StatusFx {
    public static Effect

    corosion = new MultiEffect(
            new Effect(50, e -> {
                randLenVectors(e.id, 1, 8f + e.fin() * 8f, (x, y) -> {
                    color(Color.valueOf("ffbdd4ff"), Color.gray, e.fout());
                    Fill.circle(e.x + x, e.y + y, e.fout() * 1.2f);
                });
            }),
            new Effect(40, e -> {
                color(Color.valueOf("ffbdd4ff"), Color.gray, e.fout());

                randLenVectors(e.id, 4, e.finpow() * 14, (x, y) -> {
                    Fill.square(e.x + x, e.y + y, e.fout() * 1.2f, 45);
                });
            })
    ),

    charge = new Effect(30, e -> {
        color(Color.valueOf("dbd187"));
        int r = Mathf.randomSeed(e.id,1, 3);
        randLenVectors(e.id, 1, e.finpow() * 6, (x, y) ->{
            Fill.circle(e.x + x, e.y + y, r * e.fout());
        });
    }),

    shock = new Effect(30, e -> {
        stroke(0.2f + e.fout());

        randLenVectors(e.id, 3, e.finpow() * 8f, (x, y) -> {
            color(Color.orange, Color.white, e.fin());

            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.foutpow() * 1);
        });
    }),

    growth = new Effect(15, e -> {
        color(NSitems.naturit.color, Color.gray.a(0.4f), e.fin());

        randLenVectors(e.id, 4, e.finpow() * 12f, e.rotation, 360f, (x, y) -> {
            Fill.circle(e.x + x, e.y +y, e.fout() * 2f + 0.3f);
        });
    }),

    defended = new Effect(40, e -> {
        color(Color.valueOf("889af0"));
        alpha(e.fout() * 0.5f);
        randLenVectors(e.id, 1, e.finpow() * 4f, (x, y) -> {
            Lines.circle(e.x, e.y, 5 * e.fin());
        });
    });
}
