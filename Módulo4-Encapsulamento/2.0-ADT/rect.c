#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

typedef struct Rect {
	int x, y;
    int w, h;
} Rect;

Rect* rect_new(void) {
	Rect* this = malloc(sizeof(Rect));
	this->x = 30;
	this->y = 30;
	this->w = 60;
	this->h = 40;
}

void rect_drag(Rect* this, int dx,int dy) {
	this->x += dx;
	this->y += dy;
}

void rect_print(Rect* this) {
	printf("Retângulo de tamanho (%d,%d) na posição (%d,%d).\n", this->w,this->h, this->x,this->y);
}
