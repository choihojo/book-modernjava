package ch06;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class VMExample {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Stage: start");
            String a = br.readLine();
            break;
        }

        DataData dataData = null; // Heap에 존재 X (jps, jcmd 명령어로 확인 결과 이 때 클래스 정보가 메서드 영역에조차 없음; 자바는 클래스 로더가 필요할 때 동적 로딩하기 때문)

        /**
         * C:\Program Files\Java\jdk-21\bin>jps
         * 6848 Main
         * 22436 VMExample
         * 5924 Jps
         * 9720 Launcher
         * 9836
         *
         * C:\Program Files\Java\jdk-21\bin>jcmd 22436 VM.class_hierarchy ch06.DataData
         * 22436:
         * Command executed successfully
         *
         * C:\Program Files\Java\jdk-21\bin>jcmd 22436 VM.class_hierarchy ch06.DataData
         * 22436:
         * Command executed successfully
         *
         * C:\Program Files\Java\jdk-21\bin>jcmd 22436 VM.class_hierarchy ch06.DataData
         * 22436:
         * Command executed successfully
         *
         * C:\Program Files\Java\jdk-21\bin>jcmd 22436 VM.class_hierarchy ch06.DataData
         * 22436:
         * java.lang.Object/null
         * |--ch06.DataData/0x0000020c6d48ebf0
         */

        while (true) {
            System.out.println("Stage: init");
            String a = br.readLine();
            break;
        }

        dataData = new DataData(); // Heap에 존재 O, (당연히 메서드 영역에 클래스 정보도 존재 O)

        while (true) {
            System.out.println("Stage: allocate");
            String a = br.readLine();
        }

    }

}
