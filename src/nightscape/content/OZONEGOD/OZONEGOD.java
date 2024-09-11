package nightscape.content.OZONEGOD;

import arc.graphics.Color;
import arc.math.Interp;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ContinuousLaserBulletType;
import mindustry.entities.bullet.LightningBulletType;
import mindustry.entities.part.HaloPart;
import mindustry.entities.part.ShapePart;
import mindustry.gen.PayloadUnit;
import mindustry.gen.Sounds;
import mindustry.gen.UnitEntity;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.type.Weapon;
import nightscape.content.NSstatus;
import nightscape.content.effects.OZONEGODFX;
import nightscape.entity.abilities.GODAbility;
import nightscape.entity.ai.AdvancedDefenderAI;
import nightscape.world.meta.SoundsAlt;

import static mindustry.Vars.tilePayload;

public class OZONEGOD {
    public static UnitType
    OZONEGOD;
    public static void load(){
        OZONEGOD = new UnitType("ozonegod"){{
            this.constructor = UnitEntity::create;
            outlines = false;
            faceTarget = false;

            armor = 9999f;
            health = 999999;
            speed = 0.6f;
            rotateSpeed = 2f;
            lowAltitude = true;
            range = 500;
            fogRadius = 999;

            flying = true;
            engineSize = 0;
            hitSize = 30f;
            weapons.add(
                new Weapon(){{
                    rotate = true;
                    rotateSpeed = 10;
                    reload = 2;
                    velocityRnd = 0.3f;
                    inaccuracy = 45;
                    x = 30;
                    shootSound = Sounds.none;
                    y = 30;
                    shootX = 0;
                    bullet = new BasicBulletType(){{
                        shootEffect = smokeEffect = Fx.none;
                        speed = 11;
                        lifetime = 300;
                        damage = 40;
                        trailLength = 24;
                        trailWidth = 3;
                        width = 7;
                        backColor = trailColor = Color.valueOf("fc81dd");
                        frontColor = Color.valueOf("ffbdd4");
                        homingRange = 300;
                        homingPower = 0.05f;
                    }};
                    parts.addAll(
                        new ShapePart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);;
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            hollow = true;
                            sides = 5;
                            y = -1;
                            radius = 7;
                            stroke = 0;
                            strokeTo = 1;
                            rotateSpeed = 2;
                        }}, new ShapePart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);;
                            color = Color.valueOf("ffbdd4");
                            layer = Layer.effect;
                            hollow = false;
                            sides = 5;
                            y = -1;
                            radius = 0;
                            radiusTo = 4.5f;
                            rotateSpeed = 2;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRadius = 6.5f;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRotation = -60;
                            haloRadius = 6.5f;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRotation = 60;
                            haloRadius = 6.5f;
                        }}
                    );
                }}
            );
            weapons.add(new Weapon(){{
                rotate = true;
                rotateSpeed = 10;
                reload = 20;
                velocityRnd = 0.3f;
                inaccuracy = 45;
                shoot.shots = 10;
                shootSound = SoundsAlt.nya;
                x = 30;
                y = -30;
                shootX = 0;
                bullet = new BasicBulletType(){{
                    shootEffect = smokeEffect = Fx.none;
                    speed = 11;
                    lifetime = 300;
                    damage = 40;
                    trailLength = 24;
                    trailWidth = 3;
                    width = 7;
                    backColor = trailColor = Color.valueOf("fc81dd");
                    frontColor = Color.valueOf("ffbdd4");
                    homingRange = 300;
                    homingPower = 0.05f;
                }};
                parts.addAll(new ShapePart(){{
                    progress = PartProgress.warmup.curve(Interp.pow2In);;
                    color = Color.valueOf("fc81dd");
                    layer = Layer.effect;
                    hollow = true;
                    sides = 5;
                    y = -1;
                    radius = 7;
                    stroke = 0;
                    strokeTo = 1;
                    rotateSpeed = 2;
                }}, new ShapePart(){{
                    progress = PartProgress.warmup.curve(Interp.pow2In);;
                    color = Color.valueOf("ffbdd4");
                    layer = Layer.effect;
                    hollow = false;
                    sides = 5;
                    y = -1;
                    radius = 0;
                    radiusTo = 4.5f;
                    rotateSpeed = 2;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup.curve(Interp.pow2In);
                    color = Color.valueOf("fc81dd");
                    layer = Layer.effect;
                    shapes = 1;
                    radius = 0;
                    radiusTo = 3;
                    haloRadius = 6.5f;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup.curve(Interp.pow2In);
                    color = Color.valueOf("fc81dd");
                    layer = Layer.effect;
                    shapes = 1;
                    radius = 0;
                    radiusTo = 3;
                    haloRotation = -60;
                    haloRadius = 6.5f;
                }}, new HaloPart(){{
                    progress = PartProgress.warmup.curve(Interp.pow2In);
                    color = Color.valueOf("fc81dd");
                    layer = Layer.effect;
                    shapes = 1;
                    radius = 0;
                    radiusTo = 3;
                    haloRotation = 60;
                    haloRadius = 6.5f;
                }});
            }});
            weapons.add(new Weapon(){{
                rotate = true;
                rotateSpeed = 2;
                x = 0;
                y = -40;
                mirror = false;
                reload = 90;
                bullet = new ContinuousLaserBulletType(){{
                    width = 5f;
                    damage = 80f;
                    length = 7000f;
                    drawSize = 420f;
                    lifetime = 90f;
                    shake = 2f;
                    smokeEffect = OZONEGODFX.laserSpawn;

                    colors = new Color[]{Color.valueOf("fc81dd").a(0.4f), Color.pink, Color.white};
                }};
            }});
            weapons.add(new Weapon(){{
                rotate = true;
                rotateSpeed = 10;
                x = 40;
                y = 0;
                shootSound = SoundsAlt.uwu;
                reload = 60;
                inaccuracy = 360;
                shoot.shots = 16;
                bullet = new LightningBulletType(){{
                    damage = 30;
                    lightningColor = hitColor = Color.pink;
                    lightningLength = 900;
                    lightningLengthRand = 100;
                    intervalBullets = 1;
                    lifetime = 90;
                }};
                parts.addAll(
                        new ShapePart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);;
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            hollow = true;
                            sides = 5;
                            y = -1;
                            radius = 7;
                            stroke = 0;
                            strokeTo = 1;
                            rotateSpeed = 2;
                        }}, new ShapePart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);;
                            color = Color.valueOf("ffbdd4");
                            layer = Layer.effect;
                            hollow = false;
                            sides = 5;
                            y = -1;
                            radius = 0;
                            radiusTo = 4.5f;
                            rotateSpeed = 2;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRadius = 6.5f;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRotation = -60;
                            haloRadius = 6.5f;
                        }}, new HaloPart(){{
                            progress = PartProgress.warmup.curve(Interp.pow2In);
                            color = Color.valueOf("fc81dd");
                            layer = Layer.effect;
                            shapes = 1;
                            radius = 0;
                            radiusTo = 3;
                            haloRotation = 60;
                            haloRadius = 6.5f;
                        }}
                );
            }});
            abilities.add(new GODAbility());
        }};
    }
}
