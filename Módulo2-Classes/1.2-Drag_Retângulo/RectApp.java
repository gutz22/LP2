import java.util.Scanner;

class Rect {
    int x, y; 
    int w, h;
    Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void print() {
		System.out.format("Tamanho (%d,%d) / Posição (%d,%d)\n",
		this.w, this.h, this.x, this.y);
    }
    int area() {
        return this.w * this.h;
    }
    void drag (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}

public class RectApp {
    public static void main(String[] args) {
        Rect r1 = new Rect(100, 100, 70, 45);
        r1.print();
        System.out.printf("A área do retângulo é de %dpx.\n", r1.area());

        Scanner in  = new Scanner(System.in);
        System.out.print("Diga o valor a ser arrastado no eixo x: ");
        int dx = in.nextInt();
        System.out.print("Diga o valor a ser arrastado no eixo y: ");
        int dy = in.nextInt();
        r1.drag(dx,dy);
        r1.print();
        in.close();
    }
}
