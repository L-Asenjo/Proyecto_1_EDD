
public class RouteNode {
    private Object element;
    private RouteNode next;
    private RouteNode prev;

    public RouteNode(Object element, RouteNode next, RouteNode prev) {
        this.element = element;
        this.next = this.prev = null;
    }

    public Object getElement() {
        return element;
    }

    public RouteNode getNext() {
        return next;
    }

    public RouteNode getPrev() {
        return prev;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public void setNext(RouteNode next) {
        this.next = next;
    }

    public void setPrev(RouteNode prev) {
        this.prev = prev;
    }
    
}
