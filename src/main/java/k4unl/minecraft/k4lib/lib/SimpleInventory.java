package k4unl.minecraft.k4lib.lib;

/*
Simple inventory implementation for use in Tile Entities, anywhere really
*/
public class SimpleInventory { //implements IInventory {
    /*
    //TODO: Rewrite this to use other capabilities.
    protected final ItemStack[] inventory;
    protected final String      inventoryName;
    protected final int         inventoryLimit;
    protected       Container   owner;
    
    public SimpleInventory(int slots, String inventoryName, int inventoryLimit, Container container) {
        
        this.inventoryName = inventoryName;
        this.inventoryLimit = inventoryLimit;
        inventory = new ItemStack[slots];
        this.owner = container;
    }
    
    public SimpleInventory(int slots, String inventoryName, int inventoryLimit) {
        
        this.inventoryName = inventoryName;
        this.inventoryLimit = inventoryLimit;
        inventory = new ItemStack[slots];
    }
    
    public SimpleInventory(int slots) {
        
        this.inventoryName = "";
        this.inventoryLimit = 64;
        this.inventory = new ItemStack[slots];
    }
    
    public Container getContainer() {
        
        return this.owner;
    }
    
    public void setContainer(Container container) {
        
        this.owner = container;
    }
    
    @Override
    public int getSizeInventory() {
        
        return inventory.length;
    }
    
    @Override
    public boolean isEmpty() {
        
        return false;
    }
    
    @Override
    public ItemStack getStackInSlot(int slot) {
        
        if (slot >= inventory.length)
            return null;
        
        return inventory[slot];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        ItemStack stack = null;
        
        if (inventory[slot] != null) {
            if (inventory[slot].getCount() <= amount) {
                stack = inventory[slot];
                inventory[slot] = null;
            } else {
                stack = inventory[slot].split(amount);
                if (inventory[slot].getCount() == 0) {
                    inventory[slot] = null;
                }
            }
        }
        
        return stack;
    }
    
    @Override
    public ItemStack removeStackFromSlot(int index) {
        
        return decrStackSize(index, getStackInSlot(index).getCount());
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack item) {
        
        inventory[slot] = item;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return inventoryLimit;
    }
    
    @Override
    public void markDirty() {
    
    }
    
    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        
        return false;
    }
    
  
    //TODO: Implement me
    @Override
    public void openInventory(PlayerEntity playerIn) {
    
    }
    
    //TODO: Implement me
    @Override
    public void closeInventory(PlayerEntity playerIn) {
    
    }
    
    //TODO: Implement me
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        
        return false;
    }
    
    //TODO: Implement me
    @Override
    public void clear() {
    
    }
    
    public void load(CompoundNBT nbt) {

        ListNBT list = nbt.getList("Inventory", 10);
        for (INBT tag : list) {
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                if (tag.getBoolean("Empty"))
                    inventory[slot] = null;
                else
                    inventory[slot] = new ItemStack(tag);
            }
        }
    }
    
    public void save(CompoundNBT nbt) {
        
        ListNBT itemList = new ListNBT();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                CompoundNBT tag = new CompoundNBT();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            } else {
                CompoundNBT tag = new CompoundNBT();
                tag.setByte("Slot", (byte) i);
                tag.setBoolean("Empty", true);
                itemList.appendTag(tag);
            }
        }
        nbt.setTag("Inventory", itemList);
    }*/
}
