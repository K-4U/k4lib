package k4unl.minecraft.k4lib.lib;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHelper {

    public static Block registerBlock(Block block, String name){
        GameRegistry.registerBlock(block, name);
        OreDictionary.registerOre(name, block);
        if(!OreDictionary.doesOreNameExist(name)) {
            return block;
        }else{
            return Block.getBlockFromItem(OreDictionary.getOres(name).get(0).getItem());
        }
    }

    public static Item registerItem(Item item, String name){
        GameRegistry.registerItem(item, name);
        OreDictionary.registerOre(name, item);
        if(!OreDictionary.doesOreNameExist(name)) {
            return item;
        }else{
            return OreDictionary.getOres(name).get(0).getItem();
        }
    }
}
