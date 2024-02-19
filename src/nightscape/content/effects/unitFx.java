package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Building;
import mindustry.gen.Teamc;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.world.Block;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static arc.math.Mathf.rand;

public class unitFx {
    public static Effect

    //point + vector + pluvia
    purpleHit = new Effect(16, e -> {
        color(Color.white, Color.valueOf("d297e1"), e.fin());

        e.scaled(12f, s -> {
            stroke(s.fout() * 2);
            Lines.circle(e.x, e.y, s.finpow() * 5f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 5, e.fin() * 15f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        Drawf.light(e.x, e.y, 20f, Color.valueOf("d297e1"), 0.6f * e.fout());
    }),

    //point + vector + pluvia
    purpleSmoke = new Effect(15, e -> {
        color(Color.valueOf("d297e1"), Color.lightGray, e.fin());

        randLenVectors(e.id, 6, e.finpow() * 9, e.rotation, 35, (x, y) -> {
            Fill.square(e.x + x, e.y + y, e.fout() * 2, 45);
        });
    }),

    //vector
    purpleHitBig = new Effect(25, e -> {
        color(Color.white, Color.valueOf("d297e1"), e.fin());

        e.scaled(20f, s -> {
            stroke(s.fout() * 2);
            Lines.circle(e.x, e.y, s.finpow() * 8f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 8, e.finpow() * 40f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        Drawf.light(e.x, e.y, 20f, Color.valueOf("d297e1"), 0.6f * e.fout());
    }),

    blueBlast = new Effect(25, e -> {
        color(Color.white, Color.valueOf("d8f3f4"), e.fin());

        e.scaled(20f, s -> {
            stroke(s.fout() * 2);
            Lines.circle(e.x, e.y, s.finpow() * 35f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 16, e.finpow() * 45f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            Fill.square(e.x + x, e.y + y, e.fout() * 7, 45);
        });

        Drawf.light(e.x, e.y, 20f, Color.valueOf("d8f3f4"), 0.6f * e.fout());
    }),

    //planum
    planumCharge = new Effect(25, e -> {
        e.scaled(25f, s -> {
            color(Color.valueOf("d297e1"));
            stroke(s.fout() * 2);
            Fill.circle(e.x, e.y, s.fin() * 4f);
        });
        e.scaled(25f, s -> {
            color(Color.white);
            stroke(s.fout() * 2);
            Fill.circle(e.x, e.y, s.fin() * 2f);
        });
    }),

    planumSmoke = new MultiEffect(
      new Effect(50, e -> {
          color(Color.valueOf("d297e1"), Color.lightGray, e.fin());

          randLenVectors(e.id, 8, e.fin(Interp.circleOut) * 18, e.rotation, 360, (x, y) -> {
              Fill.square(e.x + x, e.y + y, e.fout() * 4, 45);
          });
      }),
      new Effect(25, e -> {
          color(Color.valueOf("d297e1"), Color.lightGray, e.fin());

          stroke(e.fout() * 4);
          Lines.circle(e.x, e.y, e.fin(Interp.circleOut) * 12f);
      })
    ),

    blueHit = new Effect(20, e -> {
        color(Color.white, Color.valueOf("d8f3f4"), e.fin());

        e.scaled(12f, s -> {
            stroke(s.fout() * 2);
            Lines.circle(e.x, e.y, s.finpow() * 3f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 5, e.fin() * 15f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        Drawf.light(e.x, e.y, 20f, Color.valueOf("d8f3f4"), 0.6f * e.fout());
    }),

    blueSquareHit = new Effect(30, e -> {
        color(Color.white, Color.valueOf("d8f3f4"), e.fin());

        e.scaled(15f, s -> {
            stroke(s.fout() * 3);
            Lines.circle(e.x, e.y, s.finpow() * 4f);
        });

        randLenVectors(e.id, 6, e.fin() * 12, (x, y) -> {
            Fill.square(e.x + x, e.y + y, e.fout() * 2, 45);
        });
    }),

    blueSmoke = new Effect(15, e -> {
        color(Color.valueOf("d8f3f4"), Color.lightGray, e.fin());

        randLenVectors(e.id, 6, e.finpow() * 9, e.rotation, 35, (x, y) -> {
            Fill.square(e.x + x, e.y + y, e.fout() * 2, 45);
        });
    }),

    blueSmokeBig = new Effect(25, e -> {
        color(Color.valueOf("d8f3f4"), Color.lightGray, e.fin());

        randLenVectors(e.id, 5, e.finpow() * 14, e.rotation, 35, (x, y) -> {
            Fill.square(e.x + x, e.y + y, e.fout() * 2, 45);
        });
    }),


    blueHitBig = new Effect(20, e -> {
        color(Color.white, Color.valueOf("d8f3f4"), e.fin());

        e.scaled(12f, s -> {
            stroke(s.fout() * 2);
            Lines.circle(e.x, e.y, s.finpow() * 6f);
        });

        stroke(0.5f + e.fout());

        randLenVectors(e.id, 7, e.fin() * 28f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3 + 1f);
        });

        Drawf.light(e.x, e.y, 20f, Color.valueOf("d8f3f4"), 0.6f * e.fout());
    }),

    diluvioCharge = new Effect(185, e -> {
        color(Color.valueOf("d8f3f4"));
        Fill.circle(e.x, e.y, e.fin() * 10f);
        color(Color.white);
        Fill.circle(e.x, e.y, e.fin() * 5f);
    }),

    diluvioHit = new Effect(50f, 100f, e -> {
        e.scaled(7f, b -> {
            color(Color.valueOf("d8f3f4"), b.fout());
            Fill.circle(e.x, e.y, 50);
        });

        color(Color.valueOf("d8f3f4"));
        stroke(e.fout() * 3f);
        Lines.circle(e.x, e.y, 50);

        Drawf.tri(e.x, e.y, 10 * e.fout(), 30, e.rotation);
        Drawf.tri(e.x, e.y, 10 * e.fout(), -30, e.rotation);
        Drawf.tri(e.x, e.y, 10 * e.fout(), 30, e.rotation + 90);
        Drawf.tri(e.x, e.y, 10 * e.fout(), -30, e.rotation + 90);

        int points = 10;
        float offset = Mathf.randomSeed(e.id, 360f);
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            Drawf.tri(e.x + Angles.trnsx(angle, 50), e.y + Angles.trnsy(angle, 50), 5f, 20f * e.fout(), angle);
        }
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            Drawf.tri(e.x + Angles.trnsx(angle, 50), e.y + Angles.trnsy(angle, 50), 5f, -20f * e.fout(), angle);
        }

        Fill.circle(e.x, e.y, 6f * e.fout());
        color();
        Fill.circle(e.x, e.y, 3f * e.fout());
        Drawf.light(e.x, e.y, 50 * 1.6f, Color.valueOf("d8f3f4"), e.fout());
    }),

    tormentaHit = new Effect(50f, 100f, e -> {
        e.scaled(7f, b -> {
            color(Color.valueOf("d8f3f4"), b.fout());
            Fill.circle(e.x, e.y, 50);
        });

        color(Color.valueOf("d8f3f4"));
        stroke(e.fout() * 3f);
        Lines.circle(e.x, e.y, 32);

        Drawf.tri(e.x, e.y, 10 * e.fout(), 10, e.rotation);
        Drawf.tri(e.x, e.y, 10 * e.fout(), -10, e.rotation);

        int points = 5;
        float offset = Mathf.randomSeed(e.id, 360f);
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            Drawf.tri(e.x + Angles.trnsx(angle, 32), e.y + Angles.trnsy(angle, 32), 5f, 20f * e.fout(), angle);
        }
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            Drawf.tri(e.x + Angles.trnsx(angle, 32), e.y + Angles.trnsy(angle, 32), 5f, -20f * e.fout(), angle);
        }

        Fill.circle(e.x, e.y, 6f * e.fout());
        color();
        Fill.circle(e.x, e.y, 3f * e.fout());
        Drawf.light(e.x, e.y, 50 * 1.6f, Color.valueOf("d8f3f4"), e.fout());
    }),

    hitLaser = new Effect(8, e -> {
        color(Color.white, Pal.accent, e.fin());
        stroke(0.5f + e.fout());
        Lines.circle(e.x, e.y, e.fin() * 5f);

        Drawf.light(e.x, e.y, 23f, Pal.accent, e.fout() * 0.7f);
    }),

    ishiField = new Effect(60, e -> {
        color(Color.valueOf("dbd187"), Color.white, e.fin());

        stroke(2f * e.fout());
        Lines.circle(e.x, e.y, 75 * e.fin(Interp.circleOut));
    }),

    sutaField = new Effect(120, e -> {
        color(Color.valueOf("889af0"), Color.white, e.fin());

        float rad = 160 * e.fin(Interp.pow2Out);

        stroke(5f * e.fout());
        Lines.circle(e.x, e.y, rad);

        int points = 12;
        float offset = Mathf.randomSeed(e.id, 360f);
        for(int i = 0; i < points; i++){
            float angle = i* 360f / points + offset;
            Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 10f * e.fout(), -50f * e.finpow(), angle);
        }
    }),

    komettoTrail = new Effect(42, e -> {
        color(e.color);
        Fill.circle(e.x, e.y, e.rotation * e.fout());
    }).layer(Layer.flyingUnitLow - 0.002f),

    sutaTrail = new Effect(82, e -> {
        color(e.color);
        randLenVectors(e.id, 1, e.finpow() * 4f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.rotation * e.fout());
        });
    }).layer(Layer.flyingUnitLow - 0.002f),

    drain = new Effect(30, e -> {
        color(Color.white, Color.valueOf("d297e1"), e.fin());
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 5, e.fin(Interp.circleOut) * 12f, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 1 * e.fout());
        });
    }),

    komettoSplash = new Effect(20, e -> {
        color(Color.valueOf("d297e1"), Color.white, e.fout());

        stroke(2f * e.fout());
        Lines.circle(e.x, e.y, 90 * e.fin(Interp.circleOut));
    }),
    OVERheat = new Effect(15, e -> {
        stroke(0.5f + e.fout());

        randLenVectors(e.id, 14, e.finpow() * 38f, (x, y) -> {
            color(Color.orange, Color.white, e.fin());

            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.foutpow() * 3);
        });
    }),
    volumineDeathWish = new Effect(320, 500, b -> {
        float intensity = 5.6f;
        b.lifetime = 50f + intensity * 65f;

        color(Color.valueOf("d297e1"));
        alpha(0.7f);
        for(int i = 0; i < 4; i++){
            rand.setSeed(b.id*2 + i);
            float lenScl = rand.random(0.4f, 1f);
            int fi = i;
            b.scaled(b.lifetime * lenScl, e -> {
                randLenVectors(e.id + fi - 1, e.fin(Interp.pow10Out), (int)(2.9f * intensity), 19f * intensity, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5f, 1f);
                    float rad = fout * ((2f + intensity) * 1.45f);

                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5f, Pal.reactorPurple, 0.5f);
                });
            });
        }
    });
}
