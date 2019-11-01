#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

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

void compute_histogram(unsigned char *data, unsigned int *histogram)
{
    int c;

    memset(histogram, 0, sizeof(int) * 256);

    for(c = 0; c < N; c++)
    {
        histogram[data[c]]++;
    }
}

int main(int argc, char **argv)
{
    unsigned char *data = (unsigned char *) fill_random_buffer(N);
    unsigned int histogram[256];
    int c, sum;

    compute_histogram(data, histogram);

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
