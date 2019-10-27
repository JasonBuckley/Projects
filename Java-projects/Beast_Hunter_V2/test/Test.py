'''
Created on Jun 2, 2019

@author: Jason's Computer
'''
import sys
import string
from array import array
from ctypes.wintypes import CHAR

'''
def pascal_triangle(numRows):
    arr = []
    
    for i in range(0,numRows+1):
        arr.append([])
        for j in range(0,i+1):
            if(j == 0 or j == len(arr[i-1])):
                arr[i].append(1)
            else:
                arr[i].append(arr[i-1][j] + arr[i-1][j-1])
    return arr
'''
'''
def pascal_triangle(numRows):
  pascal_rows = []
  
  for i in range(0,numRows+1):
        pascal_rows.append([])
        for j in range(0,i+1):
            if(j == 0 or j == len(pascal_rows[i-1])):
                pascal_rows[i].append(1)
            else:
                pascal_rows[i].append(pascal_rows[i-1][j] + pascal_rows[i-1][j-1])
  return pascal_rows

print(pascal_triangle(5))
print((4 & 6) + (4|6))# for i in range(0,numsRows):
'''
'''
def pascal_triangle_row(k):
    pascal_row = [1]
    nfact = 1
    j = 1
    
    for current_number in range(k,1,-1):
        nfact = nfact*current_number
    
    n = nfact
    print(n)
    
    for i in range(k,0,-1):
        j = j * (k-i+1) 
        num = nfact//((n//i)*j)
        pascal_row.append(num)
        n = n//i
    
    return pascal_row

print(pascal_triangle_row(5))
print(pascal_triangle_row(6))

def minCharPalindrome(A):
    palindrome = A
    count = 0
   
    while(not isPalindrome(palindrome) and len(palindrome) > 0):
        count+=1
        palindrome = palindrome[0:-1]
        
    return count
        
def isPalindrome(input_str):
    str = ""
        
    for i in input_str.lower():
        if(i.isalpha()):
            str+= i
          
    if(str == str[::-1]):
        return True
  
    return False

'''
'''
def gcd(a,b):
    c = 0
    while(b > 0):
        c = a%b
        a = b
        b = c
    
    return a

def lcm(a,b):
    return (a*b)/gcd(a,b)

def stringoholics(A):
  count = 1
  times = []
  
  for i in range(0,len(A)):
    word = A[i]
    length = len(A[i])
    word = word[count%length:length] + word[0:count%length]
    
    while(A[i] != word):
      count+=1
      word = word[count%length:length] + word[0:count%length]
        
    times.append(count) 
    
    count = 1
    
    min_max = times[0]
    
    for i in range(1,len(times)):
            c = 0
            a = min_max
            b = times[i]
            while(b > 0):
                c = a%b
                a = b
                b = c
            min_max = (min_max*times[i])//a
            
  return int(min_max)

A = [ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "babaaaabbaba", "baaaaababaabbaaaaabbbbbbbaabbaaaababbabaababaabaaabbbaaaaa", "abaabb", "aababbbbabbaaaabbaaaaaaaababbbabbbbaabbaabaabbaabaababbbababaababaabbababaaabaaaab", "babaaaaababbbbbabbbbabbaaabaaaababbabbaabbbabbaaaabbbabaaaabaaaababb", "bbbbbbaaaaaabbbbbbababaabaabbbbbaaabbabbaabbbbaaaaaababbabaaabbabbabbbabbaabbabbbbaabbbaaaaabbabaaab", "aabaaabaabbbbababbabbabaaaababbababbbabbbbaabbaaaaababbbbbababbbbaaababababab", "aaaababbbaabbbabaabb", "ababaaaabbabbbbaaabbababbbabbbabaaa", "aaabaabbbabaa", "baababbababbbbbabbabaabbabbbbbaaaabaaaababaa", "babaa", "abbabababbbbbbbbbbbaaaaaababbababaaabbbaaaabbbababbabaabbaabbbbaabbbaabbababababaabaaabbaaabbba", "ababbaabbaaabbbabaabababbbabaaabbbaababaaa", "abbaaaababbbbaaabaaaabaaaaabaababbabbaababbbabbbbbbbbbabbaabaaabbaaababbbaa", "bbabababaabbabbabbbabbaababbabaaabbbababab", "abbbaaabaab", "bbaaabbaaabbaabbabababa", "aabaabaaaaaaabaabbbaaababbbbbbababbaabababbaaaaabbabbbabbbaababbaabababbbbbbbbbaabaab", "babbaaabbabbaabaaabbb", "bbabaabba", "baabaaaaabbaaaaaabbbbaaaabababa", "babbaababaaba", "baabaabaabbababbaabbabbbbbabaaaabbbbbabbabbbbbabbbabaabbbbabbbbaaabbbbabababaaaababbaaabbabb", "abbbbaaaabaabbabbaababaabbababbbaaabbabbbbbaaabbabbaabbb", "bababaaaaabababbabbaabababbbaabbaabaabaabbabbbababbaaabababbababbbb", "abaaabbbabbbaabba", "bbbbaaaabbbababaabbbababaaaababbaaaaaabbbabbaababababbba", "bababaabaaaabbaabbababbaabbaabaabbaaaaaaaababbaaaaaabbaaabaabaaababbababbbbaabbabbabaabab", "aabbbabaaabababaabbbbaabbabaaabbbaaabbabbbbabaabbbbaabbbaaaaabbbabbbbb", "aabbbbbbabaabbbabbaababbababaabaaababbbbabbbaababaaaabbaaabaaabaaaabbbabababbab", "abaaaaababbabaabbbaaaaabbaaaabaaaaaaaababbaabbbaabbabbbabbaaaaaab", "bbbaabbabbbbbbaaaabbabbbbbbbaaabaababbaaaabbbaababbaaabbbbbbbbabbabababbaaabaabaaabaaaabbbbbabaabaaa", "bbaaabaaabaaabaabaaabbaabaabbabaabaabbababaaaaabaabbbbaba", "abaababaaabbabaabaabbbaaabbaabababbabaaabbbbabbbbbaaaaa", "abba", "abbbababbaaabbaaabbbabaabbababaaabbbbaaaaababbabbaabbabbbaaabaabbaaaaabaaaabbbaabbbabbbbbbbabb", "bbabbaaabaaaabbaaaabbbaaaababbbaabaaaaab", "abbaabaabbaaaaaaaabbaabbabbababaaaaaaabbabaabaabbbabbaabbaababbaabbaba", "bbbbaababbaaaaaaaaabbbabbbabaabababaababaababa", "baaabaabbbbbbaabbabbbabaaaaababaabaababbbaaaaaaaabbbbbabbabaaaaaaaabababaabaababaaabbaaaaaaaaabaa", "aababbbabbaaaaababbabaababbbbbbbbaaabbaaaaabbaabbbba", "ababababaaaaaabbbabbaaababaabb", "bababbaababaabbbabbaab", "baababababbaaaaabbbbbbbbbaabababb", "bbbbb", "aabaabbbaabababbababaaaaabbbbaababaabbabbbbbbaabbaaabbababbbabbabbbaabbbab", "bbaabbbbaabbaaaaaabbbaabababbbaabaaabbbbbabaababbbaababbbaaabaaabaaaababbbbaabbaababb", "aaababbaaaaabaabababbabaabbbbabbaba" ]
B = [ "abbaabaabaabbabaaabaabbababaaabbaaba", "aaaaabbabbbbbbbaaaaababbbabbbbbb", "bbabaaaabbbaabaabbbbabaaaababbbaabbbabbbaaabbabbbbababbabbaaaaaababbbabaabbaba", "bbbabaaabbabbaabaabbbbababaabbbaaaaaba", "abaaabaababababbaabababbbaabbabbabbaababbbababaabbb", "aaabaaabbbbbaabbaaabbaabbaab", "baabababbbaaabababbaaaaabbabb", "babbabaaabbababababaabaaabaabbbaaaaabaaaababbabaabaaabbaabababaabaabaabaab", "bbbabaaabbbbaaababbbbbbaaabaaaabaabbaabbbabbabbbaaababbabaabbbbbbaabaabaaabbbaabbabbbabbbbaaabb", "abbabaaaaabbababbbaaabbbbabbbabbbaabaabaaabbbbaabbabaaababbbbabaabbabaaaabbbabbbaaabaaaabbbababb", "abbbbbbaabbbaaaabbabbabbabaaaa", "bbbababbaaabbbbbaaabaaabaabbabbbbabaabbabababbabbaabababbaabbbbabaabbabbaabbbbabbaaab", "babbaaabbbbbabbbabbbabbaaabaaabbbaabbaaabbbbabaaaaabbbbbbababbba", "abaabaaaabbb", "bbababa", "aaabababbabaaabbaabbbababbaaaaaaaaa", "aaabababbbabbbbababaabaabababbabbaaaaabbabbbaaababbaaabbaabbbaaaabaabbababbabbb", "abaabbbbbbbabbababaaaabbbababbbbabbbabababbbbaabbabbababbabbbbab", "aabbaabaaabaaabbaabbbbababababaabbbbbabbaabababbbbaaaabaa", "aaaaabbbaabaababbbababaababaabbabb", "babaaabaaabbbababaabbaabbbabababbbbabaababbaaaabbbaabbaaaaa", "aaabbabbaaabbbbbabababbaabaaaabaaabaabbabbbabaabaaaabbabbbabbbbabaabbbaaabbbabaabaabababaaab", "bbb", "bbbabbbaaaabbabbbabaaaababbbbabababababaab", "baabbaabbbbababbbbaababbbbabbabbababaaabbbbbbaabbabaaaabbabaaababbbbabababaaba", "baabaabababbabbbaaaaaaaaaaaabbbabbaabbaabbaaab", "abbaabbaabaaababbb", "bbabbaabaaabaaabaaaaabaa", "aaababababaabaababbabaabaabbbabb", "baababbbabaabbaaabbbbbbaabbaaabbabaabbbbabbbabbaabbaabaaabbababbbabababababbabb", "abaaaaaabbaab", "bbbabbabbbbaabbaab", "abbbababbbbaaaabbbbaabaabbabbbaabbbbababababbaabbbbbabbabbbabaaabbbabbbbbbaababaabbaababaaabaabb", "abbabababbabbabaaaabaabaabaaaaabbbbaaabbbbaabaababaabab", "bbbbbaabbbbbbaabbbbbbaabbbbababaaabbaaaababbaaaabbbabbbaaaabbbbbabaabbaababbabbb", "abaaab", "ababbbababbbaaababaababaaaaaabbabbaaaabbbaa", "aaaaababbbbbbbabbbaaaaaaabbababaaaaabbbaaabaabbbabbabbbaaaab", "bbbbababbabbabbbbaaabaaabaaaaaabbbbbbbaaaababbbabbbbabbbbabbaaabbbbabbabbabbbabaaababbbabbbbb", "aabbbabbbabababbbbbabbbababbbababababababbbabababbbaaaabbaaaababaabbabbbaaababababbabaaaaabaabaab", "baaababbabbabbbaaaaabaaababbababbbaabbababbbaaaabaaabbaabbbaabbbabaaaabbbbabba", "abaaabaababbaaaabaabaaabbba", "baabbba", "aabbaaabbbbabbbbbbbabbaabbbbabaaaa", "aaabbbababaaaabab", "bbbaaaaabaabaabbabaaababbab", "baaabbabaabbabababaaaaa", "baaabbaabaaaaabaabbaabbaababababbaaabaaabaababbaaabbbabbaaaababaabbbabbbabaabbbb", "aaaaabaaaabaabbbaabaaaabaaabbaabaaaaaaaabbabbbab", "abaabbbaaababbabbbbbbbababababb", "bbaabbabaabbbbaaaabab", "bbbbbbaabbaaabbaababababbbbbbabaaabbabbbbbbbabbaaabbbaabbbaabbabbbabaabbbaaabaaaaabbababaabbbbbbaba", "bbabaabbabbabaabbaabbbbaaabababbbbabbbbaaaaabababaaabbaabaaaabb", "abbaaaabaabbbaaaaaaabaaaaaaaaaaabbaababbbabaababbabaababbabbabaaaaabbbbabaabbaaaabaaaabaabbbab", "babababbbbbaabbabaab", "abbbaaaaabbaaabababbbbbaaabbaaab", "babbaabbbb", "babbaaabbbbabababbbaababbabababbbaabaabbbabbbababa", "bbabaaaaaabbbbbbbbaaaaabbaabaaabbaabbabbbaaabbbaabbaababbabbbbbab", "aaabbbabbbabaaabbaababbbbaaabbbbababaabaabbaabbbabaaabbbabbbabaaaaabbbbbaabababbbbbbbabbba", "abaaaaabbbaaaababbababaaabbbabbabbbbababbaababbbbbabbbbbaabaaabba", "aababaabbabbbaaabbbbbbbabbaaaaaaaaaaaabaaabaabaabbbaaaabaaaababbabbbabbabaaabaaabbabbabbbbaabbbaa", "baaabbabbbababaababababbaaabababbabaababbabbbbbabaababaaabaaaaabbbabbaaaabbbbaaa", "abbaaaabaabaaabaaababbaaaabaaababbabbaabbbbbabbabababbbbba", "babaaaabbbaaababbbbaabbbbaaaaaabbabbaaaabaabaaabbbbabababbabbbaababba", "bbaaaaaaaaabababbbababaaababbababaababaabaabbaaababbbab", "babaaabaabbbabbbbbaabaaaaabaabbbbaabbbbbababaaaabbabbbbabbabababaaabbaaabb", "bbbbaaababbaaaababbbbababbbbaaaababbabbaaaaaaa", "aabbbbaaaabaabaaaaabbbabaaaaaaaaabba", "aaaabaaabbabbbaaaaaaabbabbbabbabbababbaaaabbbaabaaaabbaaababaaabbbbabbabbbbbbbabbabbbabababaaabbbbb", "abaabbbaababababbabbabbabaaabbbab", "aaabaaaabaaaababaaababaaababbbabbaabababbabbbbbabbbabbabbaaabaaaaaaaba", "babababbabbaaaabbbbaababbabbababbbbaabaaabaababbbababbba", "abbbbaabaabbabbbbbbbaababbbaabbaabbbbbabbbaaabbbaabaaa" ]
print(int(stringoholics(B)%(1000000007)))

print(10**9+7)
'''
'''
def increasingTriplet(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        
        i = None
        j = None
        k = None
        
        for element in range(1,len(nums)-1):
            if(i == None or nums[element] <= i):
                i = nums[element]
            elif j == None or nums[element] <= j:
                j = nums[element] 
            elif(k == None or nums[element] <= k):
                k = nums[element]
            else:
                return True
        return False
        
'''


