package nightscape.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.type.StatusEffect;
import nightscape.content.effects.StatusFx;
import nightscape.world.status.ArmorStatus;

import static arc.graphics.g2d.Draw.color;
import static arc.math.Angles.randLenVectors;

public class NSstatus {
    public static StatusEffect ozoneCorrosion, overCharged, ascent, defended, armorNull;

    public static void load(){
        ozoneCorrosion = new StatusEffect("OzoneCorrosion"){{
            speedMultiplier = 0.90f;
            damageMultiplier = 0.90f;
            effect = StatusFx.corosion;
            damage = 0.5f / 60f;
            effectChance = 0.02f;
        }};

        overCharged = new StatusEffect("Overcharged"){{
            speedMultiplier = 0.7f;
            reloadMultiplier = 0.7f;
            damageMultiplier = 0.7f;
            effect = StatusFx.shock;
            effectChance = 0.04f;
        }};

        ascent = new StatusEffect("Ascent"){{
            speedMultiplier = 1.1f;
            healthMultiplier = 1.1f;
            damageMultiplier = 1.1f;
            effect = StatusFx.charge;
            effectChance = 0.02f;
        }};

        defended = new ArmorStatus("Defended"){{
            armorAdd = 2;
            buildSpeedMultiplier = 2f;
            damage = -0.4f;
            effect = StatusFx.defended;
            effectChance = 0.05f;
        }};

        armorNull = new ArmorStatus("ArmorNull"){{
            armorAdd = -4;
        }};
    }
}
