def solution(cap, n, deliveries, pickups):
    deliveries = deliveries[::-1]
    pickups = pickups[::-1]

    answer = 0
    have_to_deliver = 0
    have_to_pickup = 0

    for i in range(n):
        have_to_deliver += deliveries[i]
        have_to_pickup += pickups[i]

        while have_to_deliver > 0 or have_to_pickup > 0:
            answer += (n - i) * 2

            have_to_deliver -= cap
            have_to_pickup -= cap

    return answer