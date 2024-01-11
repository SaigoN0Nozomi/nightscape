package nightscape.content.blocks;

import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
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
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawTurret;
import nightscape.content.NSLiquids;
import nightscape.content.NSitems;
import nightscape.content.NSstatus;
import nightscape.content.effects.blockFx;
import nightscape.world.meta.SoundsAlt;

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
    adrenaline, stelle, combustion, magnetic, hornet;

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
            inaccuracy = 6f;
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

            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.05f));
        }};

        flicker = new ItemTurret("flicker"){{
            requirements(Category.turret, with(NSitems.tantalum, 60, NSitems.zirconium, 45));
            researchCost = with(NSitems.tantalum, 750, NSitems.zirconium, 355);
            ammo(
                    NSitems.naturit, new ArtilleryBulletType(3f,8){{
                        splashDamage = 17;
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
            minRange = 40;
            ammoUseEffect = Fx.casing1;
            health = 240;
            rotateSpeed = 2f;
            fogRadiusMultiplier = 0.5f;

            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.05f));
        }};

        adrenaline = new ItemTurret("adrenaline"){{
            requirements(Category.turret, with(NSitems.velonium, 230, NSitems.zirconium, 125));
            researchCost = with(NSitems.velonium, 1650, NSitems.zirconium, 635);
            reload = 100f;
            health = 450;
            range = 180;
            smokeEffect = blockFx.adrenalineShoot;
            itemCapacity = 30;
            shootY = 5.5f;
            recoil = 2;
            size = 2;
            rotateSpeed = 1.5f;
            shootSound = SoundsAlt.snipeShoot;
            ammoPerShot = 3;
            ammo(
                NSitems.tantalum, new BasicBulletType(15, 35){{
                    lifetime = 180/speed;
                    width = 10;
                    height = 15;
                    ammoMultiplier = 3;
                    trailLength = 4;
                    trailWidth = 1f;
                    hitEffect = despawnEffect = blockFx.adrenalinHitTantal;
                    backColor = trailColor = NSitems.tantalum.color;
                }},
                NSitems.zirconium, new BasicBulletType(20, 50){{
                    lifetime = 230/speed;
                    width = 8;
                    height = 22;
                    pierceArmor = true;
                    trailLength = 6;
                    ammoMultiplier = 4;
                    trailWidth = 2f;
                    reloadMultiplier = 0.5f;
                    rangeChange = 50;
                    hitEffect = despawnEffect = blockFx.adrenalinHitZirconium;
                    backColor = trailColor = NSitems.zirconium.color;
                    }},
                NSitems.streby, new BasicBulletType(25, 30){{
                    lifetime = 170/speed;
                    width = 12;
                    ammoMultiplier = 2;
                    height = 17;
                    trailLength = 4;
                    trailWidth = 1f;
                    hitSound = Sounds.shotgun;
                    reloadMultiplier = 0.8f;
                    backColor = trailColor = NSitems.streby.color;
                    fragBullets = 1;
                    fragRandomSpread = 0;
                    fragSpread = 0;
                    hitEffect = despawnEffect = Fx.none;
                    fragBullet = new ShrapnelBulletType(){{
                        length = 25;
                        damage = 44f;
                        width = 8f;
                        toColor = NSitems.streby.color;
                    }};
                }}
            );
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-barrel"){{
                    progress = PartProgress.recoil;
                    moveY = -2;
                }});
            }};

            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.15f));
        }};

        stelle = new LiquidTurret("stelle"){{
            requirements(Category.turret, with(NSitems.tantalum, 160, NSitems.zirconium, 95, NSitems.velonium, 15));
            researchCost = with(NSitems.tantalum, 1850, NSitems.zirconium, 670, NSitems.velonium, 320);

            ammo(
                Liquids.ozone, new BasicBulletType(2f, 15){{
                    drag = -0.04f;
                    lifetime = 43;
                    homingRange = 150f;
                    homingPower = 0.2f;
                    homingDelay = 13;
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
                }}, NSLiquids.ammonia, new BasicBulletType(1.5f, 15){{
                    drag = -0.015f;
                    lifetime = 80;
                    homingRange = 200f;
                    pierce = true;
                    homingPower = 0.16f;
                    homingDelay = 25;
                    ammoMultiplier = 0.5f;
                    reloadMultiplier = 0.6f;
                    weaveMag = 8f;
                    weaveScale = 8f;
                    backColor = trailColor = Color.valueOf("deffd1");
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 12;
                    hittable = false;
                    collidesGround = false;

                    hitEffect = despawnEffect = new Effect(25f, e -> {
                        color(Color.valueOf("deffd199"), Color.lightGray, e.fin());

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
            smokeEffect = new Effect(25f, e -> {
                color(Color.white, Color.gray, e.fin());

                randLenVectors(e.id, 8, e.finpow() * 23f, e.rotation, 90f, (x, y) -> {
                    Fill.circle(e.x + x, e.y +y, e.fout() * 4f);
                });
            });
            inaccuracy = 45f;
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
            shootType = new LaserBulletType(45f){{
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
            requirements(Category.turret, with(NSitems.tantalum, 160, NSitems.streby, 270, NSitems.velonium, 30));
            ammo(
                    NSitems.velonium, new BasicBulletType(6f, 32f){{
                        width = 10f;
                        height = 16f;
                        lifetime = 25f;
                        ammoMultiplier = 2;
                        homingRange = 60f;
                        homingDelay = 3;
                        homingPower = 0.07f;
                        backColor = Color.valueOf("d8f3f4");
                        hitEffect = despawnEffect = blockFx.magneticHit;
                        frontColor = Color.white;
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
            shoot.shotDelay = 2;

            consumeAmmoOnce = false;
            range = 140;
            shootCone = 5f;
            ammoUseEffect = Fx.casing2Double;
            health = 560;
            size = 2;
            inaccuracy = 30f;
            rotateSpeed = 7f;
            researchCost = ItemStack.with(NSitems.tantalum, 1500, NSitems.streby, 1200, NSitems.velonium, 650);
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
            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.15f));
        }};

        hornet = new ItemTurret("hornet"){{
            requirements(Category.turret, with(NSitems.tantalum, 1200, NSitems.zirconium, 930, NSitems.streby, 500));
            researchCost = with(NSitems.tantalum, 2900, NSitems.zirconium, 1900, NSitems.streby, 1150);

            ammo(
                    NSitems.streby, new BasicBulletType(0f, 1){{
                        shootEffect = Fx.shootBig;
                        smokeEffect = blockFx.hornetShoot;
                        ammoMultiplier = 1f;

                        spawnUnit = new MissileUnitType("hornet-missile"){{
                            speed = 4.6f;
                            maxRange = 6f;
                            lifetime = 530/speed;
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
                                bullet = new ExplosionBulletType(75f, 40f){{
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
                }}, new RegionPart("-missile"){{
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
            consume(new ConsumeLiquid(NSLiquids.ammonia, 0.3f));

            recoil = 0.5f;
            shoot.shots = 5;
            shoot.shotDelay = 15;
            consumeAmmoOnce = false;
            squareSprite = false;
            outlineColor = Color.valueOf("2d2630");

            fogRadiusMultiplier = 0.4f;
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
            range = 450;
            shootCone = 1f;
            health = 1200;
            rotateSpeed = 0.4f;
        }};
    }
}
