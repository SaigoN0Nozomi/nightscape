package nightscape.entity;

import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;

public class FieldBulletType extends BulletType {
    public float interval, radius, timer = 0;
    public boolean damageAllies = true;
    public FieldBulletType(float dmg, float intr, float rad){
        super();
        despawnEffect = hitEffect = Fx.none;
        damage = dmg;
        interval = intr;
        radius = rad;
        collidesAir = collidesGround = collidesTiles = false;
        speed = 0;
        absorbable = false;
        reflectable = false;
        keepVelocity = false;
    }
    @Override
    public void update(Bullet b){
        super.update(b);
        timer++;
        if(timer >= interval) {
            if (damageAllies) {
                Damage.damage(b.x, b.y, radius, b.damage / 60 * interval);
            } else {
                Damage.damage(b.team, b.x, b.y, radius, b.damage / 60 * interval);
            }
            timer = 0;
        }
    }
}
