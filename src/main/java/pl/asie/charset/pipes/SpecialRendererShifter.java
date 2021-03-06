/*
 * Copyright (c) 2015-2016 Adrian Siekierka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.asie.charset.pipes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import net.minecraftforge.client.model.IPerspectiveAwareModel;

import pl.asie.charset.pipes.TileShifter;

public class SpecialRendererShifter extends TileEntitySpecialRenderer {
	private final Minecraft mc = Minecraft.getMinecraft();
	private final RenderItem itemRenderer = mc.getRenderItem();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
		TileShifter shifter = (TileShifter) tileEntity;

		ItemStack[] filters = shifter.getFilters();

		for (int i = 0; i < filters.length; i++) {
			if (filters[i] == null) {
				continue;
			}

			EntityItem entityitem = new EntityItem(shifter.getWorld(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), filters[i]);
			EnumFacing itemDir = EnumFacing.getFront(i);
			boolean isBlock = this.itemRenderer.getItemModelMesher().getItemModel(filters[i]) instanceof IPerspectiveAwareModel.MapWrapper
					&& filters[i].getItem() instanceof ItemBlock;

			float translationConstant = 0.49375F;

			entityitem.hoverStart = 0.0F;
			GlStateManager.pushMatrix();
			GlStateManager.disableLighting();
			GlStateManager.translate(
					x + 0.5 + translationConstant * itemDir.getFrontOffsetX(),
					y + 0.5 + translationConstant * itemDir.getFrontOffsetY(),
					z + 0.5 + translationConstant * itemDir.getFrontOffsetZ()
			);

			switch (itemDir) {
				case NORTH:
					GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
					break;
				case SOUTH:
					break;
				case WEST:
					GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
					break;
				case EAST:
					GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
					break;
				case UP:
					GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
					GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
					break;
				case DOWN:
					GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
					GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
					break;
			}

			if (isBlock) {
				GlStateManager.translate(0, 0, -0.0625F);
				GlStateManager.scale(2.0F, 2.0F, 2.0F);
			} else {
				GlStateManager.scale(1.33F, 1.33F, 1.33F);
			}

			int l = tileEntity.getWorld().getCombinedLight(tileEntity.getPos().offset(itemDir), tileEntity.getWorld().getSkylightSubtracted());
			int j = l % 65536;
			int k = l / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);

			TextureAtlasSprite textureatlassprite = null;

			GlStateManager.scale(0.5F, 0.5F, 0.5F);

			RenderHelper.enableStandardItemLighting();
			this.itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.FIXED);
			RenderHelper.disableStandardItemLighting();

			if (textureatlassprite != null && textureatlassprite.getFrameCount() > 0) {
				textureatlassprite.updateAnimation();
			}

			GlStateManager.enableLighting();
			GlStateManager.popMatrix();
		}
	}
}
