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
        a[c] = c;
        b[c] = N - c;
    }
}

void sum(int *a, int *b, int *c)
{
    int i;

    for(i = 0; i < N; i++)
    {
        c[i] = a[i] + b[i];
    }
}

int main(int argc, char **argv)
{
    int a[N], b[N], c[N];
    int i;

    fill_vectors(a, b);
    sum(a, b, c);
    print_vector(c);

    for(i = 0; i < N; i++)
    {
        if (c[i] != N)
        {
            printf("FAIL!\n");
            break;
        }
    }
}

