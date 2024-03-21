def solution(today, terms, privacies):
    def get_due_date(collected_date, month):
        y, m, d = collected_date.split(".")
        
        y = int(y) + (int(m) + month - 1) // 12
        m = (int(m) + month) % 12 or 12
        
        return f"{y}.{m:02}.{d}"
    
    answer = []
    dict = {}
    
    for term in terms:
        alphabet, month = term.split()
        dict[alphabet] = int(month)
    
    for i in range(len(privacies)):
        collected_date, category = privacies[i].split()
        due_date = get_due_date(collected_date, dict[category])
        
        if due_date <= today:
            answer.append(i+1)
    
    answer.sort()
    return answer