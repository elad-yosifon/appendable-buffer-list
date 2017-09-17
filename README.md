# Appendable Buffer List:
(should write something here...)


## first benchmark results
ABL(trivial implementation) is 20%-45% faster than StringBuilder when buffer size is greater than 16 bytes, which is StringBuilder default initial capacity.

#### joining 2 buffers:
![Image](benchmark-results/2-buffers.png?raw=true)

#### joining 4 buffers:
![Image](benchmark-results/4-buffers.png?raw=true)

(raw data can be found in [here](benchmark-results/))