'''
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:

Input: "cbbd"
Output: "bb"
'''
'''
def longest_palindrome(input_string):
    length = len(input_string)
    
    longest = 0
    start = 0
    
    left = 0
    right = 0
    
    if(length < 3 and length > 1 and input_string[0] == input_string[1]):
        return input_string
    elif(length <= 1):
        return input_string
    
    for element in range(0,length):
        left = element - 1
        right = element + 1
        
        while(left >= 0 and right < length and input_string[left] == input_string[right]):
          right+=1
          left-=1
        if(input_string[left-1] == " "):
            left+=1
            right-=1
            
        if(right - left - 1 > longest):
          longest = right - left - 1
          start = left + 1
        
        left = element
        right = element + 1
        
        while(left >= 0 and right < length and input_string[left] == input_string[right]):
          left-=1
          right+=1
        
        
        if(right - left - 1 > longest):
          longest = right - left - 1
          start = left+1
        if(input_string[left-1] == " "):
            left+=1
            right-=1
    
    return input_string[(start):(start+longest)]

word = "cbbd"
print(longest_palindrome("forgeeksskeegfor"))
print(longest_palindrome("Wordl"))
print(longest_palindrome("i want to be a racecar driver"))

'''
'''
def find_pivot_index(A):
  # List is sorted, but then rotated.
  # Find the minimum element in less than linear time
  # return it's index
    length = len(A)
    min_index = 0
    low = 0
    high = length - 1
    mid = (high + low)//2
      
    while(low < high - 1 ):
        if(A[mid] < A[min_index]):
            high = mid
            min_index = mid
        else:
            low = mid
      
        mid = (high + low)//2
      
    return min_index
      
A = [ 40342, 40766, 41307, 42639, 42777, 46079, 47038, 47923, 48064, 48083, 49760, 49871, 51000, 51035, 53186, 53499, 53895, 59118, 60467, 60498, 60764, 65158, 65838, 65885, 65919, 66638, 66807, 66989, 67114, 68119, 68146, 68584, 69494, 70914, 72312, 72432, 74536, 77038, 77720, 78590, 78769, 78894, 80169, 81717, 81760, 82124, 82583, 82620, 82877, 83131, 84932, 85050, 85358, 89896, 90991, 91348, 91376, 92786, 93626, 93688, 94972, 95064, 96240, 96308, 96755, 97197, 97243, 98049, 98407, 98998, 99785, 350, 2563, 3075, 3161, 3519, 4176, 4371, 5885, 6054, 6495, 7218, 7734, 9235, 11899, 13070, 14002, 16258, 16309, 16461, 17338, 19141, 19526, 21256, 21507, 21945, 22753, 25029, 25524, 27311, 27609, 28217, 30854, 32721, 33184, 34190, 35040, 35753, 36144, 37342 ]
print(find_pivot_index([ 40342, 40766, 41307, 42639, 42777, 46079, 47038, 47923, 48064, 48083, 49760, 49871, 51000, 51035, 53186, 53499, 53895, 59118, 60467, 60498, 60764, 65158, 65838, 65885, 65919, 66638, 66807, 66989, 67114, 68119, 68146, 68584, 69494, 70914, 72312, 72432, 74536, 77038, 77720, 78590, 78769, 78894, 80169, 81717, 81760, 82124, 82583, 82620, 82877, 83131, 84932, 85050, 85358, 89896, 90991, 91348, 91376, 92786, 93626, 93688, 94972, 95064, 96240, 96308, 96755, 97197, 97243, 98049, 98407, 98998, 99785, 350, 2563, 3075, 3161, 3519, 4176, 4371, 5885, 6054, 6495, 7218, 7734, 9235, 11899, 13070, 14002, 16258, 16309, 16461, 17338, 19141, 19526, 21256, 21507, 21945, 22753, 25029, 25524, 27311, 27609, 28217, 30854, 32721, 33184, 34190, 35040, 35753, 36144, 37342 ]))
print(len(A))
'''

