#include <iostream>
#include <cstring>

using namespace std;

int d[52][52][52];

int go(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) return 1;

    int& temp = d[a][b][c];
    if (temp != -1) return temp;

    if (a > 20 || b > 20 || c > 20) return temp = go(20, 20, 20);
    if (a < b && b < c) {
        return temp = go(a, b, c-1) + go(a, b-1, c-1) - go(a, b-1, c);
    }
    return temp = go(a-1, b, c) + go(a-1, b-1, c) + go(a-1, b, c-1) - go(a-1, b-1, c-1);
}

int main() {
    memset(d, -1, sizeof(d));

    while (true) {
        int a, b, c;
        cin >> a >> b >> c;

        if (a == -1 && b == -1 && c == -1) break;

        cout << "w(" << a << ", " << b << ", " << c << ") = " << go(a, b, c) << '\n';
    }

    return 0;
}