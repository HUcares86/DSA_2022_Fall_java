//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import edu.princeton.cs.algs4.MinPQ;
class CovidSimulation {
    public int N;
    private static class Events implements Comparable<Events>{  // Event必須實作Comparable，class裏面要有compareTo
        public int date, attack_city, Traveler_num, from_City, to_City, tra_recover_date;
        public Events(int attack_city,  int date, int Traveler_num, int from_City, int to_City, int tra_recover_date){
            this.attack_city = attack_city;
            this.date = date;
            this.Traveler_num = Traveler_num;
            this.from_City = from_City;
            this.to_City = to_City;
            this.tra_recover_date =tra_recover_date;
        }
        public int compareTo(Events that){
            if (this.date < that.date) return -1;
            if (this.date > that.date) return +1;
            //同日期則病毒先入侵，旅人在到達
            if (this.attack_city >= 0 && that.attack_city < 0) return -1;
            if (this.attack_city < 0 && that.attack_city >=0) return +1;
            return 0;
        }
    }
    public City[] cities;  // 存每個City的資訊
    public static class City{
        public int citizen_num, recover_date,  must_recover;
        public City(int citizen_num, int recover_date, int  must_recover){
            this.citizen_num = citizen_num;
            this.recover_date = recover_date;  //此天是康復日
            this. must_recover =  must_recover;  //最晚在此天必會康復
        }
    }
    private final MinPQ<Events> pq = new MinPQ<Events>();

    public CovidSimulation(int[] Num_Of_Citizen) {
        N = Num_Of_Citizen.length;
        // new一個array
        cities = new City[N];
        for (int i = 0; i < N; i++) cities[i] = new City(Num_Of_Citizen[i], -1, -1);
    }

    //將 virusAttackPlan 和 TravelPlan 做成 event 剩下的參數用 0 1 -1 來表明現在士紳麽 event
    public void virusAttackPlan(int city, int date) {  // 病毒攻擊事件
        pq.insert(new Events(city, date, -1, -1,-1, -1));
    }
    public void TravelPlan(int NumberOfTraveller, int FromCity, int ToCity, int DateOfDeparture, int DateOfArrival) {  //旅人事件
        pq.insert(new Events(-DateOfArrival, DateOfDeparture, NumberOfTraveller, FromCity, ToCity, -1));
    }  // Event(用+-來判斷旅人出發還是抵達, date =出發日期, 旅行人數, 出發城市, 到達城市, 旅人恢復日（-1代表位卻整)
    public int CityWithTheMostPatient(int date) {
        int max_infected_City = -1; //最大感染人數城市
        while (!pq.isEmpty()){  //priority queue 有事件
            Events event = pq.delMin();  // Priority Queue把小的pop出去，也就是日期前面的
            if (event.date <= date) {//下一個事件日期還沒超過input日期，必定發生
                // virus入侵event && 此時城市未感染
                if (event.attack_city >= 0 && cities[event.attack_city].recover_date <= event.date) {
                    // >= 0 代表是病毒攻擊事件
                    // 被攻擊城市未確診
                    // 若已感染，則病毒攻擊無效
                    cities[event.attack_city].recover_date = event.date + 4;  // +4代表生病，例如：1+4=5，第5天康復
                    cities[event.attack_city].must_recover = event.date + 7;  // +７代表最久康復，例如：1+７=8，最多生病到第7天(恢復日那天能確診，則開始一個新的輪迴
                }
                if (event.attack_city < -1) {
                    // < -1 代表是旅人出發事件
                    // event.attack_city = -DateOfArrival --->>> 必<-1
                    cities[event.from_City].citizen_num -= event.Traveler_num;
                    // 因出發event和抵達event必不同日，故新增一個抵達event
                    pq.insert(new Events(-1, -event.attack_city, event.Traveler_num, event.from_City, event.to_City, cities[event.from_City].recover_date));
                }
                // 旅人抵達event
                if (event.attack_city == -1) {  // 在出發事件中，已將抵達事件之event.targetCity設為-1
                    cities[event.to_City].citizen_num += event.Traveler_num;
                    // 若旅人旅行到目的城鎮還沒恢復
                    if (event.date < event.tra_recover_date){
                        if (event.date < cities[event.to_City].recover_date) { //旅人抵達日<=城市康復日,城市目前是確診狀態，則恢復日延後
                            // 首先比較旅人康復跟城市康復，只要有人確診就不算康復，所以要拿比較大的
                            // 再來要比較“康復日“與“必須康復日“，“康復日“最大不能超過“必須康復日”，所以要選小
                            cities[event.to_City].recover_date = Math.min(cities[event.to_City].must_recover, Math.max(cities[event.to_City].recover_date, event.tra_recover_date));
                        } else {  // 若旅人到達日期 > 城市恢復日，則恢復日重置
                            cities[event.to_City].recover_date = event.date + 4;
                            cities[event.to_City].must_recover = event.date + 7;
                        }
                    }
                }
            }
            else {
                pq.insert(event);
                int max_infected1 = 0;   //最大感染人數

                for (int i = 0; i < N; i++) {
                    if (cities[i].recover_date > date && cities[i].citizen_num >= max_infected1) {
                        max_infected1 = cities[i].citizen_num;
                        max_infected_City = i;
                    }
                }
                return max_infected_City;
            }
        }
        // 若結束while迴圈，則跑一次for迴圈找有確診的最大居民城鎮
        int max_infected2 = 0;   //最大感染人數
        for (int i = 0; i < N; i++) {
            if (cities[i].recover_date > date && cities[i].citizen_num >= max_infected2) {
                max_infected2 = cities[i].citizen_num;
                max_infected_City = i;
            }
        }
        return max_infected_City;
    }

