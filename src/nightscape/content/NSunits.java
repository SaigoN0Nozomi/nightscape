package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.math.geom.Rect;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.part.RegionPart;
import mindustry.gen.MechUnit;
import mindustry.gen.Sounds;
import mindustry.gen.TankUnit;
import mindustry.gen.UnitEntity;
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
    point, vector,

    //flash
    procursus;

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

            health = 360f;
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
                    width = 8f;
                    height = 8f;
                    recoil = 1f;
                    trailWidth = 3;
                    trailLength = 7;
                    backColor = frontColor = trailColor = Color.valueOf("d297e1");
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
                            width = 2;
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

        procursus = new TankUnitType("procursus"){{
            this.constructor = TankUnit::create;

            health = 180f;
            armor = 4f;
            hitSize = 13f;
            rotateSpeed = 2f;
            speed = 1.2f;
            flying = false;
            targetAir = false;
            treadPullOffset = 3;
            treadRects = new Rect[]{new Rect(11 - 32f, 16 - 32f, 14, 44)};
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
                reload = 90f;
                x = 0;
                shootSound = Sounds.laser;
                soundPitchMax = 1.5f;
                soundPitchMin = 1.2f;

                bullet = new LaserBulletType(){{
                    damage = 20f;
                    status = NSstatus.overCharged;
                    statusDuration = 10f;
                    recoil = 0.05f;
                    sideAngle = 90f;
                    sideWidth = 1f;
                    sideLength = 20f;
                    healPercent = 5f;
                    collidesTeam = true;
                    length = 90f;
                    pierceCap = 2;
                    buildingDamageMultiplier = 0.01f;
                    colors = new Color[]{Color.valueOf("ffd37f").a(0.4f), Color.valueOf("ffd37f"), Color.white};
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
