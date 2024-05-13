import java.util.Arrays;

class Solution {
    public int solution(String[][] book_time) {
        // book_time을 예약 시간 기준으로 정렬
        Arrays.sort(book_time, (a, b) -> {
            String[] time1 = a[0].split(":");
            String[] time2 = b[0].split(":");
            int hour1 = Integer.parseInt(time1[0]);
            int min1 = Integer.parseInt(time1[1]);
            int hour2 = Integer.parseInt(time2[0]);
            int min2 = Integer.parseInt(time2[1]);
            if (hour1 != hour2) return hour1 - hour2;
            return min1 - min2;
        });

        int roomsNeeded = 0;
        int[] endTimes = new int[book_time.length];

        for (int i = 0; i < book_time.length; i++) {
            String[] time = book_time[i][0].split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            int startTime = hour * 60 + min; // 예약 시작 시간 (분 단위로 변환)

            // 현재 예약 시간과 겹치는 예약이 있는지 확인
            boolean overlapped = false;
            for (int j = 0; j < roomsNeeded; j++) {
                if (endTimes[j] <= startTime) {
                    // 이전 손님 퇴실 시간이 현재 손님 도착 시간보다 이른 경우
                    // 해당 객실은 청소 완료 상태이므로 다음 손님을 받을 수 있음
                    overlapped = true;
                    endTimes[j] = startTime + 10; // 청소가 끝나는 시간 업데이트
                    break;
                }
            }

            if (!overlapped) {
                // 겹치는 예약이 없는 경우 새로운 객실이 필요함
                endTimes[roomsNeeded] = startTime + 10; // 청소가 끝나는 시간 기록
                roomsNeeded++; // 새로운 객실이 필요함
            }
        }

        return roomsNeeded;
    }
}
