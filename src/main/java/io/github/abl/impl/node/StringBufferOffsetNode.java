package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

public class StringBufferOffsetNode implements IBufferNode {

    private final String content;
    private final int offset;
    private final int length;

    public StringBufferOffsetNode(String content, int offset, int length) {
        validate(content, offset, length);
        this.content = content;
        this.offset = offset;
        this.length = length;
    }

    private static void validate(String content, int offset, int length) {
        if (length < 1) {
            throw new IllegalStateException("Content length should be greater than 0");
        }
        if (length > (content.length() - offset)) {
            throw new IllegalStateException("Declared content length should be less than or equal to the actual content length");
        }
    }

    @Override
    public void copyOnto(byte[] buffer, int cursor) {
        byte[] bytes = getBytes();
        System.arraycopy(bytes, 0, buffer, cursor, bytes.length);
    }

    @Override
    public int byteLength() {
        return getBytes().length;
    }

    private byte[] getBytes() {
        return content.substring(offset, offset + length).getBytes();
    }

}
