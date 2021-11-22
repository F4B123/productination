//Fabian Ruiz fruiz@unal.edu.co
package first;

class Node<T> { 
    T data;
    Node<T> next;
    Node<T> previous;

    public Node(T data){
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public T getdata() {
        return data;
    }
}

class MyDoubleLinkedList<T>{ 
    private Node<T> first;
    private Node<T> last; 
    private Integer count; 

    public MyDoubleLinkedList(){
        this.makeEmpty();
    }

    public MyDoubleLinkedList(T[] array){
        //Crear y enlazar todos los Nodes
        if(array.length == 0){
            this.makeEmpty();
        }        
        Node<T> aux = new Node<>(array[0]);        
        this.first = aux;
        this.last = aux;
        this.count = 1;
        //this.count++;
        for(int i = 1; i< array.length; i++){
            this.insertEnd(array[i]);
        }
    
    }

    void insertEnd(T toInsert){
        
        if(this.count == 0){
            insertBegin(toInsert);
            return;
        }
        Node<T> newNode = new Node<>(toInsert);
        this.last.next = newNode;
        newNode.previous = this.last;
        this.last = newNode;
        this.count++;
    }

    void insertBegin(T toInsert){ //O(1)
        Node<T> newNode = new Node<>(toInsert);
        newNode.next = this.first;
        this.first = newNode;
        this.count++;
        if(this.count == 1){                //posibles inconvenientes con el count en 1.
            this.last = newNode;
        }
    }

    void deleteBegin(){ //O(1)

        if(this.first == null){
            System.err.println("Error, no puedo eliminar de una lista vacía");
            return;
        }
        this.first = this.first.next;
        this.count--;

    }

    void deleteEnd(){ 
        if(this.first == null){
            System.err.println("Error, no puedo eliminar de una lista vacía");
            return;
        }
        this.last = this.last.previous;
        this.last.next = null;
        this.count--;

    }

    void find(T data){ //9
        if(count == 0){
            System.err.println("Error, empty list");
            return;
        }
        Node<T> aux = this.first;
        Integer index = 0;
        boolean confirmed = false;
        while (aux != null){ //[9,9,9,9,9,9]
            if (data.equals(aux.data)){
                confirmed = true;
                System.out.print(index + " ");
                
            }
            index++;
            aux = aux.next;
        }
        if(confirmed == false){
            System.out.println("Error, element not found");
            return;
        }
        System.out.print("\n"); 
        
    }


    boolean isIn(T data){
        if(count == 0){
            return false;
        }
        Node<T> aux = this.first;
        Integer index = 0;
        while (aux != null){ //[9,9,9,9,9,9]
            if (data.equals(aux.data)){
                return true;
                
            }
            index++;
            aux = aux.next;
        }
        return false;
    }

    void makeEmpty(){
        this.count = 0;
        this.first = null;
        this.last = null;
    }

    private boolean checkIndex(int k){
        return k < 0 || k > this.count;
    }

    private Node<T> read(int k){ //[0, n-1]
        if(k < 0 || k >= this.count){
            System.out.println("No es posible realizar la búsqueda");
            return null;
        }
        Node<T> aux = this.first;
        for(int i = 0; i < k; i++)
            aux = aux.next;
        return aux;
    }

    T readElement(int k){
        Node<T> aux = this.read(k);
        return aux.data;
    }

    void insert(int k, T data){ //O(n)
        if(this.checkIndex(k)){
            System.out.println("No es posible hacer la inserción en ese índice");
            return;
        }
        if(k == 0){
            this.insertBegin(data);
            return;
        }
        Node<T> nuevoNode = new Node<>(data), aux = this.read(k-1);
        nuevoNode.next = aux.next;
        aux.next = nuevoNode;
        nuevoNode.previous = aux;
        nuevoNode.next.previous = nuevoNode;
        this.count++;
        //nuevoNode.next = this.read(k-1).next; //O(n)
        //this.read(k-1).next = nuevoNode; //O(n)
        //(2n) => O(n)
    }

    void delete(int k){ //O(n)
        if(this.checkIndex(k)){
            System.out.println("is not possible to delete this index");
            return;
        }
        if(k == 0){
            this.deleteBegin();
            return;
        }
        if(k == this.count-1){
            this.deleteEnd();
            return;
        }
        Node<T> aux = this.read(k-1);
        aux.next = aux.next.next;
        aux.next.previous = aux;
        this.count--;
    }

    public T getHead(){
        return this.first.data;
    }

    public T getTail(){
        return this.last.data;
    }

    public Integer getCount(){
        return this.count;
    }

    public boolean isEmpty(){
        if (this.count == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        if(this.count == 0){
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> aux = this.first;
        while (aux != null){
            sb.append(aux.data);
            sb.append(", ");
            aux = aux.next;
        }
        String toReturn = sb.toString();
        toReturn =  toReturn.substring(0, sb.length() - 2);
        return toReturn+"]";
    }

}