package com.example.demo;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class VoiceHandler implements AudioSendHandler, AudioReceiveHandler {

    Queue<byte[]> queue = new ConcurrentLinkedDeque<>();

    @Override
    public boolean canProvide() {
        return !queue.isEmpty();
    }

    @Override
    public boolean canReceiveCombined() {
        return queue.size() < 10;
    }

    @Override
    public void handleCombinedAudio(@Nonnull CombinedAudio combinedAudio) {
        if (!combinedAudio.getUsers().isEmpty()) {
            byte[] data = combinedAudio.getAudioData(1.0);
            queue.add(data);
        }
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        byte[] data = queue.poll();
        return data == null ? null : ByteBuffer.wrap(data).asReadOnlyBuffer();
    }

    @Override
    public boolean isOpus() {
        return false;
    }
}
