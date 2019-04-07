package io.github.abl.impl.node;

import io.github.ifc.IBufferNode;

public class StringBufferNode implements IBufferNode {

    private final String content;

    public StringBufferNode(String content) { ;
        this.content = content;
    }

    @Override
    public byte[] bytes() {
        return content.getBytes();
    }

    @Override
    public int byteLength() {
        return bytes().length;
    }
}
