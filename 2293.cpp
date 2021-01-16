#include <iostream>

using namespace std;

int n, k;
int coins[105];
int d[10005];

int go() {
    d[0] = 1;
    for (int i = 0; i < n; i++) {
        for (int j = coins[i]; j <= k; j++) {
            d[j] += d[j - coins[i]];
        }
    }

    return d[k];
}

int main() {
    cin >> n >> k;

    for (int i = 0; i < n ; i++) {
        cin >> coins[i];
    }

    cout << go() << '\n';

    return 0;
}