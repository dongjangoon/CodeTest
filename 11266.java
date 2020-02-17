import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static boolean[] isCut;
    private static int[] visitedOrder;
    private static ArrayList<Integer>[] array;
    private static int count = 0;

    private static int dfs(int here, boolean isRoot) {
        visitedOrder[here] = ++count;
        int result = visitedOrder[here];

        int child = 0;

        for(int next : array[here]) {
            if(visitedOrder[next] != 0) { // 0이 아니면 자식이 아니니까 체크
                result = Math.min(result, visitedOrder[next]); // 거쳐가는 점들 중 가장 작은 것, 여기서 here보다 작은 게 나오면 here가 단절점이 됨
                continue;
            }

            child++; // order가 0이면 자식
            int prev = dfs(next, false);

            if(!isRoot && prev >= visitedOrder[here]) { // here가 루트가 아니라면 자식 노드들이 here를 거치지 않고 빠른 순서를 지나치면 단절점
                isCut[here] = true;
            }
            result = Math.min(result, prev); // prev 갱신 
        }

        if(isRoot) { // 루트일 경우는 자식이 2개 이상이면 무조건 단절점
            if(child >= 2) {
                isCut[here] = true;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        visitedOrder = new int[v+1];
        isCut = new boolean[v+1];
        array = new ArrayList[v+1];
        for(int i =1; i <= v; i++) {
            array[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            array[a].add(b);
            array[b].add(a);
        }

        for(int i = 1; i <= v; i++) {
            if(visitedOrder[i] == 0) {
                dfs(i, true);
            }
        }

        int cnt = 0;
        for(int i = 1; i <= v; i++) {
            if(isCut[i]) cnt++;
        }
        System.out.println(cnt);

        for(int i = 1; i <= v; i++) {
            if(isCut[i]){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
