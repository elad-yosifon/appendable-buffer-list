package io.github.abl.impl.node;

import io.github.abl.IBufferNode;

public class StringBufferNode implements IBufferNode {

    private final String content;
    private final int length;

    public StringBufferNode(String content, int length) {
        validate(content, length);
        this.content = content;
        this.length = length;
    }

    private static void validate(String content, int length) {
        if (length < 1) {
            throw new IllegalStateException("Content length should be greater than 0");
        }
        if (length > content.length()) {
            throw new IllegalStateException("Declared content length should be less than or equal to the actual content length");
        }
    }

    @Override
    public void copyOnto(byte[] buffer, int cursor) {
        byte[] bytes = getBytes();
        System.arraycopy(bytes, 0, buffer, cursor, bytes.length);
    }

    private byte[] getBytes() {
        return content.substring(0, length).getBytes();
    }

    @Override
    public int byteLength() {
        return getBytes().length;
    }
}
