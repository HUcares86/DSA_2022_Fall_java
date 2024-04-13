# Homework 1 (HW1)
###### tags: `2020pdsa`

Due: 9/25 21:00

[toc]

## Version
Python: 3.8.5 (Note: no numpy)
Java: openjdk14 (https://openjdk.java.net/projects/jdk/14/)
Platform: Debian

## Our Judge
https://140.112.183.224:13000

Grading Status:
* AC: ACcepted
* TLE: Time Limit Excess
* WA: Wrong Answer
* RE: Runtime Error (Mostly your program raise some error unexpectly)

Handin the file with utf-8 encoding if there has 中文 words inside your code(including comments).

### Template Download
https://cool.ntu.edu.tw/courses/3501/files/folder/Homework_Template

### Hint
How Python sort:
`nums = sorted(nums)`

# 2sum (HW1-1)

Task: Find the only one solution of `nums[a] + nums[b] = target` where a, b is the index of the value from an int array(`nums`) and target is also an integer(`target`)

Assumption: the integers in array(`nums`) are distinct

Output: an int array with two elements (`[a,b]`) where `a<b`

## Template
`python`
``` python
from typing import List
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        return [0, 1]
```

`java`
``` java
import java.util.Arrays; 

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        return new int[]{1,3};
    }
}
```

## Example
``` python
>>> nums = [2,7,11,15]
>>> target = 9
>>> Solution().twoSum(nums, target)
[0, 1]
# 2 + 7 = 9

>>> nums = [3,2,4]
>>> target = 6
>>> Solution().twoSum(nums, target)
[1, 2]
# 2 + 4 = 6

>>> nums = list(range(1000))
>>> target = 1997
>>> Solution().twoSum(nums, target)
[998, 999]
# 998 + 999 = 1997
```

example for java is a little bit complex
``` java
import java.util.Arrays; 
        
public class Solution {
    int[] twoSum(int[] nums, int target) {
        // do something
        return new int[]{0, 0};
    }

    public static void main(String []args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 26)));
    }
}
```
```
[2, 3]
```


## TestCase
Time Limit 2s
Total 100 points
* 20 points: `-10 < a,b < 20` and N<5, Easy
* 20 points: `-20 < a,b < 20` and N<=10
* 20 points: `|a,b| < 100000` and N<=1000
* 20 points: `|a,b| < 1000000` and N<=10000
* 20 points: `|a,b| < 100000000` and N<=100000


# 3sum (HW1-2)

Task: Find all the solutions of `a + b + c = 0` where `a`, `b`, `c` is from a int array(`nums`)

Assumption: the integers in array(`nums`) are distinct

Outpu: all possible solutions in a list. Solution should be a form of a three element int array `[a,b,c]`

Make sure
* There is no duplicated solution in the solution list
* The solution item is `[a,b,c]` where `a<b<c`
* (optional) The items in solution list should be in ascending order; items should be sorted by the first number in the item; if ties happen, use the next number to sort the item;

## Template
`python`
``` python
from typing import List
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        return [[0, 1, 2]]
```

`java`
``` java
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays; 

public class Solution {
    public static List<int[]> threeSum(int[] nums) {
	List<int[]>ans = new LinkedList<>();
	//your solution process
	//if you want to add a solution [a,b,c] in the list, you can use:
	//ans.add(new int[]{a, b, c});
        return ans;
    }
}
```

## Example
``` python
>>> nums = [-1, 0, 1, 2, -2, -4]
>>> Solution().threeSum(nums)
[[-2, 0, 2], [-1, 0, 1]]

>>> nums = [0]
>>> Solution().threeSum(nums)
[]

>>> nums = list(range(-3, 10000))
    >>> Solution().threeSum(nums)
[[-3, -2, 5], [-3, -1, 4], [-3, 0, 3], [-3, 1, 2], [-2, -1, 3], [-2, 0, 2], [-1, 0, 1]]

```


## TestCase
Time Limit: 1s
Total 100 points
`N` is the length of `nums`
* 20 points: `-5 < a,b < 5` and `N<7`, Easy
* 20 points: `-20 < a,b < 20` and `N<=100`, Special Case
* 20 points: `|a,b,c| < 10` and `N<=10`
* 20 points: `|a,b,c| < 100000` and `N<=1000`
* 20 points: `|a,b,c| < 100000000` and `N<=1000`

`N` is the length of `nums`



# 4sum (HW1-3)

Task: Find all the solutions of `a + b + c + d = target` where `a`, `b`, `c`, `d` is from a int array(`nums`) and `target` is an integer

Assumption: the integers in array(`nums`) are distinct

Output: all possible solutions in a list. Solution should be in a form of a four element int array `[a,b,c,d]`

Make sure
* There is no duplicated solution in the solution list
* The solution item is `[a,b,c,d]` where `a<b<c<d`
* (optional) The items in solution list should be in ascending order; items should be sorted by the first number in the item; if ties happen, use the next number to sort the item;


## Example
```
>>> nums = [1, 0, -1, -2, 2]
>>> target = 0
>>> Solution().fourSum(nums, target)
[[-2, -1, 1, 2]]

>>> nums = [0, 1, 2, 3, 4]
>>> target = 0
>>> Solution().fourSum(nums, target)
[]
```


## Template
`python`
``` python
from typing import List
class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        return [[0, 1, 2, 3]]
```

`java`
``` java
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays; 

public class Solution {
    public static List<int[]> fourSum(int[] a, int target) {

	List<int[]>ans = new LinkedList<>();
	//your solution process
	//if you want to add a solution [a,b,c,d] in the list, you can use:
	//ans.add(new int[]{a, b, c, d});
        return ans;
    }
}
```

## TestCase
Time Limit: 2s
Total 100 points
* 20 points: `K < 5` and `N<7`, Easy
* 20 points: `K < 1000` and `N<=20`
* 20 points: `K < 100000` and `N<=30`, Special Case
* 20 points: `K < 100000000` and `N<=100`
* 20 points: `K < 100000000` and `N<=1000`

`N` is the length of `nums`
`K` is the value that `-K < a,b,c,d < K`
`target` is always bounding by 32-bits integer `-2^31 <= target < 2^31`

## Challenging Exmaple for FourSum
N = 500

Python
``` python
ans = Solution().fourSum([-12330562, -26175966, 60267619, -73907691, 1070051, -95268374, 2172984, 22204477, -78355603, -53520691, -46799703, -29363251, -25986550, 59376633, -94873316, 81516118, 17324406, -61608647, -29733317, 64026106, 70549217, -5481569, 32261163, 85829201, -35674826, -55844644, -55371704, 61022815, -3441087, 989080, 8397845, 51636322, 93494714, 99000224, 74926928, 49631922, 36853119, 68473411, -1841523, 80934032, -81710246, -83654053, -50102291, -38940666, 70816130, -83362954, 83837899, 18836944, 82464854, -70673245, -18983548, -98117108, -21623748, -31363333, 61506707, 8048367, -74068850, 21406275, 90491430, 85727089, 50618900, 79785389, 47422516, 4787502, -15293674, -15225140, -30215891, -25553394, -82069892, 8860625, -26252345, 7020641, 55370410, -75394758, 22516677, -93220212, 2139652, 4908673, 8018919, 53214845, 58577320, -88797802, 85536710, -23577294, 5521265, -78393143, 98000911, 66558324, 87317438, -76957740, -17254378, 86305226, -37312695, -19333480, 85872651, 72608113, -52942390, 8354646, -10018971, 56147773, -79886894, -21902968, -72326524, 50032540, 29745375, -588621, -72911396, 46192217, -78078013, -51462949, -74891793, 41382913, -7435206, 63842665, -60818508, -41977983, 83973159, 9010375, 36610161, 61186254, 82293067, 98956350, 5792446, 71261314, 34018216, -33953630, 16996455, -96455198, 88063842, 15485028, -16851341, 30033008, 43593025, 50629996, 7609038, 90950433, 46147154, 59378839, -23512448, -60440065, 52193374, 80367395, 99252391, 28377642, 5490561, 51953089, -97142524, -33102286, -55825823, -14151396, -67183057, 60213716, -17179618, 87318882, -16570743, -8507426, 90007297, 96677325, 57820585, 1633457, -48593986, 85950667, 98800824, -21071052, -53239526, -50503198, -54342933, -12497929, -77203435, -54117508, 2371512, -62293899, -86310987, -60415484, 45520297, -66505574, 19132906, -3673412, 32687936, 81296175, 48066499, -54389735, -39107045, -74549220, -21801427, 29180821, -80183505, -64396422, -87688513, -1793604, -37811915, -56244375, -28427113, 60703862, -81579177, -79627895, 64753553, 45652390, 53186691, 68812261, -76571042, -89276313, -4719511, -81282244, 30959033, -89698621, -38365135, 74255014, 88885193, 45163484, 25915315, -31794286, 36851587, 16656788, 58391570, -42453536, -86882484, -16670332, -96385673, 59666491, -65080520, 72979397, -73942604, -55519013, 20295160, 74148511, -97420675, -80856639, -34052060, 96400878, -83206338, -69799951, 58317652, 68366933, -44310453, -15810906, 4159520, 51071554, 27758711, 64225905, 356963, 94185964, 11671531, -45961277, 17936524, 25733118, -87778472, -27042655, 76921266, -81656055, 7744921, 64826643, 74993506, 87096988, -1766477, -9901911, -52712896, 35679311, 13686515, 67158357, -58568296, -86735807, -4745922, -28872170, 12914978, 95181357, -46004898, -2543311, 21447897, 76414321, 19174816, 47983699, 72334899, -79143239, -6757009, 19492083, -3165067, 37171800, 27071740, -7254845, -37785935, -32265292, 65262748, 49483537, 20795080, -31958503, 73422208, -81859543, -67317143, -83479264, -28469194, -15091842, -2753031, -1011412, 77735655, 91192640, -20826381, -11991452, 59126039, -36934478, 97914617, -515087, 54874746, 7608329, -84244428, -40397415, 96672579, 54792263, -95622568, 51437059, -58642020, 840843, 86258346, 52114928, 77640349, -78187337, -94551787, -46275604, 80625969, -3138208, 36216632, -36709118, -94308688, 89612625, 33232910, -50655119, -88581596, 6632945, -7756650, 3862918, -36958770, 37395906, 77857743, -98487290, -57288380, 8501700, -20985517, -81807199, -92893000, -93497797, -97069226, 57005789, 2170262, 43201469, 86029464, -31433586, -88056723, 37707935, 67524737, 59488848, -91163842, -3720614, -65456290, -36585470, -21076437, 34738870, 60519591, -64765330, 27331762, 12175322, -938945, 39064185, 59591954, 56608510, -81986140, 68628619, -47923441, 88910445, 51482934, -46714526, 3043756, 10216867, 13453156, -49186543, -89541368, -71406478, 80369922, 68829761, 51772309, 57228911, 98964474, 3405095, 63086382, -50390086, 61149122, -55809675, -7516561, 46632368, 43864146, 70678597, 2115815, -30446536, 88628862, 24716414, -33775057, -9194700, 1771717, 87603233, -83560882, 88754989, -98879175, -36680288, 46776998, -76524451, -35970515, -21802643, -81480711, 99604795, -68905432, 16436629, -41240201, -26755192, 82914954, -23956060, -12253327, 23298350, 5351077, -33539442, 88010727, -84414525, -3591127, 71311139, 44450977, 68362697, -53140499, -34782231, 39096996, -64459964, 95355394, -37556727, 41811509, -55960380, 25554595, 46082038, -66245534, -13371241, 83413794, -79649457, -28868367, 2680477, 46882173, 20399386, -86672885, -6897741, 35978557, -64874210, -20129885, 62478468, 73271441, -98119593, -79027260, -11426453, 2768512, 21280857, 20371643, 72058399, 48560257, 75183146, -39211297, 53675723, 40237909, 942835, -23181575, -52362096, 79080371, 99973909, 81446513, -34201075, -14760366, 92931980, -65825579, -6579465, -69723146, -50804082, 23509553, 84422188, 48279359, -29391648, 53567302, 48091951, -61160390, -36792134, -79516763, -16407776, -51867243, 79610031, 18988177, 43533037, -5856895, -2601257, -94175290, -93471034, 58168084, -35695885, -33627069, 78814550, 56560861, -52448718, 42658635, 13647197], 12203805)
print("answer", [[-81579177, 942835, 5521265, 87318882], [-75394758, 2680477, 36851587, 48066499], [-69799951, 5490561, 23298350, 53214845], [-58568296, 13686515, 21406275, 35679311], [-54342933, -52712896, 20295160, 98964474], [-52362096, -3720614, 22204477, 46082038], [-34052060, -28868367, 3862918, 71261314], [-20129885, -12497929, 2172984, 42658635]])
print("output", ans)
```

Java
``` java
public class Solution {
    public static List<int[]> fourSum(int[] nums, int target) {}

    public static void main(String []args) {
        List<int[]> ans = fourSum(new int[] {-12330562, -26175966, 60267619, -73907691, 1070051, -95268374, 2172984, 22204477, -78355603, -53520691, -46799703, -29363251, -25986550, 59376633, -94873316, 81516118, 17324406, -61608647, -29733317, 64026106, 70549217, -5481569, 32261163, 85829201, -35674826, -55844644, -55371704, 61022815, -3441087, 989080, 8397845, 51636322, 93494714, 99000224, 74926928, 49631922, 36853119, 68473411, -1841523, 80934032, -81710246, -83654053, -50102291, -38940666, 70816130, -83362954, 83837899, 18836944, 82464854, -70673245, -18983548, -98117108, -21623748, -31363333, 61506707, 8048367, -74068850, 21406275, 90491430, 85727089, 50618900, 79785389, 47422516, 4787502, -15293674, -15225140, -30215891, -25553394, -82069892, 8860625, -26252345, 7020641, 55370410, -75394758, 22516677, -93220212, 2139652, 4908673, 8018919, 53214845, 58577320, -88797802, 85536710, -23577294, 5521265, -78393143, 98000911, 66558324, 87317438, -76957740, -17254378, 86305226, -37312695, -19333480, 85872651, 72608113, -52942390, 8354646, -10018971, 56147773, -79886894, -21902968, -72326524, 50032540, 29745375, -588621, -72911396, 46192217, -78078013, -51462949, -74891793, 41382913, -7435206, 63842665, -60818508, -41977983, 83973159, 9010375, 36610161, 61186254, 82293067, 98956350, 5792446, 71261314, 34018216, -33953630, 16996455, -96455198, 88063842, 15485028, -16851341, 30033008, 43593025, 50629996, 7609038, 90950433, 46147154, 59378839, -23512448, -60440065, 52193374, 80367395, 99252391, 28377642, 5490561, 51953089, -97142524, -33102286, -55825823, -14151396, -67183057, 60213716, -17179618, 87318882, -16570743, -8507426, 90007297, 96677325, 57820585, 1633457, -48593986, 85950667, 98800824, -21071052, -53239526, -50503198, -54342933, -12497929, -77203435, -54117508, 2371512, -62293899, -86310987, -60415484, 45520297, -66505574, 19132906, -3673412, 32687936, 81296175, 48066499, -54389735, -39107045, -74549220, -21801427, 29180821, -80183505, -64396422, -87688513, -1793604, -37811915, -56244375, -28427113, 60703862, -81579177, -79627895, 64753553, 45652390, 53186691, 68812261, -76571042, -89276313, -4719511, -81282244, 30959033, -89698621, -38365135, 74255014, 88885193, 45163484, 25915315, -31794286, 36851587, 16656788, 58391570, -42453536, -86882484, -16670332, -96385673, 59666491, -65080520, 72979397, -73942604, -55519013, 20295160, 74148511, -97420675, -80856639, -34052060, 96400878, -83206338, -69799951, 58317652, 68366933, -44310453, -15810906, 4159520, 51071554, 27758711, 64225905, 356963, 94185964, 11671531, -45961277, 17936524, 25733118, -87778472, -27042655, 76921266, -81656055, 7744921, 64826643, 74993506, 87096988, -1766477, -9901911, -52712896, 35679311, 13686515, 67158357, -58568296, -86735807, -4745922, -28872170, 12914978, 95181357, -46004898, -2543311, 21447897, 76414321, 19174816, 47983699, 72334899, -79143239, -6757009, 19492083, -3165067, 37171800, 27071740, -7254845, -37785935, -32265292, 65262748, 49483537, 20795080, -31958503, 73422208, -81859543, -67317143, -83479264, -28469194, -15091842, -2753031, -1011412, 77735655, 91192640, -20826381, -11991452, 59126039, -36934478, 97914617, -515087, 54874746, 7608329, -84244428, -40397415, 96672579, 54792263, -95622568, 51437059, -58642020, 840843, 86258346, 52114928, 77640349, -78187337, -94551787, -46275604, 80625969, -3138208, 36216632, -36709118, -94308688, 89612625, 33232910, -50655119, -88581596, 6632945, -7756650, 3862918, -36958770, 37395906, 77857743, -98487290, -57288380, 8501700, -20985517, -81807199, -92893000, -93497797, -97069226, 57005789, 2170262, 43201469, 86029464, -31433586, -88056723, 37707935, 67524737, 59488848, -91163842, -3720614, -65456290, -36585470, -21076437, 34738870, 60519591, -64765330, 27331762, 12175322, -938945, 39064185, 59591954, 56608510, -81986140, 68628619, -47923441, 88910445, 51482934, -46714526, 3043756, 10216867, 13453156, -49186543, -89541368, -71406478, 80369922, 68829761, 51772309, 57228911, 98964474, 3405095, 63086382, -50390086, 61149122, -55809675, -7516561, 46632368, 43864146, 70678597, 2115815, -30446536, 88628862, 24716414, -33775057, -9194700, 1771717, 87603233, -83560882, 88754989, -98879175, -36680288, 46776998, -76524451, -35970515, -21802643, -81480711, 99604795, -68905432, 16436629, -41240201, -26755192, 82914954, -23956060, -12253327, 23298350, 5351077, -33539442, 88010727, -84414525, -3591127, 71311139, 44450977, 68362697, -53140499, -34782231, 39096996, -64459964, 95355394, -37556727, 41811509, -55960380, 25554595, 46082038, -66245534, -13371241, 83413794, -79649457, -28868367, 2680477, 46882173, 20399386, -86672885, -6897741, 35978557, -64874210, -20129885, 62478468, 73271441, -98119593, -79027260, -11426453, 2768512, 21280857, 20371643, 72058399, 48560257, 75183146, -39211297, 53675723, 40237909, 942835, -23181575, -52362096, 79080371, 99973909, 81446513, -34201075, -14760366, 92931980, -65825579, -6579465, -69723146, -50804082, 23509553, 84422188, 48279359, -29391648, 53567302, 48091951, -61160390, -36792134, -79516763, -16407776, -51867243, 79610031, 18988177, 43533037, -5856895, -2601257, -94175290, -93471034, 58168084, -35695885, -33627069, 78814550, 56560861, -52448718, 42658635, 13647197}, 12203805);
        System.out.println("Answer: [[-81579177, 942835, 5521265, 87318882], [-75394758, 2680477, 36851587, 48066499], [-69799951, 5490561, 23298350, 53214845], [-58568296, 13686515, 21406275, 35679311], [-54342933, -52712896, 20295160, 98964474], [-52362096, -3720614, 22204477, 46082038], [-34052060, -28868367, 3862918, 71261314], [-20129885, -12497929, 2172984, 42658635]]");
        System.out.print("Output: [");
        for (int[] a: ans) {
            System.out.print(Arrays.toString(a));
            System.out.print(", ");
        }
        System.out.println(']');
    }
}

```