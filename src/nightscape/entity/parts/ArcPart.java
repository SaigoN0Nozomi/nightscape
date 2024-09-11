package nightscape.entity.parts;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.entities.part.DrawPart;

public class ArcPart extends DrawPart {
    public PartProgress progress = PartProgress.warmup;
    public Color color = Color.white;
    public float radius = 1, radiusTo = -1;
    public float x = 0, y = 0;
    public float layer = -1f;
    public float rotateSpeed = 0, rotateSpeedTo = -1, baseRotation = 0;
    public float shapeFraction = 0.5f, shapeFractionTo = -1;
    public float stroke = 1, strokeTo = -1;
    public int shapes = 2;
    @Override
    public void draw(PartParams params){
        //Уровень отрисовки относительно других объектов
        float z = Draw.z();
        if(layer > 0) Draw.z(layer);
        if(under && turretShading) Draw.z(z - 0.0001f);

        //Прогресс(условие для движения части)
        float prog = progress.getClamp(params);

        int i = params.sideOverride;
        float sign = (i == 0 ? 1 : -1) * params.sideMultiplier;
        //радиус с учётом прогресса
        float rad = radiusTo < 0 ? radius : Mathf.lerp(radius, radiusTo, prog);
        //ширина с учётом прогресса
        float sf =  shapeFractionTo < 0 ? shapeFraction : Mathf.lerp(shapeFraction, shapeFractionTo, prog);
        //толщина с учётом прогресса
        float str = strokeTo < 0 ? stroke : Mathf.lerp(stroke, strokeTo, prog);

        Draw.color(color);
        Lines.stroke(str);
        float
        rx = params.x + Tmp.v1.x, ry = params.y + Tmp.v1.y;
        //отрисовка S частей круга
        for(int s = 0; s < shapes; s++){
            //поворот части с учётом всех переменных
            float rot = (rotateSpeedTo < 0 ? (rotateSpeed * Time.time) % 360: Mathf.lerp((rotateSpeed * Time.time) % 360, (rotateSpeedTo * Time.time) % 360, prog)) * sign + 360 / shapes * s + baseRotation + params.rotation;
            float shapeX = Angles.trnsx(rot, rad) + rx, shapeY = Angles.trnsy(rot, rad) + ry;

            Lines.arc(rx, ry, rad, sf, rot);
        }
    }
    @Override
    public void load(String name){
    }
}
