package nightscape.content;

import arc.graphics.Color;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import mindustry.world.meta.Attribute;

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
    ice, iceWall, icedStone,
    rainStone, rainStoneWall, rainBoulder,

    //ore block
    tantalumOre, electrumOre;

    public static void load(){
        //dark
        ashSwamp = new Floor("ashSwamp") {{
            variants = 3;
            playerUnmineable = true;
            statusDuration = 120f;
            speedMultiplier = 0.69f;
            cacheLayer = CacheLayer.mud;
            walkSound = Sounds.mud;
            walkSoundVolume = 0.08f;
            walkSoundPitchMin = 0.4f;
            walkSoundPitchMax = 0.5f;
        }};

        swampWall = new StaticWall("swampWall") {{
            ashSwamp.asFloor().wall = this;
        }};

        //Ash
        ash = new Floor("ash") {{
            variants = 3;
            attributes.set(NSattribute.naturit, 0.1f);
            playerUnmineable = true;
        }};

        ashWall = new StaticWall("ashWall") {{
            ash.asFloor().wall = this;
        }};

        ashVent = new SteamVent("ashVent"){{
            parent = blendGroup = ash;
            attributes.set(Attribute.steam, 1f);
            effectColor = Color.valueOf("ffbdd4");
        }};

        //naturit ash
        natAsh = new Floor("naturitAsh") {{
            variants = 3;
            attributes.set(NSattribute.naturit, 1f);
            blendGroup = ash;
        }};

        natAshWall = new StaticWall("naturitAshWall") {{
            attributes.set(NSattribute.naturit, 1f);
            natAsh.asFloor().wall = this;
        }};

        natTree = new TreeBlock("naturitTree"){{
            variants = 2;
            emitLight = true;
            lightRadius = 48;
            lightColor = Color.valueOf("eecd7499");
        }};

        natweed = new Seaweed("natweed"){{
            variants = 3;
            ash.asFloor().decoration = this;
            emitLight = true;
            lightRadius = 16;
            lightColor = Color.valueOf("eecd7444");
        }};

        //slate
        slate = new Floor("slate") {{
            variants = 3;
        }};

        slateWall = new StaticWall("slateWall") {{
            slate.asFloor().wall = this;
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
        }};

        redBoulder = new Prop("redBoulder"){{
            variants = 2;
            redStone.asFloor().decoration = this;
        }};

        //sss
        ice = new Floor("ice") {{
            variants = 3;
            attributes.set(Attribute.water, 1f);
            dragMultiplier = 0.5f;
            speedMultiplier = 0.95f;
        }};

        iceWall = new StaticWall("iceWall") {{
            ice.asFloor().wall = this;
        }};

        icedStone = new Floor("icedStone") {{
            variants = 3;
            attributes.set(Attribute.water, 0.5f);
        }};

        rainStone = new Floor("rainStone") {{
            variants = 3;
        }};

        rainStoneWall = new StaticWall("rainStoneWall") {{
            rainStone.asFloor().wall = this;
        }};

        rainBoulder = new Prop("rainBoulder"){{
            variants = 3;
            rainStone.asFloor().decoration = this;
        }};

        //ores
        tantalumOre = new OreBlock("tantalumOre"){{
            itemDrop = NSitems.tantalum;
        }};

        electrumOre = new OreBlock("electrumOre"){{
            itemDrop = NSitems.electrum;
        }};
    }
}
