import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;
import java.util.*;

public class Main {

    private static boolean[] finished; // SCC가 확정된 정점 판별
    private static Stack<Integer> s;
    private static ArrayList<ArrayList<Integer>> SCC;
    private static int[] dfsn, sn; // sn[i] : i가 속한 SCC의 번호
    private static ArrayList<Integer>[] array;
    private static int SCCTotal = 0, count = 0; // SCCTotal : SCC 개수

    // SCC에 사용될 dfs
    private static int dfs(int cur) {
        dfsn[cur] = ++count; // dfsn 결정
        s.push(cur); // 스택에 자신을 push

        // 자신의 dfsn, 자식들의 결과나 dfsn 중 가장 작은 번호를 result를 저장
        int result = dfsn[cur];
        for (int next : array[cur]) {
            // 아직 방문하지 않은 이웃
            if (dfsn[next] == 0) {
                result = Math.min(result, dfs(next));
            }

            // 방문은 했으나 아직 SCC로 추출되지는 않은 이웃
            else if (!finished[next]){
                result = Math.min(result, dfsn[next]);
            }
        }

        // 자신, 자신의 자손들이 도달 가능한 제일 높은 정점이 자신일 경우 SCC 추출
        if (result == dfsn[cur]) {
            ArrayList<Integer> curSCC = new ArrayList<>();
            // 스택에서 자신이 나올 때까지 POP
            while (true) {
                int t = s.peek();
                s.pop();
                curSCC.add(t);
                finished[t] = true;
                sn[t] = SCCTotal;

                if(t == cur) break;
            }

            // 출력을 위해 원소 정렬
            Collections.sort(curSCC);

            // SCC에 현재 이루어진 SCC 정보 push
            SCC.add(curSCC);
            SCCTotal++; // SCC 개수
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        array = new ArrayList[v+1];
        SCC = new ArrayList<>();
        sn = new int[v+1];
        dfsn = new int[v+1];
        s = new Stack<>();
        finished = new boolean[v+1];
        for(int i = 1; i <= v; i++) {
            array[i] = new ArrayList();
        }

        for(int i = 1 ; i <= e; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            array[a].add(b);
        }

        // DFS를 하며 SCC 추출
        for(int i = 1; i <= v; i++) {
            if(dfsn[i] == 0) {
                dfs(i);
            }
        }
        
        // SCC 끼리 정렬
        SCC.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                int i = 0;
                if (o1.get(i) > o2.get(i)) {
                    return 1;
                } else return -1;
            }
        });

        System.out.println(SCCTotal); // SCC 개수

        // 각 SCC 출력
        for(ArrayList curSCC : SCC) {
            for(int i = 0; i < curSCC.size(); i++) {
                System.out.print(curSCC.get(i) + " ");
            }
            System.out.print(-1);
            System.out.println();
        }
    }
}
