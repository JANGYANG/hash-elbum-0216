/*
https://programmers.co.kr/learn/courses/30/lessons/42579?language=java 
문제 설명
스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

속한 노래가 많이 재생된 장르를 먼저 수록합니다.
장르 내에서 많이 재생된 노래를 먼저 수록합니다.
장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

제한사항
genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
*/
package hash.elbum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop","test"};
        int[] plays = {500, 600, 150, 800, 2500,30000};

        System.out.println(Arrays.toString(solution(genres, plays)));

        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.solution(genres, plays)));

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		System.out.println("-------Using Lambda Functions--------");
		
		//Using Lambda function to call system.out.println()
		myList.stream().map(s -> s.toUpperCase())
				.forEach(s -> System.out.println(s));

		System.out.println("\n-------Using Method Reference--------");


		 //Using Method reference to call system.out.println()
		myList.stream().map(String::toUpperCase).sorted()
				.forEach(System.out::println);

    }

    public static int[] solution(String[] genres, int[] plays){

        HashMap<String, Integer> genres_plays =  new HashMap<String, Integer>();

        for(int i = 0; i < genres.length; i++){
            genres_plays.put(genres[i], genres_plays.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<Integer> result = new ArrayList<Integer>();
        Iterator it_g = sortByValue(genres_plays).iterator();

        while(it_g.hasNext()){
            HashMap<Integer, Integer> song = new HashMap<Integer, Integer>();

            String key = (String)it_g.next();
            for(int i = 0; i < plays.length; i++){
                if(genres[i].equals(key)){
                    song.put(i, plays[i]);
                }
            }
            Iterator it_p = sortByValue(song).iterator();
            int song_cnt = 0;
            while(it_p.hasNext()){
                if(song_cnt < 2){
                    Integer index = (Integer)it_p.next();
                    result.add(index);
                }else{
                    break;
                }
                song_cnt++;
            }
        }
        int[] answer = new int[result.size()];
        for(Integer index : result){
            answer[result.indexOf(index)] = index;
        }

        return answer;
    }
    public static List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
        Collections.sort(list,new Comparator() {
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });
        // Collections.reverse(list); // 주석시 오름차순
        return list;
    }
}