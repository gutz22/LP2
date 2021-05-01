#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r,g,b;
} Color;

Color aqua = {0, 255, 255};
Color crimson = {220, 0, 60};  
Color gold = {255, 215, 0};
Color purple = {128, 0, 128};
Color forestGreen = {34, 139, 24};
Color plum = {221, 160, 221};
Color darkOrange = {255, 140, 0};
Color silver = {192, 192, 192};
Color saddleBrown = {139, 69, 19};

typedef struct {
    int xb,yb;
    int wb, hb;
} Border;

struct Figure;
typedef void (* Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    int w, h;
    Color fg, bg;
    Border b;
    void (* print) (struct Figure*);
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Rect;

void rect_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Retangulo de tamanho (%d,%d) na posicao (%d,%d) com cor de fundo (%d,%d,%d), cor de contorno (%d,%d,%d) e borda (%d,%d,%d,%d).\n",
           sup->w,sup->h, sup->x,sup->y, sup->bg.r,sup->bg.g,sup->bg.b, 
           sup->fg.r,sup->fg.g,sup->fg.b, sup->b.xb,sup->b.yb,sup->b.wb,sup->b.hb);
}

Rect* rect_new (int x, int y, int w, int h, Color bg, Color fg) {
    Rect*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) rect_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;
    sup->bg = bg;
    sup->fg = fg;
    sup->b.xb = sup->x-2; sup->b.yb = y-2;
    sup->b.wb = sup->w+4; sup->b.hb = h+4;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Ellipse;

void Ellipse_print (Ellipse* this) {
    Figure* sup = (Figure*) this;
    printf("Elipse de tamanho (%d,%d) na posicao (%d,%d) com cor de fundo (%d,%d,%d), cor de contorno (%d,%d,%d) e borda (%d,%d,%d,%d).\n",
        sup->w,sup->h, sup->x,sup->y, sup->bg.r,sup->bg.g,sup->bg.b,
        sup->fg.r,sup->fg.g,sup->fg.b, sup->b.xb,sup->b.yb,sup->b.wb,sup->b.hb);   
}

Ellipse* ellipse_new (int x, int y, int w, int h, Color bg, Color fg) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Ellipse_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;
    sup->bg = bg;
    sup->fg = fg;
    sup->b.xb = sup->x-2; sup->b.yb = y-2;
    sup->b.wb = sup->w+4; sup->b.hb = h+4;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} LineSegment;

void LineSegment_print (Rect* this) {
    Figure* sup = (Figure*) this;
    printf("Linha de tamanho (%d,%d) na posicao (%d,%d) com cor de fundo (%d,%d,%d) e borda (%d,%d,%d,%d).\n",
           sup->w,sup->h, sup->x,sup->y, sup->bg.r,sup->bg.g,sup->bg.b, sup->b.xb,sup->b.yb,sup->b.wb,sup->b.hb);
}

LineSegment* lineSegment_new (int x, int y, int w, int h, Color bg) {
    LineSegment*   this  = malloc(sizeof(Rect));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) LineSegment_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;
    sup->bg = bg;
    sup->b.xb = sup->x-2; sup->b.yb = y-2;
    sup->b.wb = sup->w+4; sup->b.hb = h+4;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Lozenge;

void Lozenge_print (Lozenge* this) {
    Figure* sup = (Figure*) this;
    printf("Lozango de tamanho (%d,%d) na posicao (%d,%d) com cor de fundo (%d,%d,%d), cor de contorno (%d,%d,%d) e borda (%d,%d,%d,%d).\n",
        sup->w,sup->h, sup->x,sup->y, sup->bg.r,sup->bg.g,sup->bg.b,
        sup->fg.r,sup->fg.g,sup->fg.b, sup->b.xb,sup->b.yb,sup->b.wb,sup->b.hb);  
}

Lozenge* lozenge_new (int x, int y, int w, int h, Color bg, Color fg) {
    Lozenge* this = malloc(sizeof(Lozenge));
    Figure* sup = (Figure*) this;
    sup->print = (Figure_Print) Lozenge_print;
    sup->x = x;
    sup->y = y;
    sup->w = w;
    sup->h = h;
    sup->bg = bg;
    sup->fg = fg;
    sup->b.xb = sup->x-7; sup->b.yb = sup->y-7;
    sup->b.wb = sup->w+14; sup->b.hb = sup->h+14;
}

///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Figure* figs[8] = {
        (Figure*) rect_new(10,10,100,100, aqua, gold),
        (Figure*) ellipse_new(40,10,140,300, crimson, darkOrange),
        (Figure*) rect_new(10,10,100,100, forestGreen, saddleBrown),
        (Figure*) ellipse_new(210,110,305,130, gold, silver),
        (Figure*) lineSegment_new(85,90,70,50, plum),
        (Figure*) lineSegment_new(20,200,60,140, saddleBrown),
        (Figure*) lozenge_new(30,30,80,80, crimson, plum),
        (Figure*) lozenge_new(20,130,60,60, aqua, purple)
    };

    ///

    for (int i=0; i<8; i++) {
        figs[i]->print(figs[i]);
    }

    ///

    for (int i=0; i<8; i++) {
        free(figs[i]);
    }
}
