package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
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
import static mindustry.content.Fx.v;

public class blockFx {

    public static Effect

    healWall = new Effect(20, e -> {
        if(!(e.data instanceof Block block)) return;

        mixcol(e.color, 0.1f);
        alpha(e.fout() * 0.5f);
        Draw.rect(block.fullIcon, e.x, e.y);
    }),

    hornetHit = new Effect(60, e -> {
        color(Color.valueOf("aaffc6"));

        randLenVectors(e.id, 5, e.finpow() * 32, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 10f * e.fout());
        });
    }),

    hornetShoot = new Effect(80f, 300f, e -> {
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
    }),

    adrenalinHitTantal = new Effect(13, e -> {
        color(NSitems.tantalum.color);

        randLenVectors(e.id, 7, e.fin(Interp.pow2Out) * 15f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 3);
        });

        stroke(e.fout() * 2);
        Lines.circle(e.x, e.y, e.fin() * 6);
    }),

    adrenalinHitZirconium = new Effect(21, e -> {
        color(NSitems.zirconium.color);

        randLenVectors(e.id, 8, e.fin(Interp.pow2Out) * 30f, e.rotation, 30, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 6);
        });

        stroke(e.fout() * 1);
        Lines.circle(e.x, e.y, e.fin() * 4);
    }),

    adrenalineShoot = new Effect(30, e -> {
        color(Color.orange, Color.gray, e.fin());

        randLenVectors(e.id, 8, e.fin(Interp.pow3Out) * 25, e.rotation, 45, (x, y) ->{
            Fill.circle(e.x + x, e.y + y, e.fout() * 3);
        });
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
    }));
}
