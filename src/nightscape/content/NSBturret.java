package nightscape.content;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.part.RegionPart;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.draw.DrawTurret;
import nightscape.content.effects.blockFx;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static arc.math.Angles.randLenVectors;
import static mindustry.content.Items.silicon;
import static mindustry.type.ItemStack.with;

public class NSBturret {

    public static Block
            //1x1
    victim, flicker,
            //2x2
    combustion, magnetic, stelle, hornet,
            //Stations
    nebulaStation;

    public static void load(){

        victim = new ItemTurret("victim"){{
            requirements(Category.turret, with(NSitems.tantalum, 45));
            ammo(
                NSitems.tantalum, new BasicBulletType(4f, 11f){{
                    width = 9f;
                    height = 14f;
                    lifetime = 30f;
                    ammoMultiplier = 2;
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
                NSitems.velonium, new BasicBulletType(6f, 18f){{
                    width = 10f;
                    height = 16f;
                    lifetime = 20f;
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
            itemCapacity = 15;
            shootY = 3f;
            reload = 20f;
            range = 120;
            shootCone = 10f;
            ammoUseEffect = Fx.casing1;
            health = 300;
            inaccuracy = 10f;
            rotateSpeed = 7f;
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

        flicker = new ItemTurret("flicker"){{
            requirements(Category.turret, with(NSitems.tantalum, 60, NSitems.zirconium, 45));
            researchCost = with(NSitems.tantalum, 750, NSitems.zirconium, 355);
            ammo(
                    NSitems.naturit, new ArtilleryBulletType(3f,8){{
                        splashDamage = 12;
                        lifetime = 20;
                        splashDamageRadius = 30;
                        backColor = frontColor = trailColor = NSitems.naturit.color;
                        despawnEffect = hitEffect = new Effect(30f, e -> {
                                    color(NSitems.naturit.color, Color.gray, e.fin());

                                    randLenVectors(e.id, 8, e.finpow() * 30f, e.rotation, 360f, (x, y) -> {
                                        Fill.circle(e.x + x, e.y +y, e.fout() * 3f + 0.3f);
                                    });
                                });
                    }}
            );
            researchCostMultiplier = 1f;
            targetAir = false;
            inaccuracy = 5;
            squareSprite = false;
            recoil = 1f;
            reload = 110f;
            range = 180;
            minRange = 60;
            ammoUseEffect = Fx.casing1;
            health = 240;
            rotateSpeed = 2f;
            fogRadiusMultiplier = 0.5f;
        }};

        stelle = new LiquidTurret("stelle"){{
            requirements(Category.turret, with(NSitems.tantalum, 160, NSitems.zirconium, 95, NSitems.velonium, 15));
            researchCost = with(NSitems.tantalum, 1850, NSitems.zirconium, 670, NSitems.velonium, 320);

            ammo(
                Liquids.ozone, new BasicBulletType(2f, 15){{
                    drag = -0.04f;
                    lifetime = 43;
                    homingRange = 200f;
                    homingPower = 0.2f;
                    homingDelay = 20;
                    ammoMultiplier = 0.5f;
                    weaveMag = 5f;
                    weaveScale = 5f;
                    backColor = trailColor = Color.valueOf("d297e1");
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 9;
                    hittable = false;
                    status = NSstatus.ozoneCorrosion;
                    statusDuration = 120;
                    collidesGround = false;

                    hitEffect = despawnEffect = new Effect(25f, e -> {
                        color(Color.valueOf("d297e199"), Color.lightGray, e.fin());

                        randLenVectors(e.id, 5, e.finpow() * 17f, e.rotation, 50f, (x, y) -> {
                            Fill.circle(e.x + x, e.y +y, e.fout() * 2f);
                        });

                        e.scaled(12f, s -> {
                            stroke(s.fout() * 2);
                            Lines.circle(e.x, e.y, s.finpow() * 4f);
                        });
                    });
                }}
            );
            size = 2;
            shootWarmupSpeed = 0.2f;
            ammoPerShot = 10;
            shootEffect = new Effect(25f, e -> {
                color(Color.valueOf("d297e199"), Color.lightGray, e.fin());

                randLenVectors(e.id, 8, e.finpow() * 23f, e.rotation, 90f, (x, y) -> {
                    Fill.circle(e.x + x, e.y +y, e.fout() * 4f);
                });
            });
            targetGround = false;
            recoils = 2;
            squareSprite = false;
            recoil = 2f;
            shootY = 0.7f;
            ammoUseEffect = Fx.none;
            xRand = 3f;
            shootSound = Sounds.shootAlt;
            range = 190;
            health = 520;
            inaccuracy = 30f;
            rotateSpeed = 8f;
            reload = 12;
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-claw"){{
                    progress = PartProgress.warmup.curve(Interp.circleOut);
                    moveX = 2f;
                    moveRot = -22f;
                    mirror = true;
                    under = true;
                    heatColor = Color.valueOf("FFFFFF99");
                }});
                parts.add(new RegionPart("-glow"){{
                    blending = Blending.additive;
                    color = Color.valueOf("FFFFFF66");
                    outline = false;
                }});
            }};
        }};

        combustion = new PowerTurret("Combustion"){{
            requirements(Category.turret, with(NSitems.tantalum, 140, NSitems.velonium, 65, silicon, 45));
            researchCost = ItemStack.with(NSitems.tantalum, 1250, NSitems.velonium, 720, silicon, 450);
            shootType = new LaserBulletType(35f){{
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
            consumePower(0.5f);
            recoils = 3;
            squareSprite = false;
            heatRequirement = 2f;
            targetAir = false;
            maxHeatEfficiency = 4f;
            recoil = 1f;
            shootY = 0f;
            reload = 60f;
            shootSound = Sounds.laser;
            range = 80;
            health = 750;
            rotateSpeed = 4f;
            researchCostMultiplier = 1f;
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

        magnetic = new ItemTurret("magnetic"){{
            requirements(Category.turret, with(NSitems.tantalum, 160, NSitems.naturit, 270, NSitems.velonium, 30));
            ammo(
                    NSitems.velonium, new BasicBulletType(6f, 39f){{
                        width = 10f;
                        height = 16f;
                        lifetime = 25f;
                        ammoMultiplier = 1;
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
            maxHeatEfficiency = 3;
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
            researchCost = ItemStack.with(NSitems.tantalum, 1500, NSitems.naturit, 1200, NSitems.velonium, 650);
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

        hornet = new ItemTurret("hornet"){{
            requirements(Category.turret, with(NSitems.tantalum, 1200, NSitems.zirconium, 930, NSitems.streby, 500));

            ammo(
                    NSitems.streby, new BasicBulletType(0f, 1){{
                        shootEffect = Fx.shootBig;
                        smokeEffect = blockFx.hornetShoot;
                        ammoMultiplier = 1f;

                        spawnUnit = new MissileUnitType("hornet-missile"){{
                            speed = 4.6f;
                            maxRange = 6f;
                            lifetime = 775/speed;
                            outlineColor = Color.valueOf("2d2630");
                            engineColor = trailColor = Color.valueOf("aaffc6");
                            engineLayer = Layer.effect;
                            engineSize = 1.8f;
                            engineOffset = 4f;
                            rotateSpeed = 0.4f;
                            trailLength = 8;
                            missileAccelTime = 25f;
                            lowAltitude = true;
                            loopSound = Sounds.missileTrail;
                            loopSoundVolume = 0.6f;
                            deathSound = Sounds.largeExplosion;
                            targetAir = false;

                            fogRadius = 1f;

                            health = 95;

                            weapons.add(new Weapon(){{
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                deathExplosionEffect = blockFx.hornetHit;
                                shootOnDeath = true;
                                shake = 10f;
                                bullet = new ExplosionBulletType(120f, 24f){{
                                    hitEffect = Fx.none;
                                    collidesAir = false;
                                    buildingDamageMultiplier = 0.5f;
                                }};
                            }});
                        }};
                    }}
            );

            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-blade"){{
                            progress = PartProgress.warmup;
                            heatProgress = PartProgress.warmup;
                            heatColor = Color.red;
                            moveRot = 14f;
                            moveX = -2.8f;
                            moveY = -1f;
                            mirror = true;
                        }},
                        new RegionPart("-missile"){{
                            progress = PartProgress.reload.curve(Interp.pow2In);

                            colorTo = new Color(1f, 1f, 1f, 0f);
                            color = Color.white;
                            mixColorTo = Pal.accent;
                            mixColor = new Color(1f, 1f, 1f, 0f);
                            outline = false;
                            under = true;
                            layerOffset = -0.01f;
                            y = 2;

                            moves.add(new PartMove(PartProgress.warmup.inv(), 0f, -2f, 0f));
                        }});
            }};

            recoil = 0.5f;
            shoot.shots = 5;
            shoot.shotDelay = 15;
            consumeAmmoOnce = false;
            squareSprite = false;
            outlineColor = Color.valueOf("2d2630");

            fogRadiusMultiplier = 0.2f;
            coolantMultiplier = 6f;
            shootSound = Sounds.missileLaunch;
            soundPitchMin = 1.5f;
            soundPitchMax = 1.7f;

            minWarmup = 0.94f;
            shootWarmupSpeed = 0.03f;
            targetAir = false;
            targetUnderBlocks = false;

            shake = 6f;
            ammoPerShot = 5;
            maxAmmo = 40;
            shootY = -1;
            size = 2;
            reload = 900f;
            range = 650;
            shootCone = 1f;
            health = 1200;
            rotateSpeed = 0.4f;
        }};
    }
}
