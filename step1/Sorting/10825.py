n = int(input())

def quickSort(A, p, r):

    if p < r:
        q = partition(A, p, r)
        quickSort(A, p, q-1)
        quickSort(A, q+1, r)

def partition(A, p, r):

    x, i = A[r], p-1
    for j in range(p, r):
        if A[j][1] > x[1]:
            i += 1
            A[i], A[j] = A[j], A[i]
        elif A[j][1] == x[1]:
            if A[j][2] < x[2]:
                i += 1
                A[i], A[j] = A[j], A[i]
            elif A[j][2] == x[2]:
                if A[j][3] > x[3]:
                    i += 1
                    A[i], A[j] = A[j], A[i]
                elif A[j][3] == x[3]:
                    if A[j][0] < x[0]:
                        i += 1
                        A[i], A[j] = A[j], A[i]
    
    A[i+1], A[r] = A[r], A[i+1]
    return i+1

a = []
for i in range(n):
    b = input().split()
    a.append((b[0], int(b[1]), int(b[2]), int(b[3])))

quickSort(a, 0, n-1)

for i in range(n):
    print(f'{a[i][0]}')
