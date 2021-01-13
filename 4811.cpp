#include <iostream>
#include <cstring>

using namespace std;

long long d[31][31];
long long calc(int f, int h) {
    if (d[f][h] != -1) return d[f][h];
    if (h == 0) return d[f][h] = calc(f-1, h+1);
    if (f == 0) return 1;
    return d[f][h] = calc(f-1, h+1) + calc(f, h-1);
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(nullptr);

    while (true) {
        memset(d, -1, sizeof(d));
        int n;
        cin >> n;
        if (n == 0) break;
        cout << calc(n, 0) << '\n';
    }

    return 0;
}