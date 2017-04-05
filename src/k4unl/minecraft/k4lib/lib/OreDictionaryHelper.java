package k4unl.minecraft.k4lib.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class OreDictionaryHelper {

   private static boolean doesOreNameExist(String name) {
      List<ItemStack> stacks = OreDictionary.getOres(name);
      if (stacks.size() == 0)
         return false;

      boolean foundValid = false;
      for (ItemStack stack : stacks) {
         if (stack == null || stack.getItem() == null)
            FMLLog.severe("Some mod has registered a null itemstack or an itemstack with a null item\ninto oredictionary for " +
                    name + ".\nThis is NOT a K4Lib/.. issue! Do NOT report it to us! We have no way of knowing the owner!");
         else
            foundValid = true;
      }
      return foundValid;
   }

   public static Item findValidItem(String name) {
      List<ItemStack> stacks = OreDictionary.getOres(name);
      for (ItemStack stack : stacks) {
         if (stack != null && stack.getItem() != null)
            return stack.getItem();
      }

      FMLLog.severe("For some reason I have not found any valid itemstack. This is going to crash eventually...");

      return null;
   }

   public static Block findValidBlock(String name) {
      return Block.getBlockFromItem(findValidItem(name));
   }
/* TODO: Update to 1.11 code
   public static Block registerBlock(Block block, String name) {
      if (!doesOreNameExist(name)) {
         GameRegistry.registerBlock(block, name);
         OreDictionary.registerOre(name, block);
         return block;
      } else {
         return findValidBlock(name);
      }
   }

   public static Item registerItem(Item item, String name) {
      if (!doesOreNameExist(name)) {
         GameRegistry.registerItem(item, name);
         OreDictionary.registerOre(name, item);
         return item;
      } else {
         return findValidItem(name);
      }
   }*/
}
