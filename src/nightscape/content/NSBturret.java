package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootPattern;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.draw.DrawTurret;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;
import static mindustry.type.ItemStack.with;

public class NSBturret {

    public static Block
    victim, combustion, punctual;

    public static void load(){

        victim = new ItemTurret("victim"){{
            requirements(Category.turret, with(NSitems.tantalum, 45));
            ammo(
                NSitems.tantalum, new BasicBulletType(3.5f, 7f){{
                    width = 9f;
                    height = 14f;
                    lifetime = 40f;
                    ammoMultiplier = 1;
                    hitColor = backColor = trailColor = Color.valueOf("7f9da9");
                    frontColor = Color.white;
                    despawnEffect = Fx.none;
                    trailWidth = 1f;
                    trailLength = 5;
                    smokeEffect = new Effect(17f, e -> {
                        color(Color.valueOf("7f9da9"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 6, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                            Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                        });
                    });
                }},
                NSitems.velonium, new BasicBulletType(7f, 12f){{
                    width = 10f;
                    height = 16f;
                    lifetime = 19f;
                    ammoMultiplier = 3;
                    hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    despawnEffect = Fx.none;
                    trailWidth = 3f;
                    trailLength = 7;
                    reloadMultiplier = 0.6f;
                    smokeEffect = new Effect(17f, e -> {
                        color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                            Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                        });
                    });
                }}
            );

            recoils = 3;
            squareSprite = false;
            recoil = 1f;
            shootY = 3f;
            reload = 20f;
            range = 140;
            shootCone = 10f;
            ammoUseEffect = Fx.casing1;
            health = 300;
            inaccuracy = 10f;
            rotateSpeed = 7f;
            coolant = consumeCoolant(0.1f);
            researchCostMultiplier = 0.05f;
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-barrel"){{
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -2f;
                    heatColor = Color.valueOf("f03b0e");
                    mirror = false;
                    under = true;
                }});
            }};
        }};

        combustion = new PowerTurret("Combustion"){{
            requirements(Category.turret, with(NSitems.tantalum, 140, NSitems.velonium, 65));

            shootType = new LaserBulletType(21f){{
                colors = new Color[]{Color.yellow, Color.white, Color.yellow};
                width = 10f;
                sideAngle = 45f;
                sideWidth = 1f;
                sideLength = 12f;
                lifetime = 14f;
                collidesAir = false;
                drawSize = 200f;
                length = 80f;
                pierceCap = 4;
                status = NSstatus.overCharged;
                statusDuration = 300f;
            }};
            recoils = 3;
            squareSprite = false;
            heatRequirement = 1f;
            targetAir = false;
            maxHeatEfficiency = 4f;
            recoil = 1f;
            shootY = 0f;
            reload = 60f;
            shootSound = Sounds.laser;
            range = 80;
            health = 750;
            rotateSpeed = 4f;
            researchCostMultiplier = 4f;
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-wing"){{
                    progress = PartProgress.warmup;
                    moveRot = 20f;
                    moveY = -1.5f;
                    moveX = -1f;
                    x = -3f;
                    y = -2.1f;
                    mirror = true;
                }});
            }};
            size = 2;
        }};

        punctual = new ItemTurret("punctual"){{
            requirements(Category.turret, with(NSitems.tantalum, 160, NSitems.naturit, 270, NSitems.velonium, 30));
            ammo(
                    NSitems.velonium, new BasicBulletType(6f, 19f){{
                        width = 10f;
                        height = 16f;
                        lifetime = 25f;
                        ammoMultiplier = 2;
                        splashDamageRadius = 16f;
                        splashDamage = 16f;
                        homingRange = 60f;
                        homingPower = 0.04f;
                        hitColor = backColor = Color.valueOf("d8f3f4");
                        frontColor = Color.white;
                        despawnEffect = Fx.none;
                        smokeEffect = new Effect(17f, e -> {
                            color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                            randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                                Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                            });
                        });
                    }});
            ammoEjectBack = 4f;
            squareSprite = false;
            recoil = 0.5f;
            soundPitchMax = 0.75f;
            soundPitchMin = 0.6f;
            heatRequirement = 3f;
            maxHeatEfficiency = 2;
            hasLiquids = false;
            recoilTime = 30f;
            shootY = 7f;

            reload = 300f;
            shoot.shots = 15;
            itemCapacity = 30;
            shoot.shotDelay = 3;

            consumeAmmoOnce = false;
            range = 125;
            shootCone = 5f;
            ammoUseEffect = Fx.casing2Double;
            health = 560;
            size = 2;
            inaccuracy = 30f;
            rotateSpeed = 7f;
            coolant = consumeCoolant(0.1f);
            researchCostMultiplier = 0.05f;
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-wing"){{
                    progress = PartProgress.recoil.curve(Interp.pow2Out);
                    moveRot = 65f;
                    moveY = -3f;
                    moveX = -2f;
                    x = -4f;
                    y = -2.1f;
                    mirror = true;
                }});
                parts.add(new RegionPart("-ammo"){{
                    progress = PartProgress.reload.curve(Interp.pow2Out);
                    moveY = -2.8f;
                    moveX = -2.8f;
                    x = -3f;
                    y = -3f;
                    mirror = true;
                    under = true;
                }});
            }};
        }};
    }
}
