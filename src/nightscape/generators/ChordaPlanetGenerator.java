package nightscape.generators;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.math.geom.Vec3;
import arc.struct.ObjectIntMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Tmp;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import mindustry.content.Blocks;
import mindustry.content.Liquids;
import mindustry.content.Weathers;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Rules;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.*;
import mindustry.type.Weather.WeatherEntry;
import mindustry.world.*;
import nightscape.content.loadouts;

import static mindustry.Vars.world;
import static nightscape.content.blocks.NSBenvironment.*;

public class ChordaPlanetGenerator extends PlanetGenerator {
    float scl = 5f;
    float waterOffset = 0.2f;

    {
        defaultLoadout = loadouts.core;
    }
    Block[][] arr =
            {
                    {ice, coldStone, rainStone, purl, ash, rainStone, rainStone, rainStone, purl, purl, purl, purl, purl},
                    {slate, ice, coldStone, purl, natAsh, purl, ash, rainStone, purl, purl, purl, purl, purl},
                    {ice, ice, coldStone, coldStone, natAsh, rainStone, slate, slate, slate, rainStone, ash, ash, ash},
                    {slate, coldStone, slate, ash, ash, natAsh, slate, ash, ash, ash, rainStone, ash, purl},
                    {slate, ice, coldStone, rainStone, natAsh, coldStone, slate, slate, rainStone, rainStone, rainStone, rainStone, purl},
                    {ice, ice, coldStone, slate, coldStone, slate, ash, ash, rainStone, rainStone, purl, rainStone, purl},
                    {coldStone, coldStone, slate, rainStone, natAsh, ash, rainStone, slate, slate, slate, purl, rainStone, purl},
                    {coldStone, ice, rainStone, coldStone, slate, natAsh, slate, ash, slate, purl, rainStone, purl, purl},
                    {ice, coldStone, slate, rainStone, natAsh, purl, rainStone, slate, slate, purl, rainStone, purl, purl},
                    {coldStone, rainStone, rainStone, purl, purl, purl, rainStone, rainStone, rainStone, rainStone, purl, purl, purl},
                    {ice, ice, coldStone, purl, purl, purl, purl, rainStone, rainStone, purl, purl, purl, purl},
                    {coldStone, ice, coldStone, purl, natAsh, purl, ash, rainStone, purl, purl, purl, purl, purl},
                    {ice, coldStone, rainStone, purl, ash, rainStone, rainStone, rainStone, purl, purl, purl, purl, purl}
            };

    float water = 1.6f / arr[0].length;

    float rawHeight(Vec3 position){
        position = Tmp.v33.set(position).scl(scl);
        return (Mathf.pow(Simplex.noise3d(seed, 7, 0.3f, 2f/5f, position.x, position.y, position.z), 1.9f) + waterOffset) / (1f + waterOffset);
    }
    @Override
    public float getHeight(Vec3 position){
        float height = rawHeight(position);
        return Math.max(height, water);
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = getBlock(position);
        //replace salt with sand color
        if(block == Blocks.salt) return Blocks.sand.mapColor;
        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public void genTile(Vec3 position, TileGen tile){
        tile.floor = getBlock(position);
        tile.block = tile.floor.asFloor().wall;

        if(Ridged.noise3d(seed + 1, position.x, position.y, position.z, 2, 22) > 0.31){
            tile.block = Blocks.air;
        }
    }

    Block getBlock(Vec3 position){
        float height = rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(scl);
        float rad = scl;
        float temp = Mathf.clamp(Math.abs(position.y * 2f) / (rad));
        float tnoise = Simplex.noise3d(seed, 7, 0.56, 1f/3f, position.x, position.y + 999f, position.z);
        temp = Mathf.lerp(temp, tnoise, 0.5f);
        height *= 1.2f;
        height = Mathf.clamp(height);

        Block res = arr[Mathf.clamp((int)(temp * arr.length), 0, arr[0].length - 1)][Mathf.clamp((int)(height * arr[0].length), 0, arr[0].length - 1)];

        return res;
    }

    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag){
        Vec3 v = sector.rect.project(x, y).scl(5f);
        return Simplex.noise3d(seed, octaves, falloff, 1f / scl, v.x, v.y, v.z) * (float)mag;
    }
}