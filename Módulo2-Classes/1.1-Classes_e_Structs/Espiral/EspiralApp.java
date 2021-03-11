import java.lang.String;

class Espiral {
    int x, y;
    int raio;
    int numeroDeVoltas;
    boolean aberto;
    String cor;
    
    Espiral(int x, int y, int raio, int numeroDeVoltas, boolean aberto, String cor) {
        this.x = x;
        this.y = y;
        this.raio = raio;
        this.numeroDeVoltas = numeroDeVoltas;
        this.aberto = aberto;
        this.cor = cor;
    }
    void print() {
        System.out.format("Posição (%d, %d) / Diâmetro (%d) / Número de voltas (%d) / Cor (%s) / É aberto? ", 
        this.x, this.y, this.raio * 2, this.numeroDeVoltas, this.cor);
        if (this.aberto)
            System.out.println(this.aberto + ", É aberto");
        else
            System.out.println(this.aberto + ", É fechado");
    }
}

public class EspiralApp {
    public static void main (String[] args) {
        Espiral esp = new Espiral(50, 50, 20, 5, true, "laranja");
        esp.print();
    }
}
