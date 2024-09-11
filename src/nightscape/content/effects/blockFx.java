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

    natAitCraft = new MultiEffect(
        new Effect(80, e -> {
            float rad = e.fout() * 4 - e.fout(Interp.pow4In) * 4;
            color(Color.valueOf("eecd7488"));

            randLenVectors(e.id, 4, e.fout(Interp.pow3Out) * 25, e.rotation, (x, y) ->{
                Fill.circle(e.x + x, e.y + y, rad);
            });
        }),
        new Effect(40, e -> {
            float rad = e.fout() * 2 - e.fout(Interp.pow4In) * 2;
            color(Color.valueOf("eecd7488"));

            randLenVectors(e.id, 4, e.fout(Interp.pow3Out) * 10, e.rotation, (x, y) ->{
                Fill.circle(e.x + x, e.y + y, rad);
            });
        })
    ),

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
        color(Color.valueOf("a4c9dd").a(0.3f), Color.gray.a(0.1f), e.fin());

        randLenVectors(e.id, 1, e.finpow() * 20f, 60, 10, (x, y) -> {
            Fill.circle(e.x + x, e.y + y, 2 * e.fout());
        });
    }),

    furnCraft = new Effect(180, e -> {
        color(Color.valueOf("a4c9dd").a(0.6f), Color.gray.a(0.3f), e.fin());

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
