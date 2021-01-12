#include <iostream>
#include <cstring>
#include <queue>
#include <tuple>

using namespace std;

int n, m;
int a[10][10];
int dist[10][10][20];
int dx[] = {0,0,-1,1};
int dy[] = {-1,1,0,0};

int bfs() {
    memset(dist, -1, sizeof(dist));
    queue<tuple<int, int, int>> q;
    q.push(make_tuple(0, 0, 0));
    dist[0][0][0] = 0;

    while (!q.empty()) {
        int x, y, t;
        tie(x,y,t) = q.front();
        q.pop();
        if (a[x][y] >= 2 && t % a[x][y] != 0) { // 나머지가 0일 때에만 이동가능, 멈춰있기
            int nt = (t+1)%a[x][y];
            if (dist[x][y][nt] == -1) {
                dist[x][y][nt] = dist[x][y][t] + 1;
                q.push(make_tuple(x, y, nt));
            }
        } else {
            for (int k = 0; k < 4; k++) {
                int nx = x+dx[k];
                int ny = y+dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (a[x][y] >= 2 && a[nx][ny] >= 2) continue;
                    if (a[nx][ny] >= 1) {
                        int nt = (dist[x][y][t] + 1) % a[nx][ny];
                        if (dist[nx][ny][nt] == -1) {
                            dist[nx][ny][nt] = dist[x][y][t] + 1;
                            q.push(make_tuple(nx, ny, nt));
                        }
                    }
                }
            }
        }
    }
    return dist[n-1][n-1][0];
}

bool can(int x, int y) {
    bool garo = false;
    if (y+1 < n && a[x][y+1] == 0) garo = true;
    if (y-1 >= 0 && a[x][y-1] == 0) garo = true;
    bool sero = false;
    if (x-1 >= 0 && a[x-1][y] == 0) sero = true;
    if (x+1 < n && a[x+1][y] == 0) sero = true;
    return !(garo && sero);
}

int main() {
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }
    int ans = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 0 && can(i, j)) {
                a[i][j] = m;
                int temp = bfs();
                if (temp != -1) {
                    if (ans == -1 || ans > temp) {
                        ans = temp;
                    }
                }
                a[i][j] = 0;
            }
        }
    }
    cout << ans << '\n';
    return 0;
}