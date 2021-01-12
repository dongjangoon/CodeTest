#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n;
int dx[] = {0,0,-1,1};
int dy[] = {-1,1,0,0};
string a[50];

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    
    vector<vector<int>> b(n, vector<int>(n)); // 몇 번 정점인지 기록
    vector<pair<int, int>> v; // 문과 거울 설치 가능한 위치, 즉 정점
    int start = -1, end = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n ; j++) {
            if (a[i][j] == '#') {
                if (start == -1) {
                    start = v.size();
                } else {
                    end = v.size();
                }
                v.push_back(make_pair(i, j));
                b[i][j] = v.size()-1;
            } else if (a[i][j] == '!') {
                v.push_back(make_pair(i, j));
                b[i][j] = v.size() - 1;
            }
        }
    }

    int m = v.size();
    vector<vector<bool>> check(m, vector<bool>(m, false));
    for (int i = 0; i < v.size(); i++) { // 간선 만들어주기
        for (int k = 0; k < 4; k++) {
            int x = v[i].first+dx[k];
            int y = v[i].second+dy[k];
            while (0 <= x && x < n && 0 <= y && y < n) {
                if (a[x][y] == '*') break;
                if (a[x][y] == '!' || a[x][y] == '#') {
                    check[i][b[x][y]] = true;
                }
                x += dx[k];
                y += dy[k];
            }
        }
    }
    queue<int> q;
    vector<int> dist(m, -1);
    q.push(start);
    dist[start] = 0;
    while(!q.empty()) {
        int now = q.front();
        q.pop();
        for (int i = 0; i < m; i++) {
            if (check[now][i] && dist[i] == -1) {
                dist[i] = dist[now] + 1;
                q.push(i);
            }
        }
    }
    cout << dist[end]-1 << '\n';

    return 0;
}