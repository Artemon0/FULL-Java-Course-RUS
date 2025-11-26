package com.minecraft.world;

import java.util.HashMap;
import java.util.Map;

/**
 * Игровой мир
 */
public class World {

    private final long seed;
    private final Map<String, Chunk> chunks;
    private final WorldGenerator generator;

    public World(long seed) {
        this.seed = seed;
        this.chunks = new HashMap<>();
        this.generator = new WorldGenerator(seed);
    }

    public void generateInitialChunks(float playerX, float playerZ) {
        int chunkX = (int) Math.floor(playerX / 16);
        int chunkZ = (int) Math.floor(playerZ / 16);

        int renderDistance = 4;

        for (int x = chunkX - renderDistance; x <= chunkX + renderDistance; x++) {
            for (int z = chunkZ - renderDistance; z <= chunkZ + renderDistance; z++) {
                loadChunk(x, z);
            }
        }
    }

    public void update(float playerX, float playerZ) {
        // Загрузка/выгрузка чанков
    }

    private void loadChunk(int chunkX, int chunkZ) {
        String key = chunkX + "," + chunkZ;
        if (!chunks.containsKey(key)) {
            Chunk chunk = new Chunk(chunkX, chunkZ);
            generator.generateChunk(chunk);
            chunks.put(key, chunk);
        }
    }

    public int getLoadedChunksCount() {
        return chunks.size();
    }

    public Map<String, Chunk> getChunks() {
        return chunks;
    }
}
