package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import arc.math.Mathf;
import mindustry.entities.Effect;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Drawf;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.lineAngle;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;

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
    });
}
