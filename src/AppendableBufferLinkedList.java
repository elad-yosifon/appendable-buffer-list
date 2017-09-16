
import java.util.LinkedList;

public class AppendableBufferLinkedList extends LinkedList<BufferNode> implements IAppendableBufferList {

    private int contentLength;

    private AppendableBufferLinkedList(){
    }

    public static IAppendableBufferList create(){
        return new AppendableBufferLinkedList();
    }

    @Override
    public boolean add(BufferNode node) {
        if (node.getContentLength() > 0) {
            this.contentLength += node.getContentLength();
            return super.add(node);
        }
        return false;
    }

    @Override
    public boolean add(char[] node, int contentLength) {
        if (contentLength > 0) {
            //TODO: throw exception in case of an empty node
            this.contentLength += contentLength;
            return super.add(new BufferNode(node, contentLength));
        }
        return false;
    }

    @Override
    public char[] join() {
        char[] bf = new char[contentLength];
        int cur = 0;
        for (BufferNode node : this) {
            System.arraycopy(node.content, 0, bf, cur, node.contentLength);
            cur += node.contentLength;
        }
        return bf;
    }

}
