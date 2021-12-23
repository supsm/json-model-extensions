/*
 * This file is part of JSON Model Extensions and is licensed to the project under
 * terms that are compatible with the GNU Lesser General Public License.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership and licensing.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.vram.jmx.mixin;

import org.apache.commons.lang3.ObjectUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.block.model.BlockElementFace;

import io.vram.jmx.json.FaceExtData;
import io.vram.jmx.json.ext.JmxExtension;

@Mixin(BlockElementFace.class)
public class MixinBlockElementFace implements JmxExtension<FaceExtData> {
	private FaceExtData jmx_ext;

	@Override
	public FaceExtData jmx_ext() {
		return jmx_ext;
	}

	@Override
	public void jmx_ext(FaceExtData val) {
		jmx_ext = val;
	}

	@Inject(at = @At("RETURN"), method = "<init>")
	private void onInit(CallbackInfo ci) {
		jmx_ext = ObjectUtils.defaultIfNull(FaceExtData.TRANSFER.get(), FaceExtData.empty());
	}
}
