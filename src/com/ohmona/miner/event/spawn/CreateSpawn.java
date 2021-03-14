package com.ohmona.miner.event.spawn;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Slab;

public class CreateSpawn {

    public void createspawnarea(World w) {

        // remove 5x4x5 stone
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                for(int k = 20; k <= 23; k++)
                    new Location(w, i, k ,j).getBlock().setType(Material.AIR);
            }
        }
        // lantern save system
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                for(int k = 17; k <= 19; k++) {
                    new Location(w, i, k ,j).getBlock().setType(Material.BEDROCK);
                }
            }
        }
        new Location(w, 0, 19,0).getBlock().setType(Material.BARRIER);
        new Location(w, 0, 18,0).getBlock().setType(Material.SEA_LANTERN);

        // create oak fance
        new Location(w, -1, 20, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, 1, 20, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, -1, 21, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, 1, 21, -2).getBlock().setType(Material.OAK_FENCE);

        // create oak stairs
        new Location(w, 2, 20 ,0).getBlock().setType(Material.OAK_STAIRS);
        BlockData stairsData = w.getBlockAt(2,20,0).getBlockData();
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.EAST);
            w.getBlockAt(2,20,0).setBlockData(stairsData);
        }
        new Location(w, -2, 20 ,0).getBlock().setType(Material.OAK_STAIRS);
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.WEST);
            w.getBlockAt(-2,20,0).setBlockData(stairsData);
        }
        new Location(w, 0, 20 ,2).getBlock().setType(Material.OAK_STAIRS);
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.SOUTH);
            w.getBlockAt(0,20,2).setBlockData(stairsData);
        }

        // create iron bars and chains
        for(int i = 21; i <= 23; i++) {
            new Location(w, 2, i, -1).getBlock().setType(Material.IRON_BARS);
            new Location(w, 2, i, 1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -2, i, -1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -2, i, 1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -1, i, 2).getBlock().setType(Material.IRON_BARS);
            new Location(w, 1, i, 2).getBlock().setType(Material.IRON_BARS);

            new Location(w, 2, i, 0).getBlock().setType(Material.CHAIN);
            new Location(w, -2, i, 0).getBlock().setType(Material.CHAIN);
            new Location(w, 0, i, 2).getBlock().setType(Material.CHAIN);
        }
        new Location(w, 0, 22, -2).getBlock().setType(Material.IRON_BARS);

        // create glasses
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                new Location(w, i, 23, j).getBlock().setType(Material.GLASS);
            }
        }

        // create stone
        new Location(w, 1, 22, -2).getBlock().setType(Material.STONE);
        new Location(w, 1, 23, -2).getBlock().setType(Material.STONE);
        new Location(w, -1, 22, -2).getBlock().setType(Material.STONE);
        new Location(w, -1, 23, -2).getBlock().setType(Material.STONE);
        new Location(w, 0, 23, -2).getBlock().setType(Material.STONE);

        // create sign
        new Location(w, 0, 21, 1).getBlock().setType(Material.OAK_WALL_SIGN);
        Block block = w.getBlockAt(0 ,21,1);
        Sign sign = (Sign) block.getState();
        sign.setLine(0, "! warning !");
        sign.setLine(2, "block in this are");
        sign.setLine(3, "can be removed");
        sign.update();
    }

    public void createDefaultspawnarea(World w) {

        // remove 5x4x5 stone
        for(int i = -2; i <= 2; i++) {
            for(int j = -2; j <= 2; j++) {
                for(int k = 20; k <= 23; k++)
                    new Location(w, i, k ,j).getBlock().setType(Material.AIR);
            }
        }
        // lantern save system
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                for(int k = 17; k <= 19; k++) {
                    new Location(w, i, k ,j).getBlock().setType(Material.BEDROCK);
                }
            }
        }
        new Location(w, 0, 19,0).getBlock().setType(Material.BARRIER);
        new Location(w, 0, 18,0).getBlock().setType(Material.SEA_LANTERN);

        //create oak slabs
        new Location(w, 2, 20 ,-1).getBlock().setType(Material.OAK_SLAB);
        new Location(w, 2, 20 ,1).getBlock().setType(Material.OAK_SLAB);
        new Location(w, -2, 20 ,-1).getBlock().setType(Material.OAK_SLAB);
        new Location(w, -2, 20 ,-1).getBlock().setType(Material.OAK_SLAB);
        new Location(w, -1, 20 ,2).getBlock().setType(Material.OAK_SLAB);
        new Location(w, -1, 20 ,2).getBlock().setType(Material.OAK_SLAB);
        BlockData slabData = w.getBlockAt(2,20,-1).getBlockData();
        if(slabData instanceof Slab) {
            ((Slab) slabData).setType(Slab.Type.TOP);
            w.getBlockAt(2,20,-1).setBlockData(slabData);
            w.getBlockAt(2,20,1).setBlockData(slabData);
            w.getBlockAt(-2,20,-1).setBlockData(slabData);
            w.getBlockAt(-2,20,1).setBlockData(slabData);
            w.getBlockAt(-1,20,2).setBlockData(slabData);
            w.getBlockAt(1,20,2).setBlockData(slabData);
        }

        // create oak stairs
        new Location(w, 2, 20 ,0).getBlock().setType(Material.OAK_STAIRS);
        BlockData stairsData = w.getBlockAt(2,20,0).getBlockData();
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.EAST);
            w.getBlockAt(2,20,0).setBlockData(stairsData);
        }
        new Location(w, -2, 20 ,0).getBlock().setType(Material.OAK_STAIRS);
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.WEST);
            w.getBlockAt(-2,20,0).setBlockData(stairsData);
        }
        new Location(w, 0, 20 ,2).getBlock().setType(Material.OAK_STAIRS);
        if(stairsData instanceof Directional) {
            ((Directional) stairsData).setFacing(BlockFace.SOUTH);
            w.getBlockAt(0,20,2).setBlockData(stairsData);
        }

        // create iron bars and chains
        for(int i = 21; i <= 23; i++) {
            new Location(w, 2, i, -1).getBlock().setType(Material.IRON_BARS);
            new Location(w, 2, i, 1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -2, i, -1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -2, i, 1).getBlock().setType(Material.IRON_BARS);
            new Location(w, -1, i, 2).getBlock().setType(Material.IRON_BARS);
            new Location(w, 1, i, 2).getBlock().setType(Material.IRON_BARS);

            new Location(w, 2, i, 0).getBlock().setType(Material.CHAIN);
            new Location(w, -2, i, 0).getBlock().setType(Material.CHAIN);
            new Location(w, 0, i, 2).getBlock().setType(Material.CHAIN);
        }
        new Location(w, 0, 22, -2).getBlock().setType(Material.IRON_BARS);

        // create glasses
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                new Location(w, i, 23, j).getBlock().setType(Material.GLASS);
            }
        }

        // create oak fance
        new Location(w, -1, 20, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, 1, 20, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, -1, 21, -2).getBlock().setType(Material.OAK_FENCE);
        new Location(w, 1, 21, -2).getBlock().setType(Material.OAK_FENCE);

        // create stone
        new Location(w, 1, 22, -2).getBlock().setType(Material.STONE);
        new Location(w, 1, 23, -2).getBlock().setType(Material.STONE);
        new Location(w, -1, 22, -2).getBlock().setType(Material.STONE);
        new Location(w, -1, 23, -2).getBlock().setType(Material.STONE);
        new Location(w, 0, 23, -2).getBlock().setType(Material.STONE);

        // 나무판자기둥 생성
        for(int i = 20; i <= 23; i++) {
            new Location(w, 2, i ,-2).getBlock().setType(Material.OAK_PLANKS);
        }
        for(int i = 20; i <= 23; i++) {
            new Location(w, -2, i ,-2).getBlock().setType(Material.OAK_PLANKS);
        }
        for(int i = 20; i <= 23; i++) {
            new Location(w, -2, i ,2).getBlock().setType(Material.OAK_PLANKS);
        }
        for(int i = 20; i <= 23; i++) {
            new Location(w, 2, i ,2).getBlock().setType(Material.OAK_PLANKS);
        }
        // create sign
        new Location(w, 0, 21, 1).getBlock().setType(Material.OAK_WALL_SIGN);
        Block block = w.getBlockAt(0 ,21,1);
        Sign sign = (Sign) block.getState();
        sign.setLine(0, "! warning !");
        sign.setLine(2, "block in this are");
        sign.setLine(3, "can be removed");
        sign.update();
        // lantern save system
        new Location(w, 0, 19,0).getBlock().setType(Material.BARRIER);
        new Location(w, 0, 18,0).getBlock().setType(Material.SEA_LANTERN);
        for(int i = -1; i <= 1; i += 2) {
            for(int j = -1; j <= 1; j += 2) {
                for(int k = 17; k <= 19; k++) {
                    new Location(w, i, k ,j).getBlock().setType(Material.BEDROCK);
                }
            }
        }
    }

}
