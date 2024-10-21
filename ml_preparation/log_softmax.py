from math import e
import numpy as np
from sympy import denom

def log_softmax(X):
    X = X - np.max(X)

    sum_term=np.sum(np.power(e,X ))
    log_term = np.log(sum_term)
    return X -log_term


# 1/1+e^-z 
# e^-s1 + e^-s2/  sigma e^-si
    
A = np.array([1, 2, 3])
print(log_softmax(A))

