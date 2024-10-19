
public class Route {
    private int size;
    private RouteNode head, tail;

    public Route() {
        this.size = 0;
        this.head = this.tail = null;
    }

    public int getSize() {
        return size;
    }

    public RouteNode getHead() {
        return head;
    }

    public RouteNode getTail() {
        return tail;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHead(RouteNode head) {
        this.head = head;
    }

    public void setTail(RouteNode tail) {
        this.tail = tail;
    }
    
}
