package com.minecraft.world;

import java.util.Random;

/**
 * Генератор мира
 */
public class WorldGenerator {

    private final long seed;
    private final Random random;

    public WorldGenerator(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public void generateChunk(Chunk chunk) {
        int chunkX = chunk.getChunkX();
        int chunkZ = chunk.getChunkZ();

        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {
                int worldX = chunkX * Chunk.SIZE + x;
                int worldZ = chunkZ * Chunk.SIZE + z;

                // Простая генерация высоты
                int height = 64 + (int) (Math.sin(worldX * 0.1) * 10 + Math.cos(worldZ * 0.1) * 10);

                // Bedrock
                chunk.setBlock(x, 0, z, BlockType.BEDROCK);

                // Камень
                for (int y = 1; y < height - 4; y++) {
                    chunk.setBlock(x, y, z, BlockType.STONE);
                }

                // Земля
                for (int y = height - 4; y < height - 1; y++) {
                    chunk.setBlock(x, y, z, BlockType.DIRT);
                }

                // Трава
                chunk.setBlock(x, height - 1, z, BlockType.GRASS);
            }
        }
    }
}
