package nightscape.content.blocks;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;
import nightscape.content.NSattribute;
import nightscape.content.NSitems;
import nightscape.content.effects.blockFx;
import nightscape.world.block.environment.DamageTallBlock;
import nightscape.world.block.environment.Geyser;

import static mindustry.content.Items.sand;
import static mindustry.type.ItemStack.with;

public class NSBenvironment {
    public static Block

    //burned
    ashSwamp, swampWall,
    ash, ashWall, ashVent,
    //naturit attributes
    natAsh, natAshWall, natweed, natTree,
    //redLands
    slate, slateWall, slateBoulder, redStone, redWall, redBoulder,
    //ice
    ice, iceWall, icedStone, iceShank,
    rainStone, rainStoneWall, rainBoulder,
    coldStone,
    //purl
    purl, purlWall, purlGeyser,

    //ore block
    tantalumOre, zirconiumOre,

    //other
    oldTracks, tWallLargeScrap;

    public static void load(){
        //dark
        ashSwamp = new Floor("ashSwamp") {{
            variants = 3;
            playerUnmineable = true;
            statusDuration = 120f;
            speedMultiplier = 0.48f;
            cacheLayer = CacheLayer.mud;
            walkSound = Sounds.mud;
            isLiquid = true;
            walkSoundVolume = 0.08f;
            walkSoundPitchMin = 0.4f;
            walkSoundPitchMax = 0.5f;
            drownTime = 300f;
        }};

        swampWall = new StaticWall("swampWall") {{
            ashSwamp.asFloor().wall = this;
        }};

        //Ash
        ash = new Floor("ash") {{
            variants = 3;
            attributes.set(NSattribute.naturit, 0.05f);
            playerUnmineable = true;
        }};

        ashWall = new StaticWall("ashWall") {{
            ash.asFloor().wall = this;
            attributes.set(Attribute.sand, 0.5f);
            attributes.set(NSattribute.naturit, 0.1f);
        }};

        ashVent = new SteamVent("ashVent"){{
            parent = blendGroup = ash;
            attributes.set(NSattribute.ozone, 1f);
            attributes.set(NSattribute.naturit, 0.15f);
            effectColor = Color.valueOf("ffbdd4");
        }};

        //naturit ash
        natAsh = new Floor("naturitAsh") {{
            variants = 3;
            attributes.set(NSattribute.naturit, 0.5f);
            blendGroup = ash;
        }};

        natAshWall = new StaticWall("naturitAshWall") {{
            attributes.set(NSattribute.naturit, 1f);
            natAsh.asFloor().wall = this;
        }};

        natTree = new TreeBlock("naturitTree"){{
            variants = 2;
            shadowOffset = -0.75f;
            emitLight = true;
            lightRadius = 48;
            attributes.set(NSattribute.naturit, 1.5f);
            lightColor = Color.valueOf("eecd7499");
        }};

        natweed = new Seaweed("natweed"){{
            variants = 3;
            ash.asFloor().decoration = this;
            emitLight = true;
            lightRadius = 16;
            attributes.set(NSattribute.naturit, 0.5f);
            lightColor = Color.valueOf("eecd7444");
        }};

        //slate
        slate = new Floor("slate") {{
            variants = 3;
        }};

        slateWall = new StaticWall("slateWall") {{
            slate.asFloor().wall = this;
            attributes.set(Attribute.sand, 1f);
        }};

        slateBoulder = new Prop("slateBoulder"){{
            variants = 2;
            slate.asFloor().decoration = this;
        }};

        redStone = new Floor("redStone") {{
            variants = 5;
        }};

        redWall = new StaticWall("redWall") {{
            redStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 1f);
        }};

        redBoulder = new Prop("redBoulder"){{
            variants = 2;
            redStone.asFloor().decoration = this;
        }};

        //sss
        ice = new Floor("ice") {{
            variants = 3;
            attributes.set(Attribute.water, 1f);
            dragMultiplier = 0.1f;
            speedMultiplier = 0.95f;
        }};

        iceWall = new StaticWall("iceWall") {{
            ice.asFloor().wall = this;
        }};

        icedStone = new OverlayFloor("icedStone") {{
            variants = 3;
            attributes.set(Attribute.water, 0.5f);
        }};

        iceShank = new DamageTallBlock("iceShank"){{
            variants = 2;
            clipSize = 96f;
            damageRange = 13;
            damage = 12;
            shadowOffset = -1.5f;
        }};

        rainStone = new Floor("rainStone") {{
            variants = 3;
        }};

        rainStoneWall = new StaticWall("rainStoneWall") {{
            rainStone.asFloor().wall = this;
            attributes.set(Attribute.sand, 0.75f);
        }};

        rainBoulder = new Prop("rainBoulder"){{
            variants = 3;
            rainStone.asFloor().decoration = this;
        }};

        coldStone = new Floor("coldStone") {{
            variants = 3;
        }};

        //purl
        purl = new Floor("purl") {{
            variants = 6;
        }};

        purlWall = new StaticWall("purlWall"){{
           purl.asFloor().wall = this;
           mapColor = Color.valueOf("6d5869");
            attributes.set(Attribute.sand, 1.5f);
        }};

        purlGeyser = new Geyser("purlGeyser"){{
            gTime = 120 * 60;
            chargeTime = 600 * 60;
            variants = 2;
            size = 1;
            bullet = new LiquidBulletType(Liquids.slag){{
                damage = 10;
                speed = 4;
                lifetime = 20;
            }};
        }};

        //ores
        tantalumOre = new OreBlock("tantalumOre"){{
            itemDrop = NSitems.tantalum;
        }};

        zirconiumOre = new OreBlock("zirconiumOre"){{
            itemDrop = NSitems.zirconium;
        }};

        //other
        oldTracks = new Wall("old-tracks"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(NSitems.zirconium, 12, NSitems.tantalum, 12));
            variants = 3;
            health = 450;
        }};

        tWallLargeScrap = new Wall("tWall-large-scrap"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(NSitems.tantalum, 18));
            variants = 2;
            size = 2;
            health = 990;
        }};

    }
}
