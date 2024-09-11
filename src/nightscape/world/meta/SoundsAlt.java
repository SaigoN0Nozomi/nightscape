package nightscape.world.meta;

import arc.*;
import arc.assets.*;
import arc.assets.loaders.*;
import arc.audio.*;

import mindustry.*;

public class SoundsAlt {
    public static Sound
        rocket = new Sound(),
        shoot2 = new Sound(),
        nya = new Sound(),
        uwu = new Sound(),
        god = new Sound(),
        plasmaCharge = new Sound(),
        plasmaBlast = new Sound(),
        plasma = new Sound(),
        shoot = new Sound(),
        blast = new Sound(),
        bigsap = new Sound(),
        ascent = new Sound(),
        cool = new Sound(),
        artileryShoot = new Sound(),
        fireinthehole = new Sound(),
        snipeShoot = new Sound();
        public static void load(){
            shoot = loadSound("shootAlt2");
            shoot2 = loadSound("shootAlt");
            bigsap = loadSound("bigsap");
            rocket = loadSound("phh");
            plasmaCharge = loadSound("plasmaCharge");
            plasmaBlast = loadSound("plasmaBlast");
            plasma = loadSound("plasma");
            snipeShoot = loadSound("snipeShoot");
            blast = loadSound("blast");
            ascent = loadSound("ascent");
            cool = loadSound("cool");
            fireinthehole = loadSound("fireinthehole");
            artileryShoot = loadSound("artileryShoot");

            nya = loadSound("nya");
            uwu = loadSound("uwu");
            god = loadSound("god");
        }

        private static Sound loadSound(String soundName){
            if(!Vars.headless) {
            String name = "Sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }
}
