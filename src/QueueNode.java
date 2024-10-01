
public class QueueNode {
    private Object element;
    private QueueNode next;

    public QueueNode(Object element) {
        this.element = element;
        this.next = null;
    }

    public Object getElement() {
        return element;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}
