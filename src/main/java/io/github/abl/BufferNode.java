package io.github.abl;

public class BufferNode {

    final char[] content;
    final int contentLength;

    public BufferNode(char[] content, int contentLength) {
        // TODO: ensure byte[] size matches length
        this.content = content;
        this.contentLength = contentLength;
    }

    public char[] getContent() {
        return content;
    }

    public int getContentLength() {
        return contentLength;
    }
}
