package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import nightscape.content.NSitems;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

public class upunitFx {
    public static Effect

    impTrail = new Effect(50, e -> {
        color(Color.valueOf("ffd8d144"));
        int r = Mathf.randomSeed(e.id,1, 2);
        randLenVectors(e.id, 1, e.finpow() * 4, (x, y) ->{
            Fill.circle(e.x + x, e.y + y, r * e.fout());
        });
    }),

    impShield = new Effect(120, e -> {
        for(int i = 0; i < 3; i++){
            Drawf.tri(e.x + Angles.trnsx(120 * i, 0), e.y + Angles.trnsy(120 * i, 0), 4f * e.fout(), 16f, 120 * i);
        }
    }),
    vassagoTrail = new Effect(90, e -> {
        color(Color.valueOf("ffd8d144"));
        int rad = Mathf.randomSeed(e.id, 2, 3);
        int rangex = Mathf.randomSeed(e.id, 0, 8) -4;
        int rangey = Mathf.randomSeed(e.id, 0, 8) -4;
        randLenVectors(e.id, 1, e.finpow() * 5, (x, y) -> {
            Fill.circle(e.x + x + rangex, e.y + y + rangey, rad * e.fout());
        });
    }),
    vassagoDamage = new MultiEffect(
            new Effect(50, e -> {
        color(Color.valueOf("ffd8d1"), Color.white, e.fin());
        stroke(1f + e.fout());

        randLenVectors(e.id, 14, e.finpow() * 80f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.foutpow() * 8);
        });

        stroke(2f * e.fout());
        Lines.circle(e.x, e.y, 80 * e.fin(Interp.circleOut));
    }), new Effect(120, e -> {
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x + Angles.trnsx(90 * i, 0), e.y + Angles.trnsy(90 * i, 0), 6f * e.fout(), 27f, 90 * i);
        }
    })),

    bapDamage = new MultiEffect(
        new Effect(180, e -> {
            color(Color.valueOf("ffd8d1"), Color.white, e.fin());
            stroke(1f + e.fout());

            randLenVectors(e.id, 14, e.finpow() * 80f, (x, y) -> {
                float ang = Mathf.angle(x, y);
                lineAngle(e.x + x, e.y + y, ang, e.foutpow() * 8);
            });

            stroke(2f * e.fout());
            Lines.circle(e.x, e.y, 128 * e.fin(Interp.circleOut));
            Lines.circle(e.x, e.y, 64 * e.fin(Interp.circleOut));
        }), new Effect(120, e -> {
            for(int i = 0; i < 8; i++){
                Drawf.tri(e.x + Angles.trnsx(45 * i, 0), e.y + Angles.trnsy(45 * i, 0), 3f * e.fout(), 128f * e.fout(), 45 * i);
            }
        }
    )),

    bapTrail = new Effect(80, e -> {
        color(Color.valueOf("ffd8d144"));
        int r = Mathf.randomSeed(e.id, 2, 4);
        randLenVectors(e.id, 1, e.finpow() * 4, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, r * e.fout());
        });
    }),

    bapFlame = new MultiEffect(
        new Effect(32, 40, e -> {
            color(Pal.lightFlame, Color.valueOf("ffd8d144"), Color.gray, e.fin());

            randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 20f, (x, y) -> {
                Fill.circle(e.x + x, e.y + y, 0.75f + e.fout() * 1.5f);
            });
        }),
        new Effect(16, 40, e -> {
            color(Pal.darkFlame, Color.valueOf("ffd8d144"), Color.darkGray, e.fin());

            randLenVectors(e.id, 8, e.finpow() * 60f, e.rotation, 10f, (x, y) -> {
                Fill.circle(e.x + x, e.y + y, 0.45f + e.fout());
            });
        })
    ),

    belialSafeEffect = new MultiEffect(
        new Effect(80, e -> {
            color(Color.valueOf("ffd8d1"), Color.white, e.fin());
            stroke(1.3f + e.fout());

            randLenVectors(e.id, 14, e.finpow() * 50f, (x, y) -> {
                float ang = Mathf.angle(x, y);
                lineAngle(e.x + x, e.y + y, ang, e.foutpow() * 8);
            });

            stroke(2f * e.fout());
            Lines.circle(e.x, e.y, 60 * e.fin(Interp.circleOut));
            Lines.circle(e.x, e.y, 40 * e.fin(Interp.circleOut));
        }), new Effect(120, e -> {
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x + Angles.trnsx(90 * i, 0), e.y + Angles.trnsy(90 * i, 0), 9f * e.fout(), 41f, 90 * i);
        }}), new Effect(120, e -> {
            for (int i = 0; i < 4; i++) {
                Drawf.tri(e.x + Angles.trnsx(90 * i + 45, 0), e.y + Angles.trnsy(90 * i + 45, 0), 6f * e.fout(), 27f, 90 * i + 45);
            }
        }
    )),

    belialTrail = new Effect(120, e -> {
        color(Color.valueOf("ffd8d144"));
        int rad = Mathf.randomSeed(e.id, 3, 6);
        int rangex = Mathf.randomSeed(e.id, 0, 14) -7;
        int rangey = Mathf.randomSeed(e.id, 0, 14) -7;
        randLenVectors(e.id, 1, e.finpow() * 9, (x, y) -> {
            Fill.circle(e.x + x + rangex, e.y + y + rangey, rad * e.fout());
        });
    }),

    dongHit = new Effect(10, e -> {
        color(Color.valueOf("ffd8d1"), Color.valueOf("e8998c"), e.fin());

        stroke(0.6f + e.fout());

        randLenVectors(e.id, 4, e.fin() * 12f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 4);
    });
}
