package nightscape.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.world.meta.Attribute;
import nightscape.world.weather.ProjectileWeather;


public class NSweather {
    public static Weather

    ozoneRain;

    public static void load(){
        ozoneRain = new ProjectileWeather("OzoneRain"){{
            attrs.set(Attribute.light, -0.25f);
            attrs.set(NSattribute.ozone, 0.5f);
            sound = Sounds.rain;
            soundVol = 0.25f;
            liquid = Liquids.ozone;
            color = Color.pink;

            status = NSstatus.ozoneCorrosion;
            statusDuration = 120f;

            padding = 25;
            xspeed = 3;
            yspeed = 10;
            sizeMin = 6;
            sizeMax = 20;
            dropTime = 1f;
            dropAmount = 5;
            projectile = new LiquidBulletType(liquid){{
                damage = 15;
                splashDamageRadius = 24;
                splashDamage = 20;
                speed = 0;
                shootEffect = hitEffect = despawnEffect = Fx.none;
                drag = 0;
                lifetime = 0;
                collidesAir = false;
            }};
        }};
    }
}
