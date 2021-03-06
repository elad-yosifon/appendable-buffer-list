package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

import java.util.Arrays;

public class ByteBufferOffsetNode implements IBufferNode {

    private final byte[] content;
    private final int offset;
    private final int byteLength;

    public ByteBufferOffsetNode(byte[] content, int offset, int length) {
        validate(content, offset, length);
        this.content = content;
        this.offset = offset;
        this.byteLength = length;
    }

    private static void validate(byte[] content, int offset, int length) {
        if (length < 1) {
            throw new IllegalStateException("Content length should be greater than 0");
        }
        if (length > (content.length - offset)) {
            throw new IllegalStateException("Declared content length should be less than or equal to the actual content length");
        }
    }

    @Override
    public byte[] bytes() {
        return Arrays.copyOfRange(content, offset, offset + byteLength);
    }

    @Override
    public int byteLength() {
        return byteLength;
    }
}
