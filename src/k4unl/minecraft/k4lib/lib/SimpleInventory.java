package k4unl.minecraft.k4lib.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

/*
Simple inventory implementation for use in Tile Entities, anywhere really
*/
public class SimpleInventory implements IInventory {
    protected final ItemStack[] inventory;
    protected final String inventoryName;
    protected final int inventoryLimit;
    protected Container owner;

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
    public ItemStack getStackInSlot(int slot) {
        if (slot >= inventory.length)
            return null;

        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = null;

        if (inventory[slot] != null) {
            if (inventory[slot].stackSize <= amount) {
                stack = inventory[slot];
                inventory[slot] = null;
            } else {
                stack = inventory[slot].splitStack(amount);
                if (inventory[slot].stackSize == 0) {
                    inventory[slot] = null;
                }
            }
        }

        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return decrStackSize(index, getStackInSlot(index).stackSize);
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

    //TODO: Implement me
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    //TODO: Implement me
    @Override
    public void openInventory(EntityPlayer playerIn) {

    }

    //TODO: Implement me
    @Override
    public void closeInventory(EntityPlayer playerIn) {

    }

    //TODO: Implement me
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    //TODO: Implement me
    @Override
    public int getField(int id) {
        return 0;
    }

    //TODO: Implement me
    @Override
    public void setField(int id, int value) {

    }

    //TODO: Implement me
    @Override
    public int getFieldCount() {
        return 0;
    }

    //TODO: Implement me
    @Override
    public void clear() {

    }

    public void load(NBTTagCompound nbt) {
        NBTTagList list = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inventory.length) {
                if (tag.getBoolean("Empty"))
                    inventory[slot] = null;
                else
                    inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
    }

    public void save(NBTTagCompound nbt) {
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inventory.length; i++) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            } else {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                tag.setBoolean("Empty", true);
                itemList.appendTag(tag);
            }
        }
        nbt.setTag("Inventory", itemList);
    }

    //TODO: Implement me

    @Override
    public String getName() {
        return null;
    }

    //TODO: Implement me
    @Override
    public boolean hasCustomName() {
        return false;
    }

    //TODO: Implement me
    @Override
    public ITextComponent getDisplayName() {
        return null;
    }
}
