package com.mogumogumo.mushroom_concept.stats;

import com.mogumogumo.mushroom_concept.MushCutil;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum TinkerTransplantStatlessMaterialStats implements IMaterialStats {

    ARROW_FLETCHING("arrow_fletching");

    private static final List<Component> LOCALIZED = List.of(IMaterialStats.makeTooltip(TConstruct.getResource("extra.no_stats")));
    private static final List<Component> DESCRIPTION = List.of(Component.empty());
    private final MaterialStatType<TinkerTransplantStatlessMaterialStats> type;

    private TinkerTransplantStatlessMaterialStats(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(MushCutil.getResource(name)), this);
    }

    @Override
    public MaterialStatType<?> getType() {
        return this.type;
    }

    @Override
    public List<Component> getLocalizedInfo() {
        return LOCALIZED;
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {

    }
}
