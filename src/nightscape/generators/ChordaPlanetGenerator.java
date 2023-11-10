package nightscape.generators;

import arc.graphics.Color;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.math.geom.Vec2;
import arc.math.geom.Vec3;
import arc.struct.FloatSeq;
import arc.struct.ObjectMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Structs;
import arc.util.Tmp;
import arc.util.noise.Noise;
import arc.util.noise.Ridged;
import arc.util.noise.Simplex;
import mindustry.ai.Astar;
import mindustry.ai.BaseRegistry;
import mindustry.content.Blocks;
import mindustry.content.Liquids;
import mindustry.game.Schematics;
import mindustry.game.Team;
import mindustry.graphics.g3d.PlanetGrid;
import mindustry.maps.generators.BaseGenerator;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Sector;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.TileGen;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.SteamVent;
import mindustry.world.meta.Attribute;
import nightscape.content.NSBenvironment;
import nightscape.content.loadouts;

import static arc.graphics.g2d.Draw.scl;
import static mindustry.Vars.bases;
import static mindustry.Vars.world;
import static nightscape.content.NSBenvironment.*;

public class ChordaPlanetGenerator extends PlanetGenerator {
    float scl = 5f;
    float waterOffset = 0.07f;

    {
        defaultLoadout = loadouts.core;
    }
    Block[][] arr =
            {
                    {ice, icedStone, rainStone, coldStone, ash, ash, natAsh, ash, slate, slate, ash, ash, slate},
                    {ice, icedStone, coldStone, ash, ash, natAsh, ash, slate, slate, ash, slate, ash, natAsh},
                    {ice, icedStone, rainStone, ash, natAsh, ash, ash, ash, ash, redStone, slate, ash, slate},
                    {ice, icedStone, ash, ash, natAsh, ash, slate, ash, natAsh, ash, ash, slate, slate},
                    {icedStone, ice, icedStone, ash, natAsh, ash, rainStone, ash, natAsh, ash, ash, slate, ash, icedStone},
                    {icedStone, ice, icedStone, ash, natAsh, rainStone, coldStone, slate, redStone, slate, slate, redStone, icedStone},
                    {icedStone, ice, ash, ash, slate, slate, redStone, redStone, coldStone, rainStone, redStone, icedStone, ice},
                    {ice, icedStone, rainStone, coldStone, slate, redStone, slate, rainStone, coldStone, icedStone, ice, icedStone, ice},
                    {icedStone, rainStone, rainStone, ash, ash, rainStone, slate, slate, coldStone, rainStone, icedStone, icedStone, ice},
                    {icedStone, rainStone, coldStone, ash, natAsh, coldStone, slate, ice, icedStone, icedStone, ice, ice, icedStone},
                    {ice, icedStone, ice, icedStone, ash, ash, redStone, rainStone, ice, icedStone,icedStone, icedStone, ice},
                    {ice, ice, icedStone, ice, ice, ice, icedStone, coldStone, icedStone, ice, icedStone, ice, icedStone},
                    {ice, ash, ice, ice, icedStone, icedStone, icedStone, ice, ice, icedStone, ice, icedStone, ice}
            };
    float water = 4f / arr[0].length;

    float rawHeight(Vec3 position){
        position = Tmp.v33.set(position).scl(scl);
        return (Mathf.pow(Simplex.noise3d(seed, 7, 0.5f, 1f/3f, position.x, position.y, position.z), 2.3f) + waterOffset) / (1f + waterOffset);
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