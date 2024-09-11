package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.content.Items;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;
import nightscape.content.NSitems;

import static arc.graphics.g2d.Draw.alpha;
import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;
import static mindustry.content.Fx.v;

public class turretFx {
    public static Effect
    victimSmoke = new Effect(17f, e -> {
        color(Color.valueOf("7f9da9"), Color.lightGray, Color.gray, e.fin());

        randLenVectors(e.id, 6, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
            Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
        });
    }),

    victimTHit = new Effect(10, e -> {
        color(NSitems.tantalum.color);

        stroke(0.4f + e.fout());

        randLenVectors(e.id, 4, e.fin() * 12f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 4);
    }),

    victimVHit = new Effect(10, e -> {
        color(NSitems.velonium.color, Color.gray, e.fin());

        stroke(0.6f + e.fout());

        randLenVectors(e.id, 5, e.fin() * 16f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fout() * 7);
    }),
    victimSTrail = new Effect(35, e -> {
        color(Items.silicon.color);
        Fill.circle(e.x, e.y, 3 * e.fout());
    }),
    victimSHit = new Effect(10, e -> {
        color(Items.silicon.color);

        stroke(0.4f + e.fout());

        randLenVectors(e.id, 4, e.fin() * 12f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 4);
    }),
    flickerHit = new MultiEffect(
        new Effect(30f, e -> {
            color(NSitems.naturit.color, Color.gray.a(0.4f), e.fin());

            randLenVectors(e.id, 12, e.finpow() * 45f, e.rotation, 360f, (x, y) -> {
                Fill.circle(e.x + x, e.y +y, e.fout() * 3f + 0.3f);
            });
        }), new Effect(8, e ->{
            color(Color.valueOf("eecd74").a(0.5f), Color.gray.a(0.1f), e.fin());

            stroke(e.fout() * 4);
            Lines.circle(e.x, e.y, 35 + e.fin() * 10);
            stroke(e.fout() * 2);
            Lines.circle(e.x, e.y, 15 + e.fin() * 8);
        })
    ),
    adrenalinHitTantal = new Effect(13, e -> {
        color(NSitems.tantalum.color);

        randLenVectors(e.id, 7, e.fin(Interp.pow2Out) * 15f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 6);

        Drawf.tri(e.x, e.y, 5 * e.fout(), 11, e.rotation);
        Drawf.tri(e.x, e.y, 5 * e.fout(), 7, e.rotation + 180);
    }),

    adrenalinHitZirconium = new Effect(21, e -> {
        color(NSitems.zirconium.color);

        randLenVectors(e.id, 8, e.fin(Interp.pow2Out) * 30f, e.rotation, 30, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 6);
        });

        stroke(e.fout() * 1);
        Lines.circle(e.x, e.y, e.fin() * 4);

        Drawf.tri(e.x, e.y, 3 * e.fout(), 22, e.rotation);
        Drawf.tri(e.x, e.y, 3 * e.fout(), 6, e.rotation + 180);
    }),

    adrenalineShoot = new Effect(30, e -> {
        color(Color.orange, Color.gray, e.fin());

        randLenVectors(e.id, 8, e.fin(Interp.pow3Out) * 25, e.rotation, 45, (x, y) ->{
            Fill.circle(e.x + x, e.y + y, e.fout() * 3);
        });
    }),
    stelleHitOzone = new MultiEffect(
        new Effect(25f, e -> {
            color(Color.valueOf("d297e199"), Color.lightGray, e.fin());

            e.scaled(12f, s -> {
                stroke(s.fout() * 2);
                Lines.circle(e.x, e.y, s.finpow() * 4f);
            });
        }), new Effect(60f, e -> {
            color(Color.valueOf("d297e199").a(0.7f), Color.lightGray.a(0.4f), e.fin());

            randLenVectors(e.id, 6, e.finpow() * 17f, e.rotation, 50f, (x, y) -> {
                Fill.circle(e.x + x, e.y +y, e.fout() * 2f);
            });
        })
    ),
    stelleHitAm = new MultiEffect(
        new Effect(35f, e -> {
            color(Color.valueOf("deffd199"), Color.lightGray.a(0.55f), e.fin());

            e.scaled(12f, s -> {
                stroke(s.fout() * 2);
                Lines.circle(e.x, e.y, s.finpow() * 6f);
            });
        }), new Effect(40f, e -> {
            color(Color.valueOf("deffd199").a(0.7f), Color.lightGray.a(0.4f), e.fin());

            randLenVectors(e.id, 5, e.finpow() * 17f, e.rotation, 50f, (x, y) -> {
                Fill.circle(e.x + x, e.y +y, e.fout() * 2f);
            });
        })
    ),
    needleHit = new Effect(15, e -> {
        color(Color.valueOf("fbd9d1"), Color.valueOf("665551"), e.fin());
        Drawf.tri(e.x, e.y, 2 * e.fout(), 7, e.rotation);
        Drawf.tri(e.x, e.y, 2 * e.fout(), 3, e.rotation + 180);
    }),
    needleDesp = new Effect(10, e ->{
        color(Color.valueOf("fbd9d1"), Color.valueOf("665551"), e.fin());

        Drawf.tri(e.x, e.y, 4 * e.fout(), 9, e.rotation);
        Drawf.tri(e.x, e.y, 4 * e.fout(), 2, e.rotation + 180);

        randLenVectors(e.id, 4, e.fin(Interp.pow2Out) * 20f, e.rotation, 16, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3);
        });
    }),
    cleanHit = new Effect(10, e -> {
        color(Color.valueOf("ffaa5f").a(0.9f), Color.gray.a(0.4f), e.fin());

        stroke(e.fout() * 4);
        Lines.circle(e.x, e.y, 8 + e.fin() * 10);
        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, 16 + e.fin() * 8);

        for(int i=0; i < 4; i++) {
            Drawf.tri(e.x, e.y, 3 * e.fout(), 16, e.rotation + 90 * i);
        }
    }),
    hornetHit = new MultiEffect(
        new Effect(360, e -> {
            color(Color.valueOf("aaffc6").a(0.5f));

            randLenVectors(e.id, 18, e.fin(Interp.circleOut) * 102, (x, y) -> {
                Fill.circle(e.x + x, e.y + y, 15f * e.fout(Interp.circleOut));
            });

            color(Color.valueOf("aaffc6").a(0.5f));

            randLenVectors(e.id, 10, e.fin(Interp.circleOut) * 146, (x, y) -> {
                Fill.circle(e.x + x, e.y + y, 8f * e.fout(Interp.circleOut));
            });
        }), new Effect(360, e -> {
            color(Color.valueOf("aaffc6").a(0.2f), Color.valueOf("aaffc6").a(0.06f), e.fin());

            int points = 8;
            float offset = Mathf.randomSeed(e.id, 360f);
            for(int i = 0; i < points; i++){
                float angle = i* 360f / points + offset;
                Drawf.tri(e.x + Angles.trnsx(angle, 90 + 12*e.fin()), e.y + Angles.trnsy(angle, 90 + 12*e.fin()), 6f, -24f * e.fout(), angle);
                Drawf.tri(e.x + Angles.trnsx(angle, 90 + 12*e.fin()), e.y + Angles.trnsy(angle, 90 + 12*e.fin()), 6f, 12f * e.fout(), angle);
                Drawf.tri(e.x + Angles.trnsx(angle  + 22.5f, 90 + 12*e.fin()), e.y + Angles.trnsy(angle  + 22.5f, 90 + 12*e.fin()), 6f, -9f * e.fout(), angle + 22.5f);
                Drawf.tri(e.x + Angles.trnsx(angle  + 22.5f, 90 + 12*e.fin()), e.y + Angles.trnsy(angle  + 22.5f, 90 + 12*e.fin()), 6f, 5f * e.fout(), angle + 22.5f);
            }
        })
    ),
    hornetShoot = new Effect(160f, 300f, e -> {
        color(Color.valueOf("aaffc6"));
        alpha(0.5f);
        rand.setSeed(e.id);
        for(int i = 0; i < 35; i++){
            v.trns(e.rotation + 180f + rand.range(21f), rand.random(e.finpow() * 90f)).add(rand.range(3f), rand.range(3f));
            e.scaled(e.lifetime * rand.random(0.2f, 0.5f), b -> {
                Fill.circle(e.x + v.x, e.y + v.y, b.fout() * 5f + 0.3f);
            });
        }
    }),
    magneticHit = new Effect(15, e -> {
        color(NSitems.velonium.color);

        randLenVectors(e.id, 5, e.fin(Interp.pow2Out) * 15f, e.rotation, 60, (x, y) -> {
            float ang = Mathf.angle(x, y);
            Fill.circle(e.x + x, e.y + y, e.fout() * 2);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 8);
    });
}
