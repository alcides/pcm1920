#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <cuda.h>
#include "workshop.h"

#define N 10000000

unsigned char *fill_random_buffer(int size)
{
    unsigned char *ret;
    int c;

    ret = (unsigned char *) malloc(size);
    assert(ret);

    for(c = 0; c < size; c++)
    {
        ret[c] = rand();
    }

    return ret;
}

__global__ void compute_histogram(unsigned char *data, unsigned int *histogram)
{
    __shared__ unsigned int cache[256];
    int i = blockIdx.x * blockDim.x + threadIdx.x;
    
    cache[threadIdx.x] = 0;
    __syncthreads();

    while(i < N)
    {
        atomicAdd(&cache[data[i]], 1);
        i += blockDim.x * gridDim.x;
    }

    __syncthreads();
    atomicAdd(&histogram[threadIdx.x], cache[threadIdx.x]);
}

int main(int argc, char **argv)
{
    unsigned char *data = (unsigned char *) fill_random_buffer(N);
    unsigned int histogram[256];
    int c, sum;

    unsigned char *dev_data;
    unsigned int *dev_histogram;

    HANDLE_ERROR( cudaMalloc(&dev_data, N) );
    HANDLE_ERROR( cudaMalloc(&dev_histogram, sizeof(unsigned int) * 256) );

    cudaMemcpy(dev_data, data, N, cudaMemcpyHostToDevice);
    cudaMemset(dev_histogram, 0, sizeof(unsigned int) * 256);

    compute_histogram<<<30, 256>>>(dev_data, dev_histogram);

    cudaMemcpy(histogram, dev_histogram, sizeof(unsigned int) * 256, cudaMemcpyDeviceToHost);

    sum = 0;
    for(c = 0; c < 256; c++)
    {
        printf("%3d = %d\n", c, histogram[c]);
        sum += histogram[c];
    }

    if (sum != N)
    {
        printf("FAIL!\n");
    }

    free(data);
}
