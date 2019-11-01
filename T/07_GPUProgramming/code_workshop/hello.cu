#include <stdio.h>

__global__ void empty_kernel(void)
{
}

int main(int argc, char **argv)
{
    empty_kernel<<<1, 1>>>();
    printf("Hello World!\n");
    return 0;
}
