class Nodo<TipoDeDato> { //TODO Verificar si efectivamente esta clase está lista para ser usada como Nodo en una lista doblemente encadenada
    TipoDeDato dato;
    Nodo<TipoDeDato> siguiente;
    Nodo<TipoDeDato> anterior;

    public Nodo(TipoDeDato data){
        this.dato = data;
        this.siguiente = null;
        this.anterior = null;
    }

    public TipoDeDato getDato() {
        return dato;
    }
}

class MySimpleLinkedList<T>{ //TODO Ahora es lista doblemente encadenada
    Nodo<T> first;
    Nodo<T> last; //TODO Actualizar todos los métodos para que tengan en cuenta este nuevo atributo
    Integer count; //TODO Verificar que efectivamente todos los métodos que modifiquen el número de elementos en la lista (insertar, eliminar, etc.) efectivamente actualicen el valor de este atributo

    public MySimpleLinkedList(){
        this.makeEmpty();
    }

    public MySimpleLinkedList(T[] array){
        int longitud=array.length;
        count=0;
        for (int i=0;i<longitud;i++){
            this.insertEnd(array[i]);
        }
    }

    void insertEnd(T toInsert){
        //TODO Verificar si se deben tomar consideraciones adicionales aquí
        Nodo<T> nuevoNodo = new Nodo<>(toInsert);
        if (count!=0 && count!=1){           
            nuevoNodo.anterior = this.last;
            this.last = nuevoNodo;
            this.last.anterior.siguiente=this.last;
            this.count++;
        }
        else if (count==1){   
            this.last.siguiente= nuevoNodo;
            nuevoNodo.anterior = this.last;
            this.last = nuevoNodo;
            this.count++;
        }
        else{
            this.first=nuevoNodo;
            this.last=nuevoNodo;
            this.count++;
        }
    }

    void insertBegin(T toInsert){ //O(1)
        Nodo<T> nuevoNodo = new Nodo<>(toInsert);
        if (count!=0 && count!=1){           
            this.first.anterior= nuevoNodo;
            nuevoNodo.siguiente = this.first;
            this.first = nuevoNodo;
            this.count++;
        }
        else if (count==1){   
            this.last.anterior= nuevoNodo;
            nuevoNodo.siguiente = this.last;
            this.first = nuevoNodo;
            this.count++;
        }
        else{
            this.first=nuevoNodo;
            this.last=nuevoNodo;
            this.count++;
        }
    }

    void deleteBegin(){ //O(1)
        //Nodo<T> aux = this.first.siguiente;
        //this.first = aux;

        if(this.first == null){
            System.err.println("Error, no puedo eliminar de una lista vacía");
            return;
        }
        if(count==1){
            this.makeEmpty();
        }else{
            this.first = this.first.siguiente;
            this.first.anterior=null;
            this.count--;
        }
    }

    String find(T data){ //9
	    String indices="";
        if(count == 0){
            System.err.println("Error, la lista está vacía");
            return "";
        }
        Nodo<T> aux = this.first;
        Integer index = 0;
        while (aux != null){ //[9,9,9,9,9,9]
            if (data.equals(aux.dato)){
                indices+=(Integer.toString(index)+" "); //TODO Retorne todos los índices en los cuales hay match
            }
            index++;
            aux = aux.siguiente;
        } //(8n) => O(n+n) => O(n)
        if(indices.equals("")){
                System.out.println("Error, elemento no encontrado");
                return "";
        }
            return indices;
    }

    protected void makeEmpty(){
        this.count = 0;
        this.first = null;
	    this.last=null;
    }

    private boolean checkarIndice(int k){
        return k < 0 || k > this.count;
    }

    private Nodo<T> read(int k){ //[0, n-1]
        if(k < 0 || k >= this.count){
            System.out.println("No es posible realizar la búsqueda");
            return null;
        }
        
        Nodo<T> aux = this.first;
        for(int i = 0; i < k; i++)
            aux = aux.siguiente;
        return aux;
    }
    T readValor(int k){ //[0, n-1]
        if(k < 0 || k >= this.count){
            System.out.println("No es posible realizar la búsqueda");
            return null;
        }
        Nodo<T> aux = this.first;
        for(int i = 0; i < k; i++)
            aux = aux.siguiente;
        return aux.dato;
    }
    
