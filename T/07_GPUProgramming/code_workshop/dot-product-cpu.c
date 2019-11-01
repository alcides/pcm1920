#include <stdio.h>

#define N 1000

void print_vector(int *v)
{
    int c;
    for(c = 0; c < N; c++)
    {
        printf("%2d ", v[c]);
    }

    printf("\n");
}

void fill_vectors(int *a, int *b)
{
    int c;

    for(c = 0; c < N; c++)
    {
        a[c] = c + 1;
        b[c] = c + 1;
    }
}

void dot(int *a, int *b, int *c)
{
    int i;

    *c = 0;
    for(i = 0; i < N; i++)
    {
        *c += a[i] * b[i];
    }
}

int main(int argc, char **argv)
{
    int a[N], b[N], c;

    fill_vectors(a, b);
    dot(a, b, &c);
    printf("%d\n", c);
    if (c != N * (N + 1) * (2 * N + 1) / 6)
    {
        printf("FAIL!\n");
    }
}

