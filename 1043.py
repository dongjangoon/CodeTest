n, m = map(int, input().split())
people_knowing_truth = set(input().split()[1:])
parties = []

for _ in range(m):
  parties.append(set(input().split()[1:]))

for _ in range(m):
  for party in parties:
    if party & people_knowing_truth:
      people_knowing_truth = people_knowing_truth.union(party)

ans = 0
for party in parties:
  if party & people_knowing_truth:
    continue
  ans += 1

print(ans)
