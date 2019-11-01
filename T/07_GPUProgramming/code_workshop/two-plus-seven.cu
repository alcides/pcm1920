#include <stdio.h>
#include "workshop.h"

__global__ void add(int a, int b, int *c)
{
    *c = a + b;
}

int main(int argc, char **argv)
{
    int c, *dev_c;
    HANDLE_ERROR( cudaMalloc(&dev_c, sizeof(int)) );
    add<<<1, 1>>>(2, 7, dev_c);
    HANDLE_ERROR( cudaMemcpy(&c, dev_c, sizeof(int),
                             cudaMemcpyDeviceToHost) );
    printf("2 + 7 = %d\n", c);
}
