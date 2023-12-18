package nightscape.world.block.environment;

import mindustry.world.*;

public interface UpdateEnvironment<T>{
    T getDefaultValue();
    void updateTile(Tile tile,T data);

    default boolean canHaveNullData(){
        return false;
    }
}
