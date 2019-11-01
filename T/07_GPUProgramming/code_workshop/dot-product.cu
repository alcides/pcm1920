#include <stdio.h>
#include "workshop.h"

#define N 1000

#define THREADS_PER_BLOCK 256
/* we launch at most 32 blocks in an attempt to keep the GPU busy
   this value is somewhat arbitrary and should be tweaked for performance */
#define BLOCKS MIN(32, (N + THREADS_PER_BLOCK - 1) / THREADS_PER_BLOCK)

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
    /* shared memory cache for partial sum results */
    __shared__ int cache[THREADS_PER_BLOCK];

    int i = blockIdx.x * blockDim.x + threadIdx.x;
    int result = 0;

    /* multiplication step: write a partial sum into the cache */
    while(i < N)
    {
        result += a[i] * b[i];
        i += blockDim.x * gridDim.x;
    }

    cache[threadIdx.x] = result;

    /* wait for all other threads in the same block */
    __syncthreads();

    /* reduction step: sum all entries in the cache */
    i = blockDim.x / 2;
    while (i != 0)
    {
        /* only threads 0 through i are busy */
        if (threadIdx.x < i)
        {
            cache[threadIdx.x] += cache[threadIdx.x + i];
        }

        /* wait for all threads within the block */
        __syncthreads();

        i /= 2;
    }

    /* thread 0 writes the result for this block */
    if (threadIdx.x == 0)
    {
        c[blockIdx.x] = cache[0];
    }
}

int main(int argc, char **argv)
{
    int host_a[N], host_b[N], c[BLOCKS];
    int *dev_a, *dev_b, *dev_c;
    int i;

    fill_vectors(host_a, host_b);

    HANDLE_ERROR( cudaMalloc(&dev_a, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_b, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_c, sizeof(int) * BLOCKS) );

    cudaMemcpy(dev_a, host_a, sizeof(int) * N, cudaMemcpyHostToDevice);
    cudaMemcpy(dev_b, host_b, sizeof(int) * N, cudaMemcpyHostToDevice);

    dot<<<BLOCKS, THREADS_PER_BLOCK>>>(dev_a, dev_b, dev_c);
    cudaMemcpy(c, dev_c, sizeof(int) * BLOCKS, cudaMemcpyDeviceToHost);

    /* finish the sum on the CPU */
    for(i = 1; i < BLOCKS; i++)
    {
        c[0] += c[i];
    }

    cudaFree(dev_a);
    cudaFree(dev_b);
    cudaFree(dev_c);

    printf("%d\n", c[0]);
    if (c[0] != N * (N + 1) * (2 * N + 1) / 6)
    {
        printf("FAIL!\n");
    }
}
