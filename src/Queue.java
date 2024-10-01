
public class Queue {
    private QueueNode head;
    private int size;

    public Queue() {
        this.head = null;
        this.size = 0;
    }

    public QueueNode getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void setHead(QueueNode head) {
        this.head = head;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty(){
        return getHead() == null;
    }
        
    public void insert(Object element){
        QueueNode node = new QueueNode(element);
        if (isEmpty()){
            setHead(node);
        } else {
        QueueNode pointer = getHead();
        while (pointer.getNext() != null){
            pointer = pointer.getNext();
        }
        pointer.setNext(node);
        }
        size++;
    }
    
    public void delete(){
        if (isEmpty()){
            System.out.println("No hay nodos en la cola");
        } else {
            QueueNode pointer = getHead();
            setHead(pointer.getNext());
            pointer.setNext(null);
            size--;
        }
    }
    
    public void addQueueFromNode(City graph){
    
    }
}
