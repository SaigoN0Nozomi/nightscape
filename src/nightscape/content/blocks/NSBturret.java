package nightscape.content.blocks;

import arc.audio.Sound;
import arc.graphics.Blending;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.StatusEffect;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ContinuousTurret;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.LiquidTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawTurret;
import nightscape.content.NSLiquids;
import nightscape.content.NSitems;
import nightscape.content.NSstatus;
import nightscape.content.effects.StatusFx;
import nightscape.content.effects.blockFx;
import nightscape.content.effects.turretFx;
import nightscape.entity.FieldBulletType;
import nightscape.entity.parts.ArcPart;
import nightscape.world.block.turrets.AdrenalineTurret;
import nightscape.world.block.turrets.HeatingTurret;
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
    adrenaline, stelle, combustion, emission,
            //3x3
    hornet, pulse, magnetic,
                    //enemy
    clean;

    public static void load(){

        victim = new ItemTurret("victim"){{
            requirements(Category.turret, with(NSitems.tantalum, 35));
            ammo(
                NSitems.tantalum, new BasicBulletType(4f, 11f){{
                    width = 9f;
                    height = 14f;
                    lifetime = 30f;
                    ammoMultiplier = 2;
                    backColor = trailColor = Color.valueOf("7f9da9");
                    frontColor = Color.white;
                    despawnEffect = Fx.none;
                    hitEffect = turretFx.victimTHit;
                    trailWidth = 1f;
                    trailLength = 5;
                    smokeEffect = turretFx.victimSmoke;
                }},
                NSitems.velonium, new BasicBulletType(6f, 22f){{
                    reloadMultiplier = 0.7f;
                    width = 10f;
                    height = 16f;
                    lifetime = 20f;
                    ammoMultiplier = 2;
                    status = StatusEffects.slow;
                    statusDuration = 120;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    despawnEffect = Fx.none;
                    hitEffect = turretFx.victimVHit;
                    trailWidth = 3f;
                    trailLength = 7;
                    reloadMultiplier = 0.6f;
                    smokeEffect = turretFx.victimSmoke;
                }}, silicon, new BasicBulletType(4f, 15f){{
                    reloadMultiplier = 1.2f;
                    width = 6f;
                    height = 14f;
                    lifetime = 30f;
                    ammoMultiplier = 3;
                    backColor = trailColor = Color.valueOf("3a3a50");
                    trailEffect = turretFx.victimSTrail;
                    trailChance = 0.18f;
                    hitEffect = turretFx.victimSHit;
                    frontColor = Color.valueOf("8e9097");
                    despawnEffect = Fx.none;
                    homingRange = 40;
                    homingPower = 0.02f;
                    smokeEffect = turretFx.victimSmoke;
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
            requirements(Category.turret, with(NSitems.tantalum, 40, NSitems.zirconium, 25));
            researchCost = with(NSitems.tantalum, 750, NSitems.zirconium, 355);
            ammo(
                NSitems.naturit, new ArtilleryBulletType(3f,15){{
                    splashDamage = 13;
                    lifetime = 20;
                    splashDamageRadius = 45;
                    sprite = "circle-bullet";
                    backColor = frontColor = trailColor = NSitems.naturit.color;
                    despawnEffect = hitEffect = turretFx.flickerHit;
                    statusDuration = 240;
                    status = NSstatus.overgrowth;
                }}
            );
            shootSound = SoundsAlt.artileryShoot;
            researchCostMultiplier = 1f;
            ammoPerShot = 4;
            itemCapacity = 12;
            targetAir = false;
            inaccuracy = 5;
            squareSprite = false;
            recoil = 1f;
            reload = 160f;
            range = 180;
            minRange = 40;
            ammoUseEffect = Fx.casing1;
            health = 240;
            rotateSpeed = 2f;
            fogRadiusMultiplier = 0.5f;

            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.05f));
        }};

        adrenaline = new AdrenalineTurret("adrenaline"){{
            requirements(Category.turret, with(NSitems.velonium, 90, NSitems.zirconium, 125));
            researchCost = with(NSitems.velonium, 770, NSitems.zirconium, 435);
            baseReload = 30f;
            reloadPerHp = 0.2f;
            recoilTime = 80;
            health = 520;
            range = 140;
            smokeEffect = turretFx.adrenalineShoot;
            itemCapacity = 20;
            shootY = 5.5f;
            recoil = 2;
            shootCone = 1;
            size = 2;
            rotateSpeed = 1.5f;
            shootSound = SoundsAlt.snipeShoot;
            ammoPerShot = 1;
            ammo(
                NSitems.tantalum, new BasicBulletType(5, 35){{
                    lifetime = 140/speed;
                    width = 20;
                    height = 30;
                    sprite = "nscape-expans-bullet";
                    ammoMultiplier = 1;
                    trailLength = 4;
                    trailWidth = 1f;
                    hitEffect = despawnEffect = turretFx.adrenalinHitTantal;
                    backColor = trailColor = NSitems.tantalum.color;
                }},
                NSitems.zirconium, new BasicBulletType(8, 46){{
                    lifetime = 190/speed;
                    width = 15;
                    height = 37;
                    sprite = "nscape-expans-bullet-long";
                    pierceArmor = true;
                    trailLength = 6;
                    ammoMultiplier = 1;
                    trailWidth = 2f;
                    reloadMultiplier = 0.7f;
                    rangeChange = 50;
                    hitEffect = despawnEffect = turretFx.adrenalinHitZirconium;
                    backColor = trailColor = NSitems.zirconium.color;
                    }},
                NSitems.streby, new BasicBulletType(6, 22){{
                    lifetime = 140/speed;
                    width = 12;
                    ammoMultiplier = 2;
                    height = 24;
                    sprite = "nscape-expans-bullet-big";
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
                        length = 32;
                        damage = 21f;
                        width = 8f;
                        toColor = NSitems.streby.color;
                    }};
                }}
            );
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-wing"){{
                    progress = PartProgress.recoil;
                    moveY = -1;
                    moveX = 4;
                    moveRot = -44;
                    mirror = true;
                }});
                parts.add(new RegionPart("-barrel"){{
                    progress = PartProgress.recoil;
                    moveY = -5;
                }});
            }};

            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.15f));
        }};

        stelle = new LiquidTurret("stelle"){{
            requirements(Category.turret, with(NSitems.tantalum, 88, NSitems.zirconium, 64, NSitems.velonium, 12));
            researchCost = with(NSitems.tantalum, 820, NSitems.zirconium, 580, NSitems.velonium, 120);

            ammo(
                Liquids.ozone, new BasicBulletType(2f, 15){{
                    drag = -0.04f;
                    lifetime = 43;
                    homingRange = 150f;
                    homingPower = 0.2f;
                    homingDelay = 13;
                    ammoMultiplier = 0.5f;
                    velocityRnd = 0.05f;
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

                    hitEffect = despawnEffect = turretFx.stelleHitOzone;
                }}, NSLiquids.ammonia, new BasicBulletType(1.5f, 15){{
                    drag = -0.015f;
                    lifetime = 80;
                    homingRange = 200f;
                    pierce = true;
                    homingPower = 0.16f;
                    velocityRnd = 0.05f;
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

                    hitEffect = despawnEffect = turretFx.stelleHitAm;
                }}
            );
            size = 2;
            extinguish = false;
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
            reload = 8;
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

        emission = new PowerTurret("emission"){{
            requirements(Category.turret, with(NSitems.zirconium, 50, NSitems.velonium, 45, silicon, 30));
            researchCost = with(NSitems.zirconium, 1240, NSitems.velonium, 890, silicon, 120);

            consumePower(14/6f);
            consumeLiquid(NSLiquids.fernum, 4/60f);
            size = 2;
            squareSprite = false;
            recoil = 0.3f;
            recoilTime = 30;
            recoilPow = 3;
            shootSound = Sounds.shootAlt;
            soundPitchMin = 1.5f;
            soundPitchMax = 1.75f;
            range = 110;
            health = 480;
            rotateSpeed = 1.6f;
            warmupMaintainTime = 30;
            shootWarmupSpeed = 0.06f;
            minWarmup = 0.8f;
            reload = 3;
            xRand = 1;
            shootY = 3;
            inaccuracy = 5;
            velocityRnd = 0.3f;
            shootType = new BasicBulletType(10, 5){{
                backColor = Color.valueOf("665551");
                frontColor = Color.valueOf("fbd9d1");
                status = NSstatus.armorNull;
                statusDuration = 90;
                pierce = true;
                lifetime = 13;
                width = 10;
                hitSize = 4;
                sprite = "nscape-needle";
                knockback = 0.3f;
                height = 15;
                hitEffect = turretFx.needleHit;
                despawnEffect = turretFx.needleDesp;
            }};
            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-claw"){{
                    progress = PartProgress.warmup.curve(Interp.swing);
                    moveX = 2;
                    moveY = -2;
                    mirror = true;
                }});
                parts.add(new RegionPart("-tail"){{
                    progress = PartProgress.warmup.curve(Interp.swing);
                    moves.add(new PartMove(PartProgress.recoil, 0.3f, 0.75f, 10f));
                    moveRot = 25;
                    moveX = 0.4f;
                    moveY = -0.6f;
                    mirror = true;
                    under = true;
                }});
                parts.add(new RegionPart("-wing"){{
                    progress = PartProgress.warmup.curve(Interp.swing);
                    moveRot = 20;
                    moveX = 2;
                    moveY = -2;
                    mirror = true;
                    under = true;
                }});
            }};
        }};

        combustion = new PowerTurret("Combustion"){{
            requirements(Category.turret, with(NSitems.tantalum, 140, NSitems.velonium, 65, silicon, 45));
            researchCost = ItemStack.with(NSitems.tantalum, 1250, NSitems.velonium, 720, silicon, 450);
            shootType = new LaserBulletType(18f){{
                colors = new Color[]{Color.valueOf("fbdd6c"), Color.white, Color.valueOf("fbdd6c")};
                width = 10f;
                sideAngle = 45f;
                sideWidth = 1f;
                sideLength = 12f;
                lifetime = 8f;
                collidesAir = false;
                drawSize = 200f;
                length = 80f;
                pierceCap = 4;
                status = NSstatus.overCharged;
                statusDuration = 300f;
                fragBullets = 3;
                fragOnHit = false;
                fragOnAbsorb = false;
                fragRandomSpread = 40;
                fragBullet = new BasicBulletType(4, 2){{
                    height = width = 8;
                    backColor = frontColor = trailColor = Color.valueOf("fbdd6c");
                    trailWidth = 2;
                    collidesAir = false;
                    trailLength = 3;
                    lifetime = 22;
                    status = StatusEffects.burning;
                    statusDuration = 60;
                    weaveMag = 3f;
                    weaveScale = 3f;
                    hitEffect = despawnEffect = StatusFx.shock;
                    drag = 0.02f;
                }};
            }};
            consumePower(0.5f);
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

        clean = new ItemTurret("cleaner"){{
            requirements(Category.turret, with(NSitems.cyanid, 260, NSitems.tantalum, 200, NSitems.zirconium, 120));
            drawer = new DrawTurret("cyan-");
            squareSprite = false;
            reload = 90f;
            shootSound = Sounds.artillery;
            shootY = -2f;
            itemCapacity = 15;

            shoot.shots = 5;
            shoot.shotDelay = 3;
            shoot.firstShotDelay = 30;
            range = 360;
            fogRadiusMultiplier = 0.25f;
            ammoUseEffect = Fx.casing1;
            health = 360;
            size = 2;
            inaccuracy = 11f;
            rotateSpeed = 15f;
            ammo(
                NSitems.naturit, new ArtilleryBulletType(){{
                    trailWidth = 3;
                    trailLength = 6;
                    width = height = 12;
                    velocityRnd = 0.2f;
                    speed = 10;
                    splashDamageRadius = 16;
                    splashDamage = 15;
                    drag = 0.017f;
                    hitEffect = despawnEffect = turretFx.cleanHit;
                    incendAmount = 3;
                    incendChance = 0.5f;
                    incendSpread = 10;
                }}
            );
        }};

        pulse = new HeatingTurret("pulse"){{
            size = 3;
            requirements(Category.turret, with(NSitems.tantalum, 210, NSitems.velonium, 150, silicon, 90, NSitems.dense, 55));
            rotateSpeed = 1.6f;
            reload = 10f;
            inaccuracy = 1f;
            shootSound = Sounds.laser;
            range = 150;
            unitSort = UnitSorts.weakest;
            heatingMax = 280;
            coolingSound = SoundsAlt.cool;
            shootY = 7;
            heatingGain = 2;
            heatingLose = 1;
            recoilTime = 30;
            recoils = 2;
            shoot = new ShootBarrel() {{
                barrels = new float[]{
                        -5, 0, 0,
                        5, 0, 0};
            }};
            ammo(NSitems.dense, new LaserBulletType(27){{
                colors = new Color[]{Color.valueOf("635987"), Color.white};
                width = 18f;
                sideAngle = 25f;
                sideWidth = 2f;
                sideLength = 18f;
                lifetime = 8f;
                collidesAir = false;
                drawSize = 160f;
                length = 155f;
                fragBullets = 3;
                fragOnHit = false;
                fragOnAbsorb = false;
                fragRandomSpread = 40;
                fragBullet = new LightningBulletType(){{
                    damage = 11;
                    lightningLength = 17;
                    lightningLengthRand = 2;
                    lightningColor = Color.valueOf("635987");
                }};
            }});
            drawer = new DrawTurret("chorda-"){{
                parts.add(
                    new RegionPart("-barrel1"){{
                        recoilIndex = 0;
                        progress = PartProgress.recoil.curve(Interp.pow2In);
                        moveY = -3;
                    }}, new RegionPart("-barrel2"){{
                        recoilIndex = 1;
                        progress = PartProgress.recoil.curve(Interp.pow2In);
                        moveY = -3;
                    }}, new RegionPart("-core")
                );
            }};
            consume(consumePower(6f));
            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.05f));
        }};

        magnetic = new ItemTurret("magnetic"){{
            requirements(Category.turret, with(NSitems.tantalum, 270, NSitems.streby, 210, NSitems.velonium, 50));
            ammo(
                NSitems.tantalum, new BasicBulletType(6f, 19f){{
                    width = 8f;
                    height = 11f;
                    lifetime = 25f;
                    ammoMultiplier = 2;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    trailWidth = 1.3f;
                    trailLength = 3;
                    homingRange = 90;
                    homingPower = 0.006f;
                    hitEffect = despawnEffect = turretFx.magneticHit;
                    frontColor = Color.white;
                    fragBullets = 2;
                    smokeEffect = new Effect(17f, e -> {
                        color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                            Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                        });
                    });
                    fragBullet = new BasicBulletType(4f, 11f){{
                        width = 5f;
                        height = 7f;
                        lifetime = 10f;
                        backColor = trailColor = Color.valueOf("d8f3f4");
                        trailWidth = 0.6f;
                        trailLength = 2;
                        hitEffect = despawnEffect = turretFx.magneticHit;
                        frontColor = Color.white;
                    }};
                }}, NSitems.velonium, new BasicBulletType(8f, 29f){{
                    width = 8f;
                    height = 16f;
                    lifetime = 18.5f;
                    ammoMultiplier = 3;
                    pierceArmor = true;
                    homingRange = 90;
                    homingPower = 0.02f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    trailWidth = 2;
                    trailLength = 10;
                    hitEffect = despawnEffect = turretFx.magneticHit;
                    frontColor = Color.white;
                    smokeEffect = new Effect(17f, e -> {
                        color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                            Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                        });
                    });
                    fragBullet = new BasicBulletType(4f, 11f){{
                        width = 5f;
                        height = 7f;
                        lifetime = 10f;
                        backColor = trailColor = Color.valueOf("d8f3f4");
                        trailWidth = 0.6f;
                        trailLength = 4;
                        homingRange = 90;
                        homingPower = 0.05f;
                        hitEffect = despawnEffect = turretFx.magneticHit;
                        frontColor = Color.white;
                    }};
                }});
            squareSprite = false;
            consumeAmmoOnce = false;
            soundPitchMax = 0.75f;
            soundPitchMin = 0.6f;
            heatRequirement = 6f;
            maxHeatEfficiency = 1;
            recoilTime = 20f;
            shootY = 7f;
            recoils = 3;
            shoot = new ShootBarrel(){{
                barrels = new float[]
                        {-4, 1, 0,
                        0, 2, 0,
                        4, 1, 0};
            }};

            reload = 6f;
            itemCapacity = 30;
            shootWarmupSpeed = 0.03f;
            minWarmup = 0.9f;
            warmupMaintainTime = 180;

            range = 140;
            shootCone = 5f;
            ammoUseEffect = Fx.none;
            health = 780;
            size = 3;
            shootX = 0.3f;
            inaccuracy = 10f;
            rotateSpeed = 7f;
            researchCost = ItemStack.with(NSitems.tantalum, 1500, NSitems.streby, 1200, NSitems.velonium, 650);
            drawer = new DrawTurret("chorda-"){{
                parts.addAll(new RegionPart("-barrel1"){{
                    recoilIndex = 0;
                    under = true;

                    mirror = false;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -1.75f;
                }}, new RegionPart("-barrel2"){{
                    recoilIndex = 1;
                    under = true;

                    mirror = false;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -1.75f;
                }}, new RegionPart("-barrel3"){{
                    recoilIndex = 2;
                    under = true;

                    mirror = false;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -1.75f;
                }});
                parts.addAll(new RegionPart("-wing"){{
                    mirror = true;
                    progress = PartProgress.warmup.curve(Interp.pow3In);
                    moveRot = -24;
                    moveX = 1;
                    moveY = 1;
                    under = true;
                }}, new RegionPart("-feather"){{
                    mirror = true;
                    progress = PartProgress.warmup.curve(Interp.pow3In);
                    moveRot = -12;
                    under = true;
                }});
                parts.addAll(
                    new HaloPart(){{
                        progress = PartProgress.warmup.curve(Interp.pow2In);;
                        color = Color.valueOf("d8f3f4");
                        layer = Layer.effect;
                        hollow = true;
                        y = -17;
                        radius = 4;
                        rotateSpeed = 1.5f;
                        sides = 3;
                        shapes = 1;
                        stroke = 0;
                        strokeTo = 1;
                    }}, new HaloPart(){{
                        progress = PartProgress.warmup.curve(Interp.pow2In);;
                        color = Color.valueOf("d8f3f4");
                        layer = Layer.effect;
                        hollow = true;
                        sides = 30;
                        y = -7;
                        radius = 3;
                        haloRadius = 10;
                        haloRotateSpeed = -0.7f;
                        stroke = 0;
                        strokeTo = 1;
                    }}, new ArcPart(){{
                        progress = PartProgress.warmup.curve(Interp.pow2In);;
                        color = Color.valueOf("d8f3f4");
                        layer = Layer.effect;
                        radius = 7.5f;
                        shapeFraction = 45/360f;
                        shapes = 6;
                        rotateSpeed = 0.5f;
                        stroke = 0;
                        strokeTo = 1;
                    }}, new HaloPart(){{
                        progress = PartProgress.warmup.curve(Interp.pow2In);;
                        color = Color.valueOf("d8f3f4");;
                        layer = Layer.effect;
                        tri = true;
                        y = -7;
                        triLength = 0;
                        triLengthTo = 1;
                        haloRadius = 7.8f;
                        haloRotateSpeed = -0.7f;
                    }}, new HaloPart(){{
                        progress = PartProgress.warmup.curve(Interp.pow2In);;
                        color = Color.valueOf("d8f3f4");
                        layer = Layer.effect;
                        tri = true;
                        y = -7;
                        triLength = 0;
                        triLengthTo = 1;
                        haloRadius = 7.8f;
                        haloRotateSpeed = -0.7f;
                        shapeRotation = 180;
                    }}
                );
            }};
            coolant = consume(new ConsumeLiquid(NSLiquids.ammonia, 0.15f));
        }};

        hornet = new ItemTurret("hornet"){{
            requirements(Category.turret, with(NSitems.tantalum, 1550, NSitems.zirconium, 1170, NSitems.dense, 320, silicon, 620, NSitems.streby, 400));

            ammo(
                NSitems.streby, new BasicBulletType(0f, 1){{
                    shootEffect = Fx.shootBig;
                    smokeEffect = turretFx.hornetShoot;
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
                            deathExplosionEffect = turretFx.hornetHit;
                            shootOnDeath = true;
                            shake = 10f;
                            bullet = new ExplosionBulletType(189f, 48f){{
                                hitEffect = Fx.none;
                                collidesAir = false;
                                buildingDamageMultiplier = 0.7f;
                                fragBullets = 1;
                                fragBullet = new FieldBulletType(25, 60, 102){{
                                    lifetime = 360;
                                    damageAllies = false;
                                }};
                            }};
                        }});
                    }};
                }}
            );

            drawer = new DrawTurret("chorda-"){{
                parts.add(new RegionPart("-under"){{
                    under = true;
                    layerOffset = -0.02f;
                }});
                parts.add(new RegionPart("-blade"){{
                    moves.add(new PartMove(PartProgress.recoil, -2.4f, 0.4f, 14));
                    progress = PartProgress.warmup;
                    heatProgress = PartProgress.warmup;
                    heatColor = Color.red;
                    moveRot = 10f;
                    moveX = -1.8f;
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
            shoot.shots = 3;
            shoot.shotDelay = 60;
            consumeAmmoOnce = false;
            squareSprite = false;
            outlineColor = Color.valueOf("2d2630");

            fogRadiusMultiplier = 0.1f;
            coolantMultiplier = 6f;
            shootSound = Sounds.missileLaunch;
            soundPitchMin = 0.75f;
            soundPitchMax = 0.9f;

            minWarmup = 0.94f;
            shootWarmupSpeed = 0.03f;
            targetAir = false;
            targetUnderBlocks = false;

            shake = 6f;
            ammoPerShot = 50;
            maxAmmo = 150;
            shootY = -1;
            size = 4;
            reload = 1500f;
            range = 575;
            shootCone = 1f;
            health = 1200;
            rotateSpeed = 0.4f;
        }};
    }
}
