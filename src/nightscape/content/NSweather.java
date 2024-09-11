package nightscape.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.gen.Sounds;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;
import mindustry.world.meta.Attribute;
import nightscape.world.weather.ProjectileWeather;


public class NSweather {
    public static Weather

    ozoneRain, ozoneStorm, storm, natWind;

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

            dropTime = 3f;
            dropAmount = 6;
            projectile = new LiquidBulletType(liquid){{
                damage = 10;
                speed = 0;
                shootEffect = hitEffect = despawnEffect = Fx.none;
                drag = 0;
                lifetime = 0;
                collidesAir = false;
            }};
        }};

        ozoneStorm = new ProjectileWeather("OzoneStorm"){{
            attrs.set(Attribute.light, -0.75f);
            attrs.set(NSattribute.ozone, 1.5f);
            sound = Sounds.rain;
            soundVol = 0.75f;
            liquid = Liquids.ozone;
            color = Color.pink;

            status = NSstatus.ozoneCorrosion;
            statusDuration = 120f;

            padding = 45;
            xspeed = 6;
            yspeed = 20;
            sizeMin = 9;
            sizeMax = 30;

            dropTime = 5f;
            dropAmount = 10;
            projectile = new LiquidBulletType(liquid){{
                damage = 50;
                splashDamage = 100;
                splashDamageRadius = 30;
                speed = 0;
                shootEffect = hitEffect = despawnEffect = Fx.none;
                drag = 0;
                lifetime = 0;
                collidesAir = false;
            }};
        }};

        storm = new ParticleWeather("storm"){{
            color = noiseColor = Color.valueOf("453434");
            particleRegion = "particle";
            drawNoise = true;
            useWindVector = true;
            sizeMax = 70f;
            sizeMin = 30f;
            minAlpha = 0f;
            maxAlpha = 0.3f;
            density = 1750f;
            baseSpeed = 9.4f;
            attrs.set(Attribute.light, -0.5f);
            opacityMultiplier = 0.30f;
            force = 1.9f;
            sound = Sounds.wind;
            soundVol = 0.8f;
        }};

        natWind = new ParticleWeather("natWind"){{
            color = noiseColor = Color.valueOf("4f4629");
            drawNoise = true;
            useWindVector = true;
            sizeMax = 0f;
            sizeMin = 0f;
            noiseScale = 1900f;
            density = 1750f;
            baseSpeed = 1.2f;
            attrs.set(Attribute.light, -0.2f);
            opacityMultiplier = 0.45f;
            force = 0.1f;
            sound = Sounds.wind;
            soundVol = 0.8f;
        }};
    }
}
