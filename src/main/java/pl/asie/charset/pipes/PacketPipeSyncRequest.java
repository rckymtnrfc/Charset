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

import io.netty.buffer.ByteBuf;

import net.minecraftforge.fmp.multipart.IMultipart;
import net.minecraft.network.INetHandler;
import pl.asie.charset.lib.network.PacketPart;

public class PacketPipeSyncRequest extends PacketPart {
	public PacketPipeSyncRequest() {
		super();
	}

	public PacketPipeSyncRequest(IMultipart part) {
		super(part);
	}

	@Override
	public void readData(INetHandler handler, ByteBuf buf) {
		super.readData(handler, buf);

		if (part == null || !(part instanceof PartPipe)) {
			return;
		}

		((PartPipe) part).onSyncRequest();
	}
}
