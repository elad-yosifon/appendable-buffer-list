package io.github.abl.impl.node;

import io.github.abl.IBufferNode;

public class ByteBufferNode implements IBufferNode {

    private final byte[] content;
    private final int byteLength;

    public ByteBufferNode(byte[] content, int byteLength) {
        validate(content, byteLength);
        this.content = content;
        this.byteLength = byteLength;
    }

    private static void validate(byte[] content, int length) {
        if (length < 1) {
            throw new IllegalStateException("Content length should be greater than 0");
        }
        if (length > content.length) {
            throw new IllegalStateException("Declared content length should be less than or equal to the actual content length");
        }
    }

    @Override
    public void copyOnto(byte[] buffer, int cursor) {
        System.arraycopy(content, 0, buffer, cursor, byteLength);
    }

    @Override
    public int byteLength() {
        return byteLength;
    }
}
