n = int(input())
a = []

def mergeSort(A, l, r):

    if l < r:
        mid = int((l+r)/2)
        mergeSort(A, l, mid)
        mergeSort(A, mid+1, r)
        merge(A, l, mid, r)

def merge(A, l, mid, r):
    temp = []
    i, j = l, mid+1

    while (i <= mid and j <= r):
        if A[i][0] < A[j][0]:
            temp.append(A[i])
            i += 1
        elif A[i][0] > A[j][0]:
            temp.append(A[j])
            j += 1
        else:
            if A[i][1] < A[j][1]:
                temp.append(A[i])
                i += 1
            else:
                temp.append(A[j])
                j += 1
    
    while i <= mid:
        temp.append(A[i])
        i += 1
    
    while j <= r:
        temp.append(A[j])
        j += 1

    i, t = l, 0
    while i <= r:
        A[i] = temp[t]
        i += 1
        t += 1

for i in range(n):
    x, y = map(int, input().split())
    a.append((x, y))

mergeSort(a, 0, n-1)

for i in range(n):
    print(f'{a[i][0]} {a[i][1]}')



