#include <iostream>
#include <queue>

using namespace std;

int n, m;
int a[50][50];
int d[50][50];
int room[50*50];
int dx[] = {0,-1,0,1};
int dy[] = {-1,0,1,0};

int bfs(int i, int j, int roomCnt) {
    d[i][j] = roomCnt;
    queue<pair<int, int>> q;
    q.push(make_pair(i, j));
    int cnt = 0;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        cnt++;

        for (int k = 0; k < 4; k++) {
            int nx = x+dx[k];
            int ny = y+dy[k];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (d[nx][ny] != 0) continue;
                if (a[x][y] & (1 << k)) continue;
                q.push(make_pair(nx, ny));
                d[nx][ny] = roomCnt;
            }
        }
    }
    return cnt;
}



int main() {
    cin >> m >> n;

    for (int i = 0; i < n ; i++) {
        for (int j = 0; j < m;j++) {
            cin >> a[i][j];
        }
    }
    int roomCnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (d[i][j] == 0) {
                roomCnt++; // 방의 인덱스, 갯수
                room[roomCnt] = bfs(i, j, roomCnt); // 이 방이 몇칸인지 기록
            }
        }
    }
    cout << roomCnt << '\n';
    int ans = 0;
    for (int i = 1; i <= roomCnt; i++) {
        if (ans < room[i]) {
            ans = room[i];
        }
    }
    cout << ans << '\n';
    ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < 4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (d[nx][ny] == d[i][j]) continue; // 같은 방
                    if (a[i][j] & (1<<k)) {
                        if (ans < room[d[i][j]] + room[d[nx][ny]]) {
                            ans = room[d[i][j]] + room[d[nx][ny]];
                        }
                    }
                }
            }
        }
    }
    cout << ans << '\n';

    return 0;
}

