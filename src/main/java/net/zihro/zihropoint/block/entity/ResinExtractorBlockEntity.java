package net.zihro.zihropoint.block.entity;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.block.BasicTileBlock;
import com.hrznstudio.titanium.block.tile.ActiveTile;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.component.progress.ProgressBarComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.zihro.zihropoint.block.registration.ModBlockEntities;
import net.zihro.zihropoint.block.registration.ModBlocks;
import net.zihro.zihropoint.item.ModItems;

public class ResinExtractorBlockEntity extends ActiveTile<ResinExtractorBlockEntity> {

    @Save
    private InventoryComponent<ResinExtractorBlockEntity> input;
    @Save
    private InventoryComponent<ResinExtractorBlockEntity> output;
    @Save
    private ProgressBarComponent<ResinExtractorBlockEntity> progressBar;

    public ResinExtractorBlockEntity(BasicTileBlock<ResinExtractorBlockEntity> base, BlockPos pos, BlockState state) {
        super(base,ModBlockEntities.RESIN_EXTRACTOR.get(), pos, state);

        // Input Slot: Only accepts Rubber Logs
        // Input Slot
        // Inside ResinExtractorBlockEntity constructor
        this.addInventory(this.input = new InventoryComponent<ResinExtractorBlockEntity>("input", 44, 35, 1)
                .setComponentHarness(this)
                .setInputFilter((stack, integer) ->
                        stack.getItem() == ModBlocks.RUBBER_LOG.get().asItem() ||
                                stack.getItem() == ModBlocks.STRIPPED_RUBBER_LOG.get().asItem()
                )
        );

// Output Slot
        this.addInventory(this.output = new InventoryComponent<ResinExtractorBlockEntity>("output", 110, 35, 1)
                .setComponentHarness(this)
                .setInputFilter((stack, integer) -> false) // This prevents players from putting things IN the output
        );

        this.addProgressBar(this.progressBar = new ProgressBarComponent<ResinExtractorBlockEntity>(70, 35, 400)
                .setComponentHarness(this)
                .setBarDirection(ProgressBarComponent.BarDirection.ARROW_RIGHT)
                .setCanIncrease(tileEntity -> {
                    ItemStack stack = tileEntity.input.getStackInSlot(0);
                    boolean isCorrectItem = stack.getItem() == ModBlocks.RUBBER_LOG.get().asItem() ||
                            stack.getItem() == ModBlocks.STRIPPED_RUBBER_LOG.get().asItem();
                    boolean hasSpace = tileEntity.output.getStackInSlot(0).getCount() < 64;

                    return isCorrectItem && hasSpace;
                })
                .setOnFinishWork(() -> {
                    ItemStack result = new ItemStack(ModItems.RAW_RESIN.get());
                    if (output.insertItem(0, result, true).isEmpty()) {
                        input.getStackInSlot(0).shrink(1);
                        output.insertItem(0, result, false);
                    }
                })
        );
    }

    // --- ADD THESE METHODS TO FIX THE BLOCK CLASS ERRORS ---
    public InventoryComponent<ResinExtractorBlockEntity> getInputInventory() {
        return this.input;
    }

    public InventoryComponent<ResinExtractorBlockEntity> getOutputInventory() {
        return this.output;
    }
    // -------------------------------------------------------

    @Override
    public ResinExtractorBlockEntity getSelf() {
        return this;
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state, ResinExtractorBlockEntity blockEntity) {
        super.serverTick(level, pos, state, blockEntity);

        // Check if we HAVE a rubber log and ROOM for resin
        boolean hasInput = !input.getStackInSlot(0).isEmpty();
        boolean hasRoom = output.getStackInSlot(0).getCount() < 64;

        if (hasInput && hasRoom) {
            progressBar.setProgress(progressBar.getProgress() + 1);
        } else {
            // Optional: comment out the line below if you want progress to stay
            // where it is when the machine is full/empty instead of resetting.
            progressBar.setProgress(0);
        }
    }
}