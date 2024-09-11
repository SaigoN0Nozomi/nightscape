package nightscape.content.effects;

import arc.graphics.Color;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.entities.Effect;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;

public class OZONEGODFX {
    public static Effect
    laserSpawn = new Effect(30, e -> {
        color(Color.valueOf("fc81dd"), Color.white, e.fin());
        stroke(e.fout(Interp.circleOut));
        Lines.square(e.x, e.y, 21 * e.fin(), 45);
    });
}
