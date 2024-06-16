def min_cost_to_travel(n, distances, prices):
  total_cost = 0
  min_price = prices[0]

  for i in range(n-1):
    # 현재까지 최소 금액 갱신
    min_price = min(min_price, prices[i])

    # 최소 금액으로 다음 거리만큼 주유
    total_cost += min_price * distances[i]

  return total_cost

## input 받기
n = int(input())
distances = list(map(int, input().split()))
prices = list(map(int, input().split()))

print(min_cost_to_travel(n, distances, prices))
