package nightscape.world.meta;

import arc.util.Nullable;
import mindustry.gen.Iconc;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

public class NSStat {
    public static final StatUnit

    EnPerHeat = new StatUnit("EnPerHeat", "[accent]" + Iconc.power + "[]"),
    heatMax = new StatUnit("heatMax", "[red]" + Iconc.waves + "[]"),
    upto = new StatUnit("upto"),
    threshold = new StatUnit("threshold");

    public final boolean space;
    public final String name;
    public @Nullable String icon;

    public NSStat(String name, boolean space){
        this.name = name;
        this.space = space;
    }

    public NSStat(String name){
        this(name, true);
    }

    public NSStat(String name, String icon){
        this(name, true);
        this.icon = icon;
    }

    public static final Stat
    coolTime = new Stat("coolTime", StatCat.function),
    heatTime = new Stat("heatTime", StatCat.function),
    duration = new Stat("duration", StatCat.function);
}
