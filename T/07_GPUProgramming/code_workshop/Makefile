CC=cc
CFLAGS=
NVCC=nvcc
NVCFLAGS=
TARGETS=hello two-plus-seven vector-sum-cpu vector-sum-serial vector-sum dot-product-cpu dot-product-serial dot-product-global-mem dot-product

all: $(TARGETS)

%: %.c
	$(CC) $(CFLAGS) -o $@ $<

%: %.cu
	$(NVCC) $(NVCFLAGS) -o $@ $<

clean:
	rm -f $(TARGETS)