'''
def binary_search(A,target):
    length = len(A)
    low = 0
    high = length
    mid = (high + low)//2
      
    while(high >= low):
        if(target < A[mid]):
            high = mid-1
        elif(target > A[mid]):
            low = mid+1
        else:
            return mid
        
        mid = high + (high - low)//2
    
    #if(mid < length):
      #  return mid
    
    return -1

A = [1,3,5,7]
print(binary_search(A,3))
'''

'''
def merge_sorted_lists(nums1, nums2):
  # modify num1 in-place
  length = len(nums1)+len(nums2)
  x = 0 
  y = x
  
  while (x < length):
    if(x == len(nums1) or nums2[y] <= nums1[x]):
      nums1.insert(x,nums2[y])
      y+=1
     
    x+=1

nums1 = [1,2,3]
nums2 = [2,5,6]
  
merge_sorted_lists(nums1,nums2)
print(nums1)

'''
'''
def printS(A):
    for i in A:
        print(str(i) + "\n")
    print("\n")  
    
def spiral(size): # spiral problem
    count = 1
    decrement = size-1
    row = 0
    col = size-1
    flip = 1
    arr = []
    
    for index in range(0,size):
        current_row = [0]*size
        arr.append(current_row)
    
    for index in range(0,size):
        arr[row][index] = count
        count+=1
    
    while decrement > 0:
        for i in range(0,decrement):
            row = row+1*flip
            arr[row][col] = count
            count+=1
        
        for i in range(0, decrement):
            col = col-flip
            arr[row][col] = count
            count+=1
        
        flip*=-1
        decrement-=1
    return arr

A = spiral(6)
printS(A)
#https://repl.it/@NickKoskelo/Python-Advanced-Group-Practice
'''

