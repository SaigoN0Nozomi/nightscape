package nightscape.world.meta;

import arc.util.Nullable;
import mindustry.gen.Iconc;
import mindustry.world.meta.StatUnit;

public class NSStatUnit{
    public static final StatUnit

    EnPerHeat = new StatUnit("EnPerHeat", "[accent]" + Iconc.power + "[]"),
    heatMax = new StatUnit("heatMax", "[red]" + Iconc.waves + "[]"),
    upto = new StatUnit("upto");

    public final boolean space;
    public final String name;
    public @Nullable String icon;

    public NSStatUnit(String name, boolean space){
        this.name = name;
        this.space = space;
    }

    public NSStatUnit(String name){
        this(name, true);
    }

    public NSStatUnit(String name, String icon){
        this(name, true);
        this.icon = icon;
    }
}
