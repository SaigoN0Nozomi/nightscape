package nightscape.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.ai.types.DefenderAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.unit.TankUnitType;
import mindustry.type.weapons.PointDefenseWeapon;
import nightscape.content.effects.unitFx;
import nightscape.world.meta.SoundsAlt;
import nightscape.world.types.abilities.BerserkAbility;
import nightscape.world.types.abilities.DamageFieldAbility;

public class NSunits {
    public static UnitType
    //core
    observer,
    //static
    point, vector, planum,

    //flash
    procursus, radius, fluor,

    //K.YS.N.
    gutta, pluvia, diluvio,

    //defenders
    ishi, yama, kometto,

    //SUS
    pabbu;

    public static void load(){
        point = new UnitType("point"){{
            this.constructor = MechUnit::create;

            mechFrontSway = 0.55f;
            health = 170f;
            speed = 0.55f;
            armor = 5f;
            hitSize = 8f;
            rotateSpeed = 4f;

            flying = false;

            weapons.add(new Weapon(name + "-weapon"){{
                top = false;
                y = -0f;
                x = -3f;
                reload = 30f;
                recoil = 1f;
                shake = 0.3f;

                bullet = new BasicBulletType(6, 19){{
                    width = 6f;
                    height = 9f;
                    lifetime = 15f;
                    pierce = false;
                    hitEffect = despawnEffect = unitFx.purpleHit;
                    smokeEffect = unitFx.purpleSmoke;
                    pierceBuilding = false;
                    backColor = frontColor = Color.valueOf("d297e1");
                }};
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        vector = new UnitType("vector"){{
            this.constructor = MechUnit::create;

            health = 560f;
            mechFrontSway = 0.55f;
            speed = 0.5f;
            armor = 8f;
            hitSize = 10f;
            rotateSpeed = 1.8f;
            flying = false;
            targetAir = false;

            weapons.add(new Weapon("nscape-rainCannon"){{
                top = false;
                y = -0.3f;
                x = -4.8f;
                reload = 150f;
                recoil = 4f;
                shake = 2f;
                shootSound = Sounds.shootBig;

                bullet = new BasicBulletType(1.6f, 10){{
                    width = 9f;
                    height = 9f;
                    recoil = 1f;
                    smokeEffect = unitFx.purpleSmoke;
                    backColor = frontColor = Color.valueOf("d297e1");
                    pierce = true;
                    pierceCap = 3;
                    pierceBuilding = true;
                    despawnEffect = unitFx.purpleHitBig;
                    hitEffect = unitFx.purpleHit;
                    bulletInterval = 4f;
                    intervalAngle = 270f;
                    intervalSpread = 300f;
                    intervalBullet =  new LightningBulletType(){{
                        damage = 15;
                        collidesAir = false;
                        lightningColor = Color.valueOf("d297e1");
                        lightningLength = 7;
                        lightningLengthRand = 6;
                        hitEffect = Fx.none;
                    }};
                }};
                parts.add(new RegionPart("-weather"){{
                    mirror = false;
                    y = -0.8f;
                    moveRot = 0f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, -2.5f, 0, 0f));
                }});
                parts.add(new RegionPart("-weather"){{
                    mirror = false;
                    y = -1;
                    rotation = 45f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, -1.8f, -1.8f, 0f));
                }});
                parts.add(new RegionPart("-weather"){{
                    mirror = false;
                    y = -1;
                    rotation = 90f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                }});
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        planum = new UnitType("planum"){{
            this.constructor = LegsUnit::create;
            weapons.add(new Weapon(){{
                minWarmup = 0.8f;
                mirror = true;
                alternate = false;
                top = false;
                recoilTime = 70;
                y = -3.5f;
                x = 5.5f;
                layerOffset = -0.5f;
                reload = 100;
                shootSound = Sounds.laser;
                soundPitchMax = 0.8f;
                ejectEffect = unitFx.planumSmoke;
                soundPitchMin = 0.65f;
                shoot.firstShotDelay = 25f;
                bullet = new LaserBulletType(105){{
                    chargeEffect = unitFx.planumCharge;
                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = 25f;
                    layerOffset = -0.5f;
                    lifetime = 25;
                    width = 17f;
                    continuous = false;
                    sideAngle = 45f;
                    sideWidth = 2f;
                    sideLength = 24f;
                    length = 80;
                    colors = new Color[]{Color.valueOf("d297e1"), Color.valueOf("aa62ac")};
                }};
            }});
            health = 2300f;
            speed = 0.45f;
            armor = 9f;
            hitSize = 20f;
            rotateSpeed = 1.5f;
            flying = false;

            legCount = 6;
            legLength = 18;
            legBaseOffset = 2f;
            legExtension = -4;
            lockLegBase = true;
            legContinuousMove = true;

            parts.add(new RegionPart("-laser"){{
                progress = PartProgress.warmup;
                moveRot = 35f;
                y = 1.6f;
                moveX = -3f;
                moveY = 1.5f;
                mirror = true;
                moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2Out), -1, 0.5f, 15));
                layerOffset = -0.0001f;

            }});
            abilities.add(new SuppressionFieldAbility(){{
                orbRadius = 2.4f;
                y = -4f;
                particleLen = 3;
                particleSize = 1.2f;
                color = Color.valueOf("d297e1");
                range = 120;
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        procursus = new TankUnitType("procursus"){{
            this.constructor = TankUnit::create;

            health = 190f;
            armor = 4f;
            hitSize = 13f;
            rotateSpeed = 2f;
            speed = 1f;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadPullOffset = 3;
            treadRects = new Rect[]{new Rect(11 - 32f, 15 - 32f, 14, 45)};

            weapons.add(new Weapon(name + "-mount"){{
                    mirror = false;
                    reload = 180f;
                    rotate = true;
                    x = 0;
                    y = 0;
                    shoot.shots = 3;
                    shoot.shotDelay = 5;
                    bullet = new BasicBulletType(7f, 13f){{
                        width = 7f;
                        height = 12f;
                        lifetime = 20f;
                        hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                        frontColor = Color.white;
                        despawnEffect = hitEffect = unitFx.blueHit;
                        trailWidth = 1.2f;
                        trailLength = 4;
                    }};
                }});
            }};

        radius = new TankUnitType("radius"){{
            this.constructor = TankUnit::create;

            health = 610f;
            armor = 6f;
            hitSize = 20f;
            rotateSpeed = 1.8f;
            speed = 0.8f;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadPullOffset = 3;
            treadRects = new Rect[]{new Rect(17 - 48f, 10 - 48f, 19, 77)};

            weapons.add(new Weapon(name + "-mount"){{
                mirror = false;
                reload = 150f;
                rotate = true;

                recoil = 3f;
                recoilTime = 50f;
                shootSound = Sounds.shootBig;
                soundPitchMax = 0.8f;
                soundPitchMin = 0.7f;

                x = 0;
                y = 0;
                bullet = new BasicBulletType(2.5f, 20f){{
                    width = 15f;
                    height = 15f;
                    lifetime = 70f;
                    hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 7;
                    hittable = false;
                    despawnEffect = hitEffect = unitFx.blueHitBig;
                    pierceCap = 4;
                    pierceBuilding = true;
                    bulletInterval = 4;
                    intervalSpread = 45;
                    intervalRandomSpread = 45;
                    intervalBullet = fragBullet = new BasicBulletType(3f, 10f){{
                        width = 9f;
                        height = 9f;
                        lifetime = 20f;
                        hitEffect = unitFx.blueHit;
                        hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                        frontColor = Color.white;
                        despawnEffect = Fx.none;
                        trailWidth = 0.8f;
                        trailLength = 3;
                        pierceCap = 3;
                    }};
                    fragBullets = 5;
                    fragBullet.pierceCap = 1;
                    fragBullet.lifetime = 30;
                    fragBullet.speed = 5;
                    fragBullet.drag = 0.02f;
                }};
            }});
        }};

        fluor = new TankUnitType("fluor"){{
            this.constructor = TankUnit::create;

            health = 1950f;
            armor = 6f;
            hitSize = 26f;
            rotateSpeed = 1.7f;
            range = 200;
            speed = 0.7f;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadRects = new Rect[]{
                    new Rect(18 - 63f, 81 - 72f, 29, 51),
                    new Rect(8 - 63f, 20 - 72f, 25, 51)
            };

            weapons.add(new Weapon(name + "-mount"){{
                mirror = true;
                reload = 40f;
                rotate = true;
                rotateSpeed = 4;

                recoil = 1f;
                recoilTime = 20f;
                shootSound = Sounds.shootAlt;
                x = -10;
                y = -3;
                shoot.shots = 5;
                shoot.shotDelay = 3;
                bullet = new BasicBulletType(7f, 12f){{
                    width = 7f;
                    height = 12f;
                    lifetime = 20f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    smokeEffect = unitFx.blueSmoke;
                    hitEffect = despawnEffect = unitFx.blueHit;
                    trailWidth = 1.2f;
                    trailLength = 4;
                }};
            }});
            weapons.add(new Weapon(name + "-rocket"){{
                mirror = false;
                reload = 50f;
                rotate = true;
                rotateSpeed = 1;

                inaccuracy = 20f;
                recoil = 1f;
                recoilTime = 60f;
                shootSound = SoundsAlt.rocket;
                x = 0;
                y = 4;
                shoot.shots = 4;
                shoot.shotDelay = 4;
                xRand = 2;
                bullet = new MissileBulletType(3f, 42f){{
                    width = 7f;
                    height = 12f;
                    lifetime = 66.6f;
                    homingDelay = 30f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    hitEffect = despawnEffect = unitFx.blueHitBig;
                }};
                parts.add(new RegionPart("-duct"){{
                    progress = PartProgress.recoil;
                    mirror = true;
                    moveRot = 25f;
                    under = true;
                    moveX = -2f;
                }});
            }});
        }};

        gutta = new ErekirUnitType("gutta"){{
            this.constructor = ElevationMoveUnit::create;
            hovering = true;
            useEngineElevation = false;
            drag = 0.06f;
            accel = 0.14f;
            shadowElevation = 0.1f;
            targetAir = false;
            targetGround = true;
            speed = 1.3f;
            rotateSpeed = 7f;
            hitSize = 8f;
            faceTarget = false;
            health = 160;
            range = 20f;

            for(float f : new float[]{-2.5f, 2.5f}){
                parts.add(new HoverPart(){{
                    x = 3.2f;
                    y = f;
                    mirror = true;
                    radius = 3f;
                    phase = 90f;
                    stroke = 2f;
                    layerOffset = -0.001f;
                    color = Color.valueOf("d8f3f4");
                }});
            }

            engineSize = 2.4f;
            engineOffset = 4.5f;
            engineColor = Color.valueOf("d8f3f4");
            weapons.add(new Weapon(){{
                reload = 120f;
                shootCone = 180f;
                ejectEffect = unitFx.blueBlast;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;

                    rangeOverride = 30f;
                    speed = 0f;
                    splashDamageRadius = 35f;
                    instantDisappear = true;
                    splashDamage = 20f;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
            outlineColor = Color.valueOf("2d2630");
        }};

        pluvia = new UnitType("pluvia"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");
            flying = true;
            accel = 0.08f;
            drag = 0.04f;
            lowAltitude = true;
            faceTarget = true;
            targetAir = true;
            targetGround = true;
            speed = 1.3f;
            rotateSpeed = 3f;
            hitSize = 18f;
            health = 670;
            range = 180f;

            setEnginesMirror(
                    new UnitEngine(5, -3, 4f, 315f)
            );

            engineSize = 3.4f;
            engineOffset = 5f;
            engineColor = Color.valueOf("d8f3f4");
            weapons.add(new Weapon(name + "-gun"){{
                rotationLimit = 60f;
                top = true;
                x = -5;
                y = -1.5f;
                shootSound = Sounds.shootAlt;
                rotateSpeed = 5f;
                rotate = true;
                reload = 10;
                bullet = new BasicBulletType(7.5f, 7){{
                    width = 9f;
                    smokeEffect = unitFx.blueSmoke;
                    hitEffect = despawnEffect = unitFx.blueSquareHit;
                    height = 15f;
                    lifetime = 180 / 7.5f;
                    frontColor = Color.valueOf("d8f3f4");
                    backColor = trailColor = Color.valueOf("8ca9e8");
                    trailWidth = 0.8f;
                    trailLength = 4;
                    sprite = "nscape-arrow";
                }};
            }});
            abilities.add(new BerserkAbility(){{accMultiplier = 1.5f;}});
        }};

        diluvio = new UnitType("diluvio"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            flying = true;
            accel = 0.05f;
            drag = 0.02f;
            faceTarget = true;
            targetAir = true;
            targetGround = true;
            speed = 0.9f;
            armor = 3f;
            rotateSpeed = 2f;
            hitSize = 28f;
            health = 2120;
            range = 180f;
            weapons.add(new Weapon(){{
                shoot.firstShotDelay = 180;
                reload = 750;
                recoil = 12;
                mirror = false;
                rotate = false;
                x = 0;
                y = 0;
                bullet = new BasicBulletType(){{
                    chargeEffect = unitFx.diluvioCharge;
                    shootStatus = StatusEffects.unmoving;
                    despawnEffect = unitFx.blueHitBig;
                    shootStatusDuration = 210f;
                    hittable = false;
                    width = 23;
                    height = 23;
                    backColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    shrinkX = 0;
                    shrinkY = 0;
                    lifetime = 120;
                    damage = 350;
                    lightRadius = 70f;
                    lightColor = Color.valueOf("d8f3f4");
                    clipSize = 250f;
                    sprite = "circle-bullet";
                    speed = 1.5f;
                    splashDamage = 90f;
                    splashDamageRadius = 50;
                    hitShake = 4f;
                    soundPitchMin = 1;
                    soundPitchMax = 1.2f;
                    hitSound = SoundsAlt.plasmaBlast;
                    shootSound = SoundsAlt.plasma;
                    chargeSound = SoundsAlt.plasmaCharge;
                    hitEffect = unitFx.diluvioHit;
                }};
                abilities.add(new BerserkAbility(){{
                    accMultiplier = 1f;
                    healMultiplier = 20 / 60f;
                }});
            }});
            setEnginesMirror(
                    new UnitEngine(10, -7, 5f, 315f)
            );
            engineSize = 8f;
            engineOffset = 9f;
            engineColor = Color.valueOf("d8f3f4");
            parts.add(new RegionPart("-ala"){{
                moves.add(new PartMove(PartProgress.charge.curve(Interp.circleIn), -3, 3, 35));
                moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2In), -3, 3, 35));
                mirror = true;
                under = true;
                layerOffset = -0.0001f;
                heatProgress = PartProgress.charge.curve(Interp.circleIn);
            }});
        }};

        ishi = new UnitType("ishi"){{
            this.constructor = UnitEntity::create;
            aiController = DefenderAI::new;
            outlineColor = Color.valueOf("2d2630");

            armor = 3f;
            health = 270;
            speed = 1.6f;
            rotateSpeed = 1.8f;
            accel = 0.06f;
            drag = 0.026f;
            lowAltitude = true;

            flying = true;
            engineOffset = 3f;
            engineSize = 3f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = false;
            hitSize = 12f;

            abilities.add(
                    new ShieldRegenFieldAbility(5, 30, 120, 45),
                    new StatusFieldAbility(NSstatus.ascent, 60 * 8, 60 * 4, 75){{
                        activeEffect = unitFx.ishiField;
                    }}
            );

            parts.add(new RegionPart("-shield"){{
                mirror = true;
            }});
        }};

        yama = new UnitType("yama"){{
            this.constructor = UnitEntity::create;
            aiController = DefenderAI::new;
            outlineColor = Color.valueOf("2d2630");

            armor = 4f;
            health = 670;
            speed = 1.2f;
            rotateSpeed = 1.6f;
            accel = 0.05f;
            drag = 0.021f;
            lowAltitude = true;

            flying = true;
            engineOffset = 2.5f;
            engineSize = 7f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = false;
            hitSize = 24f;
            weapons.add(new PointDefenseWeapon(name + "-point"){{
                x =  10;
                y = -4.5f;
                reload = 24f;
                rotateSpeed = 3.5f;
                targetInterval = 15f;
                targetSwitchInterval = 4f;
                recoil = 1f;
                shootY = -0.4f;
                recoilTime = 60;
                bullet = new BulletType(){{
                    shootSound = Sounds.lasershoot;
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.pointHit;
                    color = Color.valueOf("d297e1");
                    maxRange = 120f;
                    damage = 30;
                }};
                parts.add(new RegionPart("-wing"){{
                    mirror = true;
                    progress = PartProgress.recoil;
                    moveRot = -18;
                    moveX = -1;
                    under = true;
                    outline = false;
                }});
            }});
            abilities.add(new RepairFieldAbility(15, 2 * 60, 75));
        }};

        kometto = new UnitType("kometto"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            armor = 4f;
            health = 1120;
            speed = 1.1f;
            rotateSpeed = 3.5f;
            accel = 0.03f;
            drag = 0.015f;
            lowAltitude = true;
            range = 80;

            flying = true;
            engineOffset = -1f;
            engineSize = 6f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = true;
            hitSize = 24f;

            abilities.add(new MoveEffectAbility(0f, -7f, Color.valueOf("d297e1"), unitFx.komettoTrail, 4f));
            weapons.add(new Weapon(){{
                ejectEffect = Fx.none;
                rotate = true;
                bullet = new BulletType(40, 0){{

                    shootEffect = despawnEffect = smokeEffect = Fx.none;
                    shootSound = Sounds.none;
                    lifetime = 3;
                    collides = false;
                }};
            }});
            abilities.add(new DamageFieldAbility(){{
                damage = 10;
                radius = 90;
                reload = 30;
                rotateSpeed = 2;
                sectors = 12;
                secRad = 0.03f;
                effect = NSstatus.overCharged;
                radColor = Color.valueOf("d297e1");
                useEffect = unitFx.komettoSplash;
            }});
            abilities.add(new ShieldArcAbility(){{
                radius = 45f;
                angle = 220f;
                regen = 3f;
                cooldown = 60f * 40f;
                max = 2400f;
                y = 0;
                width = 6f;
                whenShooting = true;
            }});
            parts.add(new RegionPart("-generator"){{
                progress = PartProgress.warmup;
                moveY = -0.7f;
                moveRot = -23;
                mirror = true;
                under = true;
                layerOffset = -0.0001f;
            }});
        }};

        pabbu = new UnitType("PABBU"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            flying = true;
            accel = 0.05f;
            drag = 0.02f;
            faceTarget = true;
            targetAir = true;
            targetGround = true;
            speed = 0.5f;
            armor = 9f;
            rotateSpeed = 0.7f;
            hitSize = 28f;
            health = 4750;
            range = 100f;
            lowAltitude = true;
            weapons.add(new Weapon(name + "-cannon"){{
                reload = 450f;
                mirror = false;
                x = 0;
                shootSound = Sounds.artillery;
                rotate = true;
                shake = 2f;
                shootY = 6;
                rotationLimit = 15;
                rotateSpeed = 0.7f;
                shoot.shots = 4;
                shoot.shotDelay = 7f;
                recoils = 2;
                bullet = new BasicBulletType(3, 150){{
                    lifetime = 70;
                    width = 9;
                    height = 14;
                }};
                parts.add(new RegionPart("-barrel1"){{
                    progress = PartProgress.recoil;
                    mirror = false;
                    x = 0;
                    y = 4;
                    moveY = -2;
                    under = true;
                }});
                parts.add(new RegionPart("-barrel2"){{
                    progress = PartProgress.recoil;
                    mirror = false;
                    x = 0;
                    y = 8;
                    moveY = -4;
                    under = true;
                }});
            }}, new Weapon(name + "-heal"){{
                shootY = 2f;
                reload = 10f;
                x = -6.3f;
                y = -6.4f;
                ejectEffect = Fx.none;
                recoil = 2f;
                rotate = true;
                shootSound = Sounds.lasershoot;
                controllable = false;
                autoTarget = true;
                bullet = new LaserBoltBulletType(5.2f, 13){{
                    lifetime = 20f;
                    hittable = false;
                    healPercent = 2f;
                    smokeEffect = hitEffect = despawnEffect = unitFx.hitLaser;
                    collidesTeam = true;
                }};
            }}, new Weapon(name + "-heal"){{
                shootY = 2f;
                reload = 15f;
                x = -8.6f;
                y = -0.3f;
                ejectEffect = Fx.none;
                recoil = 2f;
                rotate = true;
                shootSound = Sounds.lasershoot;
                controllable = false;
                autoTarget = true;
                bullet = new LaserBoltBulletType(5.2f, 13){{
                    lifetime = 20f;
                    healPercent = 2f;
                    hittable = false;
                    smokeEffect = hitEffect = despawnEffect = unitFx.hitLaser;
                    collidesTeam = true;
                }};
            }}, new Weapon(name + "-minigun"){{
                reload = 5f;
                x = -6.2f;
                y = 6.2f;
                recoil = 2f;
                rotate = true;
                bullet = new BasicBulletType(6, 10){{
                    lifetime = 20;
                    width = 4;
                    height = 6;
                }};
            }});

            abilities.add(new RegenAbility(){{amount = 0.5f;}});
            abilities.add(new BerserkAbility(){{accMultiplier = 0.75f;}});

            engineSize = 9f;
            engineOffset = 0f;

            setEnginesMirror(
                    new UnitEngine(9, -8, 6f, 315f)
            );
            setEnginesMirror(
                    new UnitEngine(9, 8, 6f, 45f)
            );
        }};

        observer = new UnitType("Observer"){{
            this.constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 9f;
            mineTier = 3;
            mineWalls = true;
            buildSpeed = 1;
            itemCapacity = 45;
            fogRadius = 6f;

            health = 80f;
            immunities.add(NSstatus.ozoneCorrosion);
            weapons.add(new Weapon(){{
                mirror = false;
                showStatSprite = false;
                reload = 30f;
                x = 0;
                shootSound = Sounds.missile;
                soundPitchMax = 1.5f;
                soundPitchMin = 1.2f;

                bullet = new MissileBulletType(3, 18){{
                    lifetime = 40;
                    status = NSstatus.overCharged;
                    statusDuration = 60f;
                    recoil = 0.05f;
                    healAmount = 20;
                    collidesTeam = true;
                    pierceCap = 2;
                    buildingDamageMultiplier = 0.01f;
                    backColor = Color.valueOf("ffd37f");
                    frontColor = Color.white;
                }};
            }});
            drag = 0.08f;
            speed = 4f;
            rotateSpeed = 20f;
            accel = 0.1f;

            engineOffset = 4f;
            hitSize = 9f;
            alwaysUnlocked = true;
            outlineColor = Color.valueOf("4c3d4e");
        }};
    }
}
