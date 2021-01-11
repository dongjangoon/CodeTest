#include <iostream>
#include <queue>
#include <string.h>

using namespace std;

int dx[] = {0,0,-1,1};
int dy[] = {1,-1,0,0};
int n, m;
string a[1500];
bool wcheck[1500][1500];
bool scheck[1500][1500];
queue<pair<int, int>> water, nwater, swan, nswan;

void simulate() {
    while (!water.empty()) {
        int x = water.front().first;
        int y = water.front().second;
        water.pop();
        a[x][y] = '.';

        for (int k = 0; k < 4; k++) {
            int nx = x+dx[k];
            int ny = y+dy[k];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (wcheck[nx][ny]) continue;
            if (a[nx][ny] == '.') {
                wcheck[nx][ny] = true;
                water.push(make_pair(nx, ny));
            }
            wcheck[nx][ny] = true;
            nwater.push(make_pair(nx, ny));
        }
    }
}

void move() {
    while (!swan.empty()) {
        int x = swan.front().first;
        int y = swan.front().second;
        swan.pop();

        for (int k = 0; k < 4; k++) {
            int nx = x+dx[k];
            int ny = y+dy[k];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (scheck[nx][ny]) continue;
            if (a[nx][ny] == '.') {
                scheck[nx][ny] = true;
                swan.push(make_pair(nx, ny));
            }
            scheck[nx][ny] = true;
            nswan.push(make_pair(nx, ny));
        }
    }
}


int main() {
    int x1, y1, x2, y2;
    x1=y1=x2=y2=-1;

    cin >> n >> m;
    for (int i = 0; i < n ; i++) {
        cin >> a[i];
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 'L') { // 백조 좌표 기록, 물로 바꿔줌
                if (x1 == -1) {
                    x1 = i, y1 = j;
                }
                else {
                    x2 = i, y2 = j;
                }
                a[i][j] = '.';
            }
            if (a[i][j] == '.') {
                water.push(make_pair(i, j));
                wcheck[i][j] = true;
            }
        } 
    }
    swan.push(make_pair(x1, y1));
    scheck[x1][y1] = true;

    int ans = 0;
    while (true) {
        
        simulate();
        move();

        if (scheck[x2][y2]) {
            cout << ans << '\n';
            break;
        }
        water = nwater;
        swan = nswan;
        nwater = queue<pair<int, int>>();
        nswan = queue<pair<int, int>>();
        ans++;
    }
    return 0;
}