package net.zihro.zihropoint.block.custom;

import com.hrznstudio.titanium.block.RotatableBlock;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.zihro.zihropoint.block.entity.ResinExtractorBlockEntity;
import net.zihro.zihropoint.block.registration.ModBlockEntities;

import javax.annotation.Nullable;

public class ResinExtractorBlock extends RotatableBlock<ResinExtractorBlockEntity> {

    public ResinExtractorBlock() {
        super("resin_extractor",
                BlockBehaviour.Properties.of()
                        .strength(2.5f)
                        .requiresCorrectToolForDrops(),
                ResinExtractorBlockEntity.class);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof ResinExtractorBlockEntity extractor) {
                // Drop Input Slot
                for (int i = 0; i < extractor.getInputInventory().getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), extractor.getInputInventory().getStackInSlot(i));
                }
                // Drop Output Slot
                for (int i = 0; i < extractor.getOutputInventory().getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), extractor.getOutputInventory().getStackInSlot(i));
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<ResinExtractorBlockEntity> getTileEntityFactory() {
        return (pos, state) -> new ResinExtractorBlockEntity(this, pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ResinExtractorBlockEntity extractor) {
            extractor.openGui(player);
            return InteractionResult.CONSUME;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // This links the Block Entity Type to the actual ticking logic
        return level.isClientSide ? null : (level1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof ResinExtractorBlockEntity extractor) {
                extractor.serverTick(level1, pos, state1, extractor);
            }
        };
    }

}