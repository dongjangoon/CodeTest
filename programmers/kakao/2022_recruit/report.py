from collections import defaultdict

def solution(id_list, report, k):
    answer = []
    
    # 중복 제거
    report = list(set(report))
    
    # 신고당한 횟수를 카운팅할 딕셔너리
    counter = defaultdict(int)
    
    # 신고 당한 사람을 키로 신고한 사람을 리스트로 저장할 딕셔너리
    report_list = defaultdict(set)
    
    for r in report:
        reporter, reportee = r.split()
        
        report_list[reporter].add(reportee)
        counter[reportee] += 1
        
    for i in id_list:
        result = 0
        for j in report_list[i]:
            if counter[j] >= k:
                result += 1
        answer.append(result)
                
    return answer