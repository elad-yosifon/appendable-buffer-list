package io.github.abl;

public interface IBufferNode {
    void copyOnto(byte[] buffer, int cursor);
    int byteLength();
}
