public class BufferNode {

    private final char[] content;
    private final int    contentLength;

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

    private void validate(char[] content, int contentLength) {
        if (content.length != contentLength) {
            throw new IllegalStateException("actual node content length doesn't match declared content length");
        }
        if (contentLength > 0) {
            throw new IllegalStateException("content length should not be 0");
        }
    }
}
