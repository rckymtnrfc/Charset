package pl.asie.charset.pipes.api;

import net.minecraft.item.ItemStack;

import net.minecraftforge.common.util.ForgeDirection;

public interface IShifter {
	enum Mode {
		Extract,
		Push
	}

	Mode getMode();
	ForgeDirection getDirection();
	int getShiftDistance();
	boolean isShifting();
	boolean hasFilter();
	boolean matches(ItemStack source);
}
