package nightscape.content.blocks;

import arc.graphics.Color;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.entities.bullet.LiquidBulletType;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BuildVisibility;
import nightscape.content.NSLiquids;
import nightscape.content.NSattribute;
import nightscape.content.NSitems;
import nightscape.content.effects.blockFx;
import nightscape.world.block.environment.DamageTallBlock;
import nightscape.world.block.environment.Geyser;

import static mindustry.type.ItemStack.with;

public class NSBenvironment {
    public static Block

    //burned
    ashSwamp, swampWall,
    ash, ashWall, ashVent,
    //naturit attributes
    natAsh, natAshWall, natweed, natTree,
    //redLands
    slate, slateWall, slateBoulder, slateStone, redStone, redWall, redBoulder, redGeyser,
    //ice
    ice, iceWall, icedStone, iceShank,
    rainStone, rainStoneWall, rainBoulder,
    coldStone,
    //purl
    purl, purlWall, purlGeyser, purlBoulder, purlWeed,
    //fernum
    softRock, softRockWall, softGay,

    //ore block
    tantalumOre, zirconiumOre, vanadiumOre, tanWallOre, cyanidOre,

    // Pryma region

    // basalt
    flameBasalt, flameBasaltWall, cyanBasaltWall, flameBasaltVent, flameBasaltBoulder,
    // granit
    flameGranit, flameGranitWall, cyanGranitWall, flameGranitBoulder, cyanCrystal,
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
            variants = 5;
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
            variants = 3;
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
            itemDrop = Items.sand;
        }};

        slateBoulder = new Prop("slateBoulder"){{
            variants = 2;
            slate.asFloor().decoration = this;
        }};

        slateStone = new TallBlock("slateStone"){{
            size = 1;
            variants = 3;
            shadowOffset = 0.2f;
            customShadow = true;
            itemDrop = Items.sand;
        }};

        redStone = new Floor("redStone") {{
            variants = 5;
        }};

        redWall = new StaticWall("redWall") {{
            redStone.asFloor().wall = this;
            itemDrop = Items.sand;
        }};

        redBoulder = new Prop("redBoulder"){{
            variants = 2;
            redStone.asFloor().decoration = this;
        }};

        redGeyser = new Geyser("redGeyser"){{
            gTime = 150 * 60;
            chargeTime = 500 * 60;
            variants = 4;
            size = 1;
            bullet = new LiquidBulletType(Liquids.slag){{
                damage = 15;
                speed = 6;
                lifetime = 15;
            }};
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
            attributes.set(NSattribute.fundum, 0.05f);
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
            attributes.set(NSattribute.fundum, 0.05f);
        }};

        //purl
        purl = new Floor("purl") {{
            variants = 6;
        }};

        purlWall = new StaticTree("purlWall"){{
            variants = 4;
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

        purlBoulder = new TallBlock("purlBoulder"){{
            size = 1;
            variants = 2;
            shadowOffset = 0.2f;
            customShadow = true;
        }};

        purlWeed = new Seaweed("purlWeed"){{
            variants = 3;
            purl.asFloor().decoration = this;
            offset = 0.1f;
        }};
        flameBasalt = new Floor("flameBasalt") {{
            variants = 4;
        }};

        flameBasaltWall = new StaticWall("flameBasaltWall"){{
            flameBasalt.asFloor().wall = this;
            itemDrop = Items.sand;
        }};

        cyanBasaltWall = new StaticWall("cyanBasaltWall"){{
            variants = 3;
            itemDrop = NSitems.cyanid;
        }};

        flameBasaltVent = new SteamVent("flameBasaltVent"){{
            parent = blendGroup = flameBasalt;
            attributes.set(NSattribute.ozone, 1f);
            attributes.set(NSattribute.naturit, 0.10f);
            effectColor = Color.valueOf("ffbdd4");
        }};

        flameBasaltBoulder = new Prop("flameBasaltBoulder"){{
            variants = 2;
            flameBasalt.asFloor().decoration = this;
        }};

        flameGranit = new Floor("flameGranit") {{
            variants = 4;
            attributes.set(Attribute.sand, 1f);
        }};

        flameGranitWall = new StaticWall("flameGranitWall"){{
            flameGranit.asFloor().wall = this;
        }};

        cyanGranitWall = new StaticWall("cyanGranitWall"){{
            variants = 3;
            attributes.set(Attribute.sand, 1f);
            itemDrop = NSitems.cyanid;
        }};

        flameGranitBoulder = new Prop("flameGranitBoulder"){{
            variants = 2;
            flameGranit.asFloor().decoration = this;
        }};

        cyanCrystal = new DamageTallBlock("cyanCrystal"){{
            itemDrop = NSitems.cyanid;
            variants = 3;
            clipSize = 96f;
            damageRange = 11;
            damage = 7;
            shadowOffset = -1.5f;
        }};
        softRock = new Floor("softRock") {{
            variants = 3;
            attributes.set(NSattribute.fundum, 0.15f);
        }};

        softRockWall = new StaticWall("softWall"){{
            softRock.asFloor().wall = this;
            attributes.set(Attribute.sand, 1f);
        }};

        softGay = new Geyser("softRockGay"){{
            gTime = 150 * 60;
            chargeTime = 500 * 60;
            variants = 2;
            size = 1;
            bullet = new LiquidBulletType(NSLiquids.fernum){{
                damage = 8;
                speed = 5;
                lifetime = 10;
            }};
        }};

        //ores
        tantalumOre = new OreBlock("tantalumOre"){{
            itemDrop = NSitems.tantalum;
        }};

        zirconiumOre = new OreBlock("zirconiumOre"){{
            itemDrop = NSitems.zirconium;
        }};

        vanadiumOre = new OreBlock("vanadiumWallOre"){{
            itemDrop = NSitems.vanadium;
            wallOre = true;
        }};

        cyanidOre = new OreBlock("cyanidWallOre"){{
            itemDrop = NSitems.cyanid;
            wallOre = true;
        }};

        tanWallOre = new OreBlock("tantalumWallOre"){{
            itemDrop = NSitems.tantalum;
            wallOre = true;
        }};

        //other
        oldTracks = new Wall("old-tracks"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(NSitems.zirconium, 12, NSitems.tantalum, 12));
            variants = 3;
            health = 450;
            rotate = true;
        }};

        tWallLargeScrap = new Wall("tWall-large-scrap"){{
            requirements(Category.defense, BuildVisibility.sandboxOnly, with(NSitems.tantalum, 18));
            variants = 2;
            size = 2;
            health = 990;
        }};
    }
}