    void insert(int k, T data){ //O(n)
        if(this.checkarIndice(k)){
            System.out.println("No es posible hacer la inserción en ese índice");
            return;
        }
        if(k == 0){
            this.insertBegin(data);
            return;
        }
        if(k==count){
            this.insertEnd(data);
            return;
        }
        Nodo<T> nuevoNodo = new Nodo<>(data), aux = this.read(k-1);
        nuevoNodo.siguiente = aux.siguiente;
        nuevoNodo.siguiente.anterior=nuevoNodo;
        aux.siguiente = nuevoNodo;
        nuevoNodo.anterior=aux;
        
        this.count++;
        //nuevoNodo.siguiente = this.read(k-1).siguiente; //O(n)
        //this.read(k-1).siguiente = nuevoNodo; //O(n)
        //(2n) => O(n)
    }

  

    //TODO Eliminar en la pocisión K
    void delete(int k){
        if(k<0||k>this.count){
            System.err.println("No se puede borrar");
            return;
        }
        if(count==1){
            this.makeEmpty();
            return;
        }
        if(k == 0){
            this.deleteBegin();
            return;
        }
        if(k==count-1){
            this.deleteEnd();
            return;
        }
	Nodo aux;
	aux=read(k);
	aux.siguiente.anterior=aux.anterior;
	aux.anterior.siguiente=aux.siguiente;
	aux.siguiente=null;
	aux.anterior=null;
	aux=null;
        count--;
    }
	
    //TODO Insertar al final de la lista (Tail)

	
	
	
    //TODO Eliminar al final de la lista (Tail)
    void deleteEnd(){
        if(this.last == null){
                System.err.println("Error, no puedo eliminar de una lista vacía");
                return;
        }
        if (count==1){
            this.makeEmpty();
        }else{
            this.last=this.last.anterior;
            this.last.siguiente=null;
            this.count--;
        }
    }
    //TODO Consultar el elemento en el inicio de la lista (Head)
    T revisarFirst(){
        if(this.first == null){
            System.err.println("Error, no puedo eliminar de una lista vacía");
            return null;
	}
	return this.first.dato;
    }
    //TODO Consultar el elemento al final de la lista (Tail)
    T revisarLast(){
        if(this.last == null){
            System.err.println("Error, no puedo eliminar de una lista vacía");
            return null;
	}
	return this.last.dato;
    }    
    //TODO Modificar el método read para que solo sea accesible dentro de la clase, adicionalmente buscar la manera para reutilizar ese código e implementar un método que retorne únicamente el valor (no el nodo) del elemento en la posición K.
    //TODO Implementar un método que retorna la cantidad de elementos de la lista
    int cantidad(){
	    return this.count;
    }

    //TODO Implementar un método que retorne un booleano indicando si la lista está vacía o no
    boolean estaVacia(){
        boolean a=false;
        if (count==0){
            a=true;
        }
        return a;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> aux = this.first;
        while (aux != null){
            sb.append(aux.dato);
            sb.append(", ");
            aux = aux.siguiente;
        }
        String toReturn = sb.toString();
        toReturn =  toReturn.substring(0, sb.length() - 2);
        return toReturn+"]";
    }

}
class Cola <T> extends MySimpleLinkedList{
    Cola(){
        this.makeEmpty();
    }
    void enqueue(T a){
        this.insertEnd(a);
    }
    Object dequeue(){
        Object toReturn=this.revisarFirst();
        this.deleteBegin();
        return toReturn;
    }
    Object peek(){
        return this.revisarFirst();
    }
    void imprimir(){
        System.out.println(toString());
    }
} 
class Pila <T> extends MySimpleLinkedList{
    Pila(){
        this.makeEmpty();
    }
    void push(T a){
        this.insertEnd(a);
    }
    Object pop(){
        Object toReturn=this.revisarLast();
        this.deleteEnd();
        return toReturn;
    }
    Object top(){
        return this.revisarLast();
    }
    void imprimir(){
        System.out.println(toString());
    }
}
