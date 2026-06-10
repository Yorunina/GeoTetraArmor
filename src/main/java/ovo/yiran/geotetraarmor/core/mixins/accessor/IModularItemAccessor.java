package ovo.yiran.geotetraarmor.core.mixins.accessor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import se.mickelus.tetra.items.modular.IModularItem;

import javax.annotation.Nullable;

@Mixin(value = IModularItem.class, remap = false)
public interface IModularItemAccessor {
    @Invoker("getReducedDamage")
    int getReducedDamage(int amount, ItemStack itemStack, @Nullable LivingEntity responsibleEntity);
}