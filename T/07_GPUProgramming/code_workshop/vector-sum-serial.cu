#include <stdio.h>
#include "workshop.h"

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

__global__ void sum(int *a, int *b, int *c)
{
    int i;

    for(i = 0; i < N; i++)
    {
        c[i] = a[i] + b[i];
    }
}

int main(int argc, char **argv)
{
    int host_a[N], host_b[N], host_c[N];
    int *dev_a, *dev_b, *dev_c;
    int i;

    fill_vectors(host_a, host_b);

    HANDLE_ERROR( cudaMalloc(&dev_a, sizeof(int) * N));
    HANDLE_ERROR( cudaMalloc(&dev_b, sizeof(int) * N));
    HANDLE_ERROR( cudaMalloc(&dev_c, sizeof(int) * N));

    cudaMemcpy(dev_a, host_a, sizeof(int) * N, cudaMemcpyHostToDevice);
    cudaMemcpy(dev_b, host_b, sizeof(int) * N, cudaMemcpyHostToDevice);

    sum<<<1, 1>>>(dev_a, dev_b, dev_c);

    cudaMemcpy(host_c, dev_c, sizeof(int) * N, cudaMemcpyDeviceToHost);

    cudaFree(dev_a);
    cudaFree(dev_b);
    cudaFree(dev_c);

    print_vector(host_c);

    for(i = 0; i < N; i++)
    {
        if (host_c[i] != N)
        {
            printf("FAIL!\n");
            break;
        }
    }
}

