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

package pl.asie.charset.lib;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import net.minecraftforge.fmp.capabilities.CapabilityWrapperRegistry;
import pl.asie.charset.api.audio.AudioSink;
import pl.asie.charset.api.audio.IAudioSource;
import pl.asie.charset.api.wires.IBundledEmitter;
import pl.asie.charset.api.wires.IBundledReceiver;
import pl.asie.charset.api.wires.IRedstoneEmitter;
import pl.asie.charset.api.wires.IRedstoneReceiver;
import pl.asie.charset.lib.capability.*;

public class Capabilities {
	@CapabilityInject(IAudioSource.class)
	public static Capability<IAudioSource> AUDIO_SOURCE;
	@CapabilityInject(AudioSink.class)
	public static Capability<AudioSink> AUDIO_SINK;

	@CapabilityInject(IBundledEmitter.class)
	public static Capability<IBundledEmitter> BUNDLED_EMITTER;
	@CapabilityInject(IBundledReceiver.class)
	public static Capability<IBundledReceiver> BUNDLED_RECEIVER;
	@CapabilityInject(IRedstoneEmitter.class)
	public static Capability<IRedstoneEmitter> REDSTONE_EMITTER;
	@CapabilityInject(IRedstoneReceiver.class)
	public static Capability<IRedstoneReceiver> REDSTONE_RECEIVER;

	public static void init() {
		CapabilityManager.INSTANCE.register(IAudioSource.class, new NullCapabilityStorage<IAudioSource>(), DefaultAudioSource.class);
		CapabilityManager.INSTANCE.register(AudioSink.class, new NullCapabilityStorage<AudioSink>(), DefaultAudioSink.class);

		CapabilityManager.INSTANCE.register(IBundledEmitter.class, new DefaultBundledEmitterStorage(), DefaultBundledEmitter.class);
		CapabilityManager.INSTANCE.register(IRedstoneEmitter.class, new DefaultRedstoneEmitterStorage(), DefaultRedstoneEmitter.class);
		CapabilityManager.INSTANCE.register(IBundledReceiver.class, new NullCapabilityStorage<IBundledReceiver>(), DummyRedstoneReceiver.class);
		CapabilityManager.INSTANCE.register(IRedstoneReceiver.class, new NullCapabilityStorage<IRedstoneReceiver>(), DummyRedstoneReceiver.class);

		CapabilityWrapperRegistry.registerCapabilityWrapper(Capabilities.BUNDLED_EMITTER, new BundledEmitterWrapper());
		CapabilityWrapperRegistry.registerCapabilityWrapper(Capabilities.REDSTONE_EMITTER, new RedstoneEmitterWrapper());
		CapabilityWrapperRegistry.registerCapabilityWrapper(Capabilities.BUNDLED_RECEIVER, new BundledReceiverWrapper());
		CapabilityWrapperRegistry.registerCapabilityWrapper(Capabilities.REDSTONE_RECEIVER, new RedstoneReceiverWrapper());
 	}
}
