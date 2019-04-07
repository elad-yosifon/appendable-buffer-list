package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

public class ByteBufferNode implements IBufferNode {

    private final byte[] content;

    public ByteBufferNode(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] bytes() {
        return content;
    }

    @Override
    public int byteLength() {
        return content.length;
    }
}