'''
[1, 5, 2, 4, 3, 6]; Starting array
[1, 5, 2, 3, 6, 4]; DA 4
[1, 2, 3, 6, 4, 5]; DA 5
[1, 2, 3, 4, 5, 6]; DA 6
'''

def delete_sort(nums):
    ordered = []
    min_value_unordered = None
    
    
    for i in nums:
        while(len(ordered) > 0 and i < ordered[len(ordered)-1]):
            if(min_value_unordered is None or i < min_value_unordered):
                min_value_unordered = ordered.pop()
            else:
                ordered.pop()
                
        ordered.append(i)
    
    while(ordered[-1] > min_value_unordered):
        ordered.pop()
    
    return len(nums) - len(ordered)

test1 = [1, 5, 2, 4, 3, 6]

print(delete_sort(test1))

'''
def squareRoot(num):
    if(num <= 1):
        return num
          
    low = 1
    high = num/2
    mid = (low+high)//2
        
    while(low <= high):
        mid_squared = mid*mid
      
        if(mid_squared == num):
            return int(mid)
        elif(mid_squared > num):
            high = mid-1
        else:
            low = mid + 1
            
        mid = (low+high)//2
        
    return int(mid)
        
  
sampleInput = 5
print(squareRoot(sampleInput))
'''

