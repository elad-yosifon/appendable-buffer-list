package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

public class StringPrefixBufferNode implements IBufferNode {

    private final String content;
    private final int length;

    public StringPrefixBufferNode(String content, int length) {
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
    public byte[] bytes() {
        return content.substring(0, length).getBytes();
    }

    @Override
    public int byteLength() {
        return bytes().length;
    }
}
