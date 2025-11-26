package com.minecraft.world;

/**
 * Типы блоков в игре
 */
public enum BlockType {
    AIR(0, false, false),
    GRASS(1, true, true),
    DIRT(2, true, true),
    STONE(3, true, true),
    WOOD(4, true, true),
    LEAVES(5, true, false),
    SAND(6, true, true),
    WATER(7, true, false),
    GLASS(8, true, false),
    BEDROCK(9, true, true);

    private final int id;
    private final boolean solid;
    private final boolean opaque;

    BlockType(int id, boolean solid, boolean opaque) {
        this.id = id;
        this.solid = solid;
        this.opaque = opaque;
    }

    public int getId() {
        return id;
    }

    public boolean isSolid() {
        return solid;
    }

    public boolean isOpaque() {
        return opaque;
    }

    public static BlockType fromId(int id) {
        for (BlockType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return AIR;
    }
}
