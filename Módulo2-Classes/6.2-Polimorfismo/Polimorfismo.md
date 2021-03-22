### Polimorfismo com despacho dinâmico
É uma característica de linguagens orientadas a objeto, no qual é possível invocar diferentes métodos de mesmo nome a partir de diferentes classes. Veremos um simples exemplo a seguir:
```
class Animal {
  public void animalSound() {
    System.out.println("The animal makes a sound");
  }
}

class Pig extends Animal {
  public void animalSound() {
    System.out.println("The pig says: wee wee");
  }
}

class Dog extends Animal {
  public void animalSound() {
    System.out.println("The dog says: bow wow");
  }
}
```
Notemos que o código acima descreve uma superclasse com o método `animalSound`, em que suas classes derivadas `Pig` e `Dog` o herdam porém sobrepondo sua funcionalidade ao alterarem seu bloco de execução.

Esse tipo de polimorfismo, também conhecido como polimorfismo de inclusão/ vinculação tardia/ vinculação dinâmica, diferentemente do polimorfismo estático, ocorre em tempo de execução e seus métodos herdados devem conter o mesmo número de argumentos de sua implementação na classe pai (ou interface), pois caso contrário o método já não seria mais o mesmo, o que seria chamado de "method overloading".
```
class Main {
  public static void main(String[] args) {
    Animal myAnimal = new Animal();  // Create a Animal object
    Animal myPig = new Pig();  // Create a Pig object
    Animal myDog = new Dog();  // Create a Dog object
    myAnimal.animalSound();
    myPig.animalSound();
    myDog.animalSound();
  }
}

// The animal makes a sound
// The pig says: wee wee
// The dog says: bow wow
```
Tendo como base o trecho de código anterior, fica visível que o método a ser executado é determinado com base no tipo de objeto em contexto no momento da invocação (despacho dinâmico). Tal conceito pode ser interpretado como um contraste ao "duck typing", o qual é um tipo de abstração em que o comportamento é dado a partir do método independentemente do tipo do objeto.

Dessa forma, a vinculação dinâmica consiste em uma forma de se executar uma mesma tarefa para cada contexto especifíco de uma classe com suas próprias implementações, sendo assim útil pela sua simplicidade e reutilização de código.


#### Bibliografia
- [w3schools](https://www.w3schools.com/java/java_polymorphism.asp#:~:text=Polymorphism%20means%20%22many%20forms%22%2C,methods%20to%20perform%20different%20tasks.)
- [geeksforgeeks](https://www.geeksforgeeks.org/dynamic-method-dispatch-runtime-polymorphism-java/)
- [devmedia](https://www.devmedia.com.br/uso-de-polimorfismo-em-java/26140)
- [qastack](https://qastack.com.br/programming/20783266/what-is-the-difference-between-dynamic-and-static-polymorphism-in-java)
- [javatpoint](https://www.javatpoint.com/pt/conceitos-de-poo-em-java)
- [stackexchange](https://softwareengineering.stackexchange.com/questions/121778/is-duck-typing-a-subset-of-polymorphism)