'''
# reverses the first k elements of an array
def flip(arr, k):
  for i in range(k//2):
    arr[i], arr[k-i-1] = arr[k-i-1], arr[i]

def pancake_sort(nums):
  kvalues = []
  max_unsorted_value = None
  max_unsorted_index = None
  last_unsorted_value = None
  
  for i in range(len(nums)):
    for j in range(len(nums)-i):
      if(max_unsorted_value is None or nums[j] > max_unsorted_value):
         max_unsorted_index = j
         max_unsorted_value = nums[j]
    
    flip(nums,max_unsorted_index+1)
    if(max_unsorted_index > 0):
      kvalues.append(max_unsorted_index+1)
      
    print(kvalues)
    print(nums)
    print("")  
    
    flip(nums,len(nums) - i)
    kvalues.append(len(nums)-i)
    
    isSorted = True
    
    for k in range(len(nums)-1):
      if(nums[k] > nums[k+1]):
        isSorted = False
        break
  
    if(isSorted):
      break
    
    
    last_unsorted_value = max_unsorted_value
    max_unsorted_value = None
    max_unsorted_index = None
    
    print(kvalues)
    print(nums)
    print("")
  
    
  return kvalues
    
test = [3,2,4,1]

'''

'''
print(pancake_sort(test))
print(pancake_sort([5, 2, 3, 4, 1]))
'''

