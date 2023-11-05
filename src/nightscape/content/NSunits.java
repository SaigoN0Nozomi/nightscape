package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.entities.bullet.*;
import mindustry.entities.part.RegionPart;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import mindustry.type.unit.TankUnitType;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class NSunits {
    public static UnitType
    //core
    observer,

    //static
    point, vector, planum,

    //flash
    procursus, radius;

    public static void load(){
        point = new UnitType("point"){{
            this.constructor = MechUnit::create;

            mechFrontSway = 0.55f;
            health = 175f;
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

                bullet = new BasicBulletType(6, 12){{
                    width = 6f;
                    height = 9f;
                    lifetime = 15f;
                    pierce = false;
                    pierceBuilding = false;
                    shootEffect = new Effect(17f, e -> {
                        color(Color.valueOf("d297e1"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 4, e.fin() * 4f, e.rotation, 12f, (x, y) -> {
                            Fill.circle(e.x + x, e.y + y, e.fout() * 1.2f + 0.1f);
                        });
                    });
                    hitEffect = new Effect(25f, e -> {
                        color(Color.valueOf("d297e1"), Color.lightGray, e.fout());

                        randLenVectors(e.id, 6, e.fin() * 16f, e.rotation, 60f, (x, y) -> {
                            Fill.square(e.x + x, e.y + y, e.fout() * 2.3f + 0.2f);
                        });
                    });
                    despawnEffect = new Effect(25f, e -> {
                        color(Color.valueOf("d297e1"), Color.lightGray, e.fout());

                        randLenVectors(e.id, 4, e.fin() * 11f, e.rotation, 90f, (x, y) -> {
                            Fill.square(e.x + x, e.y + y, e.fout() * 1.1f + 0.1f);
                        });
                    });
                    backColor = frontColor = Color.valueOf("d297e1");
                }};
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        vector = new UnitType("vector"){{
            this.constructor = MechUnit::create;

            health = 840f;
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
                    backColor = frontColor = Color.valueOf("d297e1");
                    pierce = true;
                    pierceCap = 3;
                    pierceBuilding = true;
                    despawnEffect = hitEffect = Fx.none;
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
                bullet = new LaserBulletType(105){{
                    layerOffset = -0.5f;
                    lifetime = 25;
                    width = 17f;
                    sideAngle = 45f;
                    sideWidth = 2f;
                    sideLength = 24f;
                    length = 120;
                    colors = new Color[]{Color.valueOf("d297e1"), Color.valueOf("aa62ac")};
                }};
            }});
            health = 2540f;
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
                range = 120;
            }});
            outlineColor = Color.valueOf("4c3d4e");
        }};

        procursus = new TankUnitType("procursus"){{
            this.constructor = TankUnit::create;

            health = 180f;
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
                        ammoMultiplier = 3;
                        hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                        frontColor = Color.white;
                        despawnEffect = Fx.none;
                        trailWidth = 1.2f;
                        trailLength = 4;
                        smokeEffect = new Effect(17f, e -> {
                            color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                            randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                                Fill.square(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                            });
                        });
                    }};
                }});
            }};

        radius = new TankUnitType("radius"){{
            this.constructor = TankUnit::create;

            health = 670f;
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
                    ammoMultiplier = 3;
                    hitColor = backColor = trailColor = Color.valueOf("d8f3f4");
                    frontColor = Color.white;
                    despawnEffect = Fx.none;
                    trailWidth = 1.5f;
                    trailLength = 7;
                    pierceCap = 4;
                    pierceBuilding = true;
                    smokeEffect = new Effect(17f, e -> {
                        color(Color.valueOf("d8f3f4"), Color.lightGray, Color.gray, e.fin());

                        randLenVectors(e.id, 8, e.finpow() * 13f, e.rotation, 25f, (x, y) -> {
                            Fill.circle(e.x + x, e.y +y, e.fout() * 2f + 0.2f);
                        });
                    });
                    bulletInterval = 4;
                    intervalSpread = 45;
                    intervalRandomSpread = 45;
                    intervalBullet = fragBullet = new BasicBulletType(3f, 10f){{
                        width = 9f;
                        height = 9f;
                        lifetime = 20f;
                        ammoMultiplier = 3;
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
                continuous = false;
                reload = 30f;
                x = 0;
                shootSound = Sounds.missile;
                soundPitchMax = 1.5f;
                soundPitchMin = 1.2f;

                bullet = new MissileBulletType(3, 10){{
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
