**Input**:

*n*, an integer representing size of Sudoku board. Followed by *w*, the width of the internal region. 

Base 36(0-9, A-Z)
$$ {asdf}
2 \leqslant n \leqslant 36
$$
Empty spots are '0'. 

Finally, a zero to indicate the end of input OR a new n to signal the beginning of a new test case.

Example:

```
12 4
0 0 3 4 A 8 0 0 0 2 0 0
0 0 0 1 0 0 0 0 0 0 0 4
0 8 0 0 0 4 3 0 5 0 0 0
0 0 0 0 8 0 0 9 0 0 0 A
8 0 0 C 7 0 4 0 9 6 0 3
A 1 9 3 B 6 0 0 0 0 C 0
0 3 0 0 0 0 6 4 2 A 5 B
1 0 4 2 0 A 0 5 3 0 0 8
C 0 0 0 3 0 0 7 0 0 0 0
0 0 0 5 0 7 9 0 0 0 A 0
3 0 0 0 0 0 0 0 6 0 0 0
0 0 C 0 0 0 2 8 4 B 0 0
0
```

### Output:

If it's possible to solve, prints "Solved:" and under that the complete solution.

Include spaces and line-breaks to visually indicate the internal regions.

If it's NOT possible to solve, prints "IMPOSSIBLE!"

#### Example:

```
Solved:
5 9 3 4  A 8 1 6  7 2 B C  
2 C 6 1  5 9 7 B  A 3 8 4  
B 8 A 7  2 4 3 C  5 1 6 9 

4 2 7 6  8 3 C 9  B 5 1 A  
8 5 B C  7 1 4 A  9 6 2 3  
A 1 9 3  B 6 5 2  8 4 C 7 

7 3 8 9  1 C 6 4  2 A 5 B  
1 6 4 2  9 A B 5  3 C 7 8  
C A 5 B  3 2 8 7  1 9 4 6 

6 B 1 5  4 7 9 3  C 8 A 2  
3 4 2 8  C B A 1  6 7 9 5  
9 7 C A  6 5 2 8  4 B 3 1  
```