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
        a[c] = c + 1;
        b[c] = c + 1;
    }
}

__global__ void dot(int *a, int *b, int *c)
{
    int i = blockIdx.x * blockDim.x + threadIdx.x;
    while(i < N)
    {
        c[i] = a[i] * b[i];
        i += blockDim.x * gridDim.x;
    }
}

int main(int argc, char **argv)
{
    int host_a[N], host_b[N], host_c[N];
    int *dev_a, *dev_b, *dev_c;
    int i;

    fill_vectors(host_a, host_b);

    HANDLE_ERROR( cudaMalloc(&dev_a, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_b, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_c, sizeof(int) * N) );

    cudaMemcpy(dev_a, host_a, sizeof(int) * N, cudaMemcpyHostToDevice);
    cudaMemcpy(dev_b, host_b, sizeof(int) * N, cudaMemcpyHostToDevice);

    dot<<<256, 256>>>(dev_a, dev_b, dev_c);

    cudaMemcpy(host_c, dev_c, sizeof(int) * N, cudaMemcpyDeviceToHost);

    cudaFree(dev_a);
    cudaFree(dev_b);
    cudaFree(dev_c);

    for(i = 1; i < N; i++)
    {
        host_c[0] += host_c[i];
    }

    printf("%d\n", host_c[0]);
    if (host_c[0] != N * (N + 1) * (2 * N + 1) / 6)
    {
        printf("FAIL!\n");
    }
}
