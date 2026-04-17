package net.zihro.zihropoint.block.custom;

import com.hrznstudio.titanium.block.RotatableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.zihro.zihropoint.block.entity.SolidFuelGeneratorBlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;


public class SolidFuelGeneratorBlock extends RotatableBlock<SolidFuelGeneratorBlockEntity> {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public SolidFuelGeneratorBlock() {
        super("solid_fuel_generator",
                BlockBehaviour.Properties.of()
                        .strength(3.0f)
                        .requiresCorrectToolForDrops()
                        // Emits light level 13 when LIT is true, 0 when false
                        .lightLevel(state -> state.getValue(LIT) ? 13 : 0),
                SolidFuelGeneratorBlockEntity.class);

        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, LIT);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof SolidFuelGeneratorBlockEntity generator) {
                for (int i = 0; i < generator.getFuelSlot().getSlots(); i++) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), generator.getFuelSlot().getStackInSlot(i));
                }
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockEntityType.BlockEntitySupplier<SolidFuelGeneratorBlockEntity> getTileEntityFactory() {
        return (pos, state) -> new SolidFuelGeneratorBlockEntity(this, pos, state);
    }
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) return InteractionResult.SUCCESS;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SolidFuelGeneratorBlockEntity generator) {
            // This is the Titanium helper that opens the GUI automatically
            generator.openGui(player);
            return InteractionResult.CONSUME;
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite())
                .setValue(LIT, false);
    }
}