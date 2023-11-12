package nightscape.content;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.part.HoverPart;
import mindustry.entities.part.RegionPart;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.TankUnitType;
import nightscape.content.effects.unitFx;

public class NSunits {
    public static UnitType
    //core
    observer,

    //static
    point, vector, planum,

    //flash
    procursus, radius, fluor,

    //K.YS.N.
    gutta, pluvia;

    public static void load(){
        point = new UnitType("point"){{
            this.constructor = MechUnit::create;

            mechFrontSway = 0.55f;
            health = 210f;
            speed = 0.4f;
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

            health = 740f;
            mechFrontSway = 0.55f;
            speed = 0.38f;
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
                soundPitchMin = 0.65f;
                shoot.firstShotDelay = 25f;
                bullet = new LaserBulletType(105){{
                    chargeEffect = unitFx.planumCharge;
                    shootEffect = unitFx.planumSmoke;
                    shootStatus = StatusEffects.unmoving;
                    shootStatusDuration = 45f;
                    layerOffset = -0.5f;
                    lifetime = 25;
                    width = 17f;
                    continuous = false;
                    sideAngle = 45f;
                    sideWidth = 2f;
                    sideLength = 24f;
                    length = 120;
                    colors = new Color[]{Color.valueOf("d297e1"), Color.valueOf("aa62ac")};
                }};
            }});
            health = 2300f;
            speed = 0.35f;
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
                    despawnEffect = Fx.none;
                    trailWidth = 1.5f;
                    trailLength = 7;
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
                reload = 70f;
                rotate = true;
                rotateSpeed = 4;

                controllable = false;
                autoTarget = true;

                recoil = 1f;
                recoilTime = 20f;
                shootSound = Sounds.shootAlt;
                x = -10;
                y = -3;
                shoot.shots = 5;
                shoot.shotDelay = 4;
                bullet = new BasicBulletType(7f, 7f){{
                    width = 7f;
                    height = 12f;
                    lifetime = 20f;
                    backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    hitEffect = despawnEffect = unitFx.blueHit;
                    trailWidth = 1.2f;
                    trailLength = 4;
                }};
            }});
            weapons.add(new Weapon(name + "-rocket"){{
                mirror = false;
                reload = 500f;
                rotate = true;
                rotateSpeed = 1;

                inaccuracy = 20f;
                recoil = 1f;
                recoilTime = 60f;
                shootSound = Sounds.missileSmall;
                x = 0;
                y = 4;
                shoot.shots = 24;
                shoot.shotDelay = 4;
                xRand = 2;
                bullet = new MissileBulletType(3f, 20f){{
                    width = 7f;
                    height = 12f;
                    lifetime = 66.5f;
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

        gutta = new UnitType("gutta"){{
            this.constructor = ElevationMoveUnit::create;

            hovering = true;
            useEngineElevation = false;
            drag = 0.06f;
            accel = 0.14f;
            shadowElevation = 0.1f;
            targetAir = false;
            targetGround = true;
            speed = 1.6f;
            rotateSpeed = 7f;
            hitSize = 8f;
            health = 230;
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
                    color = Color.valueOf("d297e1");
                }});
            }

            engineSize = 2.4f;
            engineOffset = 4.5f;
            engineColor = Color.valueOf("d297e1");
            weapons.add(new Weapon(){{
                continuous = true;
                mirror = false;
                reload = 20;
                x = 0;
                bullet = new LightningBulletType(){{
                    damage = 5;
                    collidesAir = false;
                    lightningColor = Color.valueOf("d297e1");
                    lightningLength = 5;
                    lightningAngle = 5;
                    lightningLengthRand = 2;
                    hitEffect = Fx.none;
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
            engineColor = Color.valueOf("d297e1");
            weapons.add(new Weapon(name + "-gun"){{
                rotationLimit = 60f;
                top = true;
                x = -5;
                y = -1.5f;
                shootSound = Sounds.shootAlt;
                rotateSpeed = 5f;
                rotate = true;
                reload = 5;
                bullet = new BasicBulletType(7.5f, 7){{
                    width = 9f;
                    smokeEffect = unitFx.purpleSmoke;
                    hitEffect = despawnEffect = unitFx.purpleHit;
                    height = 15f;
                    lifetime = 180 / 7.5f;
                    frontColor = Color.valueOf("d297e1");
                    backColor = trailColor = Color.valueOf("aa62ac");
                    trailWidth = 0.8f;
                    trailLength = 4;
                    sprite = "nscape-arrow";
                }};
            }});
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
            fogRadius = 3f;

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
                    statusDuration = 10f;
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
