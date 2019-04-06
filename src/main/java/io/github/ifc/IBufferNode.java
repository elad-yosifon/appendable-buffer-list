package io.github.ifc;

public interface IBufferNode {
    void copyOnto(byte[] buffer, int cursor);
    int byteLength();
}