class Node:
  def __init__(self, data):
    self.data = data
    self.left = None
    self.right = None

    ## INSTEAD, non binary
    self.children = []

    self.parent = None
  
  def to_list(self,node_list):
    if(self.left != None):
      self.left.to_list(node_list)
        
    node_list.append(self.data)
      
    if(self.right != None):
      self.right.to_list(node_list)
  
    return node_list
class Tree:
  def __init__(self):
    self.root = None

  def in_order_traversal(self):

    def dfs(node):
      if node:

        dfs(node.left)
        print(node.data)
        dfs(node.right)

    dfs(self.root)

  def level_order_traversal(self):
    if not self.root:
      return

    queue = [self.root]

    while len(queue) > 0:
      current_node = queue.pop(0)
      if current_node.left:
        queue.append(current_node.left)
      print(current_node.data)
      if current_node.right:
        queue.append(current_node.right)

  def add(self,node):
    if not self.root:
      self.root = node
      return 

    def insert(root, node):
      if root.data > node.data:
        if root.left is None:
          root.left = node
        else:
          insert(root.left, node)
      else:

        if root.right is None:
          root.right = node
        else:
          insert(root.right, node)
    
    insert(self.root, node)

  def height(self):

    def get_height(node):

      if node is None:
        return 0
      else:
        left_height = get_height(node.left)
        right_height = get_height(node.right)

        if left_height > right_height:
          return left_height +1
        else:
          return right_height +1
    return get_height(self.root)
  
  def balance(self):
    temp = self.root.to_list([])
    self.root = None
    self.balance_helper(temp, len(temp)-1,0)
    
          
    
  def balance_helper(self,node_list,right,left):
      mid = (right+left)//2
      
      if(right > left):
        print(node_list[mid])
        self.add(Node(node_list[mid]))
        self.balance_helper(node_list,mid,left)
        self.balance_helper(node_list,right,mid+1)
      else:
        if(mid == len(node_list)-1):
          self.add((Node(node_list[mid]))

tree = Tree()
tree.add(Node(6))
tree.add(Node(27))
tree.add(Node(15))
tree.add(Node(10))
tree.add(Node(13))
tree.add(Node(8))

tree.balance()
print(tree.root)
tree.in_order_traversal()