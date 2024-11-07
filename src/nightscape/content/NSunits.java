package nightscape.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.type.StatusEffect;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.ErekirUnitType;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.unit.TankUnitType;
import mindustry.type.weapons.PointDefenseWeapon;
import mindustry.type.weapons.RepairBeamWeapon;
import mindustry.world.meta.BlockFlag;
import nightscape.content.effects.baseunitFx;
import nightscape.content.effects.enemyunitFx;
import nightscape.content.effects.upunitFx;
import nightscape.entity.FieldBulletType;
import nightscape.entity.abilities.*;
import nightscape.entity.ai.*;
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

    //decompilers
    imp, vassago, baphometh, belial,

    //buggs
    xing, dier, dong,

    //something like only for enemy fractions units
    invader, rush, force, barier, //first level
    archangel, shield, harpoon, //second level

    //boss
    pabbu;

    public static void load() {
        point = new UnitType("point") {{
            this.constructor = MechUnit::create;
            researchCostMultiplier = 1f;
            mechFrontSway = 0.55f;
            health = 170f;
            speed = 0.55f;
            armor = 4f;
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
                    hitEffect = despawnEffect = baseunitFx.purpleHit;
                    smokeEffect = baseunitFx.purpleSmoke;
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
            armor = 6f;
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
                    smokeEffect = baseunitFx.purpleSmoke;
                    backColor = frontColor = Color.valueOf("d297e1");
                    pierce = true;
                    pierceCap = 3;
                    pierceBuilding = true;
                    despawnEffect = baseunitFx.purpleHitBig;
                    hitEffect = baseunitFx.purpleHit;
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
                ejectEffect = baseunitFx.planumSmoke;
                soundPitchMin = 0.65f;
                shoot.firstShotDelay = 25f;
                bullet = new LaserBulletType(105) {{
                    chargeEffect = baseunitFx.planumCharge;
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
            health = 1720f;
            speed = 0.45f;
            armor = 8f;
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
                    damage = 21;
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
                radius = 120;
                afterDamage = 330;
                cloudLifetime = 320;
                damage = 90;
                deathWish = baseunitFx.volumineDeathWish;
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
                reload = 60f;
                rotate = true;
                shootSound = Sounds.shootAlt;
                x = 0;
                y = 0;
                shoot.shots = 3;
                shoot.shotDelay = 5;
                bullet = new BasicBulletType(7f, 6f) {{
                    width = 7f;
                    height = 12f;
                    lifetime = 20f;
                    hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    despawnEffect = hitEffect = baseunitFx.blueHit;
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
                bullet = new BasicBulletType(2.5f, 14f) {{
                    width = 15f;
                    height = 15f;
                    lifetime = 55f;
                    hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 7;
                    hittable = false;
                    despawnEffect = hitEffect = baseunitFx.blueHitBig;
                    pierceCap = 4;
                    pierceBuilding = true;
                    bulletInterval = 4;
                    intervalSpread = 45;
                    intervalRandomSpread = 45;
                    intervalBullet = fragBullet = new BasicBulletType(3f, 6f) {{
                        width = 9f;
                        height = 9f;
                        lifetime = 20f;
                        hitEffect = baseunitFx.blueHit;
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

            health = 1420f;
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
                    smokeEffect = baseunitFx.blueSmoke;
                    hitEffect = despawnEffect = baseunitFx.blueHit;
                    trailWidth = 1.2f;
                    trailLength = 4;
                }};
            }});
            weapons.add(new Weapon(name + "-rocket") {{
                mirror = false;
                reload = 60f;
                rotate = true;
                rotateSpeed = 1;

                inaccuracy = 10f;
                recoil = 1f;
                recoilTime = 60f;
                shootSound = SoundsAlt.rocket;
                x = 0;
                y = 4;
                shoot.shots = 4;
                shoot.shotDelay = 4;
                xRand = 2;
                bullet = new MissileBulletType(6f, 26f) {{
                    width = 7f;
                    height = 12f;
                    splashDamage = 39;
                    splashDamageRadius = 24;
                    lifetime = 22.5f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    hitEffect = despawnEffect = baseunitFx.blueHitRocket;
                    weaveMag = 2;
                    weaveScale = 4;
                    weaveRandom = true;
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

            crushDamage = 20f / 60f;
            treadRects = new Rect[]{
                    new Rect(27 - 85f, 121 - 93f, 30, 54),
                    new Rect(22 - 85f, 20 - 93f, 34, 51)
            };
            weapons.add(new Weapon(name + "-weapon") {{
                mirror = false;
                top = true;
                reload = 40;
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
                bullet = new BasicBulletType(8f, 52f) {{
                    width = 6f;
                    height = 6f;
                    sprite = "circle-bullet";
                    pierceCap = 3;
                    pierceBuilding = true;
                    lifetime = 20f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    smokeEffect = baseunitFx.blueSmokeBig;
                    hitEffect = baseunitFx.blueHitBig;
                    despawnEffect = baseunitFx.blueHit;
                    trailWidth = 1.2f;
                    trailLength = 8;
                }};
            }});
            abilities.add(new MinigunAbility() {{
                heatDamage = 60;
                heatMax = 200;
                heatReloadMultiplier = 2;
            }});
        }};

        gutta = new ErekirUnitType("gutta") {{
            this.constructor = ElevationMoveUnit::create;
            hovering = true;
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
                ejectEffect = baseunitFx.blueBlast;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType() {{
                    collidesTiles = false;
                    collides = false;
                    splashDamagePierce = true;

                    rangeOverride = 30f;
                    speed = 0f;
                    splashDamageRadius = 35f;
                    instantDisappear = true;
                    splashDamage = 12f;
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
                    smokeEffect = baseunitFx.blueSmoke;
                    hitEffect = despawnEffect = baseunitFx.blueSquareHit;
                    height = 15f;
                    lifetime = 140 / 7.5f;
                    frontColor = Color.valueOf("d8f3f4");
                    backColor = trailColor = Color.valueOf("8ca9e8");
                    trailWidth = 1.4f;
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
                    chargeEffect = baseunitFx.diluvioCharge;
                    shootStatus = StatusEffects.unmoving;
                    despawnEffect = baseunitFx.blueHitBig;
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
                    splashDamagePierce = true;
                    soundPitchMin = 1;
                    soundPitchMax = 1.2f;
                    hitSound = SoundsAlt.plasmaBlast;
                    shootSound = SoundsAlt.plasma;
                    chargeSound = SoundsAlt.plasmaCharge;
                    hitEffect = baseunitFx.diluvioHit;
                    fragBullets = 4;
                    fragBullet = new BasicBulletType() {{
                        shootStatus = StatusEffects.unmoving;
                        despawnEffect = baseunitFx.blueHitBig;
                        hittable = false;
                        width = 12;
                        height = 12;
                        backColor = Color.valueOf("d8f3f4");
                        frontColor = Color.white;
                        shrinkX = -0.2f;
                        shrinkY = -0.2f;
                        lifetime = 110;
                        damage = 30;
                        lightRadius = 70f;
                        lightColor = Color.valueOf("d8f3f4");
                        clipSize = 120f;
                        sprite = "circle-bullet";
                        speed = 3f;
                        drag = 0.05f;
                        splashDamage = 40f;
                        splashDamageRadius = 25;
                        splashDamagePierce = true;
                        hitShake = 4f;
                        soundPitchMin = 1;
                        soundPitchMax = 1.2f;
                        hitSound = SoundsAlt.plasmaBlast;
                        shootSound = SoundsAlt.plasma;
                        hitEffect = baseunitFx.diluvioHitSmall;
                        collides = false;
                    }};
                }};
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
            rotateSpeed = 2.4f;
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
                    hitEffect = baseunitFx.tormentaHit;
                    collidesAir = true;
                }};
            }});
            weapons.add(new Weapon() {{
                shootCone = 360f;
                mirror = false;
                reload = 1f;
                shootOnDeath = true;
                shake = 10f;
                bullet = new FieldBulletType(40f, 10f, 64) {{
                    collidesAir = true;
                    damageAllies = false;
                }};
            }});
        }};

        tormenta = new UnitType("tormenta") {{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            flying = true;
            lowAltitude = true;
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
            parts.add(new ShapePart(){{
                progress = PartProgress.warmup.delay(0.1f);
                color = Color.valueOf("d8f3f4");
                layer = Layer.effect;
                circle = true;
                hollow = true;
                stroke = 0f;
                strokeTo = 1f;
                radius = 6f;
                y = -5;
                rotateSpeed = 2;
            }});
            parts.add(new HaloPart(){{
                progress = PartProgress.warmup.delay(0.5f);
                color = Color.valueOf("d8f3f4");
                layer = Layer.effect;
                y = -5;
                haloRotateSpeed = 2;

                haloRotation = 145f;
                shapes = 3;
                triLength = 0f;
                triLengthTo = 2f;
                haloRadius = 5.5f;
                tri = true;
                radius = 3f;
                shapeRotation = 180;
            }});
            parts.add(new HaloPart(){{
                progress = PartProgress.warmup.delay(0.5f);
                color = Color.valueOf("d8f3f4");
                layer = Layer.effect;
                y = -5;
                haloRotateSpeed = 1;

                haloRotation = 145f;
                shapes = 5;
                triLength = 0f;
                triLengthTo = 5f;
                haloRadius = 10f;
                tri = true;
                radius = 3f;
                shapeRotation = 0;
            }});
            parts.add(new HaloPart(){{
                progress = PartProgress.warmup.delay(0.5f);
                color = Color.valueOf("d8f3f4");
                layer = Layer.effect;
                y = -5;
                haloRotateSpeed = 1;

                haloRotation = 145f;
                shapes = 5;
                triLength = 0f;
                triLengthTo = 2f;
                haloRadius = 10f;
                tri = true;
                radius = 3f;
                shapeRotation = 180;
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
                    new ShieldRegenFieldAbility(3, 20, 120, 45),
                    new StatusFieldAbility(NSstatus.ascent, 60 * 2, 60 * 2, 75) {{
                        activeEffect = baseunitFx.ishiField;
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
            abilities.add(new RepairFieldAbility(20, 3 * 60, 75));
        }};

        kometto = new UnitType("kometto") {{
            this.constructor = UnitEntity::create;
            aiController = SemiDefenderAI::new;
            outlineColor = Color.valueOf("2d2630");

            armor = 4f;
            health = 1420;
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

            abilities.add(new MoveEffectAbility(0f, -7f, Color.valueOf("d297e1"), baseunitFx.komettoTrail, 4f));
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
                useEffect = baseunitFx.komettoSplash;
            }});
            abilities.add(new ForceFieldAbility(70f, 1f, 1350f, 60f * 20, 4, 45f));
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

            armor = 6f;
            health = 3120;
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
            abilities.add(new MoveEffectAbility(0f, -10f, Color.valueOf("d297e1"), baseunitFx.sutaTrail, 4f));
            abilities.add(new StatusFieldAbility(NSstatus.defended, 60 * 30, 60 * 5, 150) {{
                activeEffect = baseunitFx.sutaField;
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
                    smokeEffect = baseunitFx.purpleSmoke;
                    backColor = frontColor = Color.valueOf("d297e1");
                    pierce = true;
                    pierceCap = 3;
                    pierceBuilding = true;
                    despawnEffect = baseunitFx.purpleHitBig;
                    hitEffect = baseunitFx.purpleHit;
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

        imp = new UnitType("imp"){{
            this.constructor = MechUnit::create;
            speed = 0.75f;
            rotateSpeed = 2.2f;
            health = 130f;
            armor = 2f;
            outlineColor = Color.valueOf("302626");
            hitSize = 9;

            mechFrontSway = 0.3f;
            fogRadius = 12;
            flying = false;
            abilities.add(new SafeAbility(0.3f, 90, 8 * 60, upunitFx.impTrail){{
                safeUseFx = upunitFx.impShield;
                trailChance = 0.2f;
            }});
            weapons.add(new Weapon(name + "-slag"){{
                x = 5;
                y = -0.5f;
                shootSound = Sounds.mud;
                reload = 60;
                shoot.shots = 4;
                shoot.shotDelay = 1;
                top = false;
                bullet = new LiquidBulletType(Liquids.slag){{
                    damage = 9;
                    speed = 4;
                    lifetime = 20;
                }};
            }});
        }};

        vassago = new UnitType("vassago"){{
            this.constructor = MechUnit::create;
            speed = 0.70f;
            rotateSpeed = 2.2f;
            health = 420f;
            armor = 3f;
            outlineColor = Color.valueOf("302626");
            hitSize = 15;

            mechFrontSway = 0.35f;
            fogRadius = 14;
            flying = false;

            immunities.addAll(StatusEffects.burning, StatusEffects.melting);
            abilities.add(new SafeAbility(140, 80, upunitFx.vassagoDamage, upunitFx.vassagoTrail){{
                safeHeal = 140;
                reload = 18 * 60;
                safeShield = 40;
                trailChance = 0.1f;
            }});
            weapons.add(new Weapon(name + "-gun"){{
                x = 6.15f;
                y = -0.15f;
                shootSound = Sounds.shootBig;
                reload = 15;
                recoil = 2;
                top = false;
                shootStatus = StatusEffects.slow;
                shootStatusDuration = 20;
                bullet = new ArtilleryBulletType(2, 12){{
                    makeFire = true;
                    sprite = "circle-bullet";
                    trailLength = 8;
                    recoil = 0.4f;
                    trailWidth = 2;
                    trailEffect = Fx.none;
                    lifetime = 40;
                    fragBullet = new LiquidBulletType(Liquids.slag){{
                        speed = 1.5f;
                        damage = 3;
                        lifetime = 15;
                    }};
                }};
            }});
        }};

        baphometh = new UnitType("baphometh"){{
            this.constructor = MechUnit::create;
            speed = 0.55f;
            rotateSpeed = 1.8f;
            health = 1220f;
            armor = 4f;
            outlineColor = Color.valueOf("302626");
            hitSize = 20;

            mechFrontSway = 0.25f;
            fogRadius = 10;
            flying = false;

            immunities.addAll(StatusEffects.burning, StatusEffects.melting);
            abilities.add(new SafeAbility(){{
                safeDamage = 120;
                safeDamageRadius = 128;
                reload = 30 * 60;
                threshold = 0.12f;
                safeHeal = 200;
                safeShield = 200;
                trailChance = 0.2f;
                readyTrail = upunitFx.bapTrail;
                safeUseFx = upunitFx.bapDamage;
            }});

            weapons.add(new Weapon(name + "-flame"){{
                top = false;
                x = 8.4f;
                shootSound = Sounds.flame;
                reload = 8f;
                recoil = 1f;
                ejectEffect = Fx.none;
                bullet = new BulletType(2.2f, 46f){{
                    hitSize = 20f;
                    lifetime = 10f;
                    pierce = true;
                    pierceBuilding = true;
                    makeFire = true;
                    pierceCap = 3;
                    statusDuration = 60f * 8;
                    shootEffect = upunitFx.bapFlame;
                    hitEffect = Fx.hitFlameSmall;
                    despawnEffect = Fx.none;
                    status = StatusEffects.melting;
                    keepVelocity = false;
                    hittable = false;
                }};
            }});
        }};

        belial = new UnitType("belial"){{
            this.constructor = MechUnit::create;
            speed = 0.46f;
            rotateSpeed = 3.5f;
            health = 3120f;
            armor = 6f;
            outlineColor = Color.valueOf("302626");
            hitSize = 30;

            mechStepParticles = true;
            mechFrontSway = 1.2f;
            mechSideSway = 0.9f;
            fogRadius = 16;
            flying = false;

            immunities.addAll(StatusEffects.burning, StatusEffects.melting);
            abilities.add(new SafeAbility(){{
                selfStatus = NSstatus.reloadout;
                reload = 90 * 60;
                statusDur = 15 * 60;
                threshold = 0.5f;
                safeHeal = 600;
                safeShield = 600;
                trailChance = 0.2f;
                readyTrail = upunitFx.belialTrail;
                safeUseFx = upunitFx.belialSafeEffect;
                useSound = SoundsAlt.ascent;
            }});
            weapons.add(new Weapon(name + "-gun"){{
                x = 16.6f;
                y = 0f;
                shootSound = Sounds.artillery;
                reload = 15;
                recoilTime = 40;
                recoil = 3;
                top = false;
                bullet = new BasicBulletType(5, 43){{
                    backColor = Color.valueOf("e8998c");
                    smokeEffect = Fx.fire;
                    makeFire = true;
                    lifetime = 20;
                    width = 17;
                    height = 24;
                }};
            }});
            weapons.add(new Weapon(name + "-spray"){{
                x = 9f;
                y = 0f;
                shootSound = Sounds.flame;
                reload = 5;
                recoil = 1;
                top = false;
                bullet = new LiquidBulletType(Liquids.slag){{
                    damage = 7;
                    speed = 4;
                    lifetime = 20;
                }};
            }});
        }};

        xing = new UnitType("xing"){{
            this.constructor = UnitEntity::create;
            health = 90;
            armor = 3;
            accel = 0.13f;
            drag = 0.05f;
            flying = true;
            hitSize = 10f;
            rotateSpeed = 2.7f;
            outlineColor = Color.valueOf("302626");
            engineSize = 3f;
            engineOffset = 0.8f;
            range = 1f;
            speed = 1.8f;
            fogRadius = 8;
            weapons.add(new Weapon(name + "-"){{
                x = 0;
                y = 0;
                mirror = false;
                reload = 300;
                shoot.shots = 20;
                shootSound = Sounds.mud;
                shoot.shotDelay = 1;
                shootStatusDuration = 90;
                shootStatus = StatusEffects.slow;
                inaccuracy = 17f;
                velocityRnd = 0.12f;
                recoilTime = 60;
                bullet = new LiquidBulletType(Liquids.slag){{
                    damage = 18;
                    recoil = 0.4f;
                    speed = 10;
                    lifetime = 10;
                }};
                parts.add(new RegionPart("claw"){{
                    progress = PartProgress.recoil;
                    under = true;
                    mirror = true;
                    rotation = -1f;
                    moveRot = 35f;
                    moveX = -2;
                    moveY = 0.7f;
                }});
                parts.add(new RegionPart("wing"){{
                    progress = PartProgress.recoil;
                    mirror = true;
                    x = -0.6f;
                    rotation = -25f;
                    moveRot = 25f;
                    moveX = 0.6f;
                }});
            }});
        }};

        dier = new UnitType("dier"){{
            this.constructor = UnitEntity::create;
            health = 280;
            armor = 4;
            accel = 0.03f;
            drag = 0.05f;
            flying = true;
            hitSize = 10f;
            rotateSpeed = 6f;
            outlineColor = Color.valueOf("302626");
            engineSize = 3f;
            engineOffset = 1.7f;
            range = 30f;
            speed = 3.3f;
            fogRadius = 10;
            weapons.add(new Weapon(name + "-"){{
                minShootVelocity = 0.9f;
                x = 0;
                y = 4;
                top = false;
                continuous = true;
                alwaysContinuous = true;
                mirror = false;
                reload = 40;
                cooldownTime = 0;
                ejectEffect = Fx.none;
                alwaysShootWhenMoving = true;
                bullet = new ContinuousFlameBulletType(){{
                    damage = 30f;
                    shootSound = SoundsAlt.fireinthehole;
                    length = 40;
                    width = 3;
                    knockback = 1f;
                    pierceCap = 4;
                    recoil = -0.15f;
                    hitColor = flareColor = Color.valueOf("ffd8d1");

                    colors = new Color[]{Color.valueOf("ffd8d1").a(0.55f), Color.valueOf("ffd8d1")};
                }};
                parts.add(new RegionPart("tail"){{
                    progress = PartProgress.recoil;
                    moveRot = -40;
                    moveY = 2.5f;
                    layerOffset = -0.01f;
                    mirror = true;
                    rotation = 0.4f;
                    y = -4;
                }});
                parts.add(new RegionPart("claw"){{
                    progress = PartProgress.recoil;
                    moveX = -1;
                    moveY = -1;
                    moveRot = 6;
                    layerOffset = -0.01f;
                    mirror = true;
                    rotation = -0.4f;
                    y = -4;
                }});
            }});
        }};

        dong = new UnitType("dong"){{
            this.constructor = UnitEntity::create;
            health = 630;
            armor = 5;
            accel = 0.02f;
            drag = 0.04f;
            flying = true;
            lowAltitude = true;
            hitSize = 19f;
            rotateSpeed = 1.7f;
            outlineColor = Color.valueOf("302626");
            engineSize = 4.5f;
            engineOffset = 7f;
            range = 120f;
            speed = 1.9f;
            fogRadius = 17;
            setEnginesMirror(
                    new UnitEngine(5.6f, -11f, 3f, 225f)
            );
            weapons.add(new Weapon(name + "-blast"){{
                x = -12.2f;
                reload = 90;
                recoilTime = 60;
                shootSound = SoundsAlt.artileryShoot;
                shake = 1.6f;
                layerOffset = -0.01f;
                rotate = true;
                rotateSpeed = 4;
                rotationLimit = 45f;
                bullet = new BasicBulletType(5, 12){{
                    lifetime = 30;
                    hitSound = Sounds.shotgun;
                    width = 12;
                    height = 19;
                    frontColor = Color.valueOf("ffd8d1");
                    backColor = Color.valueOf("e8998c");
                    fragBullets = 3;
                    fragRandomSpread = 0;
                    fragSpread = 30f;
                    fragOnHit = true;
                    fragBullet = new ShrapnelBulletType(){{
                        length = 30f;
                        width = 12f;
                        damage = 20;
                        serrationSpacing = 4;
                        hitShake = 3;
                        toColor = Color.valueOf("ffd8d1");
                    }};
                }};
                parts.add(
                    new RegionPart("-gun"){{
                        progress = PartProgress.recoil;
                        moveY = -2f;
                    }}
                );
            }});
            weapons.addAll(
                new Weapon(name + "-pew"){{
                    y = 5f;
                    x = 4;
                    top = true;
                    rotate = true;
                    rotateSpeed = 4.2f;
                    alternate = false;
                    reload = 30;
                    bullet = new BasicBulletType(3, 5){{
                        lifetime = 50;
                        frontColor = Color.valueOf("ffd8d1");
                        backColor = Color.valueOf("e8998c");
                        hitEffect = despawnEffect = upunitFx.dongHit;
                    }};
                }}, new Weapon(name + "-pew"){{
                    y = -6f;
                    x = 7f;
                    top = true;
                    rotate = true;
                    rotateSpeed = 3.6f;
                    alternate = false;
                    reload = 45;
                    bullet = new BasicBulletType(3, 5){{
                        lifetime = 50;
                        frontColor = Color.valueOf("ffd8d1");
                        backColor = Color.valueOf("e8998c");
                        hitEffect = despawnEffect = upunitFx.dongHit;
                    }};
                }}
            );
        }};

        invader = new UnitType("Invader"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            engineColor = trailColor = Color.valueOf("8deebb");
            flying = true;
            rotateMoveFirst = true;
            accel = 0.09f;
            drag = 0.05f;
            faceTarget = false;
            circleTarget = true;
            targetAir = false;
            targetGround = true;
            speed = 1.4f;
            armor = 2f;
            rotateSpeed = 3.8f;
            hitSize = 8f;
            health = 270;
            range = 10f;
            targetFlags = new BlockFlag[]{BlockFlag.reactor, BlockFlag.generator, BlockFlag.battery, null};

            engineSize = 3f;
            engineOffset = 2f;
            weapons.add(
                new Weapon(){{
                    x = 0;
                    minShootVelocity = 0.5f;
                    shootY = 0f;
                    reload = 8f;
                    shootCone = 180f;
                    ejectEffect = Fx.none;
                    inaccuracy = 30f;
                    ignoreRotation = true;
                    shootSound = Sounds.none;
                    bullet = new BombBulletType(19f, 16f){{
                        frontColor = Color.valueOf("8deebb");
                        backColor = Color.valueOf("5ec1bf");
                        width = 6f;
                        height = 10f;
                        hitEffect = enemyunitFx.bombEx;
                        shootEffect = Fx.none;
                        smokeEffect = Fx.none;

                        status = StatusEffects.blasted;
                        statusDuration = 60f;
                    }};
                }}
            );
        }};

        rush = new UnitType("rush"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            engineColor = trailColor = Color.valueOf("8deebb");
            flying = true;
            accel = 0.06f;
            drag = 0.035f;
            faceTarget = true;
            circleTarget = false;
            targetAir = true;
            targetGround = true;
            speed = 1.8f;
            armor = 1f;
            rotateSpeed = 2.2f;
            hitSize = 10f;
            health = 190;
            range = 90f;

            engineSize = 3f;
            engineOffset = 4.5f;
            weapons.add(new Weapon(){{
                x = 2;
                reload = 90f;
                rotate = false;
                baseRotation = -120;
                inaccuracy = 18f;
                shootCone = 180f;
                alternate = false;
                shoot.shots = 4;
                shoot.shotDelay = 10;
                bullet = new BasicBulletType(4, 11){{
                    width = height = 12;
                    sprite = "nscape-ring";
                    frontColor = Color.valueOf("8deebb");
                    backColor = trailColor = Color.valueOf("5ec1bf");
                    despawnEffect = enemyunitFx.smallCyanHit;
                    hitEffect = enemyunitFx.cyanHit;
                    trailLength = 6;
                    trailWidth = 0.9f;
                    lifetime = 34;
                    velocityRnd = 0.05f;
                    homingPower = 0.18f;
                    homingRange = 120;
                }};
            }});
        }};

        barier = new UnitType("barier"){{
            outlineColor = Color.valueOf("2d2630");
            this.constructor = MechUnit::create;

            health = 140f;
            speed = 0.65f;
            armor = 5f;
            hitSize = 8f;
            rotateSpeed = 2f;
            fogRadius = 10;

            abilities.add(new ForceFieldAbility(22, 20/60f, 140, 4*60));
        }};

        force = new UnitType("force"){{
            outlineColor = Color.valueOf("2d2630");
            this.constructor = LegsUnit::create;
            hovering = true;
            shadowElevation = 0.4f;

            legCount = 6;
            legLength = 8f;
            legBaseOffset = 1f;
            legMoveSpace = 1.9f;
            legForwardScl = 0.7f;
            legSpeed = 0.05f;
            legExtension = 3f;

            health = 240f;
            speed = 0.75f;
            armor = 3f;
            hitSize = 12f;
            rotateSpeed = 4f;
            fogRadius = 10;
            abilities.add(new StoreDamageAbility(){{
                maxStored = 300;
                mod = 0.85f;
                healReload = 30;
                heal = 5;
                shield = 50;
                shieldRange = 32;
            }});
            weapons.add(new Weapon(name + "-plasma"){{
                y = 0;
                x = -5;
                top = false;
                shootSound = Sounds.bolt;
                reload = 15;
                rotationLimit = 20;
                rotate = true;
                bullet = new LaserBoltBulletType(6f, 8) {{
                    lifetime = 16f;
                    hittable = false;
                    backColor = Color.valueOf("8deebb");
                    smokeEffect = hitEffect = despawnEffect = enemyunitFx.smallCyanHit;
                }};
            }});
        }};

        harpoon = new UnitType("harpoon"){{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            engineColor = trailColor = Color.valueOf("8deebb");
            flying = true;
            accel = 0.12f;
            drag = 0.07f;
            faceTarget = true;
            circleTarget = false;
            targetAir = false;
            targetGround = true;
            speed = 1.1f;
            armor = 4f;
            rotateSpeed = 1.2f;
            hitSize = 20f;
            health = 1210;
            range = 120f;
            targetFlags = new BlockFlag[]{BlockFlag.repair, BlockFlag.turret, null};

            engineSize = 6f;
            engineOffset = 7.5f;
            weapons.add(new Weapon(name + "-bomb"){{
                reload = 60;
                x = -7.3f;
                recoilTime = 90;
                shake = 1f;
                y = 1.3f;
                layerOffset = -0.001f;
                rotate = true;
                rotationLimit = 30;
                bullet = new ArtilleryBulletType(9, 1){{
                    height = width = 16;
                    splashDamage = 62;
                    splashDamageRadius = 20;
                    hitEffect = despawnEffect = enemyunitFx.bombHit;
                    hitShake = 2f;
                    recoil = 0.6f;
                    lifetime = 15;
                    frontColor = Color.valueOf("8deebb");
                    backColor = trailColor = Color.valueOf("5ec1bf");
                }};
            }});
        }};

        archangel = new UnitType("archangel"){{
            this.aiController = FarAttackAI::new;
            outlineColor = Color.valueOf("2d2630");
            this.constructor = LegsUnit::create;
            hovering = true;
            shadowElevation = 0.4f;

            faceTarget = true;
            targetAir = false;
            targetGround = true;
            rotateMoveFirst = true;
            speed = 0.4f;
            armor = -3f;
            rotateSpeed = 0.9f;
            hitSize = 20f;
            health = 650;
            range = 240f;
            legCount = 4;
            legLength = 13f;
            legBaseOffset = 4f;
            legMoveSpace = 1.9f;
            legForwardScl = 0.7f;
            legSpeed = 0.05f;
            legExtension = 3f;
            weapons.add(new Weapon("archangel-weapon"){{
                top = false;
                rotate = false;
                mirror = false;
                reload = 880;
                x = y = 0;
                shake = 5;
                shoot.shotDelay = 30;
                shoot.shots = 3;
                shootSound = Sounds.artillery;
                bullet = new ArtilleryBulletType(){{
                    damage = 4;
                    splashDamage = 23;
                    splashDamageRadius = 32;
                    splashDamagePierce = true;
                    width = height = 12;
                    speed = 2;
                    lifetime = 130;
                    recoil = 1.5f;
                    frontColor = Color.valueOf("8deebb");
                    backColor = trailColor = Color.valueOf("5ec1bf");
                    hitEffect = despawnEffect = enemyunitFx.artHit;
                }};
            }});
            parts.add(new RegionPart("-shells"){{
                layerOffset = -0.01f;
                under = true;
                mirror = true;
                progress = PartProgress.recoil;
                moveY = -3;
            }});
        }};

        shield = new UnitType("shield"){{
            this.aiController = FarAttackAI::new;
            outlineColor = Color.valueOf("2d2630");
            this.constructor = LegsUnit::create;
            hovering = true;
            shadowElevation = 0.3f;

            speed = 1.2f;
            armor = 7f;
            rotateSpeed = 2.4f;
            hitSize = 14f;
            health = 430;
            range = 30f;
            legContinuousMove = lockLegBase = true;
            legExtension = 4;
            legCount = 6;
            legLength = 11f;
            legBaseOffset = 4f;
            legMoveSpace = 2;
            weapons.add(new RepairBeamWeapon(""){{
                x = 0;
                y = -2;
                mirror = false;
                shootY = 0;
                repairSpeed = 0.3f;
                shootSound = Sounds.sap;
                bullet = new BulletType() {{
                    maxRange = 80f;
                }};
            }});
            weapons.add(new Weapon(){{
                rotate = false;
                x = 0;
                y = -2;
                mirror = false;
                shootY = 0;
                reload = 5;
                bullet = new SapBulletType(){{
                    damage = 3;
                    lifetime = 10;
                    healAmount = 1;
                    color = Color.valueOf("8deebb");
                    length = 60;
                    smokeEffect = shootEffect = Fx.none;
                }};
            }});
        }};

        pabbu = new UnitType("PABBU") {{
            this.constructor = UnitEntity::create;
            outlineColor = Color.valueOf("2d2630");

            deathExplosionEffect = enemyunitFx.PABBUdeathFx;
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
            health = 1450;
            range = 100f;
            lowAltitude = true;
            weapons.add(new Weapon(name + "-cannon") {{
                reload = 240f;
                mirror = false;
                x = 0;
                shootSound = Sounds.artillery;
                rotate = true;
                shake = 2f;
                shootY = 6;
                rotationLimit = 15;
                rotateSpeed = 0.7f;
                shoot.shots = 4;
                shoot.shotDelay = 10f;
                inaccuracy = 10;
                recoils = 2;
                bullet = new BasicBulletType(6, 42) {{
                    lifetime = 35;
                    width = 9;
                    height = 14;
                    fragRandomSpread = 90;
                    hitEffect = despawnEffect = enemyunitFx.PABBUcannonHit;
                    fragBullets = 3;
                    backColor = Color.valueOf("8deebb");
                    fragBullet = new LaserBulletType(16) {{
                        colors = new Color[]{Color.valueOf("8deebb"), Color.white};
                        lifetime = 20;
                        width = 7;
                        length = 35;
                        sideLength = 0;
                        sideWidth = 0;
                    }};
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
                parts.addAll(
                    new HaloPart(){{ //наружа
                        color = Color.valueOf("8deebb");
                        y = -8;
                        progress = PartProgress.warmup;
                        tri = true;
                        triLength = 0;
                        triLengthTo = 2.2f;
                        sides = 3;
                        haloRadius = 3;
                        haloRadiusTo = 4;
                        haloRotateSpeed = 1;
                        radius = 3;
                        layer = Layer.effect;
                    }}, new HaloPart(){{ //внутрь
                        color = Color.valueOf("8deebb");
                        y = -8;
                        progress = PartProgress.warmup;
                        shapeRotation = 180;
                        tri = true;
                        triLength = 0;
                        triLengthTo = 3f;
                        sides = 3;
                        haloRadius = 3;
                        haloRadiusTo = 4;
                        haloRotateSpeed = -2.2f;
                        radius = 3.5f;
                        layer = Layer.effect;
                    }}, new ShapePart(){{
                        color = Color.valueOf("8deebb");
                        y = -8;
                        progress = PartProgress.warmup;
                        circle = true;
                        hollow = true;
                        stroke = 0;
                        strokeTo = 1;
                        radius = 3;
                        radiusTo = 4;
                        layer = Layer.effect;
                    }}
                );
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
                bullet = new LaserBoltBulletType(5.2f, 9) {{
                    lifetime = 20f;
                    hittable = false;
                    healPercent = 2f;
                    backColor = Color.valueOf("8deebb");
                    smokeEffect = hitEffect = despawnEffect = enemyunitFx.smallCyanHit;
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
                    backColor = Color.valueOf("8deebb");
                    smokeEffect = hitEffect = despawnEffect = enemyunitFx.smallCyanHit;
                    collidesTeam = true;
                }};
            }}, new Weapon(name + "-minigun") {{
                reload = 15f;
                x = -6.2f;
                y = 6.2f;
                recoil = 2f;
                rotate = true;
                shootSound = Sounds.laser;
                bullet = new LaserBulletType(30) {{
                    colors = new Color[]{Color.valueOf("8deebb"), Color.white};
                    lifetime = 10;
                    width = 7;
                    length = 50;
                    sideLength = 9;
                    sideWidth = 2;
                }};
            }});
            parts.add(
                new ShapePart(){{
                    color = Color.valueOf("5ec1bf");
                    hollow = true;
                    stroke = 1;
                    y = -4;
                    sides = 4;
                    radius = 16;
                    rotateSpeed = 0.75f;
                    layer = Layer.flyingUnitLow - 1;
                }}, new ShapePart(){{
                    color = Color.valueOf("5ec1bf");
                    hollow = true;
                    stroke = 1;
                    y = -4;
                    sides = 4;
                    radius = 14;
                    rotateSpeed = -0.5f;
                    layer = Layer.flyingUnitLow - 1;
                }}
            );

            abilities.add(new RegenAbility() {{
                amount = 0.2f;
            }});
            abilities.add(new BerserkAbility() {{
                accMultiplier = 0.5f;
            }});

            engineSize = 9f;
            engineOffset = 0f;
            setEnginesMirror(
                new UnitEngine(7.5f, 7, 4.5f, 45f){{
                    engineColor = trailColor = Color.valueOf("8deebb");
                }}
            );
        }};

        observer = new UnitType("Observer") {{
            this.constructor = UnitEntity::create;
            aiController = BuilderAI::new;
            isEnemy = false;

            lowAltitude = true;
            flying = true;
            mineSpeed = 10f;
            mineTier = 3;
            mineWalls = true;
            buildSpeed = 1.5f;
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
            buildSpeed = 2f;
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
                bullet = new MissileBulletType(4, 11) {{
                    lifetime = 30;
                    status = NSstatus.overCharged;
                    statusDuration = 120f;
                    recoil = 0.01f;
                    healAmount = 15;
                    collidesTeam = true;
                    pierceCap = 3;
                    buildingDamageMultiplier = 0.01f;
                    backColor = Color.valueOf("ffd37f");
                    frontColor = Color.white;
                }};
            }});
            abilities.add(new ForceFieldAbility(40, 0.3f, 150, 240));
        }};
    }
}
