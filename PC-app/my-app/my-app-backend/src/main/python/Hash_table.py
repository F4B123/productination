import json
import time


class Hash_table:
    def __init__(self):
        self.capacity = 100 # random number
        self.size = 0
        self.buckets = [None]* self.capacity

    # Computar la clave a un identificador único.
    def hash(self, key):
        hash_sum = 0
        for i in range(len(key)):
            hash_sum =  37 * hash_sum + ord(key[i])
        hash_sum %= self.capacity
        if(hash_sum < 0):
            hash_sum += self.capacity
        return hash_sum

    def insert(self, key, value):
        self.size += 1
        index = self.hash(key)
        node = self.buckets[index]
        if node is None: #No hubo colisión
            self.buckets[index] = Node(key, value)
            return
        #Ocurrió una colisión.
        previous = node
        while node is not None:
            previous = node
            node = node.next
        #Se crea un nodo al final de la lista y se coloca el valor allí.
        previous.next = Node(key, value)

    def find(self, key):
        try:
            index = self.hash(key)
            node = self.buckets[index]
            while node is not None and node.key != key:
                node = node.next
            if node is None:
                return -1 #Not found
            else: #return node
                return node.value
        except:
            return 0
    def remove(self, key):
        index = self.hash(key)
        node = self.buckets[index]
        previous = None
        while node is not None and node.key != key:
            previous = node
            node = node.next
        if node is None:#Not found
            return None
        else:
            self.size -= 1
            deleted = node.value
            if previous is None:
                self.buckets[index] = node.next
            else:
                previous.next = previous.next.next
    
    def toJSON(self):
        return json.dumps(self, default=lambda o: o.__dict__, 
            sort_keys=True, indent=4)

class Node:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.next = None

if __name__ == '__main__':
    ht = Hash_table()
    ht.insert("Hola", 1)
    ht.insert("hola", int(time.time()))
#    ht.remove("Hola")
#    ht.remove("willy")
    ht.insert("Hola", 2)
    print(ht.find("hola"))#No existe -> None
    print(ht.find("Hola"))

#    print(ht.remove("hola"))
#    print(ht.remove("Hola"))
    if ht.find("uww") == -1:
        print("no esta")
#    print(ht.find("Hola").value)

    json_data = ht.toJSON()

#    json_data = json.dumps(ht.__dict__)
    print(json_data)