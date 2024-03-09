minkyum_num = input()
_max, _min = "", ""
m_count = 0

for char in minkyum_num:
  if char == 'M':
    m_count += 1
  else:
    _max += '5' + '0' * m_count
    if m_count > 0:
      _min += '1' + '0' * (m_count - 1)
    _min += '5'
    m_count = 0

if m_count > 0:
  _max += '1' * m_count
  _min += '1' + '0' * (m_count - 1)

print(int(_max))
print(int(_min))
