
public class Stack {
    private StackNode peak;
    private int size;

    public Stack() {
        this.peak = null;
        this.size = 0;
    }

    public StackNode getPeak() {
        return peak;
    }

    public void setPeak(StackNode peak) {
        this.peak = peak;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public boolean isEmpty(){
        return peak == null;
    }
    
    public void push(Object element){
        StackNode nodo = new StackNode(element);
        if (isEmpty()){
            setPeak(nodo);
        } else {
            nodo.setNext(getPeak());
            setPeak(nodo);
        }
        size++;
    }
    
    public void pop(){
        if (isEmpty()){
            System.out.println("la pila esta vacia");
        } else {
            StackNode pointer = getPeak();
            setPeak(pointer.getNext());
            pointer.setNext(null);
            size--;
        }
    }
    
    public void print(){
        if (isEmpty()){
            System.out.println("no hay elementos que imprimir");
        } else {
            StackNode aux = getPeak();
            while (aux != null){
                System.out.println(aux.getElement());
                aux = aux.getNext();
            }
        }
    }
}
