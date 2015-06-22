package k4unl.minecraft.k4lib.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by K-4U on 22-6-2015.
 */
public class OreDictionaryHelper {

    public static Block registerBlock(Block block, String name){
        if(!OreDictionary.doesOreNameExist(name)) {
            GameRegistry.registerBlock(block, name);
            OreDictionary.registerOre(name, block);
            return block;
        }else{
            return Block.getBlockFromItem(OreDictionary.getOres(name).get(0).getItem());
        }
    }

    public static Item registerItem(Item item, String name){
        if(!OreDictionary.doesOreNameExist(name)) {
            GameRegistry.registerItem(item, name);
            OreDictionary.registerOre(name, item);
            return item;
        }else{
            return OreDictionary.getOres(name).get(0).getItem();
        }
    }
}
