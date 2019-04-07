package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

import java.util.Arrays;

public class BytePrefixBufferNode implements IBufferNode {

    private final byte[] content;
    private final int byteLength;

    public BytePrefixBufferNode(byte[] content, int byteLength) {
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
    public byte[] bytes() {
        return Arrays.copyOfRange(content,0, byteLength);
    }

    @Override
    public int byteLength() {
        return byteLength;
    }
}
