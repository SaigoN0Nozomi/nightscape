package nightscape.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.unit.TankUnitType;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import nightscape.content.effects.unitFx;
import nightscape.entity.abilities.*;
import nightscape.entity.ai.AdvancedDefenderAI;
import nightscape.entity.ai.KinzokuAI;
import nightscape.entity.ai.SemiDefenderAI;
import nightscape.world.meta.SoundsAlt;

import static mindustry.Vars.tilePayload;

public class NSunits {
    public static UnitType
    //core
    observer, suppressor,
    //static
    point, vector, planum, volumine,

    //flash
    procursus, radius, fluor, flumen,

    //K.YS.N.
    gutta, pluvia, diluvio, tormenta,

    //defenders
    ishi, yama, kometto, suta, kinzoku,

    //SUS
    pabbu;

    public static void load() {
        point = new UnitType("point") {{
            this.constructor = MechUnit::create;

            mechFrontSway = 0.55f;
            health = 170f;
            speed = 0.55f;
            armor = 5f;
            hitSize = 8f;
            rotateSpeed = 4f;
            fogRadius = 10;
            flying = false;

            weapons.add(new Weapon(name + "-weapon") {{
                top = false;
                y = -0f;
                x = -3f;
                reload = 30f;
                recoil = 1f;
                shake = 0.3f;

                bullet = new BasicBulletType(6, 19) {{
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

        vector = new UnitType("vector") {{
            this.constructor = MechUnit::create;

            health = 560f;
            mechFrontSway = 0.55f;
            speed = 0.5f;
            armor = 8f;
            hitSize = 10f;
            rotateSpeed = 1.8f;
            fogRadius = 8;
            flying = false;
            targetAir = false;

            weapons.add(new Weapon("nscape-rainCannon") {{
                top = false;
                y = -0.3f;
                x = -4.8f;
                reload = 150f;
                recoil = 4f;
                shake = 2f;
                shootSound = Sounds.shootBig;

                bullet = new BasicBulletType(1.6f, 10) {{
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
                    intervalBullet = new LightningBulletType() {{
                        damage = 15;
                        collidesAir = false;
                        lightningColor = Color.valueOf("d297e1");
                        lightningLength = 7;
                        lightningLengthRand = 6;
                        hitEffect = Fx.none;
                    }};
                }};
                parts.add(new RegionPart("-weather") {{
                    mirror = false;
                    y = -0.8f;
                    moveRot = 0f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, -2.5f, 0, 0f));
                }});
                parts.add(new RegionPart("-weather") {{
                    mirror = false;
                    y = -1;
                    rotation = 45f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, -1.8f, -1.8f, 0f));
                }});
                parts.add(new RegionPart("-weather") {{
                    mirror = false;
                    y = -1;
                    rotation = 90f;
                    under = true;
                    moves.add(new PartMove(PartProgress.recoil, 0f, -3f, 0f));
                }});
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        planum = new UnitType("planum") {{
            this.constructor = LegsUnit::create;
            weapons.add(new Weapon() {{
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
                bullet = new LaserBulletType(105) {{
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
            fogRadius = 12;

            legCount = 6;
            legLength = 18;
            legBaseOffset = 2f;
            legExtension = -4;
            lockLegBase = true;
            legContinuousMove = true;

            parts.add(new RegionPart("-laser") {{
                progress = PartProgress.warmup;
                moveRot = 35f;
                y = 1.6f;
                moveX = -3f;
                moveY = 1.5f;
                mirror = true;
                moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2Out), -1, 0.5f, 15));
                layerOffset = -0.0001f;

            }});
            abilities.add(new SuppressionFieldAbility() {{
                orbRadius = 2.4f;
                y = -4f;
                particleLen = 3;
                particleSize = 1.2f;
                color = Color.valueOf("d297e1");
                range = 120;
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        volumine = new UnitType("volumine") {{
            this.constructor = LegsUnit::create;
            health = 3666f;
            speed = 0.42f;
            armor = 15f;
            hitSize = 32f;
            rotateSpeed = 1.5f;
            flying = false;
            fogRadius = 13;

            legCount = 6;
            legLength = 26;
            legBaseOffset = 2f;
            legExtension = -4;
            lockLegBase = true;
            legContinuousMove = true;
            outlineColor = Color.valueOf("4c3d4e");

            weapons.add(new Weapon(name + "-mg") {{
                reload = 4;
                recoil = 0.5f;
                shootY = -2;
                rotationLimit = 15;
                minWarmup = 0.8f;
                inaccuracy = 12;
                top = false;
                layerOffset = -0.001f;
                shootSound = SoundsAlt.bigsap;
                x = -12;
                bullet = new SapBulletType() {{
                    damage = 46;
                    smokeEffect = Fx.none;
                    color = Color.valueOf("d297e1");
                    sapStrength = 0.1f;
                    length = 60f;
                    width = 0.75f;
                    lifetime = 15;
                }};
                parts.add(new RegionPart("-backs") {{
                    moveY = -3;
                    moveX = -3;
                    under = true;
                }});
                parts.add(new RegionPart("-wing") {{
                    moveY = -3;
                    moveRot = 35;
                }});
            }});
            abilities.add(new SuppressionFieldAbility() {{
                orbRadius = 3.4f;
                y = -5f;
                particleLen = 3;
                particleSize = 1.6f;
                color = Color.valueOf("d297e1");
                range = 150;
            }});
            abilities.add(new DeathWishAbility() {{
                radius = 150;
                damage = 2460;
                deathWish = unitFx.volumineDeathWish;
            }});
        }};

        procursus = new TankUnitType("procursus") {{
            this.constructor = TankUnit::create;

            health = 190f;
            armor = 4f;
            hitSize = 13f;
            rotateSpeed = 2f;
            speed = 1f;
            fogRadius = 8;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadPullOffset = 3;
            treadRects = new Rect[]{new Rect(11 - 32f, 15 - 32f, 14, 45)};

            weapons.add(new Weapon(name + "-mount") {{
                mirror = false;
                reload = 180f;
                rotate = true;
                shootSound = Sounds.shootAlt;
                x = 0;
                y = 0;
                shoot.shots = 3;
                shoot.shotDelay = 5;
                bullet = new BasicBulletType(7f, 13f) {{
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

        radius = new TankUnitType("radius") {{
            this.constructor = TankUnit::create;

            health = 610f;
            armor = 6f;
            hitSize = 20f;
            rotateSpeed = 1.8f;
            speed = 0.8f;
            fogRadius = 9;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadPullOffset = 3;
            treadRects = new Rect[]{new Rect(17 - 48f, 10 - 48f, 19, 77)};

            weapons.add(new Weapon(name + "-mount") {{
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
                bullet = new BasicBulletType(2.5f, 20f) {{
                    width = 15f;
                    height = 15f;
                    lifetime = 55f;
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
                    intervalBullet = fragBullet = new BasicBulletType(3f, 10f) {{
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

        fluor = new TankUnitType("fluor") {{
            this.constructor = TankUnit::create;

            health = 1950f;
            armor = 6f;
            hitSize = 26f;
            rotateSpeed = 1.7f;
            fogRadius = 13;
            range = 200;
            speed = 0.7f;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadRects = new Rect[]{
                    new Rect(18 - 63f, 81 - 72f, 29, 51),
                    new Rect(8 - 63f, 20 - 72f, 25, 51)
            };

            weapons.add(new Weapon(name + "-mount") {{
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
                bullet = new BasicBulletType(7f, 12f) {{
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
            weapons.add(new Weapon(name + "-rocket") {{
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
                bullet = new MissileBulletType(3f, 42f) {{
                    width = 7f;
                    height = 12f;
                    lifetime = 66.6f;
                    homingDelay = 30f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    hitEffect = despawnEffect = unitFx.blueHitBig;
                }};
                parts.add(new RegionPart("-duct") {{
                    progress = PartProgress.recoil;
                    mirror = true;
                    moveRot = 25f;
                    under = true;
                    moveX = -2f;
                }});
            }});
        }};

        flumen = new TankUnitType("flumen") {{
            this.constructor = TankUnit::create;

            health = 5350f;
            armor = 7f;
            hitSize = 34f;
            rotateSpeed = 1.6f;
            fogRadius = 16;
            range = 260;
            speed = 0.6f;
            flying = false;
            targetAir = true;
            outlineColor = Color.valueOf("2d2630");

            treadRects = new Rect[]{
                    new Rect(27 - 85f, 121 - 93f, 30, 54),
                    new Rect(22 - 85f, 20 - 93f, 34, 51)
            };
            weapons.add(new Weapon(name + "-weapon") {{
                mirror = false;
                top = true;
                reload = 60;
                recoilTime = 120;
                recoils = 3;
                minWarmup = 0.8f;
                shootWarmupSpeed = 0.04f;
                x = 0;
                rotate = true;
                rotateSpeed = 1.2f;
                layerOffset = 0.001f;
                shootY = 7;
                shootSound = Sounds.shootBig;
                shake = 1.9f;
                shoot = new ShootMulti(
                        new ShootBarrel() {{
                            barrels = new float[]{-4, 0, 0,
                                    0, 0, 0,
                                    4, 0, 0};
                        }}, new ShootSpread(3, 2));
                parts.addAll(new RegionPart("-barrel") {{
                    mirror = false;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -4;
                    recoilIndex = 1;
                    under = true;
                }}, new RegionPart("-barrel") {{
                    mirror = false;
                    x = -3.9f;
                    recoilIndex = 0;
                    under = true;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -4;
                }}, new RegionPart("-barrel") {{
                    mirror = false;
                    x = 3.9f;
                    recoilIndex = 2;
                    under = true;
                    progress = PartProgress.recoil.curve(Interp.pow2In);
                    moveY = -4;
                }});
                parts.addAll(new RegionPart("-ring") {{
                    mirror = false;
                    under = true;
                }}, new RegionPart("-blade") {{
                    mirror = true;
                    moveRot = -45;
                    moveY = -2;
                    layerOffset = 0.0003f;
                    progress = PartProgress.warmup.curve(Interp.pow2);
                }}, new RegionPart("-blade") {{
                    mirror = true;
                    progress = PartProgress.warmup.curve(Interp.pow2);
                    layerOffset = 0.0001f;
                    moveRot = -22.5f;
                }});
                bullet = new BasicBulletType(8f, 24f) {{
                    width = 6f;
                    height = 6f;
                    sprite = "circle-bullet";
                    pierceCap = 3;
                    pierceBuilding = true;
                    lifetime = 20f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    smokeEffect = unitFx.blueSmokeBig;
                    hitEffect = unitFx.blueHitBig;
                    despawnEffect = unitFx.blueHit;
                    trailWidth = 1.2f;
                    trailLength = 8;
                }};
            }});
            abilities.add(new MinigunAbility() {{
                heatDamage = 60;
                heatMax = 175;
                heatReloadMultiplier = 2;
            }});
        }};

        gutta = new ErekirUnitType("gutta") {{
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
            fogRadius = 6;
            range = 20f;

            for (float f : new float[]{-2.5f, 2.5f}) {
                parts.add(new HoverPart() {{
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
            weapons.add(new Weapon() {{
                reload = 120f;
                shootCone = 180f;
                ejectEffect = unitFx.blueBlast;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType() {{
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

        pluvia = new UnitType("pluvia") {{
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
            fogRadius = 10;
            range = 140f;

            setEnginesMirror(
                    new UnitEngine(5, -3, 4f, 315f)
            );

            engineSize = 3.4f;
            engineOffset = 5f;
            engineColor = Color.valueOf("d8f3f4");
            weapons.add(new Weapon(name + "-gun") {{
                rotationLimit = 60f;
                top = true;
                x = -5;
                y = -1.5f;
                shootSound = SoundsAlt.shoot2;
                rotateSpeed = 5f;
                rotate = true;
                reload = 10;
                bullet = new BasicBulletType(7.5f, 7) {{
                    width = 9f;
                    smokeEffect = unitFx.blueSmoke;
                    hitEffect = despawnEffect = unitFx.blueSquareHit;
                    height = 15f;
                    lifetime = 140 / 7.5f;
                    frontColor = Color.valueOf("d8f3f4");
                    backColor = trailColor = Color.valueOf("8ca9e8");
                    trailWidth = 0.8f;
                    trailLength = 4;
                    sprite = "nscape-arrow";
                }};
            }});
            abilities.add(new BerserkAbility() {{
                accMultiplier = 1.5f;
            }});
        }};

        diluvio = new UnitType("diluvio") {{
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
            range = 120f;
            fogRadius = 16;
            weapons.add(new Weapon() {{
                shoot.firstShotDelay = 180;
                reload = 750;
                recoil = 12;
                mirror = false;
                rotate = false;
                x = 0;
                y = 0;
                bullet = new BasicBulletType() {{
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
                    lifetime = 80;
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
                abilities.add(new BerserkAbility() {{
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
            parts.add(new RegionPart("-ala") {{
                moves.add(new PartMove(PartProgress.charge.curve(Interp.circleIn), -3, 3, 35));
                moves.add(new PartMove(PartProgress.recoil.curve(Interp.pow2In), -3, 3, 35));
                mirror = true;
                under = true;
                layerOffset = -0.0001f;
                heatProgress = PartProgress.charge.curve(Interp.circleIn);
            }});
        }};

        MissileUnitType tormentaMissile = new MissileUnitType("tormenta-missile") {{
            speed = 3.2f;
            maxRange = 6f;
            outlineColor = Color.valueOf("2d2630");
            engineColor = trailColor = Color.valueOf("d8f3f4");
            engineLayer = Layer.effect;
            engineSize = 1.2f;
            engineOffset = 2f;
            rotateSpeed = 1.9f;
            trailLength = 14;
            missileAccelTime = 10f;
            lowAltitude = true;
            loopSound = Sounds.missileTrail;
            loopSoundVolume = 0.6f;
            deathSound = Sounds.largeExplosion;
            targetAir = true;
            fogRadius = 3f;
            health = 125;
            weapons.add(new Weapon() {{
                shootCone = 360f;
                mirror = false;
                reload = 1f;
                shootOnDeath = true;
                shake = 10f;
                bullet = new ExplosionBulletType(125f, 32f) {{
                    hitEffect = unitFx.tormentaHit;
                    collidesAir = true;
                }};
            }});
        }};

        tormenta = new UnitType("tormenta") {{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            flying = true;
            accel = 0.08f;
            drag = 0.05f;
            faceTarget = true;
            targetAir = true;
            targetGround = true;
            speed = 0.8f;
            armor = 5f;
            rotateSpeed = 1.6f;
            hitSize = 36f;
            health = 3650;
            range = 220f;
            fogRadius = 22;

            engineSize = 5.4f;
            engineOffset = 7f;
            engineColor = Color.valueOf("d8f3f4");
            weapons.add(new Weapon() {{
                shoot.firstShotDelay = 70;
                reload = 180;
                mirror = true;
                rotate = false;
                baseRotation = 90;
                shootCone = 360;
                x = -5;
                y = -5;
                bullet = new BulletType() {{
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootSmokeTitan;
                    shake = 1f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = false;
                    spawnUnit = tormentaMissile;
                    spawnUnit.lifetime = 120;
                }};
            }});
            weapons.add(new Weapon() {{
                shoot.firstShotDelay = 70;
                reload = 180;
                mirror = true;
                rotate = false;
                baseRotation = 110;
                shootCone = 360;
                x = -5;
                y = -5;
                bullet = new BulletType() {{
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootSmokeTitan;
                    shake = 1f;
                    speed = 0f;
                    keepVelocity = false;
                    collidesAir = false;
                    spawnUnit = tormentaMissile;
                    spawnUnit.lifetime = 160;
                }};
            }});
            parts.add(new RegionPart("-ala") {{
                moveRot = -23;
                moveY = 4;
                moveX = 0.5f;
                mirror = true;
                under = true;
                layerOffset = -0.0001f;
            }});
        }};

        ishi = new UnitType("ishi") {{
            this.constructor = UnitEntity::create;
            aiController = AdvancedDefenderAI::new;
            outlineColor = Color.valueOf("2d2630");
            circleTarget = true;

            armor = 3f;
            health = 270;
            speed = 1.6f;
            rotateSpeed = 3.5f;
            accel = 0.06f;
            drag = 0.026f;
            lowAltitude = true;
            fogRadius = 10;

            flying = true;
            engineOffset = 3f;
            engineSize = 3f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = false;
            hitSize = 12f;

            abilities.add(
                    new ShieldRegenFieldAbility(5, 30, 120, 45),
                    new StatusFieldAbility(NSstatus.ascent, 60 * 8, 60 * 4, 75) {{
                        activeEffect = unitFx.ishiField;
                    }}
            );

            parts.add(new RegionPart("-shield") {{
                mirror = true;
            }});
        }};

        yama = new UnitType("yama") {{
            outlineColor = Color.valueOf("2d2630");
            this.constructor = UnitEntity::create;
            aiController = AdvancedDefenderAI::new;
            circleTarget = true;

            armor = 4f;
            health = 670;
            speed = 1.2f;
            rotateSpeed = 1.9f;
            accel = 0.05f;
            drag = 0.021f;
            lowAltitude = true;
            fogRadius = 10;

            flying = true;
            engineOffset = 2.5f;
            engineSize = 7f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = false;
            hitSize = 24f;
            weapons.add(new PointDefenseWeapon(name + "-point") {{
                x = 7.3f;
                y = -2.5f;
                reload = 24f;
                rotateSpeed = 3.5f;
                targetInterval = 15f;
                targetSwitchInterval = 4f;
                recoil = 1f;
                shootY = -0.4f;
                recoilTime = 60;
                bullet = new BulletType() {{
                    shootSound = Sounds.lasershoot;
                    shootEffect = Fx.sparkShoot;
                    hitEffect = Fx.pointHit;
                    color = Color.valueOf("d297e1");
                    maxRange = 120f;
                    damage = 30;
                }};
                parts.add(new RegionPart("-wing") {{
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

        kometto = new UnitType("kometto") {{
            this.constructor = UnitEntity::create;
            aiController = SemiDefenderAI::new;
            outlineColor = Color.valueOf("2d2630");

            armor = 4f;
            health = 920;
            speed = 1.1f;
            rotateSpeed = 3.5f;
            accel = 0.03f;
            drag = 0.015f;
            lowAltitude = true;
            range = 80;
            fogRadius = 14;

            flying = true;
            engineOffset = -1f;
            engineSize = 6f;
            engineColor = Color.valueOf("d297e1");
            faceTarget = true;
            hitSize = 24f;

            abilities.add(new MoveEffectAbility(0f, -7f, Color.valueOf("d297e1"), unitFx.komettoTrail, 4f));
            weapons.add(new Weapon() {{
                ejectEffect = Fx.none;
                rotate = true;
                rotateSpeed = 4;
                bullet = new BulletType(40, 0) {{
                    shootEffect = despawnEffect = smokeEffect = Fx.none;
                    shootSound = Sounds.none;
                    lifetime = 3;
                    collides = false;
                }};
            }});
            abilities.add(new DamageFieldAbility() {{
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
            abilities.add(new ForceFieldAbility(70f, 0.5f, 3000f, 60f * 20, 4, 45f));
            parts.add(new RegionPart("-generator") {{
                progress = PartProgress.warmup;
                moveY = -0.7f;
                moveRot = -23;
                mirror = true;
                under = true;
                layerOffset = -0.0001f;
            }});
        }};

        kinzoku = new UnitType("kinzoku") {{
            this.constructor = LegsUnit::create;
            controller = u -> new KinzokuAI();
            playerControllable = logicControllable = false;

            health = 200f;
            speed = 0.7f;
            armor = 2f;
            hitSize = 4f;
            rotateSpeed = 3f;
            flying = false;
            fogRadius = 5;
            range = 40;

            legCount = 6;
            legForwardScl = 0.3f;
            legLength = 7;
            legBaseOffset = 0;
            legExtension = -1;
            outlineColor = Color.valueOf("4c3d4e");
            weapons.add(new RepairBeamWeapon() {{
                x = 0;
                y = 2;
                mirror = false;
                shootY = 0;
                repairSpeed = 20 / 60f;
                beamWidth = 0.4f;
                bullet = new BulletType() {{
                    maxRange = 60f;
                }};
                shootStatus = NSstatus.overCharged;
                shootStatusDuration = 60;
            }});
        }};

        suta = new UnitType("suta") {{
            this.constructor = PayloadUnit::create;
            aiController = AdvancedDefenderAI::new;
            outlineColor = Color.valueOf("2d2630");
            faceTarget = false;
            payloadCapacity = (4f * 4f) * tilePayload;

            armor = 12f;
            health = 4120;
            speed = 1f;
            rotateSpeed = 1.3f;
            accel = 0.02f;
            drag = 0.05f;
            lowAltitude = true;
            range = 120;
            fogRadius = 19;

            flying = true;
            engineOffset = 10f;
            engineSize = 4f;
            engineColor = Color.valueOf("d297e1");
            hitSize = 28f;
            abilities.add(new MoveEffectAbility(0f, -10f, Color.valueOf("d297e1"), unitFx.sutaTrail, 4f));
            abilities.add(new StatusFieldAbility(NSstatus.defended, 60 * 30, 60 * 5, 150) {{
                activeEffect = unitFx.sutaField;
            }});
            abilities.add(new DamageSpawnAbility(kinzoku, 600, 8, -6, 5), new DamageSpawnAbility(kinzoku, 600, -8, -6, 20));
            weapons.add(new RepairBeamWeapon() {{
                x = 0;
                y = 11;
                mirror = false;
                shootY = 0;
                repairSpeed = 1;
                bullet = new BulletType() {{
                    maxRange = 140f;
                }};
            }});
            BulletType bul = new BasicBulletType(3f, 11) {
                {
                    width = 9f;
                    height = 16f;
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
                    intervalBullet = new LightningBulletType() {{
                        damage = 13;
                        collidesAir = false;
                        lightningColor = Color.valueOf("d297e1");
                        lightningLength = 7;
                        lightningLengthRand = 6;
                        hitEffect = Fx.none;
                    }};
                }
            };
            weapons.add(new Weapon(name + "-cannon") {{
                rotate = true;
                mirror = false;
                controllable = false;
                autoTarget = true;
                shootSound = SoundsAlt.blast;
                layerOffset = -0.0002f;
                x = 7;
                y = 3;
                reload = 60;
                rotationLimit = 120;
                baseRotation = -50;
                bullet = bul;
            }}, new Weapon(name + "-cannon") {{
                rotate = true;
                mirror = false;
                controllable = false;
                autoTarget = true;
                shootSound = SoundsAlt.blast;
                layerOffset = -0.0002f;
                x = -7;
                y = 3;
                reload = 60;
                rotationLimit = 120;
                baseRotation = 50;
                bullet = bul;
            }});
            parts.add(new RegionPart("-generator") {{
                mirror = true;
                progress = PartProgress.warmup;
                moveX = 1;
                moveY = 1;
                moveRot = 10;
                layerOffset = -0.0001f;
            }});
        }};

        pabbu = new UnitType("PABBU") {{
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
            weapons.add(new Weapon(name + "-cannon") {{
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
                bullet = new BasicBulletType(3, 150) {{
                    lifetime = 70;
                    width = 9;
                    height = 14;
                }};
                parts.add(new RegionPart("-barrel1") {{
                    progress = PartProgress.recoil;
                    mirror = false;
                    x = 0;
                    y = 4;
                    moveY = -2;
                    under = true;
                }});
                parts.add(new RegionPart("-barrel2") {{
                    progress = PartProgress.recoil;
                    mirror = false;
                    x = 0;
                    y = 8;
                    moveY = -4;
                    under = true;
                }});
            }}, new Weapon(name + "-heal") {{
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
                bullet = new LaserBoltBulletType(5.2f, 13) {{
                    lifetime = 20f;
                    hittable = false;
                    healPercent = 2f;
                    smokeEffect = hitEffect = despawnEffect = unitFx.hitLaser;
                    collidesTeam = true;
                }};
            }}, new Weapon(name + "-heal") {{
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
                bullet = new LaserBoltBulletType(5.2f, 13) {{
                    lifetime = 20f;
                    healPercent = 2f;
                    hittable = false;
                    smokeEffect = hitEffect = despawnEffect = unitFx.hitLaser;
                    collidesTeam = true;
                }};
            }}, new Weapon(name + "-minigun") {{
                reload = 5f;
                x = -6.2f;
                y = 6.2f;
                recoil = 2f;
                rotate = true;
                bullet = new BasicBulletType(6, 10) {{
                    lifetime = 20;
                    width = 4;
                    height = 6;
                }};
            }});

            abilities.add(new RegenAbility() {{
                amount = 0.5f;
            }});
            abilities.add(new BerserkAbility() {{
                accMultiplier = 0.75f;
            }});

            engineSize = 9f;
            engineOffset = 0f;

            setEnginesMirror(
                    new UnitEngine(9, -8, 6f, 315f)
            );
            setEnginesMirror(
                    new UnitEngine(9, 8, 6f, 45f)
            );
        }};

        observer = new UnitType("Observer") {{
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

            health = 120f;
            immunities.add(NSstatus.ozoneCorrosion);
            weapons.add(new Weapon() {{
                mirror = false;
                showStatSprite = false;
                reload = 30f;
                x = 0;
                shootSound = Sounds.missile;
                soundPitchMax = 1.5f;
                soundPitchMin = 1.2f;
                bullet = new MissileBulletType(3, 18) {{
                    lifetime = 40;
                    status = NSstatus.overCharged;
                    statusDuration = 60f;
                    recoil = 0.03f;
                    healAmount = 10;
                    collidesTeam = true;
                    pierceCap = 3;
                    buildingDamageMultiplier = 0.01f;
                    backColor = Color.valueOf("ffd37f");
                    frontColor = Color.white;
                }};
            }});
            drag = 0.08f;
            speed = 3f;
            rotateSpeed = 20f;
            accel = 0.1f;

            engineOffset = 4f;
            hitSize = 9f;
            alwaysUnlocked = true;
            outlineColor = Color.valueOf("4c3d4e");
        }};

        suppressor = new UnitType("suppressor") {{
            this.constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            drag = 0.06f;
            speed = 5f;
            rotateSpeed = 20f;
            accel = 0.05f;

            engineOffset = 8f;
            hitSize = 11f;
            alwaysUnlocked = false;
            outlineColor = Color.valueOf("4c3d4e");

            lowAltitude = true;
            flying = true;
            mineSpeed = 15f;
            mineTier = 4;
            mineWalls = true;
            buildSpeed = 1.5f;
            itemCapacity = 70;
            fogRadius = 9f;

            health = 180f;
            immunities.add(NSstatus.ozoneCorrosion);
            weapons.add(new Weapon(){{
                mirror = false;
                showStatSprite = false;
                reload = 60f;
                x = 0;
                shoot.shots = 3;
                shoot.shotDelay = 5;
                shootSound = Sounds.missile;
                bullet = new MissileBulletType(4, 19) {{
                    lifetime = 40;
                    status = NSstatus.overCharged;
                    statusDuration = 120f;
                    recoil = 0.01f;
                    healAmount = 30;
                    collidesTeam = true;
                    pierceCap = 5;
                    buildingDamageMultiplier = 0.01f;
                    backColor = Color.valueOf("ffd37f");
                    frontColor = Color.white;
                }};
            }});
            abilities.add(new ForceFieldAbility(40, 0.3f, 150, 240));
        }};
    }
}
