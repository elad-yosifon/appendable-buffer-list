package io.github.abl;

public class BufferNode {

    private final char[] content;
    private final int contentLength;

    public BufferNode(char[] content, int contentLength) {
        validate(content, contentLength);
        this.content = content;
        this.contentLength = contentLength;
    }

    public char[] getContent() {
        return content;
    }

    public int getContentLength() {
        return contentLength;
    }

    private static void validate(char[] content, int contentLength) {
        if (contentLength < 1) {
            throw new IllegalStateException("Content length should be greater than 0");
        }
        if (contentLength > content.length) {
            throw new IllegalStateException("Declared content length should be less than or equal to the actual content length");
        }
    }
}
