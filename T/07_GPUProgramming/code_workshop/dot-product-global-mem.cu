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

__global__ void dot(int *a, int *b, int *temp, int *c)
{
    int outputIndex = blockIdx.x * blockDim.x + threadIdx.x;
    int i = outputIndex;
    int result = 0;

    /* multiplication step: compute partial sum */
    while(i < N)
    {
        result += a[i] * b[i];
        i += blockDim.x * gridDim.x;
    }

    temp[outputIndex] = result;

    /* wait for all threads to be done multiplying */
    __syncthreads();

    /* reduction step: sum all entries in the block and write to c */
    /* this requires that blockDim.x be a power of two! */
    i = blockDim.x / 2;
    while (i != 0)
    {
        /* only threads 0 through i are busy */
        if (threadIdx.x < i)
        {
            /* sum our output element with the one half a block away */
            temp[outputIndex] += temp[outputIndex + i];
        }

        /* wait for all threads within the block */
        __syncthreads();

        i /= 2;
    }

    /* thread 0 writes the results for this block */
    if (threadIdx.x == 0)
    {
        c[blockIdx.x] = temp[outputIndex];
    }
}

int main(int argc, char **argv)
{
    int host_a[N], host_b[N], host_c[BLOCKS];
    int *dev_a, *dev_b, *dev_tmp, *dev_c;
    int i;

    fill_vectors(host_a, host_b);

    HANDLE_ERROR( cudaMalloc(&dev_a, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_b, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_tmp, sizeof(int) * N) );
    HANDLE_ERROR( cudaMalloc(&dev_c, sizeof(int) * BLOCKS) );

    cudaMemcpy(dev_a, host_a, sizeof(int) * N, cudaMemcpyHostToDevice);
    cudaMemcpy(dev_b, host_b, sizeof(int) * N, cudaMemcpyHostToDevice);

    dot<<<BLOCKS, THREADS_PER_BLOCK>>>(dev_a, dev_b, dev_tmp, dev_c);
    cudaMemcpy(host_c, dev_c, sizeof(int) * BLOCKS, cudaMemcpyDeviceToHost);

    /* finish the sum on the CPU */
    for(i = 1; i < BLOCKS; i++)
    {
        host_c[0] += host_c[i];
    }

    cudaFree(dev_a);
    cudaFree(dev_b);
    cudaFree(dev_tmp);
    cudaFree(dev_c);

    printf("%d\n", host_c[0]);
    if (host_c[0] != N * (N + 1) * (2 * N + 1) / 6)
    {
        printf("FAIL!\n");
    }
}
