package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;
import mindustry.world.Block;

import static arc.graphics.g2d.Draw.*;
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
    });
}
