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

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;
import pl.asie.charset.lib.render.ModelPipeLike;

public class ModelPipe extends ModelPipeLike<PartPipe> {
    public static final ResourceLocation PIPE_TEXTURE_LOC = new ResourceLocation("charsetpipes", "blocks/pipe");
    public static TextureAtlasSprite[] sprites;

    public ModelPipe() {
        super(PartPipe.PROPERTY);
    }

    @Override
    public float getThickness() {
        return 7.995f;
    }

    @Override
    public int getInsideColor(EnumFacing facing) {
        return facing.getAxis() == EnumFacing.Axis.Y ? 0xFFC8C8C8 : 0xFFE8E8E8;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public TextureAtlasSprite getTexture(EnumFacing side, int connectionMatrix) {
        return sprites != null ? sprites[connectionMatrix] : null;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return sprites != null ? sprites[15] : null;
    }
}
