package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import arc.math.Rand;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;

import java.util.Random;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;

public class enemyunitFx {
    public static Effect
    smallCyanHit = new Effect(10, e ->{
        color(Color.valueOf("8deebb"), Color.white, e.fin());
        stroke(e.fout(Interp.circleOut));
        Lines.square(e.x, e.y, 3 * e.fin(), 45);
    }),

    cyanHit = new Effect(10, e ->{
        color(Color.valueOf("8deebb"), Color.white, e.fin());
        stroke(1.7f * e.fout());
        Lines.square(e.x, e.y, 6f * e.fin(), 45);

        stroke(e.fout());
        Lines.square(e.x, e.y, 7f * e.fin(), 0);
    }),

    bombEx = new Effect(20, e ->{
        color(Color.valueOf("8deebb"), Color.white, e.fin());
        stroke(e.fout(Interp.circleOut));
        Lines.square(e.x, e.y, 8 * e.fin(), 45);

        for(int i = 0; i < 4; i++) {
            Drawf.tri(e.x + Angles.trnsx(90 * i, 0), e.y + Angles.trnsy(90 * i, 0), 9f * e.fout(), 6f, 90 * i);
        };
    }),
    PABBUcannonHit = new MultiEffect(
        new Effect(15, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            stroke(1.4f * e.fout(Interp.circleOut));

            Lines.square(e.x, e.y, 8 * e.fin(), 45);
        }),
        new Effect(25, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            Fill.circle(e.x, e.y, 4 * e.fout(Interp.circleOut));
        })
    ),

    bombHit = new MultiEffect(
        new Effect(10, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            int offset = Mathf.randomSeed(e.id, 0, 180);

            Drawf.tri(e.x, e.y, 4 * e.fout(), 10, offset + 45);
            Drawf.tri(e.x, e.y, 4 * e.fout(), 10, offset + 135);
            Drawf.tri(e.x, e.y, 4 * e.fout(), 10, offset + 225);
            Drawf.tri(e.x, e.y, 4 * e.fout(), 10, offset + 315);
            Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset + 90);
            Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset + 270);
        }), new Effect(15, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            stroke(3f * e.fout());

            Lines.square(e.x, e.y, 15 + 5 * e.fin(), 45);
        })
    ),

    artHit = new MultiEffect(
            new Effect(20, e -> {
                color(Color.valueOf("8deebb"), Color.white, e.fin());
                int offset = Mathf.randomSeed(e.id, 0, 180);

                Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset);
                Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset + 180);
                Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset + 90);
                Drawf.tri(e.x, e.y, 4 * e.fout(), 30, offset + 270);
            }), new Effect(30, e -> {
        color(Color.valueOf("8deebb"), Color.white, e.fin());
        stroke(5f * e.fout());

        Lines.square(e.x, e.y, 16 + 16 * e.fin(), 45);
        Lines.square(e.x, e.y, 32 - 16 * e.fin());
    })
    ),
    PABBUdeathFx = new MultiEffect(
        new Effect(80, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            stroke(6f * e.fout());

            Lines.square(e.x, e.y, 80 * e.fin(Interp.pow2Out), 45 + 45 * e.fout(Interp.pow2Out));
        }), new Effect(80, e -> {
            color(Color.valueOf("8deebb"), Color.white, e.fin());
            stroke(6f * e.fout());

            Lines.square(e.x, e.y, 80 * e.fin(Interp.pow2Out), 45 + 45 * e.fin(Interp.pow2Out));
        }), new Effect(40, e -> {
            color(Color.white, Color.valueOf("8deebb"), e.fin());
            stroke(0.5f + e.fout());

            randLenVectors(e.id, 10, e.fin() * 15f, (x, y) -> {
                float ang = Mathf.angle(x, y);
                lineAngle(e.x + x, e.y + y, ang, e.fout() * 5 + 1f);
            });

            Drawf.light(e.x, e.y, 60f, Color.valueOf("8deebb"), e.fout());
        })
    );
}
