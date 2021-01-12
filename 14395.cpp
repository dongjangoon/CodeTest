#include <iostream>
#include <queue>
#include <set>

using namespace std;
const long long limit = 1000000000LL;

int main() {
    long long s,t,max;
    cin >> s >> t;

    if (s == t) {
        cout << 0 << '\n';
        return 0;
    }

    queue<pair<long long, string>> q;
    set<long long> check;
    check.insert(s);
    q.push(make_pair(s, ""));

    while (!q.empty()) {
        long long now = q.front().first;
        string str = q.front().second;
        q.pop();

        if (now == t) {
            if (str.length() == 0) {
                str = "0";
            }
            cout << str << '\n';
            return 0;
        }

        if (0 <= now*now && now*now <= limit && !check.count(now*now)) {
            long long next = now*now;
            check.insert(next);
            q.push(make_pair(next, str+"*"));
        }
        if (0 <= now+now && now+now <= limit && !check.count(now+now)) {
            long long next = now+now;
            check.insert(next);
            q.push(make_pair(next, str+"+"));
        }
        if (0 <= now-now && now-now <= limit && !check.count(now-now)) {
            long long next = now-now;
            check.insert(next);
            q.push(make_pair(next, str+"-"));
        }
        if (0 <= now/now && now/now <= limit && !check.count(now/now)) {
            long long next = now/now;
            check.insert(next);
            q.push(make_pair(next, str+"/"));
        }
    }
    cout << -1 << '\n';
    return 0;
}