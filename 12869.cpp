#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>

using namespace std;

int n;
int scv[3];
int d[61][61][61];

int go(int a, int b, int c) {
    if (a < 0) return go(0, b, c);
    if (b < 0) return go(a, 0, c);
    if (c < 0) return go(a, b, 0);

    if (a == 0 && b == 0 && c == 0) {
        return 0;
    }

    int &ans = d[a][b][c];
    if (ans != -1) return ans;
    ans = 1000000;
    vector<int> p = {1, 3, 9};
    do {
        if (ans > go(a-p[0], b-p[1], c-p[2])) {
            ans = go(a-p[0], b-p[1], c-p[2]);
        }
    } while (next_permutation(p.begin(), p.end()));
    ans += 1;
    return ans;
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> scv[i];
    }

    memset(d, -1, sizeof(d));

    cout << go(scv[0], scv[1], scv[2]) << '\n';

    return 0;
}