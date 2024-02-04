n = int(input())
a = []

def mergeSort(A, p, q):

    if p < q:
        mid = int((p + q) / 2)
        mergeSort(A, p, mid)
        mergeSort(A, mid+1, q)
        merge(A, p, mid, q)

def merge(A, p, mid, q):
    temp = []
    i, j = p, mid+1
    while(i <= mid and j <= q):
        if A[i] >= A[j]:
            temp.append(A[j])
            j += 1
        else:
            temp.append(A[i])
            i += 1

    while i <= mid:
        temp.append(A[i])
        i += 1
    
    while j <= q:
        temp.append(A[j])
        j += 1

    i, t = p, 0
    while i <= q:
        A[i] = temp[t]
        t += 1
        i += 1


for i in range(n):
    a.append(int(input()))

mergeSort(a, 0, n-1)

for i in range(n):
    print(a[i])
