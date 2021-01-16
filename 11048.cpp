#include <iostream>

using namespace std;

int n, m;
int a[1001][1001];
int d[1001][1001];

int go() {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            int maxNum = max(d[i-1][j-1], max(d[i-1][j], d[i][j-1]));
            d[i][j] = maxNum + a[i][j];
        }
    }

    return d[n][m];
}


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(nullptr);
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cin >> a[i][j];
        }
    }

    cout << go() << '\n';

    return 0;
}