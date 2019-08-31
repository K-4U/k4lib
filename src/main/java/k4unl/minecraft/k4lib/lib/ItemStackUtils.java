package k4unl.minecraft.k4lib.lib;

import net.minecraft.item.ItemStack;

public class ItemStackUtils {
    
    public static boolean canMergeStacks(ItemStack a, ItemStack b) {
        
        return ItemStack.areItemStacksEqual(a, b);
    }
    
    public static ItemStack mergeStacks(ItemStack a, ItemStack b) {
        
        if (a == null && b != null) {
            return b.copy();
        } else if (b == null && a != null) {
            return a.copy();
        } else if (a == null && b == null) {
            return null;
        } else if (a.getCount() + b.getCount() > a.getMaxStackSize()) {
            return null;
        }
        
        a.setCount(a.getCount() + b.getCount());
        return a;
    }
}
