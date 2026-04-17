package net.zihro.zihropoint.block.entity;

import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.MachineTile;
import com.hrznstudio.titanium.block.tile.PoweredTile;
import com.hrznstudio.titanium.component.energy.EnergyStorageComponent;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ForgeHooks;
import net.zihro.zihropoint.block.custom.SolidFuelGeneratorBlock;
import net.zihro.zihropoint.block.registration.ModBlockEntities;

import javax.annotation.Nonnull;

public class SolidFuelGeneratorBlockEntity extends PoweredTile<SolidFuelGeneratorBlockEntity> {
    private InventoryComponent<SolidFuelGeneratorBlockEntity> fuelSlot;
    private int burnTime;
    private int maxBurnTime;

    public SolidFuelGeneratorBlockEntity(BasicTileBlock<SolidFuelGeneratorBlockEntity> base, BlockPos pPos, BlockState pBlockState) {
        // Note: PoweredTile constructor expects (base, type, pos, state)
        super(base, ModBlockEntities.SOLID_FUEL_GENERATOR.get(), pPos, pBlockState);
        // Add your inventory as usual
        this.addInventory(this.fuelSlot = new InventoryComponent<SolidFuelGeneratorBlockEntity>(
                "fuel_input", 50, 20, 1)
                .setComponentHarness(this)
                .setInputFilter((stack, integer) -> ForgeHooks.getBurnTime(stack, RecipeType.SMELTING) > 0)
        );
    }

    @Nonnull
    @Override
    protected EnergyStorageComponent<SolidFuelGeneratorBlockEntity> createEnergyStorage() {
        // This is where you define your 25k FE capacity and GUI (80, 20)
        return new EnergyStorageComponent<>(25000, 80, 20);
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, SolidFuelGeneratorBlockEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);

        boolean isDirty = false;
        boolean wasBurning = this.burnTime > 0;

        // 1. Generate Energy if burning
        if (this.burnTime > 0) {
            this.burnTime--;
            // Access the built-in storage via getEnergyStorage()
            this.getEnergyStorage().receiveEnergy(40, false);
            isDirty = true;
        }

        // 2. Refuel logic
        if (this.burnTime <= 0 && this.getEnergyStorage().getEnergyStored() < this.getEnergyStorage().getMaxEnergyStored()) {
            ItemStack fuel = this.fuelSlot.getStackInSlot(0);
            if (!fuel.isEmpty()) {
                this.maxBurnTime = this.burnTime = ForgeHooks.getBurnTime(fuel, RecipeType.SMELTING);
                fuel.shrink(1);
                isDirty = true;
            }
        }

        // 3. Update LIT state
        if (wasBurning != (this.burnTime > 0)) {
            level.setBlock(pos, state.setValue(SolidFuelGeneratorBlock.LIT, this.burnTime > 0), 3);
        }

        if (isDirty) this.markComponentDirty();
    }
    public InventoryComponent<SolidFuelGeneratorBlockEntity> getFuelSlot() {
        return this.fuelSlot;
    }

    @Override
    public SolidFuelGeneratorBlockEntity getSelf() {
        return this;
    }
    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        // This saves the actual item stacks to the disk
        compound.put("Inventory", this.getFuelSlot().serializeNBT());
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        // This reads the item stacks back from the disk when you log in
        if (compound.contains("Inventory")) {
            this.getFuelSlot().deserializeNBT(compound.getCompound("Inventory"));
        }
    }
}