    public static void main(String[] args) {
        CovidSimulation sol = new CovidSimulation(new int[]{10, 100, 15, 25, 10, 13});

        sol.virusAttackPlan(0, 1);
        sol.virusAttackPlan(4, 3);
        sol.TravelPlan(3, 0, 3, 3, 4);
        sol.TravelPlan(3, 4, 0, 3, 4);

        System.out.println(sol.CityWithTheMostPatient(2));
        // output = 0

        sol.virusAttackPlan(5, 5);
        sol.TravelPlan(1, 5, 0, 5, 6);

        System.out.println(sol.CityWithTheMostPatient(4));
        // output = 3
        System.out.println(sol.CityWithTheMostPatient(8));
        // output = 5
    }

    //day 1:{10, 100, 15, 25, 10, 13}
    //infectedList:{1, 0, 0, 0, 0, 0}
    //day 2：{10, 100, 15, 25, 10, 13}
    //infectedList:{1, 0, 0, 0, 0, 0}
    //day 3：{7, 100, 15, 25, 7, 13}
    //infectedList:{1, 0, 0, 0, 1, 0}
    //day 4：{10, 100, 15, 28, 7, 13}
    //infectedList:{1, 0, 0, 1, 1, 0}
    //day 5：{10, 100, 15, 28, 7, 12}
    //infectedList:{1, 0, 0, 1, 1, 1}
    //day 6：{11, 100, 15, 28, 7, 12}
    //infectedList:{1, 0, 0, 1, 1, 1}
    //day 7：{11, 100, 15, 28, 7, 12}
    //infectedList:{1, 0, 0, 1, 0, 1}
    //day 8：{11, 100, 15, 28, 7, 12}
    //infectedList:{0, 0, 0, 0, 0, 1}

//    public static void main(String[] args) {
//        CovidSimulation g;
//        JSONParser jsonParser = new JSONParser();
//        try (FileReader reader = new FileReader(args[0])) {
//            JSONArray all = (JSONArray) jsonParser.parse(reader);
//            int waSize = 0;
//            int count = 0;
//            for (Object CaseInList : all) {
//                JSONArray a = (JSONArray) CaseInList;
//                //Board Setup
//                JSONObject argsSetting = (JSONObject) a.get(0);
//                a.remove(0);
//
//                JSONArray argSettingArr = (JSONArray) argsSetting.get("args");
//                int citySetting[] = new int[argSettingArr.size()];
//                for (int i = 0; i < argSettingArr.size(); i++) {
//                    citySetting[i] = (Integer.parseInt(argSettingArr.get(i).toString()));
//                }
//                g = new CovidSimulation(citySetting);
//
//                for (Object o : a) {
//                    JSONObject person = (JSONObject) o;
//                    String func = person.get("func").toString();
//                    JSONArray arg = (JSONArray) person.get("args");
//
//                    switch (func) {
//                        case "virusPlan":
//                            g.virusAttackPlan(Integer.parseInt(arg.get(0).toString()),
//                                    Integer.parseInt(arg.get(1).toString()));
//                            break;
//                        case "TravelPlan":
//                            g.TravelPlan(Integer.parseInt(arg.get(0).toString()), Integer.parseInt(arg.get(1).toString()), Integer.parseInt(arg.get(2).toString()),
//                                    Integer.parseInt(arg.get(3).toString()), Integer.parseInt(arg.get(4).toString()));
//                            break;
//
//                        case "CityMax":
//                            count++;
//                            int ans_sol = g.CityWithTheMostPatient(Integer.parseInt(arg.get(0).toString()));
//                            Long answer = (Long) person.get("answer");
//                            int ans = Integer.parseInt(answer.toString());
//                            if (ans_sol == ans) {
//                                System.out.println(count + ": AC");
//                            } else {
//                                waSize++;
//                                System.out.println(count + ": WA");
//                            }
//                    }
//
//                }
//            }
//            System.out.println("Score: " + (count - waSize) + " / " + count + " ");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }

}
