# include <stdio.h>

typedef struct {
    int x;
    int y;
    int numeroDeVertices;
    char cor[20];
    char corDaBorda[20];
} Estrela;

void print(Estrela* est ){ 
    printf("Posição (%d, %d), / Número de Vértices (%d) / Cor (%s) / Cor da Borda (%s)\n", 
    est->x, est->y, est->numeroDeVertices, est->cor, est->corDaBorda);
}

int main() {
    Estrela est = {70, 70, 6, "marrom", "amarelo"};
    print(&est);
}