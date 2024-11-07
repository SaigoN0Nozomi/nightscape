package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import nightscape.content.NSitems;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;
import static arc.math.Mathf.randomSeed;
import static mindustry.content.Fx.v;

public class blockFx {

    public static Effect
    natExtraction = new Effect(60, e -> {
        randLenVectors(e.id, 4, e.fout(Interp.pow3In) * 22f + 4, (x, y) -> {
            color(Color.valueOf("e4ac5c"), Color.valueOf("fff989").a(0.4f), e.fin());

            Fill.circle(e.x + x, e.y + y, 0.1f + e.fout() * 2f);
        });
    }),

    shockBlast = new MultiEffect(
        new Effect(30, e -> {
            color(Color.pink, Color.lightGray, e.fin());
            randLenVectors(e.id, 12, 5f + e.finpow() * 14f, (x, y) -> {
                Fill.square(e.x + x, e.y + y, e.fout() * 2.5f + 0.5f, 45);
            });
        }), new Effect(50f, e -> {
            color(e.color);

            stroke(e.fout() * 1.5f);

            randLenVectors(e.id, 12, 4f + e.finpow() * e.rotation, (x, y) -> {
                lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fout() * 5 + 1f);
            });

            e.scaled(30f, b -> {
                Lines.stroke(3f * b.fout());
                Lines.circle(e.x, e.y, b.finpow() * 19f);
            });
        }), new Effect(130f, e -> {
            float length = 3f + e.finpow() * 14f;
            rand.setSeed(e.id);
            for(int i = 0; i < 13; i++){
                v.trns(rand.random(360f), rand.random(length));
                float sizer = rand.random(1.3f, 3.7f);

                e.scaled(e.lifetime * rand.random(0.5f, 1f), b -> {
                    color(Color.gray, b.fslope() * 0.93f);

                    Fill.circle(e.x + v.x, e.y + v.y, sizer + b.fslope() * 1.2f);
                });
            }
        }).startDelay(30f)
    ),

    healWall = new Effect(20, e -> {
        if(!(e.data instanceof Block block)) return;

        mixcol(e.color, 0.1f);
        alpha(e.fout() * 0.5f);
        Draw.rect(block.fullIcon, e.x, e.y);
    }),

    velFurnace = new Effect(30, e -> {
        randLenVectors(e.id, 8, 4f + e.fin(Interp.pow3Out) * 5f, (x, y) -> {
            color(Color.valueOf("a4c9dd"), Color.valueOf("ffbe94").a(0.4f), e.fin());
            Fill.square(e.x + x, e.y + y, 0.1f + e.fout() * 3f, 45);
        });
    }),

    blastFurnace = new Effect(20, e -> {
        int rnd = randomSeed(e.id, 0, 60);

        color(Color.valueOf("ff675a"), Color.orange, e.fin());
        alpha(e.fout()*0.7f + 0.3f);
        Lines.stroke(2 * e.fout());
        Lines.circle(e.x, e.y, 14 * e.fin(Interp.circleOut));
        Drawf.light(e.x, e.y, 43 * e.fout(), e.color, e.fin());

        stroke(0.4f + e.fout());

        randLenVectors(e.id, 7, e.fin() * 15f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 2 + 0.5f);
        });

        Drawf.tri(e.x + Angles.trnsx(90, 0), e.y + Angles.trnsy(90, 0), 2f * e.fout(), 9f, e.rotation + rnd + rnd * e.fout(Interp.pow2));
        Drawf.tri(e.x + Angles.trnsx(90, 0), e.y + Angles.trnsy(90, 0), 2f * e.fout(), -9f, e.rotation + rnd + rnd * e.fout(Interp.pow2));
        Drawf.tri(e.x + Angles.trnsx(90, 0), e.y + Angles.trnsy(90, 0), 2f * e.fout(), 9f, e.rotation - rnd - rnd * e.fout(Interp.pow2));
        Drawf.tri(e.x + Angles.trnsx(90, 0), e.y + Angles.trnsy(90, 0), 2f * e.fout(), -9f, e.rotation - rnd - rnd * e.fout(Interp.pow2));
    }),

    shieldBrokeFx = new MultiEffect( new Effect(140, b -> {
        b.lifetime = 21 + 5 * 45f;

        color(Color.valueOf("c8b591"));
        alpha(0.7f);
        for(int i = 0; i < 4; i++){
            rand.setSeed(b.id*2 + i);
            float lenScl = rand.random(0.4f, 1f);
            int fi = i;
            b.scaled(b.lifetime * lenScl, e -> {
                randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * 5), 8f * 3, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * (7 * 0.87f);

                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, Color.valueOf("c8b591"), 0.5f);
                });
            });
        }
    }), new Effect(40, e -> {
        stroke(0.8f + e.fout());

        randLenVectors(e.id, 9, e.finpow() * 23f, (x, y) -> {
            color(Color.valueOf("dea63f"), Color.white, e.fin());

            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 9);
        });
    })),

    furnSmoke = new Effect(120, e -> {
        color(Color.valueOf("a4c9dd").a(0.3f), Color.valueOf("a0a0a0").a(0.1f), e.fin());

        randLenVectors(e.id, 1, e.finpow() * 20f, 60, 10, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 2 * e.fout());
        });
    }),

    furnCraft = new Effect(180, e -> {
        color(Color.valueOf("a4c9dd").a(0.6f), Color.valueOf("a0a0a0").a(0.3f), e.fin());

        randLenVectors(e.id, 5, e.finpow() * 30f, 60, 18, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 2 * e.fout());
        });
    }),
    amSmoke = new Effect(60, e -> {
        color(Color.valueOf("a9db7f"), Color.gray, e.fin());
        alpha(0.55f);

        randLenVectors(e.id, 1, e.finpow() * 12f, 0, 360, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 3 * e.fout());
        });
    });
}
